package hr.asee.android.template.domain.mapper

import hr.asee.android.template.data.model.remote.response.ApiUserCompact
import hr.asee.android.template.domain.model.common.UserCompact

interface UserCompactMapper {

    fun toUser(apiUserCompact: ApiUserCompact?): UserCompact

    fun toApiUserCompact(user: UserCompact): ApiUserCompact
}