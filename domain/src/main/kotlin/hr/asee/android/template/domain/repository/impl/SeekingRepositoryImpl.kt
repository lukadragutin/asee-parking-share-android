package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.GetSeekingsInteractor
import hr.asee.android.template.domain.mapper.SeekingMapper
import hr.asee.android.template.domain.model.common.service.Seeking
import hr.asee.android.template.domain.repository.SeekingRepository
import java.time.LocalDateTime

class SeekingRepositoryImpl(
    private val getSeekingsInteractor: GetSeekingsInteractor,
    private val seekingMapper: SeekingMapper
) : SeekingRepository {

    override suspend fun getSeekings(
        dateStart: LocalDateTime,
        dateEnd: LocalDateTime
    ): List<Seeking> {
        val seekingsList: MutableList<Seeking> = mutableListOf()

        getSeekingsInteractor(dateStart = dateStart, dateEnd = dateEnd).forEach {
            seekingsList.add(seekingMapper.toSeeking(it))
        }

        return seekingsList
    }
}