# SafeDepositBox

This is a Wrapper for SharedPreferences, it simplify preferences implementation for storing value/s.

# Value/s Support

This can store more value/s type such as `String, List<String>, Float, List<Float>, Double, List<Double>, Boolean, List<Boolean>, Object, List<Object>`

# Usage

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
  repositories {
	  ...
	  maven { url 'https://jitpack.io' }
	}
}
```

Add the library to your apps build.gradle:

```gradle
implementation 'com.github.RazibKani:SafeDepositBox:0.2'
```

# Examples

## Store String
```kotlin

val safeDepositBox = SafeDepositBox(context, "SafeDepositBoxSample")

// store string
safeDepositBox.storeString("tatang", "Tatang Sutarna")

// get string without default value, it will return null if no value with key "tatang"
val tatang = safeDepositBox.getString("tatang")

// get string with default value, it will return default value if no value with key "tatang"
val tatang = safeDepositBox.getString("tatang", "Default Tatang")

```

## Store Object
```kotlin

val safeDepositBox = SafeDepositBox(context, "SafeDepositBoxSample")

// create object user
val tatang = User("tatang", "sutarna")

// store object user
safeDepositBox.storeObject("tatang", tatang)

// get object user
val userTatang = safeDepositBox.getObject<User>("tatang")

```
