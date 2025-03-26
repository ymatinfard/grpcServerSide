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
		artifact = "com.google.protobuf:protoc:4.28.2" // Match with protobuf-kotlin version
	}
	plugins {
		create("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.57.2"
		}
		create("grpckt") { // Plugin for grpc-kotlin
			artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.0:jdk8@jar" // Ensure it resolves correctly
		}
	}
	generateProtoTasks {
		all().configureEach {
			plugins {
				create("grpc")  // Use grpc plugin
				create("grpckt") // Use grpc-kotlin plugin
			}
			builtins {
				create("kotlin") // Generate Kotlin code
			}
		}
	}
}

	dependencies {
		// Core Spring Boot (already included by grpc-server-spring-boot-starter)
		implementation("org.springframework.boot:spring-boot-starter")

		// Kotlin essentials
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

		// gRPC Core (use versions managed by grpc-server-spring-boot-starter)
		implementation("io.grpc:grpc-kotlin-stub") // Version managed by starter
		implementation("io.grpc:grpc-netty-shaded") // Version managed by starter

		// Protobuf
//		implementation("com.google.protobuf:protobuf-kotlin") // Version managed by Spring Boot

		// Spring Boot gRPC (pick ONE of these)
		implementation("net.devh:grpc-server-spring-boot-starter:2.15.0.RELEASE") // Recommended
		// implementation("io.github.lognet:grpc-spring-boot-starter:4.7.0") // Remove - conflicting

		implementation("io.grpc:grpc-kotlin-stub:1.4.0")
		implementation("com.google.protobuf:protobuf-kotlin:4.28.2")

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
