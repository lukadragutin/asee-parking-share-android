package hr.asee.android.template.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.mapper.impl.AccessTokenMapperImpl

@Module
@InstallIn(ViewModelComponent::class)
object MapperModule {

    @Provides
    @ViewModelScoped
    fun provideAccessTokenMapper(): AccessTokenMapper = AccessTokenMapperImpl()
}
