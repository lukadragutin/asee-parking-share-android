package hr.asee.android.template.domain.usecase.offering

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import org.threeten.bp.LocalDateTime

interface AddOfferingUseCase {

	suspend operator fun invoke(dateStart: LocalDateTime, dateEnd: LocalDateTime, parkingSpaceId: Int): EmptyResource
}