package hr.asee.android.template.domain.usecase.seeking

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Seeking

interface GetSeekingsForSeekerUseCase {

	suspend operator fun invoke(seekerId: Int): Resource<List<Seeking>>
}