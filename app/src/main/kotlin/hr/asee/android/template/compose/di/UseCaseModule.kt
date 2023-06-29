package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.repository.NavigationItemsRepository
import hr.asee.android.template.domain.repository.OfferRepository
import hr.asee.android.template.domain.repository.ParkingSpaceRepository
import hr.asee.android.template.domain.repository.ReservationRepository
import hr.asee.android.template.domain.repository.SeekingRepository
import hr.asee.android.template.domain.usecase.AddParkingSpaceUseCase
import hr.asee.android.template.domain.usecase.ChangeParkingLocationUseCase
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import hr.asee.android.template.domain.usecase.DateSelectUseCase
import hr.asee.android.template.domain.usecase.GetAccountUseCase
import hr.asee.android.template.domain.usecase.GetOffersUseCase
import hr.asee.android.template.domain.usecase.GetParkingSpaceByIdUseCase
import hr.asee.android.template.domain.usecase.GetParkingSpacesUseCase
import hr.asee.android.template.domain.usecase.GetReservationsUseCase
import hr.asee.android.template.domain.usecase.GetSeekingsUseCase
import hr.asee.android.template.domain.usecase.LoginUseCase
import hr.asee.android.template.domain.usecase.PutReservationByIdUseCase
import hr.asee.android.template.domain.usecase.impl.AddParkingSpaceUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.ChangeParkingLocationUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetAllBottomNavItemsUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.DateSelectUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetAccountUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetOffersUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetParkingSpaceByIdUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetParkingSpacesUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetReservationsUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetSeekingsUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.LoginUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.PutReservationByIdUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetAllBottomNavItemsUseCase(navigationItemsRepository: NavigationItemsRepository): GetAllBottomNavItemsUseCase {
        return GetAllBottomNavItemsUseCaseImpl(navigationItemsRepository = navigationItemsRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideLoginUseCase(authenticationRepository: AuthenticationRepository): LoginUseCase {
        return LoginUseCaseImpl(authenticationRepository = authenticationRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetAccountUseCase(authenticationRepository: AuthenticationRepository): GetAccountUseCase {
        return GetAccountUseCaseImpl(authenticationRepository = authenticationRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideFilterByDateUseCase(): DateSelectUseCase {
        return DateSelectUseCaseImpl()
    }

    @Provides
    @ViewModelScoped
    fun provideGetSeekingsUseCase(seekingRepository: SeekingRepository): GetSeekingsUseCase {
        return GetSeekingsUseCaseImpl(seekingRepository = seekingRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetOffersUseCase(offerRepository: OfferRepository): GetOffersUseCase {
        return GetOffersUseCaseImpl(offerRepository = offerRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetReservationsUseCase(reservationRepository: ReservationRepository): GetReservationsUseCase {
        return GetReservationsUseCaseImpl(reservationRepository = reservationRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetParkingSpacesUseCase(parkingSpaceRepository: ParkingSpaceRepository): GetParkingSpacesUseCase {
        return GetParkingSpacesUseCaseImpl(parkingSpaceRepository = parkingSpaceRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetParkingSpaceByIdUseCase(parkingSpaceRepository: ParkingSpaceRepository): GetParkingSpaceByIdUseCase {
        return GetParkingSpaceByIdUseCaseImpl(parkingSpaceRepository = parkingSpaceRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideAddParkingSpaceUseCase(parkingSpaceRepository: ParkingSpaceRepository): AddParkingSpaceUseCase {
        return AddParkingSpaceUseCaseImpl(parkingSpaceRepository = parkingSpaceRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideChangeParkingLocationUseCase(parkingSpaceRepository: ParkingSpaceRepository): ChangeParkingLocationUseCase {
        return ChangeParkingLocationUseCaseImpl(parkingSpaceRepository = parkingSpaceRepository)
    }

    @Provides
    @ViewModelScoped
    fun providePutReservationByIdUseCase(reservationRepository: ReservationRepository): PutReservationByIdUseCase {
        return PutReservationByIdUseCaseImpl(reservationRepository = reservationRepository)
    }
}
