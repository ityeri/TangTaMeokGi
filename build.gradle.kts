plugins {
    kotlin("jvm") version "2.1.20-Beta1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "com.github.ityeri"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }

    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }

    // CommandFramework
    maven ("https://repo.aikar.co/content/groups/aikar/")
    maven ("https://hub.spigotmc.org/nexus/content/groups/public/")
}

dependencies {
    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    // 리플렉션
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.20-Beta1")

    // 패이퍼 / 스피갓 / 코틀린
    compileOnly("io.papermc.paper:paper-api:1.21.7-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.spigotmc:spigot-api:1.15.2-R0.1-SNAPSHOT")

    // CommandFramework
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")

}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.21.7")
    }
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.shadowJar {
    relocate("co.aikar.commands", "com.github.ityeri.tangTaMeokGi")
    relocate("co.aikar.locales", "com.github.ityeri.tangTaMeokGi")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}


tasks.jar {
    manifest {
        attributes["Implementation-Title"] = "탕타묵기"
        attributes["Implementation-Version"] = version
    }
    from(configurations.compileClasspath.get().filter { it.name.endsWith("kotlin-stdlib.jar") }.map { if (it.isDirectory) it else zipTree(it) })

    // To avoid the duplicate handling strategy error
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // To add all the dependencies
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

}