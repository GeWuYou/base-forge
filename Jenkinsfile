pipeline {
    agent any
    environment {
        IMAGE_REGISTRY = "${env.IMAGE_REGISTRY ?: 'localhost:5000'}"
    }
    stages {
        // 克隆代码
        stage('Clone Repository') {
            steps {
                git credentialsId: 'github', url: 'https://github.com/GeWuYou/base-forge.git'
            }
        }
        // 检测变更的服务
        stage('Detect Changed Services') {
            steps {
                script {
                    def changedFiles = sh(script: 'git diff --name-only HEAD~1 HEAD', returnStdout: true).trim().split("\n")
                    def changedServices = []

                    changedFiles.each { file ->
                        if (file.startsWith("base-forge-config/")) {
                            changedServices << "base-forge-config"
                        }
                        if (file.startsWith("base-forge-discovery/")) {
                            changedServices << "base-forge-discovery"
                        }
                    }

                    changedServices = changedServices.unique()
                    env.CHANGED_SERVICES = changedServices.join(" ")
                    echo "检测到变更的服务: ${env.CHANGED_SERVICES}"
                }
            }
        }
        // 构建镜像
        stage('Build with Jib') {
            steps {
                script {
                    def services = env.CHANGED_SERVICES.tokenize(" ")
                    if (services) {
                        services.each { service ->
                            echo "正在构建服务: ${service}"
                            sh "cd ${service} && ./gradlew jib"
                        }
                    } else {
                        echo "没有需要构建的服务，跳过 Jib 构建。"
                    }
                }
            }
        }
        // 部署服务
        stage('Deploy Services') {
            steps {
                script {
                    def services = env.CHANGED_SERVICES.tokenize(" ")
                    if (services) {
                        services.each { service ->
                            echo "正在增量部署服务: ${service}"
                            sh "docker-compose up -d --no-deps --build ${service}"
                        }
                    } else {
                        echo "没有需要部署的服务，跳过部署。"
                    }
                }
            }
        }
    }
}
