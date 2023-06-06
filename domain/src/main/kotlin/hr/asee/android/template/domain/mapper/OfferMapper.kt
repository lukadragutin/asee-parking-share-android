package hr.asee.android.template.domain.mapper

import hr.asee.android.template.data.model.remote.response.ApiOffer
import hr.asee.android.template.domain.model.common.service.Offer

interface OfferMapper {

    fun toOffer(apiOffer: ApiOffer): Offer
}