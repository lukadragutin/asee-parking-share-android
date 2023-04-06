package hr.asee.android.template.data.model.remote.body


import com.google.gson.annotations.SerializedName

data class ApiLoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
