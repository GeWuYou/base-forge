dependencies {
    api("com.gewuyou:base-forge-i18n-spring-boot-starter")
    api(libs.base.forge.i18n.starter)
    api("com.gewuyou:base-forge-core-spring-boot-starter")
    api(libs.base.forge.core.starter)
    api("com.gewuyou:base-forge-util-spring-boot-starter")
    api(libs.base.forge.util.starter)
    api("com.gewuyou:base-forge-json-spring-boot-starter")
    api(libs.base.forge.json.starter)
    api("org.springframework.boot:spring-boot-starter-web")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor
    api ("org.springframework.boot:spring-boot-configuration-processor")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
    api ("org.springframework.boot:spring-boot-starter-validation")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    api (libs.commons.lang3)
    api ("org.springframework.boot:spring-boot-starter-aop")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-freemarker
    implementation ("org.springframework.boot:spring-boot-starter-freemarker")
    // https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-generator
    implementation (libs.mybatis.plus.generator)
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