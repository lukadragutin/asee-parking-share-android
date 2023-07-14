package hr.asee.android.template.data.model.remote.response

import com.google.gson.annotations.SerializedName
import hr.asee.android.template.data.model.common.SerializableDataModel

data class ApiUser(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String,
    @SerializedName("activated") val activated: Boolean,
    @SerializedName("langKey") val langKey: String,
    @SerializedName("createdBy") val createdBy: String,
    @SerializedName("createdDate") val createdDate: String?,
    @SerializedName("lastModifiedBy") val lastModifiedBy: String,
    @SerializedName("lastModifiedDate") val lastModifiedDate: String?,
    @SerializedName("authorities") val authorities: List<String>
): SerializableDataModel