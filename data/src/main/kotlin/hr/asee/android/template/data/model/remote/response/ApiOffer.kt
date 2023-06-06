package hr.asee.android.template.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class ApiOffer(
    @SerializedName("id") val id: Int,
    @SerializedName("dateStart") val dateStart: String,
    @SerializedName("dateEnd") val dateEnd: String,
    @SerializedName("parkingSpace") val parkingSpace: ApiParkingSpace
)