# 🚀 SuperState

**SuperState** is a lightweight, powerful extension library for Kotlin Coroutines `Flow` and `StateFlow`. It simplifies state management in Android by providing seamless **parameter binding**, property-level observation, and bidirectional state synchronization.

Stop fighting with complex state copies. Start using **SuperState**.

---

## ✨ Features

- **⚡ SuperMutableState**: An enhanced wrapper around `MutableStateFlow` for easier manipulation.
- **🔗 Property Binding**: Extract specific properties from a complex State object into individual reactive streams.
- **🔄 Bidirectional Sync**: Use `bind()` to create a two-way bridge between a property and the parent state.
- **🛠 Utility Extensions**: Convert existing `MutableStateFlow` to `SuperMutableState` instantly with `.asSuperMutableState()`.
- **🎯 Targeted Updates**: Update nested properties without manual `copy()` boilerplate using `bindSetter`.

---

## 🚀 Quick Start

### 1. Define your State

```kotlin
data class ExampleForm(
    val input1: String = "",
    val input2: String = "",
    val input3: String = ""
)
```

### 2. Initialize SuperState

You can create it from scratch or wrap an existing Flow:

```kotlin
val state = SuperMutableState(ExampleForm())

// Or from an existing Flow
val stateFromFlow = MutableStateFlow(ExampleForm()).asSuperMutableState()
```

### 3. Smart Binding (The Power of SuperState)

SuperState allows you to "extract" a property to observe it or modify it independently, while keeping the main state in sync.

### Bidirectional Binding (`bind`)

The `bind` method creates a sub-state that reflects the property value and updates the parent when changed.

```kotlin
val input1Bind = state.bind(
    prop = ExampleForm::input1,
    initialValue = "",
    coroutineScope = viewModelScope // or custom scope
)
// Setting a value on the binding...
input1Bind.set("Hello World")

// ...automatically updates the parent state!
println(state.value.value) // Output: ExampleForm(input1="Hello World", ...)
```

### Specialized Binding

If you only need one-way communication:

- **`bindGetter`**: Observe a specific property of the state.
- **`bindSetter`**: Create a handle to update a specific property without needing a scope.

---

## 🤝 Contributing

Contributions are welcome! If you find a bug or have a feature request, please open an issue or submit a pull request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

---

**Developed with ❤️ by Supermegazinc**