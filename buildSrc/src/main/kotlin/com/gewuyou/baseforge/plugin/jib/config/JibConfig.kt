package com.gewuyou.baseforge.plugin.jib.config

import com.gewuyou.baseforge.plugin.jib.entity.JibProject


// 在此配置需要引入的项目
val jibProjects = listOf(
    JibProject(
        projectName = "base-forge-config",
        ports = listOf("8080"),
        environment = mapOf("SPRING_PROFILES_ACTIVE" to "native")
    ),
    JibProject(
        projectName = "base-forge-discovery",
        ports = listOf("8761"),
        environment = mapOf("SPRING_PROFILES_ACTIVE" to "prod"),
        entrypoint = listOf(
            "/bin/sh", "-c",
            "apt-get update && apt-get install -y netcat-openbsd && " +
                    "/entrypoint.sh -d base-forge-config:8888 -c " +
                    "'java -cp \$( cat /app/jib-classpath-file ) \$( cat /app/jib-main-class-file )'"
        )
    ),
    JibProject(
        projectName = "base-forge-gateway",
        ports = listOf("8082"),
        environment = mapOf("SPRING_PROFILES_ACTIVE" to "prod"),
        entrypoint = listOf(
            "/bin/sh", "-c",
            "apt-get update && apt-get install -y netcat-openbsd && " +
                    "/entrypoint.sh -d base-forge-discovery:8761 -c " +
                    "'java -cp \$( cat /app/jib-classpath-file ) \$( cat /app/jib-main-class-file )'"
        )
    )
)