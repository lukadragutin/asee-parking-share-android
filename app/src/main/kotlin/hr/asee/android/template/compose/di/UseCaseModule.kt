package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.domain.repository.AccountRepository
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.repository.NavigationItemsRepository
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import hr.asee.android.template.domain.usecase.FilterByDateUseCase
import hr.asee.android.template.domain.usecase.GetAccountUseCase
import hr.asee.android.template.domain.usecase.GetOffersUseCase
import hr.asee.android.template.domain.usecase.GetParkingSpacesUseCase
import hr.asee.android.template.domain.usecase.GetReservationsUseCase
import hr.asee.android.template.domain.usecase.GetSeekingsUseCase
import hr.asee.android.template.domain.usecase.LoginUseCase
import hr.asee.android.template.domain.usecase.impl.GetAllBottomNavItemsUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.FilterByDateUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetAccountUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetOffersUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetParkingSpacesUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetReservationsUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.GetSeekingsUseCaseImpl
import hr.asee.android.template.domain.usecase.impl.LoginUseCaseImpl

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
    fun provideGetAccountUseCase(accountRepository: AccountRepository): GetAccountUseCase {
        return GetAccountUseCaseImpl(accountRepository = accountRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideFilterByDateUseCase(): FilterByDateUseCase {
        return FilterByDateUseCaseImpl()
    }

    @Provides
    @ViewModelScoped
    fun provideGetSeekingsUseCase(accountRepository: AccountRepository): GetSeekingsUseCase {
        return GetSeekingsUseCaseImpl(accountRepository = accountRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetOffersUseCase(accountRepository: AccountRepository): GetOffersUseCase {
        return GetOffersUseCaseImpl(accountRepository = accountRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetReservationsUseCase(accountRepository: AccountRepository): GetReservationsUseCase {
        return GetReservationsUseCaseImpl(accountRepository = accountRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetParkingSpacesUseCase(accountRepository: AccountRepository): GetParkingSpacesUseCase {
        return GetParkingSpacesUseCaseImpl(accountRepository = accountRepository)
    }
}
