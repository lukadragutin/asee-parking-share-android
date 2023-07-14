package hr.asee.android.template.domain.usecase.seeking.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Seeking
import hr.asee.android.template.domain.repository.SeekingRepository
import hr.asee.android.template.domain.usecase.seeking.GetSeekingsForSeekerUseCase

class GetSeekingsForSeekerUseCaseImpl(private val seekingRepository: SeekingRepository): GetSeekingsForSeekerUseCase {

	override suspend fun invoke(seekerId: Int): Resource<List<Seeking>> {
		return try {
			val seekings = seekingRepository.getSeekingsForSeeker(seekerId)
			Resource.Success(seekings)
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}