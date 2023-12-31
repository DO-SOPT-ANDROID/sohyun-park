package org.sopt.dosopttemplate.data_remote.api

import org.sopt.dosopttemplate.data.dto.BaseResponseNullable
import org.sopt.dosopttemplate.data.dto.response.ResponseListUsers
import org.sopt.dosopttemplate.data_remote.api.AuthApiService.Companion.API
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresApiService {

    companion object {
        const val USERS = "users"
        const val PAGE = "page"
    }

    @GET("/$API/$USERS")
    suspend fun getListUsers(
        @Query(PAGE) page: Int
    ): BaseResponseNullable<List<ResponseListUsers>>
}