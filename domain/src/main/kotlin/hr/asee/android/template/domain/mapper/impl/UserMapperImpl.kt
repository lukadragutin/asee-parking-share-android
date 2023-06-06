package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiUser
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.Seeker
import hr.asee.android.template.domain.model.common.User
import java.time.LocalDateTime

class UserMapperImpl : UserMapper {

    override fun toUser(apiUser: ApiUser): User {
        if (apiUser.role == "seeker")
            return Seeker(
                id = apiUser.id,
                firstName = apiUser.firstName,
                lastName = apiUser.lastName,
                email = apiUser.email,
                activated = apiUser.activated,
                langKey = apiUser.langKey,
                createdBy = apiUser.createdBy,
                createdDate = LocalDateTime.parse(apiUser.createdDate),
                lastModifiedBy = apiUser.lastModifiedBy,
                lastModifiedDate = LocalDateTime.parse(apiUser.lastModifiedDate),
                authorities = apiUser.authorities
                )
        else
            return Giver(
                id = apiUser.id,
                firstName = apiUser.firstName,
                lastName = apiUser.lastName,
                email = apiUser.email,
                activated = apiUser.activated,
                langKey = apiUser.langKey,
                createdBy = apiUser.createdBy,
                createdDate = LocalDateTime.parse(apiUser.createdDate),
                lastModifiedBy = apiUser.lastModifiedBy,
                lastModifiedDate = LocalDateTime.parse(apiUser.lastModifiedDate),
                authorities = apiUser.authorities
            )
    }
}