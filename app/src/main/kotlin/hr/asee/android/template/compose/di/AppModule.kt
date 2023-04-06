package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.delegate.impl.BottomNavBarDelegateImpl
import hr.asee.android.template.compose.delegate.NavigationDelegate
import hr.asee.android.template.compose.delegate.impl.NavigationDelegateImpl
import hr.asee.android.template.compose.navigation.Router
import hr.asee.android.template.compose.navigation.impl.RouterImpl
import hr.asee.android.template.compose.util.log.Logger
import hr.asee.android.template.compose.util.log.impl.LoggerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNavigationDelegate(): NavigationDelegate = NavigationDelegateImpl()

    @Provides
    @Singleton
    fun provideBottomNavBarDelegate(): BottomNavBarDelegate = BottomNavBarDelegateImpl()

    @Provides
    @Singleton
    fun provideRouter(navigationDelegate: NavigationDelegate, bottomNavBarDelegate: BottomNavBarDelegate): Router =
        RouterImpl(navigationDelegate = navigationDelegate, bottomNavBarDelegate = bottomNavBarDelegate)

    @Provides
    @Singleton
    fun provideLogger(): Logger {
        return LoggerImpl()
    }
}
