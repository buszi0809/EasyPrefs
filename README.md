# EasyPrefs
Adapter library for SharedPreferences which reduces boilerplate needed to store simple data, but open enough to not interfere with your own logic.

## Quick preview

Lets see the example usage of SharedPreferences:

```Kotlin
class MyPreferences(private val preferences: SharedPreferences) {
   
   var myString: String
        get() = preferences.getString(MY_KEY, null) ?: MY_DEFAULT_VALUE
        set(value) {
            prefs.edit(commit = true) { 
                putString(MY_KEY, value)
            }
        }
    
    companion object {
        const val MY_KEY = "my_key"
        const val MY_DEFAULT_VALUE = "default"
    }
}
```
We can see a lot of boilerplate code, also if you would want to add another property you need to create another field with getter and setter, add key and default value to companion object. And here **EasyPrefs** comes to the rescue! What if we would be able to declare your SharedPreferences property like this:

```Kotlin
class MyPreferences(private val preferences: SharedPreferences) : EasyPrefs(preferences) {
    
    var myString by string(commit = true, default = MY_DEFAULT_VALUE)
    
    companion object {
        const val MY_DEFAULT_VALUE = "default"
    }
}
```
We got rid of repetitive boilerplate code, brilliant! But there is more. **EasyPrefs** makes it easy to observe value changes with use of `Kotlin Flow`, just implement `EasyPrefsFlow` and use flow delegates:

```Kotlin
class MyPreferences(private val preferences: SharedPreferences) : EasyPrefsFlow(preferences) {
    
    var myString by string(commit = true, default = MY_DEFAULT_VALUE)
    // key is the property you want to observe
    val myStringFlow by stringFlow(key = ::myString, default = MY_DEFAULT_VALUE)
    ...
}
```
**EasyPrefs** also makes it easy to use custom property types that are not supported by SharedPreferences. Just create implementation of `TypeAdapter` that will convert your objects into nullable string values and then recreate them when needed. It also supports Flow delegates!

```Kotlin
class ByteArrayAdapter : TypeAdapter<ByteArray?> {
    override fun toString(value: ByteArray?): String? =
        value?.let { Base64.encodeToString(it, Base64.NO_WRAP) }

    override fun fromString(value: String?): ByteArray? =
        value?.let { Base64.decode(it, Base64.NO_WRAP) }
}
```
```Kotlin
class MyPreferences(
    private val preferences: SharedPreferences,
    byteArrayAdapter: TypeAdapter<ByteArray?>,
) : EasyPrefsFlow(preferences) {
    var someData: ByteArray? by custom(commit = true, adapter = byteArrayAdapter)
    val someDataFlow: Flow<ByteArray?> by customFlow(key = ::someData, adapter = byteArrayAdapter)
}
```

## Keys
#### But now the great question arises. What about the keys to actual SharedPreferences implementation? It couldn't just disappear! 🤔

The keys are generated with use of Kotlin Reflect. This is the formula under the hood:

```Kotlin
fun getKeyFor(thisRef: EasyPrefs, property: KProperty<*>): String =
    "${thisRef::class.qualifiedName}_${property.name}"
```

So the library just generates the key for example like this: `"com.example.app.data.prefs.Preferences_myString"`. This way we don't need to create them ourselves, and we don't need to make sure that every key is unique manually! If you want to obtain this key for your property you can do so with use of `getKeyFor(KProperty<*>)` function in `EasyPrefs` abstraction. The only downside of this solution is that to create a Flow for property, the Flow delegate needs to be used within the same class.

## Setup

The library is avaliable on Maven repository, just add `mavenCentral()` to your project and this dependency:
```
implementation "io.github.buszi0809:easyprefs:1.1.0"
```
Then extend `EasyPrefs` or `EasyPrefsFlow` in object that handles your SharedPreferences and you're easy to go. 😄

## New

Support for RxJava3! Extend `EasyPrefsRx` and use Observable delegates:

```Kotlin
class MyPreferences(private val preferences: SharedPreferences) : EasyPrefsRx(preferences) {
    
    var myString by string(commit = true, default = MY_DEFAULT_VALUE)
    
    val myStringObservable by stringObservable(ke= = ::myString, default = My_DEFAULT_VALUE)
    
    companion object {
        const val MY_DEFAULT_VALUE = "default"
    }
}
```

## Feedback

The library is created and maintained by a single unit, a passionate Android Developer that had seen the field for improvements within SharedPreferences component. If you have any ideas or feedback - **please feel free to use *Discussions* tab of this Repo!**
