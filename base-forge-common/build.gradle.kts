plugins {
    // Protobuf 支持
    alias(libs.plugins.protobuf)
}
dependencies {
    val libs = rootProject.libs
    api(libs.springBootStarter.baseforge.i18n)
    api(libs.springBootStarter.baseforge.core)
    api(libs.springBootStarter.baseforge.util)
    api(libs.springBootStarter.baseforge.json)
    api(libs.springBootStarter.web)
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor
    api (libs.springBoot.configuration.processor)
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
    api (libs.springBootStarter.validation)
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    api (libs.commons.lang3)
    api (libs.springBootStarter.aop)
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-freemarker
    implementation (libs.springBootStarter.freemarker)
    // https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-generator
    implementation (libs.mybatis.plus.generator)
    // Springdoc OpenApi
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    implementation(libs.mybatis.plus.spring.boot3.starter)
    // SpringBootStarter Data JPA
    implementation(libs.springBootStarter.data.jpa)
    compileOnly(libs.bundles.grpcBase)
}
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${libs.versions.protoc.version.get()}"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
            }
            it.group = "grpc"
            it.description = "从 protobuf 文件生成 Java 和 gRPC 代码"
        }
    }
}

sourceSets {
    main {
        java {
            srcDirs("build/generated/source/proto/main/grpc", "build/generated/source/proto/main/java")
        }
    }
}