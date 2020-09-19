package id.codepresso.safedepositbox

import android.content.Context
import android.text.TextUtils
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson

/**
 * Crafted by Razib Kani Maulidan on 14/04/20.
 **/

class SafeDepositBox(private val context: Context, private val prefName: String) {

    protected val gson by lazy { Gson() }

    private val sharedPrefs by lazy {
        val masterKeyAlias =
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM)

        EncryptedSharedPreferences.create(
            context, prefName, masterKeyAlias.build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
    }
    private val sharedPrefsEditor = sharedPrefs.edit()

    private val delimiter = "::"

    /**
     * Store Boolean value into SharedPreferences
     * @param key SharedPreferences key
     * @param value Boolean value to be stored
     */
    fun storeBoolean(key: String, value: Boolean) {
        sharedPrefsEditor.putBoolean(key, value).apply()
    }

    /**
     * Store List<Boolean> value into SharedPreferences
     * @param key SharedPreferences key
     * @param values List<Boolean> value to be stored
     */
    fun storeListBoolean(key: String, values: List<Boolean>) {
        val maskedBooleanList = mutableListOf<String>()
        values.forEach { item ->
            if (item) {
                maskedBooleanList.add("true")
            } else {
                maskedBooleanList.add("false")
            }
        }

        storeListString(key, maskedBooleanList)
    }

    /**
     * Store String value into SharedPreferences
     * @param key SharedPreferences key
     * @param value String value to be stored
     */
    fun storeString(key: String, value: String?) {
        sharedPrefsEditor.putString(key, value).apply()
    }

    /**
     * Store Set<String> value into SharedPreferences
     * @param key SharedPreferences key
     * @param values Set<String> value to be stored
     */
    fun storeStringSet(key: String, values: Set<String>) {
        sharedPrefsEditor.putStringSet(key, values).apply()
    }

    /**
     * Store List<String> value into SharedPreferences
     * @param key SharedPreferences key
     * @param values List<String> value to be stored
     */
    fun storeListString(key: String, values: List<String>) {
        val arrayString: Array<String> = values.toTypedArray()
        sharedPrefsEditor.putString(key, TextUtils.join(delimiter, arrayString)).apply()
    }

    /**
     * Store Int value into SharedPreferences
     * @param key SharedPreferences key
     * @param value Int value to be stored
     */
    fun storeInt(key: String, value: Int) {
        sharedPrefsEditor.putInt(key, value).apply()
    }

    /**
     * Store List<Int> value into SharedPreferences
     * @param key SharedPreferences key
     * @param values List<Int> value to be stored
     */
    fun storeListInt(key: String, values: List<Int>) {
        val arrayInt: Array<Int> = values.toTypedArray()
        sharedPrefsEditor.putString(key, TextUtils.join(delimiter, arrayInt)).apply()
    }

    /**
     * Store Long value into SharedPreferences
     * @param key SharedPreferences key
     * @param value Long value to be stored
     */
    fun storeLong(key: String, value: Long) {
        sharedPrefsEditor.putLong(key, value).apply()
    }

    /**
     * Store List<Long> value into SharedPreferences
     * @param key SharedPreferences key
     * @param values List<Long> value to be stored
     */
    fun storeListLong(key: String, values: List<Long>) {
        val arrayLong: Array<Long> = values.toTypedArray()
        sharedPrefsEditor.putString(key, TextUtils.join(delimiter, arrayLong)).apply()
    }

    /**
     * Store Float value into SharedPreferences
     * @param key SharedPreferences key
     * @param value Float value to be stored
     */
    fun storeFloat(key: String, value: Float) {
        sharedPrefsEditor.putFloat(key, value).apply()
    }

    /**
     * Store List<Float> value into SharedPreferences
     * @param key SharedPreferences key
     * @param values List<Float> value to be stored
     */
    fun storeListFloat(key: String, values: List<Float>) {
        val arrayFloat: Array<Float> = values.toTypedArray()
        sharedPrefsEditor.putString(key, TextUtils.join(delimiter, arrayFloat)).apply()
    }

    /**
     * Store Double value into SharedPreferences
     * @param key SharedPreferences key
     * @param value Double value to be stored
     */
    fun storeDouble(key: String, value: Double) {
        sharedPrefsEditor.putString(key, value.toString()).apply()
    }

    /**
     * Store List<Double> value into SharedPreferences
     * @param key SharedPreferences key
     * @param values List<Double> value to be stored
     */
    fun storeListDouble(key: String, values: List<Double>) {
        val arrayDouble: Array<Double> = values.toTypedArray()
        sharedPrefsEditor.putString(key, TextUtils.join(delimiter, arrayDouble)).apply()
    }

    /**
     * Store Any value into SharedPreferences
     * @param key SharedPreferences key
     * @param value Any value to be stored
     */
    fun storeObject(key: String, value: Any) {
        sharedPrefsEditor.putString(key, gson.toJson(value)).apply()
    }

    /**
     * Store List<Any> value into SharedPreferences
     * @param key SharedPreferences key
     * @param values List<Any> value to be stored
     */
    fun storeListObject(key: String, values: List<Any>) {
        val objectStrings = mutableListOf<String>()
        values.forEach { obj ->
            objectStrings.add(gson.toJson(obj))
        }
        storeListString(key, objectStrings)
    }

