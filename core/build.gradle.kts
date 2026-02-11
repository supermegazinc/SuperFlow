import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.android.library)
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
}
java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)

	testImplementation(libs.junit)

	testImplementation(kotlin("test"))
	implementation(kotlin("reflect"))
}