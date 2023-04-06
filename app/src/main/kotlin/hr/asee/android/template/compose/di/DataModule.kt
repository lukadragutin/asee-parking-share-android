package hr.asee.android.template.compose.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import hr.asee.android.template.compose.BuildConfig
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.data.local.storage.ApplicationStorage
import hr.asee.android.template.data.local.storage.impl.ApplicationStorageImpl
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import hr.asee.android.template.data.resolver.impl.ReqResServiceErrorResolverImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val Context.dataStore by preferencesDataStore(Config.DATA_STORE_PREFERENCES_NAME)

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    @ViewModelScoped
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @ViewModelScoped
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        val reqresRetrofit = Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(Config.REQRES_BASE_URL)

        if (BuildConfig.DEBUG) {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            reqresRetrofit.client(client)
        }

        return reqresRetrofit.build()
    }

    @Provides
    @ViewModelScoped
    fun provideReqResServiceErrorResolver(): ReqResServiceErrorResolver = ReqResServiceErrorResolverImpl()

    @Provides
    @ViewModelScoped
    fun provideApplicationStorage(@ApplicationContext context: Context): ApplicationStorage = ApplicationStorageImpl(dataStorePreferences = context.dataStore)
}
