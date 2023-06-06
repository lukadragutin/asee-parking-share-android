package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.repository.AccountRepository
import hr.asee.android.template.domain.usecase.GetOffersUseCase
import java.time.LocalDateTime

class GetOffersUseCaseImpl(
    private val accountRepository: AccountRepository
): GetOffersUseCase {

    override suspend fun invoke(dateStart: LocalDateTime, dateEnd: LocalDateTime): Resource<List<Offer>> {
        val offersList = try {
            accountRepository.getOffers(dateStart = dateStart, dateEnd = dateEnd)
        } catch (throwable: Throwable) {
            return Resource.Error(GetOffersUseCase.GetOffersError.GENERAL_GET_OFFERS_ERROR, throwable)
        }

        return Resource.Success(offersList)
    }
}