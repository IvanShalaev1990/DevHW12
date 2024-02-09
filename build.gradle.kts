plugins {
    id("java")
}

group = "org.spacetravel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
java{
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.8.0")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    compileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
//    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
//    testImplementation("org.projectlombok:lombok:1.18.30")
    implementation("org.postgresql:postgresql:42.7.1")
    implementation("org.hibernate.orm:hibernate-core:6.3.1.Final")
    implementation("org.flywaydb:flyway-core:8.5.13")
    testImplementation("org.testcontainers:postgresql:1.19.3")
}

tasks.test {
    useJUnitPlatform()
}