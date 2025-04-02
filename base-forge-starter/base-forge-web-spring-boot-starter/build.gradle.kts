dependencies {
    val libs = rootProject.libs

    // Base Forge Modules (Compile Only)
    compileOnly(project(":base-forge-i18n-spring-boot-starter"))
    compileOnly(project(":base-forge-util-spring-boot-starter"))
    compileOnly(project(":base-forge-request-trace-spring-boot-starter"))
    // Spring Data JPA (Compile Only)
    compileOnly(libs.springBootStarter.data.jpa)

    // SpringDoc OpenAPI
    implementation(libs.springdoc.openapi.starter.webmvc.ui)

}