package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.RegisterInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.data.interactor.impl.LoginInteractorImpl
import hr.asee.android.template.data.interactor.impl.RegisterInteractorImpl
import hr.asee.android.template.data.interactor.impl.StoreAccessTokenInteractorImpl
import hr.asee.android.template.data.local.storage.ApplicationStorage
import hr.asee.android.template.data.network.ReqresApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object InteractorModule {

    @Provides
    @ViewModelScoped
    fun provideLoginInteractor(retrofit: Retrofit, reqResServiceErrorResolver: ReqResServiceErrorResolver): LoginInteractor = LoginInteractorImpl(
        reqresApiService = retrofit.create(ReqresApiService::class.java),
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
    fun provideStoreAccessTokenInteractor(applicationStorage: ApplicationStorage): StoreAccessTokenInteractor =
        StoreAccessTokenInteractorImpl(applicationStorage = applicationStorage)
}
