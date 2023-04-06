package hr.asee.android.template.compose.util

import org.mockito.Mockito

object MockitoHelper {
    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    fun <T> eq(obj: T): T = Mockito.eq(obj)

    fun <T> isA(clazz: Class<T>): T = Mockito.isA(clazz)

    @Suppress("UNCHECKED_CAST")
    fun <T> uninitialized(): T = null as T
}
