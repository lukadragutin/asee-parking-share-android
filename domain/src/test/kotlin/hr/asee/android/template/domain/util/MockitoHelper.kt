package hr.asee.android.template.domain.util

import org.mockito.Mockito

object MockitoHelper {
    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

    @Suppress("UNCHECKED_CAST")
    fun <T> uninitialized(): T = null as T
}
