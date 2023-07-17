package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.data.interactor.GetAccessTokenInteractor
import hr.asee.android.template.data.interactor.GetAccountInteractor
import hr.asee.android.template.data.interactor.GetUserByLoginInteractor
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.LogoutInteractor
import hr.asee.android.template.data.interactor.RegisterInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.data.interactor.impl.GetAccessTokenInteractorImpl
import hr.asee.android.template.data.interactor.impl.GetAccountInteractorImpl
import hr.asee.android.template.data.interactor.impl.GetUserByLoginInteractorImpl
import hr.asee.android.template.data.interactor.impl.LoginInteractorImpl
import hr.asee.android.template.data.interactor.impl.LogoutInteractorImpl
import hr.asee.android.template.data.interactor.impl.RegisterInteractorImpl
import hr.asee.android.template.data.interactor.impl.StoreAccessTokenInteractorImpl
import hr.asee.android.template.data.interactor.offering.AddOfferingInteractor
import hr.asee.android.template.data.interactor.offering.DeleteOfferingInteractor
import hr.asee.android.template.data.interactor.offering.GetOfferingsForGiverInteractor
import hr.asee.android.template.data.interactor.offering.GetOffersInteractor
import hr.asee.android.template.data.interactor.offering.impl.AddOfferingInteractorImpl
import hr.asee.android.template.data.interactor.offering.impl.DeleteOfferingInteractorImpl
import hr.asee.android.template.data.interactor.offering.impl.GetOfferingsForGiverInteractorImpl
import hr.asee.android.template.data.interactor.offering.impl.GetOffersInteractorImpl
import hr.asee.android.template.data.interactor.onboarding.GetIsOnboardingCompleteInteractor
import hr.asee.android.template.data.interactor.onboarding.SetOnboardingCompleteInteractor
import hr.asee.android.template.data.interactor.onboarding.impl.GetIsOnboardingCompleteInteractorImpl
import hr.asee.android.template.data.interactor.onboarding.impl.SetOnboardingCompleteInteractorImpl
import hr.asee.android.template.data.interactor.parkingspace.AddParkingSpaceInteractor
import hr.asee.android.template.data.interactor.parkingspace.ChangeParkingLocationInteractor
import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpaceByIdInteractor
import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpaceForGiverInteractor
import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpacesInteractor
import hr.asee.android.template.data.interactor.parkingspace.impl.AddParkingSpaceInteractorImpl
import hr.asee.android.template.data.interactor.parkingspace.impl.ChangeParkingLocationInteractorImpl
import hr.asee.android.template.data.interactor.parkingspace.impl.GetParkingSpaceByIdInteractorImpl
import hr.asee.android.template.data.interactor.parkingspace.impl.GetParkingSpaceForGiverInteractorImpl
import hr.asee.android.template.data.interactor.parkingspace.impl.GetParkingsSpacesInteractorImpl
import hr.asee.android.template.data.interactor.reservation.GetReservationsForGiverInteractor
import hr.asee.android.template.data.interactor.reservation.GetReservationsForSeekerInteractor
import hr.asee.android.template.data.interactor.reservation.GetReservationsInteractor
import hr.asee.android.template.data.interactor.reservation.PutReservationByIdInteractor
import hr.asee.android.template.data.interactor.reservation.impl.GetReservationsForGiverInteractorImpl
import hr.asee.android.template.data.interactor.reservation.impl.GetReservationsForSeekerInteractorImpl
import hr.asee.android.template.data.interactor.reservation.impl.GetReservationsInteractorImpl
import hr.asee.android.template.data.interactor.reservation.impl.PutReservationByIdInteractorImpl
import hr.asee.android.template.data.interactor.seeking.GetSeekingsForSeekerInteractor
import hr.asee.android.template.data.interactor.seeking.GetSeekingsInteractor
import hr.asee.android.template.data.interactor.seeking.impl.GetSeekingsForSeekerInteractorImpl
import hr.asee.android.template.data.interactor.seeking.impl.GetSeekingsInteractorImpl
import hr.asee.android.template.data.local.storage.ApplicationStorage
import hr.asee.android.template.data.network.AuthenticationApiService
import hr.asee.android.template.data.network.OfferApiService
import hr.asee.android.template.data.network.ParkingSpaceApiService
import hr.asee.android.template.data.network.ReqresApiService
import hr.asee.android.template.data.network.ReservationApiService
import hr.asee.android.template.data.network.SeekingApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object InteractorModule {

	@Provides
	@ViewModelScoped
	fun provideSetOnboardingCompleteInteractor(applicationStorage: ApplicationStorage): SetOnboardingCompleteInteractor = SetOnboardingCompleteInteractorImpl(applicationStorage)

	@Provides
	@ViewModelScoped
	fun provideGetIsOnboardingCompleteInteractor(applicationStorage: ApplicationStorage): GetIsOnboardingCompleteInteractor =
		GetIsOnboardingCompleteInteractorImpl(applicationStorage)

	@Provides
	@ViewModelScoped
	fun provideLoginInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): LoginInteractor = LoginInteractorImpl(
		authenticationApiService = retrofit.create(AuthenticationApiService::class.java),
		reqResServiceErrorResolver = reqResServiceErrorResolver,
	)

    @Provides
    @ViewModelScoped
    fun provideRegisterInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): RegisterInteractor = RegisterInteractorImpl(
        reqresApiService = retrofit.create(ReqresApiService::class.java),
        reqResServiceErrorResolver = reqResServiceErrorResolver,
    )

	@Provides
	@ViewModelScoped
	fun provideLogoutInteractor(applicationStorage: ApplicationStorage): LogoutInteractor = LogoutInteractorImpl(applicationStorage)

	@Provides
	@ViewModelScoped
	fun provideStoreAccessTokenInteractor(applicationStorage: ApplicationStorage): StoreAccessTokenInteractor =
		StoreAccessTokenInteractorImpl(applicationStorage = applicationStorage)

	@Provides
	@ViewModelScoped
	fun provideGetAccessTokenInteractor(applicationStorage: ApplicationStorage): GetAccessTokenInteractor = GetAccessTokenInteractorImpl(applicationStorage = applicationStorage)

	@Provides
	@ViewModelScoped
	fun provideGetAccountInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetAccountInteractor =
		GetAccountInteractorImpl(authenticationApiService = retrofit.create(AuthenticationApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)

	@Provides
	@ViewModelScoped
	fun provideGetUserByLoginInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetUserByLoginInteractor =
		GetUserByLoginInteractorImpl(authenticationApiService = retrofit.create(AuthenticationApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)

	@Provides
	@ViewModelScoped
	fun provideGetSeekingsInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetSeekingsInteractor =
		GetSeekingsInteractorImpl(seekingApiService = retrofit.create(SeekingApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)

	@Provides
	@ViewModelScoped
	fun provideGetOffersInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetOffersInteractor =
		GetOffersInteractorImpl(offerApiService = retrofit.create(OfferApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)

	@Provides
	@ViewModelScoped
	fun provideGetOfferingsForGiverInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetOfferingsForGiverInteractor =
		GetOfferingsForGiverInteractorImpl(offerApiService = retrofit.create(OfferApiService::class.java))

	@Provides
	@ViewModelScoped
	fun provideAddOfferingInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): AddOfferingInteractor =
		AddOfferingInteractorImpl(retrofit.create(OfferApiService::class.java))

	@Provides
	@ViewModelScoped
	fun provideDeleteOfferingInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): DeleteOfferingInteractor =
		DeleteOfferingInteractorImpl(retrofit.create(OfferApiService::class.java))

	@Provides
	@ViewModelScoped
	fun provideGetSeekingsForSeekerInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetSeekingsForSeekerInteractor =
		GetSeekingsForSeekerInteractorImpl(seekingApiService = retrofit.create(SeekingApiService::class.java))

	@Provides
	@ViewModelScoped
	fun provideGetReservationsInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetReservationsInteractor =
		GetReservationsInteractorImpl(reservationApiService = retrofit.create(ReservationApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)

	@Provides
	@ViewModelScoped
	fun provideGetReservationsForSeekerInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetReservationsForSeekerInteractor {
		return GetReservationsForSeekerInteractorImpl(
			reservationApiService = retrofit.create(ReservationApiService::class.java),
		)
	}

	@Provides
	@ViewModelScoped
	fun provideGetReservationsForGiverInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetReservationsForGiverInteractor {
		return GetReservationsForGiverInteractorImpl(
			reservationApiService = retrofit.create(ReservationApiService::class.java),
		)
	}

	@Provides
	@ViewModelScoped
	fun provideGetParkingSpacesInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetParkingSpacesInteractor =
		GetParkingsSpacesInteractorImpl(parkingSpaceApiService = retrofit.create(ParkingSpaceApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)

	@Provides
	@ViewModelScoped
	fun provideGetParkingSpaceByIdInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetParkingSpaceByIdInteractor =
		GetParkingSpaceByIdInteractorImpl(parkingSpaceApiService = retrofit.create(ParkingSpaceApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)

	@Provides
	@ViewModelScoped
	fun provideGetParkingSpaceForGiverInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetParkingSpaceForGiverInteractor =
		GetParkingSpaceForGiverInteractorImpl(parkingSpaceApiService = retrofit.create(ParkingSpaceApiService::class.java))

	@Provides
	@ViewModelScoped
	fun provideAddParkingSpaceInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): AddParkingSpaceInteractor =
		AddParkingSpaceInteractorImpl(parkingSpaceApiService = retrofit.create(ParkingSpaceApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)

	@Provides
	@ViewModelScoped
	fun provideChangeParkingLocationInteractpr(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): ChangeParkingLocationInteractor =
		ChangeParkingLocationInteractorImpl(parkingSpaceApiService = retrofit.create(ParkingSpaceApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)

	@Provides
	@ViewModelScoped
	fun providePutReservationByIdInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): PutReservationByIdInteractor =
		PutReservationByIdInteractorImpl(reservationApiService = retrofit.create(ReservationApiService::class.java), reqResServiceErrorResolver = reqResServiceErrorResolver)
}
