package hr.asee.android.template.domain.mapper

import hr.asee.android.template.data.model.remote.response.ApiUserCompact
import hr.asee.android.template.domain.model.common.User

interface UserCompactMapper {

    fun toUser(apiUserCompact: ApiUserCompact): User

    fun toApiUserCompact(user: User): ApiUserCompact
}