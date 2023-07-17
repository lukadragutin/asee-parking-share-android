package hr.asee.android.template.data.model.remote.body

import com.google.gson.annotations.SerializedName
import java.util.Calendar

data class ApiRegisterRequest (
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("firstName") val firstName : String,
    @SerializedName("lastName") val lastName : String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("role") val role: String,
    @SerializedName("activated") val activated: Boolean,
    @SerializedName("langKey") val langKey: String,
    @SerializedName("createdBy") val createdBy: String,
    @SerializedName("createdDate") val createdDate: String,
    @SerializedName("lastModifiedBy") val lastModifiedBy: String,
    @SerializedName("lastModifiedDate") val lastModifiedDate: String,
    @SerializedName("authorities") val authorities: Array<String>
)
