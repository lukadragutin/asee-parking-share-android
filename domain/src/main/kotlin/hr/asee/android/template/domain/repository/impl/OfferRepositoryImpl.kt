package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.offering.AddOfferingInteractor
import hr.asee.android.template.data.interactor.offering.DeleteOfferingInteractor
import hr.asee.android.template.data.interactor.offering.GetOfferingsForGiverInteractor
import hr.asee.android.template.data.interactor.offering.GetOffersInteractor
import hr.asee.android.template.data.model.remote.body.ApiOfferingRequest
import hr.asee.android.template.data.model.remote.response.ApiParkingSpace
import hr.asee.android.template.domain.mapper.OfferMapper
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.repository.OfferRepository
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

class OfferRepositoryImpl(
	private val getOffersInteractor: GetOffersInteractor,
	private val getOfferingsForGiverInteractor: GetOfferingsForGiverInteractor,
	private val addOfferingInteractor: AddOfferingInteractor,
	private val deleteOfferingInteractor: DeleteOfferingInteractor,
	private val offerMapper: OfferMapper
) : OfferRepository {

	override suspend fun getOffers(
		dateStart: LocalDateTime?,
		dateEnd: LocalDateTime?
	): List<Offer> {
		val offersList: MutableList<Offer> = mutableListOf()

		getOffersInteractor(dateStart = dateStart, dateEnd = dateEnd).forEach {
			offersList.add(offerMapper.toOffer(it))
		}

		return offersList
	}

	override suspend fun getOfferingsForGiver(giverId: Int): List<Offer> {
		return getOfferingsForGiverInteractor(giverId).map(offerMapper::toOffer)
	}

	override suspend fun addOffering(dateStart: LocalDateTime, dateEnd: LocalDateTime, parkingSpaceId: Int) {
		addOfferingInteractor(ApiOfferingRequest(dateStart = dateStart.toInstant(ZoneOffset.UTC).toString(),
												 dateEnd = dateEnd.toInstant(ZoneOffset.UTC).toString(),
												 ApiParkingSpace(parkingSpaceId, "", null)))
	}

	override suspend fun deleteOffering(offerId: Int) {
		deleteOfferingInteractor(offerId)
	}
}