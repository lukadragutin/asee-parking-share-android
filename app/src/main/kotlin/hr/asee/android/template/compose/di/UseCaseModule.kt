package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.repository.NavigationItemsRepository
import hr.asee.android.template.domain.repository.OfferRepository
import hr.asee.android.template.domain.repository.OnboardingRepository
import hr.asee.android.template.domain.repository.ParkingSpaceRepository
import hr.asee.android.template.domain.repository.ReservationRepository
import hr.asee.android.template.domain.repository.SeekingRepository
import hr.asee.android.template.domain.usecase.DateSelectUseCase
import hr.asee.android.template.domain.usecase.GetAccountUseCase
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import hr.asee.android.template.domain.usecase.LoginUseCase
import hr.asee.android.template.domain.usecase.impl.DateSelectUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetAccountUseCaseImpl
import hr.asee.android.template.domain.usecase.RegisterUseCase
import hr.asee.android.template.domain.usecase.impl.GetAllBottomNavItemsUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.LoginUseCaseImpl
import hr.asee.android.template.domain.usecase.login.IsLoginActiveUseCase
import hr.asee.android.template.domain.usecase.login.LogoutUseCase
import hr.asee.android.template.domain.usecase.login.impl.IsLoginActiveUseCaseImpl
import hr.asee.android.template.domain.usecase.login.impl.LogoutUseCaseImpl
import hr.asee.android.template.domain.usecase.offering.AddOfferingUseCase
import hr.asee.android.template.domain.usecase.offering.DeleteOfferingUseCase
import hr.asee.android.template.domain.usecase.offering.GetOfferingsForGiverUseCase
import hr.asee.android.template.domain.usecase.offering.GetOffersUseCase
import hr.asee.android.template.domain.usecase.offering.impl.AddOfferingUseCaseImpl
import hr.asee.android.template.domain.usecase.offering.impl.DeleteOfferingUseCaseImpl
import hr.asee.android.template.domain.usecase.offering.impl.GetOfferingsForGiverUseCaseImpl
import hr.asee.android.template.domain.usecase.offering.impl.GetOffersUseCaseImpl
import hr.asee.android.template.domain.usecase.onboarding.GetIsOnboardingCompleteUseCase
import hr.asee.android.template.domain.usecase.onboarding.SetOnboardingCompleteUseCase
import hr.asee.android.template.domain.usecase.onboarding.impl.GetIsOnboardingCompleteUseCaseImpl
import hr.asee.android.template.domain.usecase.onboarding.impl.SetOnboardingCompleteUseCaseImpl
import hr.asee.android.template.domain.usecase.parkingspace.AddParkingSpaceUseCase
import hr.asee.android.template.domain.usecase.parkingspace.ChangeParkingLocationUseCase
import hr.asee.android.template.domain.usecase.parkingspace.GetParkingSpaceByIdUseCase
import hr.asee.android.template.domain.usecase.parkingspace.GetParkingSpaceForGiverUseCase
import hr.asee.android.template.domain.usecase.parkingspace.GetParkingSpacesUseCase
import hr.asee.android.template.domain.usecase.parkingspace.impl.AddParkingSpaceUseCaseImpl
import hr.asee.android.template.domain.usecase.parkingspace.impl.ChangeParkingLocationUseCaseImpl
import hr.asee.android.template.domain.usecase.parkingspace.impl.GetParkingSpaceByIdUseCaseImpl
import hr.asee.android.template.domain.usecase.parkingspace.impl.GetParkingSpaceForGiverUseCaseImpl
import hr.asee.android.template.domain.usecase.parkingspace.impl.GetParkingSpacesUseCaseImpl
import hr.asee.android.template.domain.usecase.reservation.GetReservationsForGiverUseCase
import hr.asee.android.template.domain.usecase.reservation.GetReservationsForSeekerUseCase
import hr.asee.android.template.domain.usecase.reservation.GetReservationsUseCase
import hr.asee.android.template.domain.usecase.reservation.PutReservationByIdUseCase
import hr.asee.android.template.domain.usecase.reservation.impl.GetReservationsForGiverUseCaseImpl
import hr.asee.android.template.domain.usecase.reservation.impl.GetReservationsForSeekerUseCaseImpl
import hr.asee.android.template.domain.usecase.reservation.impl.GetReservationsUseCaseImpl
import hr.asee.android.template.domain.usecase.reservation.impl.PutReservationByIdUseCaseImpl
import hr.asee.android.template.domain.usecase.seeking.GetSeekingsForSeekerUseCase
import hr.asee.android.template.domain.usecase.seeking.GetSeekingsUseCase
import hr.asee.android.template.domain.usecase.seeking.impl.GetSeekingsForSeekerUseCaseImpl
import hr.asee.android.template.domain.usecase.seeking.impl.GetSeekingsUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.RegisterUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

	@Provides
	@ViewModelScoped
	fun provideSetOnboardingCompleteUseCase(onboardingRepository: OnboardingRepository): SetOnboardingCompleteUseCase {
		return SetOnboardingCompleteUseCaseImpl(onboardingRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetIsOnboardingCompleteUseCase(onboardingRepository: OnboardingRepository): GetIsOnboardingCompleteUseCase {
		return GetIsOnboardingCompleteUseCaseImpl(onboardingRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideIsLoginActiveUseCase(authenticationRepository: AuthenticationRepository): IsLoginActiveUseCase {
		return IsLoginActiveUseCaseImpl(authenticationRepository)
	}

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
    fun provideRegisterUseCase(authenticationRepository: AuthenticationRepository): RegisterUseCase {
        return RegisterUseCaseImpl(authenticationRepository = authenticationRepository)
    }
	@Provides
	@ViewModelScoped
	fun provideLoginUseCase(authenticationRepository: AuthenticationRepository): LoginUseCase {
		return LoginUseCaseImpl(authenticationRepository = authenticationRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideLogoutUseCase(authenticationRepository: AuthenticationRepository): LogoutUseCase {
		return LogoutUseCaseImpl(authenticationRepository)
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
	fun provideGetSeekingsForSeekerUseCase(seekingRepository: SeekingRepository): GetSeekingsForSeekerUseCase {
		return GetSeekingsForSeekerUseCaseImpl(seekingRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetOffersUseCase(offerRepository: OfferRepository): GetOffersUseCase {
		return GetOffersUseCaseImpl(offerRepository = offerRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideAddOfferingUseCase(offerRepository: OfferRepository): AddOfferingUseCase {
		return AddOfferingUseCaseImpl(offerRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideDeleteOfferingUseCase(offerRepository: OfferRepository): DeleteOfferingUseCase {
		return DeleteOfferingUseCaseImpl(offerRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetOfferingsForGiverUseCase(offerRepository: OfferRepository): GetOfferingsForGiverUseCase {
		return GetOfferingsForGiverUseCaseImpl(offerRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetReservationsUseCase(reservationRepository: ReservationRepository): GetReservationsUseCase {
		return GetReservationsUseCaseImpl(reservationRepository = reservationRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetReservationsForGiverUseCase(reservationRepository: ReservationRepository): GetReservationsForGiverUseCase {
		return GetReservationsForGiverUseCaseImpl(reservationRepository = reservationRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetReservationsForSeekerUseCase(reservationRepository: ReservationRepository): GetReservationsForSeekerUseCase {
		return GetReservationsForSeekerUseCaseImpl(reservationRepository)
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
	fun provideGetParkingSpaceForGiverUseCase(parkingSpaceRepository: ParkingSpaceRepository): GetParkingSpaceForGiverUseCase {
		return GetParkingSpaceForGiverUseCaseImpl(parkingSpaceRepository = parkingSpaceRepository)
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
