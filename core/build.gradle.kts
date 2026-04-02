import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.android.library)
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

	testImplementation(kotlin("test"))
	implementation(kotlin("reflect"))
}