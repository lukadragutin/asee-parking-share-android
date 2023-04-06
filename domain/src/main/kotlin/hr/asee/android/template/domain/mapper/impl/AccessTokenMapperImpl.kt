package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.model.common.AccessToken

class AccessTokenMapperImpl : AccessTokenMapper {

    override fun toAccessToken(apiToken: ApiAccessToken): AccessToken {
        return AccessToken(
            value = apiToken.accessToken
        )
    }
}
