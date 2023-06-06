package hr.asee.android.template.domain.mapper

import hr.asee.android.template.data.model.remote.response.ApiSeeking
import hr.asee.android.template.domain.model.common.service.Seeking

interface SeekingMapper {

    fun toSeeking(apiSeeking: ApiSeeking): Seeking
}