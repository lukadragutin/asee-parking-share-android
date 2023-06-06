package hr.asee.android.template.data.model.remote.response.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldError (
    @SerialName("objectName") val objectName: String?,
    @SerialName("field") val field: String?,
    @SerialName("message") val message: String?
)