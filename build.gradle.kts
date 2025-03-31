plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.protobuf") version "0.9.4"
}

group = "com.matin.happychat"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:4.28.2"
	}
	plugins {
		create("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.57.2"
		}
		create("grpckt") {
			artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.0:jdk8@jar"
		}
	}
	generateProtoTasks {
		all().configureEach {
			plugins {
				create("grpc")
				create("grpckt")
			}
		}
	}
}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")

		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

		implementation("net.devh:grpc-server-spring-boot-starter:2.15.0.RELEASE")

		implementation("io.grpc:grpc-kotlin-stub:1.4.0")
		implementation("com.google.protobuf:protobuf-kotlin:4.28.2")

		implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

		implementation("org.postgresql:r2dbc-postgresql:1.0.4.RELEASE")

		implementation("org.springframework.boot:spring-boot-starter-webflux")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

		// Test
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
