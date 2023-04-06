package hr.asee.android.template.data.resolver

interface ReqResServiceErrorResolver {

    fun toException(jsonError: String): Exception
}
