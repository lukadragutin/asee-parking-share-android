package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.data.interactor.GetAccessTokenInteractor
import hr.asee.android.template.data.interactor.GetAccountInteractor
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.RegisterInteractor
import hr.asee.android.template.data.interactor.LogoutInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.data.interactor.offering.AddOfferingInteractor
import hr.asee.android.template.data.interactor.offering.DeleteOfferingInteractor
import hr.asee.android.template.data.interactor.offering.GetOfferingsForGiverInteractor
import hr.asee.android.template.data.interactor.offering.GetOffersInteractor
import hr.asee.android.template.data.interactor.onboarding.GetIsOnboardingCompleteInteractor
import hr.asee.android.template.data.interactor.onboarding.SetOnboardingCompleteInteractor
import hr.asee.android.template.data.interactor.parkingspace.AddParkingSpaceInteractor
import hr.asee.android.template.data.interactor.parkingspace.ChangeParkingLocationInteractor
import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpaceByIdInteractor
import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpaceForGiverInteractor
import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpacesInteractor
import hr.asee.android.template.data.interactor.reservation.GetReservationsForGiverInteractor
import hr.asee.android.template.data.interactor.reservation.GetReservationsForSeekerInteractor
import hr.asee.android.template.data.interactor.reservation.GetReservationsInteractor
import hr.asee.android.template.data.interactor.reservation.PutReservationByIdInteractor
import hr.asee.android.template.data.interactor.seeking.GetSeekingsForSeekerInteractor
import hr.asee.android.template.data.interactor.seeking.GetSeekingsInteractor
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.mapper.OfferMapper
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.mapper.ReservationMapper
import hr.asee.android.template.domain.mapper.SeekingMapper
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.repository.NavigationItemsRepository
import hr.asee.android.template.domain.repository.OfferRepository
import hr.asee.android.template.domain.repository.OnboardingRepository
import hr.asee.android.template.domain.repository.ParkingSpaceRepository
import hr.asee.android.template.domain.repository.ReservationRepository
import hr.asee.android.template.domain.repository.SeekingRepository
import hr.asee.android.template.domain.repository.impl.AuthenticationRepositoryImpl
import hr.asee.android.template.domain.repository.impl.NavigationItemsRepositoryImpl
import hr.asee.android.template.domain.repository.impl.OfferRepositoryImpl
import hr.asee.android.template.domain.repository.impl.OnboardingRepositoryImpl
import hr.asee.android.template.domain.repository.impl.ParkingSpaceRepositoryImpl
import hr.asee.android.template.domain.repository.impl.ReservationRepositoryImpl
import hr.asee.android.template.domain.repository.impl.SeekingRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

	@Provides
	@ViewModelScoped
	fun provideOnboardingRepository(setOnboardingCompleteInteractor: SetOnboardingCompleteInteractor,
									getIsOnboardingCompleteInteractor: GetIsOnboardingCompleteInteractor): OnboardingRepository =
		OnboardingRepositoryImpl(setOnboardingCompleteInteractor = setOnboardingCompleteInteractor, getIsOnboardingCompleteInteractor = getIsOnboardingCompleteInteractor)

	@Provides
	@ViewModelScoped
	fun provideNavigationItemsRepository(): NavigationItemsRepository = NavigationItemsRepositoryImpl(Config.BOTTOM_NAV_BAR_ITEMS)

	@Provides
	@ViewModelScoped
	fun provideAuthenticationRepository(loginInteractor: LoginInteractor, logoutInteractor: LogoutInteractor, registerInteractor: RegisterInteractor, accessTokenMapper: AccessTokenMapper, storeAccessTokenInteractor: StoreAccessTokenInteractor,
										getAccessTokenInteractor: GetAccessTokenInteractor, getAccountInteractor: GetAccountInteractor,
										userMapper: UserMapper): AuthenticationRepository = AuthenticationRepositoryImpl(loginInteractor = loginInteractor,
																														 logoutInteractor = logoutInteractor,
                                                                                                                         registerInteractor = registerInteractor,
																														 accessTokenMapper = accessTokenMapper,
																														 storeAccessTokenInteractor = storeAccessTokenInteractor,
																														 getAccessTokenInteractor = getAccessTokenInteractor,
																														 getAccountInteractor = getAccountInteractor,
																														 userMapper = userMapper)

	@Provides
	@ViewModelScoped
	fun provideParkingSpaceRepository(getParkingSpaceByIdInteractor: GetParkingSpaceByIdInteractor, getParkingSpacesInteractor: GetParkingSpacesInteractor,
									  getParkingSpaceForGiverInteractor: GetParkingSpaceForGiverInteractor, addParkingSpaceInteractor: AddParkingSpaceInteractor,
									  changeParkingLocationInteractor: ChangeParkingLocationInteractor, parkingSpaceMapper: ParkingSpaceMapper): ParkingSpaceRepository =
		ParkingSpaceRepositoryImpl(getParkingSpaceByIdInteractor = getParkingSpaceByIdInteractor,
								   getParkingSpacesInteractor = getParkingSpacesInteractor,
								   getParkingSpaceForGiverInteractor = getParkingSpaceForGiverInteractor,
								   addParkingSpaceInteractor = addParkingSpaceInteractor,
								   changeParkingLocationInteractor = changeParkingLocationInteractor,
								   parkingSpaceMapper = parkingSpaceMapper)

	@Provides
	@ViewModelScoped
	fun provideOfferRepository(offerMapper: OfferMapper, getOffersInteractor: GetOffersInteractor, getOfferingsForGiverInteractor: GetOfferingsForGiverInteractor,
							   addOfferingInteractor: AddOfferingInteractor, deleteOfferingInteractor: DeleteOfferingInteractor): OfferRepository =
		OfferRepositoryImpl(offerMapper = offerMapper,
							getOffersInteractor = getOffersInteractor,
							getOfferingsForGiverInteractor = getOfferingsForGiverInteractor,
							addOfferingInteractor = addOfferingInteractor,
							deleteOfferingInteractor = deleteOfferingInteractor)

	@Provides
	@ViewModelScoped
	fun provideReservationRepository(getReservationsInteractor: GetReservationsInteractor, getReservationsForGiverInteractor: GetReservationsForGiverInteractor,
									 getReservationsForSeekerInteractor: GetReservationsForSeekerInteractor, putReservationByIdInteractor: PutReservationByIdInteractor,
									 reservationMapper: ReservationMapper): ReservationRepository = ReservationRepositoryImpl(getReservationsInteractor = getReservationsInteractor,
																															  getReservationsForGiverInteractor = getReservationsForGiverInteractor,
																															  getReservationsForSeekerInteractor = getReservationsForSeekerInteractor,
																															  putReservationByIdInteractor = putReservationByIdInteractor,
																															  reservationMapper = reservationMapper)

	@Provides
	@ViewModelScoped
	fun provideSeekingRepository(getSeekingsInteractor: GetSeekingsInteractor, getSeekingsForSeekerInteractor: GetSeekingsForSeekerInteractor,
								 seekingMapper: SeekingMapper): SeekingRepository =
		SeekingRepositoryImpl(getSeekingsInteractor = getSeekingsInteractor, getSeekingsForSeekerInteractor = getSeekingsForSeekerInteractor, seekingMapper = seekingMapper)
}
