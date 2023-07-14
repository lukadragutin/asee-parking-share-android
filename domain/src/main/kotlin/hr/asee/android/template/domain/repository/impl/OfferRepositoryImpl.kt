package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.offering.GetOfferingsForGiverInteractor
import hr.asee.android.template.data.interactor.offering.GetOffersInteractor
import hr.asee.android.template.domain.mapper.OfferMapper
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.repository.OfferRepository
import org.threeten.bp.LocalDateTime

class OfferRepositoryImpl(
	private val getOffersInteractor: GetOffersInteractor,
	private val getOfferingsForGiverInteractor: GetOfferingsForGiverInteractor,
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
}