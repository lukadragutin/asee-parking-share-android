package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.interactor.impl.StoreAccessTokenInteractorImpl
import hr.asee.android.template.data.local.storage.ApplicationStorage
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class StoreAccessTokenInteractorTest {

    lateinit var applicationStorage: ApplicationStorage
    lateinit var storeAccessTokenInteractor: StoreAccessTokenInteractor

    @BeforeEach
    fun setUp() {
        applicationStorage = mock(ApplicationStorage::class.java)
        storeAccessTokenInteractor = StoreAccessTokenInteractorImpl(applicationStorage)
    }

    @Test
    fun `invoke should save access to application storage when invoked`(): Unit = runBlocking {
        // Arrange
        val key = "accessToken"
        val token = "accessToken"

        // Act
        storeAccessTokenInteractor(key = key, accessToken = token)

        // Assert
        verify(applicationStorage).saveString(key = key, value = token)
    }
}
