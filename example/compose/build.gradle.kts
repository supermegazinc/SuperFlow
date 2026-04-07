import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.supermegazinc.flow.example.compose"
	compileSdk {
		version = release(36)
	}

	defaultConfig {
		minSdk = 21

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
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
	buildFeatures {
		compose = true
	}
}

dependencies {
	implementation(project(":core"))
	implementation(libs.androidx.core.ktx)

	testImplementation(libs.junit)
	testImplementation(kotlin("test"))

	val composeBom = platform("androidx.compose:compose-bom:2026.03.00")
	implementation(composeBom)
	androidTestImplementation(composeBom)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.lifecycle.viewmodel.compose)
	implementation(libs.androidx.ui.tooling.preview)
	debugImplementation(libs.androidx.ui.tooling)
}