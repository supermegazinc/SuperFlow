import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.compose.compiler)
	id("maven-publish")
}

android {
	namespace = "com.supermegazinc.flow.core"

	defaultConfig {
		minSdk = 21
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}
	compileSdk = 36
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlin {
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_11)
		}
	}
	publishing {
		singleVariant("release") {
			withSourcesJar()
		}
	}
	buildFeatures {
		compose = true
	}
}

publishing {
	publications {
		register<MavenPublication>("release") {
			afterEvaluate {
				groupId = "com.supermegazinc"
				artifactId = "superflow"
				from(components["release"])
			}
		}
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)

	testImplementation(libs.junit)

	val composeBom = platform("androidx.compose:compose-bom:2026.03.00")
	implementation(composeBom)
	androidTestImplementation(composeBom)
	implementation("androidx.compose.material3:material3")

	testImplementation(kotlin("test"))
	implementation(kotlin("reflect"))
}