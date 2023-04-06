package hr.asee.android.template.data.model.remote.response.common

import hr.asee.android.template.data.model.common.SerializableDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    @SerialName("error") val error: String
) : SerializableDataModel
