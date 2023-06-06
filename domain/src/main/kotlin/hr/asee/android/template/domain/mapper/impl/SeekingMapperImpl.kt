package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiSeeking
import hr.asee.android.template.domain.mapper.SeekingMapper
import hr.asee.android.template.domain.mapper.UserCompactMapper
import hr.asee.android.template.domain.model.common.service.Seeking
import java.time.LocalDateTime

class SeekingMapperImpl(
    private val userCompactMapper: UserCompactMapper
) : SeekingMapper {

    override fun toSeeking(apiSeeking: ApiSeeking): Seeking {
        return Seeking(
            id = apiSeeking.id,
            dateStart = LocalDateTime.parse(apiSeeking.dateStart),
            dateEnd = LocalDateTime.parse(apiSeeking.dateEnd),
            seeker = userCompactMapper.toUser(apiSeeking.seeker)
        )
    }
}