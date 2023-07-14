package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiUser
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.model.common.Admin
import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.Seeker
import hr.asee.android.template.domain.model.common.User
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime

class UserMapperImpl : UserMapper {

	override fun toUser(apiUser: ApiUser): User {
		return when (apiUser.role) {
			"seeker" -> {
				Seeker(id = apiUser.id,
					   firstName = apiUser.firstName,
					   lastName = apiUser.lastName,
					   email = apiUser.email,
					   activated = apiUser.activated,
					   langKey = apiUser.langKey,
					   createdBy = apiUser.createdBy,
					   createdDate = apiUser.createdDate?.let { ZonedDateTime.parse(it).toLocalDateTime() } ?: LocalDateTime.now(),
					   lastModifiedBy = apiUser.lastModifiedBy,
					   lastModifiedDate = apiUser.lastModifiedDate?.let { ZonedDateTime.parse(it).toLocalDateTime() } ?: LocalDateTime.now(),
					   authorities = apiUser.authorities)
			}
			"giver" -> {
				Giver(id = apiUser.id,
					  firstName = apiUser.firstName,
					  lastName = apiUser.lastName,
					  email = apiUser.email,
					  activated = apiUser.activated,
					  langKey = apiUser.langKey,
					  createdBy = apiUser.createdBy,
					  createdDate = apiUser.createdDate?.let { ZonedDateTime.parse(it).toLocalDateTime() } ?: LocalDateTime.now(),
					  lastModifiedBy = apiUser.lastModifiedBy,
					  lastModifiedDate = apiUser.lastModifiedDate?.let { ZonedDateTime.parse(it).toLocalDateTime() } ?: LocalDateTime.now(),
					  authorities = apiUser.authorities)
			}
			else -> {
				return Admin(id = apiUser.id,
							 firstName = apiUser.firstName,
							 lastName = apiUser.lastName,
							 email = apiUser.email,
							 activated = apiUser.activated,
							 langKey = apiUser.langKey,
							 createdBy = apiUser.createdBy,
							 createdDate = apiUser.createdDate?.let { ZonedDateTime.parse(it).toLocalDateTime() } ?: LocalDateTime.now(),
							 lastModifiedBy = apiUser.lastModifiedBy,
							 lastModifiedDate = apiUser.lastModifiedDate?.let { ZonedDateTime.parse(it).toLocalDateTime() } ?: LocalDateTime.now(),
							 authorities = apiUser.authorities)
			}
		}
	}
}