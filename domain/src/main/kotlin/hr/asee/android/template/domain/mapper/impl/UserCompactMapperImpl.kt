package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiUserCompact
import hr.asee.android.template.domain.mapper.UserCompactMapper
import hr.asee.android.template.domain.model.common.Role
import hr.asee.android.template.domain.model.common.UserCompact

class UserCompactMapperImpl: UserCompactMapper {

	override fun toUser(apiUserCompact: ApiUserCompact?): UserCompact {
		return if (apiUserCompact == null) {
			UserCompact.EMPTY
		} else {
			UserCompact(id = apiUserCompact.id,
						login = apiUserCompact.login,
						role = apiUserCompact.role?.let { Role.fromString(it) } ?: Role.SEEKER)
		}
	}

	override fun toApiUserCompact(user: UserCompact): ApiUserCompact {
		return ApiUserCompact(
			id = user.id,
			login = user.login,
			role = user.role.value
		)
	}
}