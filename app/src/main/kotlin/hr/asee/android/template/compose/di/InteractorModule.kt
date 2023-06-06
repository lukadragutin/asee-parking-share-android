package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.data.interactor.GetAccessTokenInteractor
import hr.asee.android.template.data.interactor.GetAccountInteractor
import hr.asee.android.template.data.interactor.GetOffersInteractor
import hr.asee.android.template.data.interactor.GetParkingSpacesInteractor
import hr.asee.android.template.data.interactor.GetReservationsInteractor
import hr.asee.android.template.data.interactor.GetSeekingsInteractor
import hr.asee.android.template.data.interactor.GetUserByLoginInteractor
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.data.interactor.impl.GetAccessTokenInteractorImpl
import hr.asee.android.template.data.interactor.impl.GetAccountInteractorImpl
import hr.asee.android.template.data.interactor.impl.GetOffersInteractorImpl
import hr.asee.android.template.data.interactor.impl.GetParkingsSpacesInteractorImpl
import hr.asee.android.template.data.interactor.impl.GetReservationsInteractorImpl
import hr.asee.android.template.data.interactor.impl.GetSeekingsInteractorImpl
import hr.asee.android.template.data.interactor.impl.GetUserByLoginInteractorImpl
import hr.asee.android.template.data.interactor.impl.LoginInteractorImpl
import hr.asee.android.template.data.interactor.impl.StoreAccessTokenInteractorImpl
import hr.asee.android.template.data.local.storage.ApplicationStorage
import hr.asee.android.template.data.network.AuthenticationApiService
import hr.asee.android.template.data.network.OfferApiService
import hr.asee.android.template.data.network.ParkingSpaceApiService
import hr.asee.android.template.data.network.ReservationApiService
import hr.asee.android.template.data.network.SeekingApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object InteractorModule {

    @Provides
    @ViewModelScoped
    fun provideLoginInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): LoginInteractor = LoginInteractorImpl(
        authenticationApiService = retrofit.create(AuthenticationApiService::class.java),
        reqResServiceErrorResolver = reqResServiceErrorResolver,
    )

    @Provides
    @ViewModelScoped
    fun provideStoreAccessTokenInteractor(applicationStorage: ApplicationStorage): StoreAccessTokenInteractor =
        StoreAccessTokenInteractorImpl(applicationStorage = applicationStorage)

    @Provides
    @ViewModelScoped
    fun provideGetAccessTokenInteractor(applicationStorage: ApplicationStorage): GetAccessTokenInteractor =
        GetAccessTokenInteractorImpl(applicationStorage = applicationStorage)

    @Provides
    @ViewModelScoped
    fun provideGetAccountInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetAccountInteractor =
        GetAccountInteractorImpl(
            authenticationApiService = retrofit.create(AuthenticationApiService::class.java),
            reqResServiceErrorResolver = reqResServiceErrorResolver
        )

    @Provides
    @ViewModelScoped
    fun provideGetUserByLoginInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetUserByLoginInteractor =
        GetUserByLoginInteractorImpl(
            authenticationApiService = retrofit.create(AuthenticationApiService::class.java),
            reqResServiceErrorResolver = reqResServiceErrorResolver
        )

    @Provides
    @ViewModelScoped
    fun provideGetSeekingsInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetSeekingsInteractor =
        GetSeekingsInteractorImpl(
            seekingApiService = retrofit.create(SeekingApiService::class.java),
            reqResServiceErrorResolver = reqResServiceErrorResolver
        )

    @Provides
    @ViewModelScoped
    fun provideGetOffersInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetOffersInteractor =
        GetOffersInteractorImpl(
            offerApiService = retrofit.create(OfferApiService::class.java),
            reqResServiceErrorResolver = reqResServiceErrorResolver
        )

    @Provides
    @ViewModelScoped
    fun provideGetReservationsInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetReservationsInteractor =
        GetReservationsInteractorImpl(
            reservationApiService = retrofit.create(ReservationApiService::class.java),
            reqResServiceErrorResolver = reqResServiceErrorResolver
        )

    @Provides
    @ViewModelScoped
    fun provideGetParkingSpacesInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): GetParkingSpacesInteractor =
        GetParkingsSpacesInteractorImpl(
            parkingSpaceApiService = retrofit.create(ParkingSpaceApiService::class.java),
            reqResServiceErrorResolver = reqResServiceErrorResolver
        )
}
