package hr.asee.android.template.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class ApiReservation(
    @SerializedName("id") val id: Int,
    @SerializedName("dateStart") val dateStart: String,
    @SerializedName("dateEnd") val dateEnd: String,
    @SerializedName("cancellationPending") val cancellationPending: Boolean,
    @SerializedName("parkingSpace") val parkingSpace: ApiParkingSpace,
    @SerializedName("seeker") val seeker: ApiUserCompact
)
