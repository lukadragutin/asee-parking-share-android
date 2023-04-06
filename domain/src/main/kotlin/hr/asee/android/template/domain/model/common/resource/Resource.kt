package hr.asee.android.template.domain.model.common.resource

typealias EmptyResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val errorData: ErrorData? = null) {

    class Success<T>(data: T) : Resource<T>(data = data) {
        companion object {
            fun empty(): Resource<Unit> = Success(Unit)
        }
    }

    class Error<T> : Resource<T> {
        constructor(errorData: ErrorData) : super(errorData = errorData)
        constructor(errorType: ErrorType? = null, throwable: Throwable? = null) : this(ErrorData(errorType, throwable))
    }

    suspend fun onFinished(successCallback: suspend (T) -> Unit, errorCallback: (ErrorData) -> Unit) {
        when {
            data != null -> successCallback(data)
            errorData != null -> errorCallback(errorData)
        }
    }

    suspend fun onFinished(successCallback: suspend (T) -> Unit, errorCallback: () -> Unit) {
        onFinished(successCallback = successCallback, errorCallback = { _ -> errorCallback() })
    }

    suspend fun onFinished(successCallback: suspend () -> Unit, errorCallback: (ErrorData) -> Unit) {
        onFinished(successCallback = { _ -> successCallback() }, errorCallback = errorCallback)
    }

    suspend fun onSuccess(successCallback: suspend (T) -> Unit) {
        if (data != null) {
            successCallback(data)
        }
    }
}
