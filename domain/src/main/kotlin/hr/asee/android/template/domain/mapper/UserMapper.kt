package hr.asee.android.template.domain.mapper

import hr.asee.android.template.data.model.remote.response.ApiUser
import hr.asee.android.template.domain.model.common.User

interface UserMapper {

    fun toUser(apiUser: ApiUser): User
}