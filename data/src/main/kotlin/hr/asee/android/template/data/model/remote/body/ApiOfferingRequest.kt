package hr.asee.android.template.data.model.remote.body

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace

data class ApiOfferingRequest(
	val dateStart: String,
	val dateEnd: String,
	val parkingSpace: ApiParkingSpace
)
