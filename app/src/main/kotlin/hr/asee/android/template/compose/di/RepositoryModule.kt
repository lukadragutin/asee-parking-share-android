package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.repository.NavigationItemsRepository
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
}
