// ✅ 使用 Active Choices Plugin 让 `SERVICES_TO_MANAGE` 变成可多选复选框
properties([
        parameters([
                // 选择构建操作
                choice(name: 'ACTION', choices: ['构建并部署', '管理服务'], description: '选择执行操作', defaultValue: '构建并部署'),

                // 选择要操作的服务（可多选）
                [$class: 'CascadeChoiceParameter',
                 name: 'SERVICES_TO_MANAGE',
                 description: '选择要操作的服务（可多选）',
                 choiceType: 'PT_CHECKBOX',
                 randomName: 'choice-parameter',
                 script: [$class: 'GroovyScript',
                          script: [sandbox: true, script: '''
                    return ["base-forge-config", "base-forge-discovery", "base-forge-gateway", "base-forge-user"]
                ''']
                 ]
                ],

                // ✅ 手动管理服务时的操作类型
                choice(name: 'MANAGE_TYPE', choices: ['启动', '停止', '重新构建', '重新构建并部署'], description: '选择操作类型（仅适用于 管理服务）')
        ])
])

pipeline {
    agent any

    environment {
        PRIVATE_DOCKER_REGISTRY_PASSWORD = credentials('PRIVATE_DOCKER_REGISTRY_PASSWORD') // Jenkins 凭证管理
    }

    stages {
        stage('Init') {
            when {
                expression { params.ACTION == '构建并部署' }
            }
            steps {
                script {
                    def GIT_BRANCH = env.BRANCH_NAME
                    echo "当前分支: ${GIT_BRANCH}"

                    def IMAGE_REGISTRY = "localhost:5000"
                    def DEPLOY_COMMAND = (GIT_BRANCH == "develop") ? "docker-compose -f docker-compose.dev.yml" : "docker-compose -f docker-compose.prod.yml"

                    env.GIT_BRANCH = GIT_BRANCH
                    env.IMAGE_REGISTRY = IMAGE_REGISTRY
                    env.DEPLOY_COMMAND = DEPLOY_COMMAND

                    // 如果 `ACTION` 为空（例如 GitHub 触发），默认设为 `构建并部署`
                    if (!params.ACTION?.trim()) {
                        echo "⚠️ 未检测到 `ACTION` 参数，默认执行 `构建并部署`"
                        env.ACTION = '构建并部署'
                    }
                }
            }
        }

        // **🔹 处理手动管理服务**
        stage('Manage Services') {
            when {
                expression { params.ACTION == '管理服务' }
            }
            steps {
                script {
                    def services = params.SERVICES_TO_MANAGE.split(',').collect { it.trim() }.findAll { it }
                    if (services.isEmpty()) {
                        error "必须选择至少一个服务"
                    }

                    for (service in services) {
                        echo "执行 ${params.MANAGE_TYPE} 操作: ${service}"

                        def runningContainer = sh(script: "docker ps -q -f name=${service}", returnStdout: true).trim()
                        def allContainers = sh(script: "docker ps -aq -f name=${service}", returnStdout: true).trim()

                        if (params.MANAGE_TYPE == '启动') {
                            if (runningContainer) {
                                echo "${service} 已经在运行，跳过启动"
                            } else if (allContainers) {
                                echo "${service} 存在但未运行，使用 docker start 启动"
                                sh "docker start ${service}"
                            } else {
                                echo "${service} 不存在，使用 docker-compose 启动"
                                sh "${env.DEPLOY_COMMAND} up -d ${service}"
                            }
                        }
                        else if (params.MANAGE_TYPE == '停止') {
                            if (!runningContainer) {
                                echo "${service} 已经停止，跳过关闭"
                            } else {
                                echo "停止 ${service}"
                                sh "docker stop ${service}"
                            }
                        }
                        else if (params.MANAGE_TYPE == '重新构建') {
                            echo "重新构建 ${service}"
                            sh "docker stop ${service} || true"
                            sh "docker rm ${service} || true"
                            withEnv(["IMAGE_REGISTRY=${env.IMAGE_REGISTRY}", "PRIVATE_DOCKER_REGISTRY_PASSWORD=${env.PRIVATE_DOCKER_REGISTRY_PASSWORD}"]) {
                                sh "./gradlew :${service}:jib"
                            }
                        }
                        else if (params.MANAGE_TYPE == '重新构建并部署') {
                            echo "尝试增量更新 ${service}"
                            def updateSuccess = sh(script: "${env.DEPLOY_COMMAND} up -d --no-deps ${service} || echo 'FAILED'", returnStdout: true).trim()
                            if (updateSuccess.contains('FAILED')) {
                                echo "增量更新失败，执行完整重启"
                                sh "docker stop ${service} || true"
                                sh "docker rm ${service} || true"
                                sh "docker rmi ${env.IMAGE_REGISTRY}/${service}:latest || true"
                                withEnv(["IMAGE_REGISTRY=${env.IMAGE_REGISTRY}", "PRIVATE_DOCKER_REGISTRY_PASSWORD=${env.PRIVATE_DOCKER_REGISTRY_PASSWORD}"]) {
                                    sh "./gradlew :${service}:jib"
                                }
                                sh "${env.DEPLOY_COMMAND} up -d ${service}"
                            }
                        }
                    }
                }
            }
        }

        // **🔹 仅在 `构建并部署` 时执行完整构建**
        stage('Clone Repository') {
            when {
                expression { params.ACTION == '构建并部署' }
            }
            steps {
                script {
                    sh "git pull origin ${env.GIT_BRANCH}"
                }
            }
        }

        stage('Detect Changed Services') {
            when {
                expression { params.ACTION == '构建并部署' }
            }
            steps {
                script {
                    def changedFiles = sh(script: 'git diff --name-only HEAD~1 HEAD', returnStdout: true).trim().split("\n")
                    echo "变更的文件: ${changedFiles}"

                    def servicesToBuild = []
                    def allServicesList = ["base-forge-config", "base-forge-discovery", "base-forge-gateway", "base-forge-user"]

                    for (service in allServicesList) {
                        if (changedFiles.any { it.startsWith("${service}/") }) {
                            servicesToBuild.add(service)
                        }
                    }

                    if (!servicesToBuild.isEmpty()) {
                        echo "需要构建的服务: ${servicesToBuild.join(', ')}"
                        env.SERVICES_TO_BUILD = servicesToBuild.join(',')
                    } else {
                        echo "没有检测到需要构建的服务，跳过构建"
                        env.SERVICES_TO_BUILD = ''
                    }
                }
            }
        }

        stage('Build with Jib') {
            when {
                expression { params.ACTION == '构建并部署' }
            }
            steps {
                script {
                    def services = env.SERVICES_TO_BUILD ? env.SERVICES_TO_BUILD.split(',') : []
                    if (services.isEmpty()) {
                        echo "没有需要构建的服务，跳过 Jib 构建。"
                        return
                    }
                    for (service in services) {
                        echo "开始构建: ${service}"
                        withEnv(["IMAGE_REGISTRY=${env.IMAGE_REGISTRY}", "PRIVATE_DOCKER_REGISTRY_PASSWORD=${env.PRIVATE_DOCKER_REGISTRY_PASSWORD}"]) {
                            sh "./gradlew :${service}:jib"
                        }
                    }
                }
            }
        }

        stage('Deploy Services') {
            when {
                expression { params.ACTION == '构建并部署' }
            }
            steps {
                script {
                    def services = env.SERVICES_TO_BUILD.split(',')
                    for (service in services) {
                        sh "${env.DEPLOY_COMMAND} up -d --force-recreate ${service}"
                    }
                }
            }
        }
    }
}
