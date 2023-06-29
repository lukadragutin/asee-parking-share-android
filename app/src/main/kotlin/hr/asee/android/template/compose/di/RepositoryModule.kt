package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.data.interactor.AddParkingSpaceInteractor
import hr.asee.android.template.data.interactor.ChangeParkingLocationInteractor
import hr.asee.android.template.data.interactor.GetAccountInteractor
import hr.asee.android.template.data.interactor.GetOffersInteractor
import hr.asee.android.template.data.interactor.GetParkingSpaceByIdInteractor
import hr.asee.android.template.data.interactor.GetParkingSpacesInteractor
import hr.asee.android.template.data.interactor.GetReservationsInteractor
import hr.asee.android.template.data.interactor.GetSeekingsInteractor
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.PutReservationByIdInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.mapper.OfferMapper
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.mapper.ReservationMapper
import hr.asee.android.template.domain.mapper.SeekingMapper
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.repository.NavigationItemsRepository
import hr.asee.android.template.domain.repository.OfferRepository
import hr.asee.android.template.domain.repository.ParkingSpaceRepository
import hr.asee.android.template.domain.repository.ReservationRepository
import hr.asee.android.template.domain.repository.SeekingRepository
import hr.asee.android.template.domain.repository.impl.AuthenticationRepositoryImpl
import hr.asee.android.template.domain.repository.impl.NavigationItemsRepositoryImpl
import hr.asee.android.template.domain.repository.impl.OfferRepositoryImpl
import hr.asee.android.template.domain.repository.impl.ParkingSpaceRepositoryImpl
import hr.asee.android.template.domain.repository.impl.ReservationRepositoryImpl
import hr.asee.android.template.domain.repository.impl.SeekingRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideNavigationItemsRepository(): NavigationItemsRepository = NavigationItemsRepositoryImpl(Config.BOTTOM_NAV_BAR_ITEMS)

    @Provides
    @ViewModelScoped
    fun provideAuthenticationRepository(
        loginInteractor: LoginInteractor,
        accessTokenMapper: AccessTokenMapper,
        storeAccessTokenInteractor: StoreAccessTokenInteractor,
        getAccountInteractor: GetAccountInteractor,
        userMapper: UserMapper
    ): AuthenticationRepository = AuthenticationRepositoryImpl(
        loginInteractor = loginInteractor,
        accessTokenMapper = accessTokenMapper,
        storeAccessTokenInteractor = storeAccessTokenInteractor,
        getAccountInteractor = getAccountInteractor,
        userMapper = userMapper
    )

    @Provides
    @ViewModelScoped
    fun provideParkingSpaceRepository(
        getParkingSpaceByIdInteractor: GetParkingSpaceByIdInteractor,
        getParkingSpacesInteractor: GetParkingSpacesInteractor,
        addParkingSpaceInteractor: AddParkingSpaceInteractor,
        changeParkingLocationInteractor: ChangeParkingLocationInteractor,
        parkingSpaceMapper: ParkingSpaceMapper
    ): ParkingSpaceRepository = ParkingSpaceRepositoryImpl(
        getParkingSpaceByIdInteractor = getParkingSpaceByIdInteractor,
        getParkingSpacesInteractor = getParkingSpacesInteractor,
        addParkingSpaceInteractor = addParkingSpaceInteractor,
        changeParkingLocationInteractor = changeParkingLocationInteractor,
        parkingSpaceMapper = parkingSpaceMapper
    )

    @Provides
    @ViewModelScoped
    fun provideOfferRepository(
        offerMapper: OfferMapper,
        getOffersInteractor: GetOffersInteractor
    ): OfferRepository = OfferRepositoryImpl(
        offerMapper = offerMapper,
        getOffersInteractor = getOffersInteractor
    )

    @Provides
    @ViewModelScoped
    fun provideReservationRepository(
        getReservationsInteractor: GetReservationsInteractor,
        putReservationByIdInteractor: PutReservationByIdInteractor,
        reservationMapper: ReservationMapper
    ): ReservationRepository = ReservationRepositoryImpl(
        getReservationsInteractor = getReservationsInteractor,
        putReservationByIdInteractor = putReservationByIdInteractor,
        reservationMapper = reservationMapper
    )

    @Provides
    @ViewModelScoped
    fun provideSeekingRepository(
        getSeekingsInteractor: GetSeekingsInteractor,
        seekingMapper: SeekingMapper
    ): SeekingRepository = SeekingRepositoryImpl(
        getSeekingsInteractor = getSeekingsInteractor,
        seekingMapper = seekingMapper
    )
}
