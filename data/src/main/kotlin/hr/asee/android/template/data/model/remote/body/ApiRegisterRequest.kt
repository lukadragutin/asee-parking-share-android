package hr.asee.android.template.data.model.remote.body

import com.google.gson.annotations.SerializedName

data class ApiRegisterRequest (
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirmPassword") val confirmPassword: String
        )
