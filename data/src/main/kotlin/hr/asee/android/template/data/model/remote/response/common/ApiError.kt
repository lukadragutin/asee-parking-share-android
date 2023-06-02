package hr.asee.android.template.data.model.remote.response.common

import hr.asee.android.template.data.model.common.SerializableDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    @SerialName("error") val error: String,
    @SerialName("type") val type: String,
    @SerialName("title") val title: String,
    @SerialName("status") val status: Int,
    @SerialName("path") val path: String,
    @SerialName("message") val message: String,
    @SerialName("fieldErrors") val fieldErrors: Array<FieldError>?
) : SerializableDataModel
