package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiSeeking
import hr.asee.android.template.domain.mapper.SeekingMapper
import hr.asee.android.template.domain.mapper.UserCompactMapper
import hr.asee.android.template.domain.model.common.service.Seeking
import org.threeten.bp.ZonedDateTime

class SeekingMapperImpl(
    private val userCompactMapper: UserCompactMapper
) : SeekingMapper {

    override fun toSeeking(apiSeeking: ApiSeeking): Seeking {
        return Seeking(
            id = apiSeeking.id,
            dateStart = ZonedDateTime.parse(apiSeeking.dateStart).toLocalDateTime(),
            dateEnd = ZonedDateTime.parse(apiSeeking.dateEnd).toLocalDateTime(),
            seeker = userCompactMapper.toUser(apiSeeking.seeker)
        )
    }
}