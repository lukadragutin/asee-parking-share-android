package hr.asee.android.template.data.model.remote.response

import com.google.gson.annotations.SerializedName
import hr.asee.android.template.data.model.common.SerializableDataModel

data class ApiAccessToken(
    @SerializedName("id_token") val accessToken: String
) : SerializableDataModel
