package hr.asee.android.template.domain.model.common.resource

data class ErrorData(
    val errorType: ErrorType? = null,
    val throwable: Throwable?
)
