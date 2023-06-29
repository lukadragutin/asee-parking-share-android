package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.common.service.Offer
import java.time.LocalDateTime

interface OfferRepository {

    suspend fun getOffers(dateStart: LocalDateTime, dateEnd: LocalDateTime): List<Offer>
}