package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.repository.NavigationItemsRepository
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import hr.asee.android.template.domain.usecase.LoginUseCase
import hr.asee.android.template.domain.usecase.impl.GetAllBottomNavItemsUseCaseImpl
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
}
