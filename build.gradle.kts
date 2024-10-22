plugins {
	kotlin("jvm") version "2.0.20"
}

group = "com.diploma"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(kotlin("test"))

	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}
