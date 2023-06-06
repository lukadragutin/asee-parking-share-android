package hr.asee.android.template.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class ApiSeeking(
    @SerializedName("id") val id: Int,
    @SerializedName("dateStart") val dateStart: String,
    @SerializedName("dateEnd") val dateEnd: String,
    @SerializedName("seeker") val seeker: ApiUserCompact
)