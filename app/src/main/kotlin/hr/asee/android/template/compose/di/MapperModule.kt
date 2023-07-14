package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.mapper.OfferMapper
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.mapper.ReservationMapper
import hr.asee.android.template.domain.mapper.SeekingMapper
import hr.asee.android.template.domain.mapper.UserCompactMapper
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.mapper.impl.AccessTokenMapperImpl
import hr.asee.android.template.domain.mapper.impl.OfferMapperImpl
import hr.asee.android.template.domain.mapper.impl.ParkingSpaceMapperImpl
import hr.asee.android.template.domain.mapper.impl.ReservationMapperImpl
import hr.asee.android.template.domain.mapper.impl.SeekingMapperImpl
import hr.asee.android.template.domain.mapper.impl.UserCompactMapperImpl
import hr.asee.android.template.domain.mapper.impl.UserMapperImpl

@Module
@InstallIn(ViewModelComponent::class)
object MapperModule {

    @Provides
    @ViewModelScoped
    fun provideAccessTokenMapper(): AccessTokenMapper = AccessTokenMapperImpl()

    @Provides
    @ViewModelScoped
    fun provideUserMapper(): UserMapper = UserMapperImpl()

    @Provides
    @ViewModelScoped
    fun provideUserCompactMapper(
    ): UserCompactMapper = UserCompactMapperImpl()

    @Provides
    @ViewModelScoped
    fun provideSeekingMapper(
        userCompactMapper: UserCompactMapper
    ): SeekingMapper = SeekingMapperImpl(userCompactMapper = userCompactMapper)

    @Provides
    @ViewModelScoped
    fun provideOfferMapper(
        parkingSpaceMapper: ParkingSpaceMapper
    ): OfferMapper = OfferMapperImpl(parkingSpaceMapper = parkingSpaceMapper)

    @Provides
    @ViewModelScoped
    fun provideReservationMapper(
        userCompactMapper: UserCompactMapper,
        parkingSpaceMapper: ParkingSpaceMapper
    ): ReservationMapper = ReservationMapperImpl(
        parkingSpaceMapper = parkingSpaceMapper,
        userCompactMapper = userCompactMapper
    )

    @Provides
    @ViewModelScoped
    fun provideParkingSpaceMapper(
        userCompactMapper: UserCompactMapper
    ): ParkingSpaceMapper = ParkingSpaceMapperImpl(userCompactMapper = userCompactMapper)
}
