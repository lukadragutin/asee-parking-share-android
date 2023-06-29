package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.interactor.GetUserByLoginInteractor
import hr.asee.android.template.data.model.remote.response.ApiUserCompact
import hr.asee.android.template.domain.mapper.UserCompactMapper
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.model.common.Seeker
import hr.asee.android.template.domain.model.common.User
import kotlinx.coroutines.runBlocking

class UserCompactMapperImpl(
    private val getUserByLoginInteractor: GetUserByLoginInteractor,
    private val userMapper: UserMapper
) : UserCompactMapper {

    override fun toUser(apiUserCompact: ApiUserCompact): User {
        return runBlocking { userMapper.toUser(getUserByLoginInteractor(apiUserCompact.login)) }
    }

    override fun toApiUserCompact(user: User): ApiUserCompact {
        return ApiUserCompact(
            id = user.id,
            login = user.firstName + user.lastName,
            role = if (user is Seeker) "seeker" else "giver"
        )
    }
}