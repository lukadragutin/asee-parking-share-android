package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.common.AccessToken
import java.util.*

interface AuthenticationRepository {

    suspend fun login(email: String, password: String): AccessToken

    suspend fun register(id : Int,
                         login: String,
                         firstName: String,
                         lastName: String,
                         email: String,
                         password: String,
                         role : String,
                         activated: Boolean,
                         langKey: String,
                         createdBy: String,
                         createdDate: String,
                         lastModifiedBy: String,
                         lastModifiedDate: String,
                         authorities: Array<String>

    ): Unit

    suspend fun storeAccessToken(accessToken: AccessToken)
}
