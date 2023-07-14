package hr.asee.android.template.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class ApiParkingSpace(
    @SerializedName("id") val id: Int,
    @SerializedName("location") val location: String,
    @SerializedName("owner") val owner: ApiUserCompact?
)