    /**
     * Get Boolean value from SharedPreferences at 'key'. If key not found, return 'defaultValue'
     * @param key SharedPreferences key
     * @param defaultValue boolean value returned if key was not found
     * @return Boolean value at 'key' or 'defaultValue' if key not found
     */
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPrefs.getBoolean(key, defaultValue)
    }

    /**
     * Get List<Boolean> value from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return List<Boolean> value at 'key' or Empty List if key not found
     */
    fun getListBoolean(key: String): List<Boolean> {
        val maskedBooleanList = getListString(key)
        return mutableListOf<Boolean>().apply {
            maskedBooleanList.forEach { item ->
                if (item == "true") {
                    this.add(true)
                } else {
                    this.add(false)
                }
            }
        }
    }

    /**
     * Get String value from SharedPreferences at 'key'. If key not found, return 'defaultValue'
     * @param key SharedPreferences key
     * @param defaultValue boolean value returned if key was not found
     * @return String value at 'key' or 'defaultValue' if key not found
     */
    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPrefs.getString(key, defaultValue)
    }

    /**
     * Get Set<String> value from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return Set<String> value at 'key' or Null Set if key not found
     */
    fun getStringSet(key: String, defaultValues: Set<String> = setOf()): Set<String>? {
        return sharedPrefs.getStringSet(key, defaultValues)
    }

    /**
     * Get List<String> value from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return List<String> value at 'key' or Empty List if key not found
     */
    fun getListString(key: String): List<String> {
        return TextUtils.split(sharedPrefs.getString(key, ""), delimiter).toList()
    }

    /**
     * Get Int value from SharedPreferences at 'key'. If key not found, return 'defaultValue'
     * @param key SharedPreferences key
     * @param defaultValue boolean value returned if key was not found
     * @return Int value at 'key' or 'defaultValue' if key not found
     */
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPrefs.getInt(key, defaultValue)
    }

    /**
     * Get List<Int> value from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return List<Int> value at 'key' or Empty List if key not found
     */
    fun getListInt(key: String): List<Int> {
        val maskedIntList = TextUtils.split(sharedPrefs.getString(key, ""), delimiter).toList()
        return mutableListOf<Int>().apply {
            maskedIntList.forEach { item -> this.add(item.toInt()) }
        }
    }

    /**
     * Get Long value from SharedPreferences at 'key'. If key not found, return 'defaultValue'
     * @param key SharedPreferences key
     * @param defaultValue boolean value returned if key was not found
     * @return Long value at 'key' or 'defaultValue' if key not found
     */
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return sharedPrefs.getLong(key, defaultValue)
    }

    /**
     * Get List<Long> value from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return List<Long> value at 'key' or Empty List if key not found
     */
    fun getListLong(key: String): List<Long> {
        val maskedLongList = TextUtils.split(sharedPrefs.getString(key, ""), delimiter).toList()
        return mutableListOf<Long>().apply {
            maskedLongList.forEach { item -> this.add(item.toLong()) }
        }
    }

    /**
     * Get Float value from SharedPreferences at 'key'. If key not found, return 'defaultValue'
     * @param key SharedPreferences key
     * @param defaultValue boolean value returned if key was not found
     * @return Float value at 'key' or 'defaultValue' if key not found
     */
    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return sharedPrefs.getFloat(key, defaultValue)
    }

    /**
     * Get List<Float> value from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return List<Float> value at 'key' or Empty List if key not found
     */
    fun getListFloat(key: String): List<Float> {
        val maskedFloatList = TextUtils.split(sharedPrefs.getString(key, ""), delimiter).toList()
        return mutableListOf<Float>().apply {
            maskedFloatList.forEach { item -> this.add(item.toFloat()) }
        }
    }

    /**
     * Get Double value from SharedPreferences at 'key'. If key not found, return 'defaultValue'
     * @param key SharedPreferences key
     * @param defaultValue boolean value returned if key was not found
     * @return Double value at 'key' or 'defaultValue' if key not found
     */
    fun getDouble(key: String, defaultValue: Double = 0.0): Double {
        val maskedDouble =
            sharedPrefs.getString(key, defaultValue.toString()) ?: defaultValue.toString()
        return maskedDouble.toDouble()
    }

    /**
     * Get List<Double> value from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return List<Double> value at 'key' or Empty List if key not found
     */
    fun getListDouble(key: String): List<Double> {
        val maskedDoubleList = TextUtils.split(sharedPrefs.getString(key, ""), delimiter).toList()
        return mutableListOf<Double>().apply {
            maskedDoubleList.forEach { item -> this.add(item.toDouble()) }
        }
    }

    /**
     * Get T value from SharedPreferences at 'key'.
     * @param key SharedPreferences key
     * @return T value at 'key' or NPE if value not found
     */
    inline fun <reified T : Any> getObject(key: String): T {
        val objectString = getString(key)
        return `access$gson`.fromJson(objectString, T::class.java) ?: throw NullPointerException()
    }

    /**
     * Get List<T> value from SharedPreferences at 'key'.
     * @param key SharedPreferences key
     * @return List<T> value at 'key' or NPE if value not found
     */
    inline fun <reified T : Any> getListObject(key: String): List<T> {
        val objectStrings = getListString(key)
        val objects = mutableListOf<T>()

        objectStrings.forEach { objectString ->
            val value = `access$gson`.fromJson(objectString, T::class.java) ?: throw NullPointerException()
            objects.add(value)
        }

        return objects
    }

    /**
     * Remove value from SharedPreferences at 'key'
     * @param key SharedPreferences key
     */
    fun remove(key: String) {
        sharedPrefsEditor.remove(key).apply()
    }

    /**
     * Remove all value from SharedPreferences
     */
    fun clear() {
        sharedPrefsEditor.clear().apply()
    }

    @PublishedApi
    internal val `access$gson`: Gson
        get() = gson
}