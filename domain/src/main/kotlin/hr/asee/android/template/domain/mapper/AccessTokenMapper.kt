package hr.asee.android.template.domain.mapper

import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import hr.asee.android.template.domain.model.common.AccessToken

interface AccessTokenMapper {
    fun toAccessToken(apiToken: ApiAccessToken): AccessToken
}
