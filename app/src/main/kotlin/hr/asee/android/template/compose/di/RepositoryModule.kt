package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.data.interactor.GetAccessTokenInteractor
import hr.asee.android.template.data.interactor.GetAccountInteractor
import hr.asee.android.template.data.interactor.GetOffersInteractor
import hr.asee.android.template.data.interactor.GetParkingSpacesInteractor
import hr.asee.android.template.data.interactor.GetReservationsInteractor
import hr.asee.android.template.data.interactor.GetSeekingsInteractor
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.mapper.OfferMapper
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.mapper.ReservationMapper
import hr.asee.android.template.domain.mapper.SeekingMapper
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.repository.AccountRepository
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.repository.NavigationItemsRepository
import hr.asee.android.template.domain.repository.impl.AccountRepositoryImpl
import hr.asee.android.template.domain.repository.impl.AuthenticationRepositoryImpl
import hr.asee.android.template.domain.repository.impl.NavigationItemsRepositoryImpl

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
    ): AuthenticationRepository = AuthenticationRepositoryImpl(
        loginInteractor = loginInteractor,
        accessTokenMapper = accessTokenMapper,
        storeAccessTokenInteractor = storeAccessTokenInteractor,
    )

    @Provides
    @ViewModelScoped
    fun provideAccountRepository(
        getAccountInteractor: GetAccountInteractor,
        getSeekingsInteractor: GetSeekingsInteractor,
        getOffersInteractor: GetOffersInteractor,
        getReservationsInteractor: GetReservationsInteractor,
        getParkingSpacesInteractor: GetParkingSpacesInteractor,
        parkingSpaceMapper: ParkingSpaceMapper,
        reservationMapper: ReservationMapper,
        seekingMapper: SeekingMapper,
        offerMapper: OfferMapper,
        userMapper: UserMapper
    ): AccountRepository = AccountRepositoryImpl(
        getAccountInteractor = getAccountInteractor,
        getSeekingsInteractor = getSeekingsInteractor,
        getOffersInteractor = getOffersInteractor,
        getReservationsInteractor = getReservationsInteractor,
        getParkingSpacesInteractor = getParkingSpacesInteractor,
        parkingSpaceMapper = parkingSpaceMapper,
        reservationMapper = reservationMapper,
        offerMapper = offerMapper,
        seekingMapper = seekingMapper,
        userMapper = userMapper
    )
}
