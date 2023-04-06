package hr.asee.android.template.data.local.storage

interface ApplicationStorage {

    suspend fun containsKey(key: String): Boolean

    suspend fun saveString(key: String, value: String)

    suspend fun getString(key: String, default: String = ""): String

    suspend fun deleteValue(key: String)
}
