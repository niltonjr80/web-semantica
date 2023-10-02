plugins {
    id("application")
    id("java")
    kotlin("jvm") version "1.9.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.apache.jena:jena-core:4.9.0")
    implementation("org.apache.jena:apache-jena-libs:4.9.0")
    implementation("org.apache.jena:jena-rdfpatch:4.9.0")
    implementation("org.apache.jena:jena-tdb:4.9.0")
    implementation("ch.qos.logback:logback-classic:1.2.6")

    testImplementation("junit:junit:4.13.1")
}

application {
    mainClass.set("br.com.niltonjr80.websemantica.SparqlQueryEngine")
}

tasks.register<JavaExec>("runSparqlQueryEngine") {
    main = "br.com.niltonjr80.websemantica.SparqlQueryEngine"
    classpath.from(sourceSets.main.runtimeClasspath)

    args = listOf(
        "/workspaces/web-semantica/rdf-simpsons/src/main/resources/input/exercise_1.rq",
        "/workspaces/web-semantica/rdf-simpsons/src/main/resources/input/simpsons.ttl"
    )
}

