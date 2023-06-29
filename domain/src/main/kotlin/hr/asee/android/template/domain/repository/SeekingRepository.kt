package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.common.service.Seeking
import java.time.LocalDateTime

interface SeekingRepository {

    suspend fun getSeekings(dateStart: LocalDateTime, dateEnd: LocalDateTime): List<Seeking>
}