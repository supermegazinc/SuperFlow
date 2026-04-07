# 🥏 SuperFlow

**SuperFlow** is a lightweight, reactive state management library for Android. It bridges the gap between complex **Kotlin Coroutines Flow** and **Jetpack Compose**, eliminating boilerplate for nested state updates and bidirectional data binding.

Stop fighting with manual `copy()` calls. Start using **SuperFlow**.

---

## ✨ Key Features

*   **⚡ SuperMutableState**: An enhanced wrapper for `MutableStateFlow` that simplifies complex object updates.
*   **🎨 Compose First**: Native support for Jetpack Compose with `collectAsStateWithLifecycle` integrated under the hood.
*   **🔗 Property Binding**: Extract specific properties from a state object into independent, reactive streams.
*   **🔄 Bidirectional Sync**: Create a two-way bridge between UI components and your ViewModel state.
*   **🤝 Delegation Support**: Use the `by` keyword in Compose for clean, readable code.

---

## 📦 Installation

Add the JitPack repository to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Then, add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.supermegazinc:SuperFlow:LATEST_VERSION")
}
```

---

## 🚀 Quick Start

### 1. Define your State
```kotlin
data class RegistrationForm(
    val username: String = "",
    val email: String = "",
    val isAdmin: Boolean = false
)
```

### 2. Initialize in ViewModel
Use `SuperMutableState` to wrap your state. It works just like a Flow but with extra powers.

```kotlin
class AuthViewModel : ViewModel() {
    // Initialize from scratch
    val formState = SuperMutableState(RegistrationForm())

    // Or convert an existing Flow
    // val state = existingFlow.asSuperMutableState()

    // Bind a specific property for 2-way sync
    val usernameBinding = formState.bind(
        prop = RegistrationForm::username,
        initialValue = "",
        coroutineScope = viewModelScope
    )
}
```

---

## 🎨 Jetpack Compose Integration

SuperFlow shines in the UI layer. By calling `.compose()`, your state becomes aware of the Lifecycle and allows for elegant property delegation.



### Using Property Delegation
```kotlin
@Composable
fun RegistrationScreen(vm: AuthViewModel = viewModel()) {
    // Convert to Compose-friendly state
    val stateCompose = vm.usernameBinding.compose()

    // Use 'by' for seamless read/write access
    var username by stateCompose

    Column {
        Text(text = "Current user: $username")
        
        TextField(
            value = username,
            onValueChange = { username = it }, // Automatically updates ViewModel!
            label = { Text("Username") }
        )
    }
}
```

---

## 🛠 Advanced Usage

### Specialized Bindings
If you don't need full bidirectional sync, you can optimize:

*   **`bindGetter`**: Create a read-only stream of a specific nested property.
*   **`bindSetter`**: Create a handle to update a specific property without needing a `CoroutineScope`.

### Under the Hood
The `.compose()` extension uses `collectAsStateWithLifecycle()` and `remember`, ensuring your app stays performant and doesn't leak memory during configuration changes.

---

## 🤝 Contributing

We love contributors! If you want to make SuperFlow even better:

1.  **Fork** the Project.
2.  Create your **Feature Branch** (`git checkout -b feature/AmazingFeature`).
3.  **Commit** your Changes (`git commit -m 'Add some AmazingFeature'`).
4.  **Push** to the Branch (`git push origin feature/AmazingFeature`).
5.  Open a **Pull Request**.

---

## 📄 License

Distributed under the **MIT License**. See `LICENSE` for more information.

Developed with ❤️ by **Supermegazinc**