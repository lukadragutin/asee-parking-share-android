package hr.asee.android.template.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class ApiUserCompact(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("role") val role: String
)