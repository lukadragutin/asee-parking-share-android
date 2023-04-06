package hr.asee.android.template.data.local.storage

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import hr.asee.android.template.data.local.storage.impl.ApplicationStorageImpl
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationStorageTest {

    companion object {
        private const val KEY_TEST_DATA = "applicationStorageTestDataKey"
        private val Context.dataStore by preferencesDataStore("testDataStore")
        private val context = InstrumentationRegistry.getInstrumentation().context
        private val applicationStorage = ApplicationStorageImpl(context.dataStore)
    }

    @Before
    fun setUp(): Unit = runBlocking {
        if (applicationStorage.containsKey(KEY_TEST_DATA)) {
            applicationStorage.deleteValue(KEY_TEST_DATA)
        }
    }

    @Test
    fun containsKeyShouldNotFindKeyWhenKeyDoesNotExist(): Unit = runBlocking {
        // Given

        // When
        val doesKeyExist = applicationStorage.containsKey(KEY_TEST_DATA)

        // Then
        Assertions.assertThat(doesKeyExist).isFalse
    }

    @Test
    fun containsKeyShouldFindKeyWhenKeyExists(): Unit = runBlocking {
        // Given
        applicationStorage.saveString(KEY_TEST_DATA, "The implementation of applications storage is using data store.")

        // When
        val doesKeyExists = applicationStorage.containsKey(KEY_TEST_DATA)

        // Then
        Assertions.assertThat(doesKeyExists).isTrue
    }

    @Test
    fun saveStringShouldSaveStringAndGetStringShouldReturnSavedStringWhenInvoked(): Unit = runBlocking {
        // Given
        val data = "Data store very good, yes?"

        // When
        applicationStorage.saveString(KEY_TEST_DATA, data)
        val storageValueExists = applicationStorage.containsKey(KEY_TEST_DATA)
        val storageValue = applicationStorage.getString(KEY_TEST_DATA)

        // Then
        Assertions.assertThat(storageValueExists).isTrue
        Assertions.assertThat(storageValue).isEqualTo(data)
    }

    @Test
    fun saveStringShouldOverridePreviouslySavedStringWhenInvokedAfterSavingValueWithTheSameKey(): Unit = runBlocking {
        // Given
        val data = "More strings."
        val overrideData = "Some lorem ipsum."

        // When
        applicationStorage.saveString(KEY_TEST_DATA, data)
        applicationStorage.saveString(KEY_TEST_DATA, overrideData)
        val storageData = applicationStorage.getString(KEY_TEST_DATA)

        // Then
        Assertions.assertThat(storageData).isEqualTo(overrideData)
    }

    @Test
    fun deleteStringShouldDeleteValueWhenValueWasPreviouslySaved(): Unit = runBlocking {
        // Given
        applicationStorage.saveString(KEY_TEST_DATA, "We will go back someday.")

        // When
        val valueExistsBeforeDeleting = applicationStorage.containsKey(KEY_TEST_DATA)
        applicationStorage.deleteValue(KEY_TEST_DATA)
        val valueExistsAfterDeleting = applicationStorage.containsKey(KEY_TEST_DATA)

        // Then
        Assertions.assertThat(valueExistsBeforeDeleting).isTrue
        Assertions.assertThat(valueExistsAfterDeleting).isFalse
    }

    @After
    fun cleanUp(): Unit = runBlocking {
        if (applicationStorage.containsKey(KEY_TEST_DATA)) {
            applicationStorage.deleteValue(KEY_TEST_DATA)
        }
    }
}
