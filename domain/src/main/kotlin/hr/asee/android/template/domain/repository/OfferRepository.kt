package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.common.service.Offer
import org.threeten.bp.LocalDateTime

interface OfferRepository {

    suspend fun getOffers(dateStart: LocalDateTime?, dateEnd: LocalDateTime?): List<Offer>

    suspend fun getOfferingsForGiver(giverId: Int): List<Offer>

    suspend fun addOffering(dateStart: LocalDateTime, dateEnd: LocalDateTime, parkingSpaceId: Int)

    suspend fun deleteOffering(offerId: Int)
}