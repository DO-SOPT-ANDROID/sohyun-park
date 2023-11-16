package org.sopt.dosopttemplate.data_remote.api

import org.sopt.dosopttemplate.data.dto.BaseResponseNullable
import org.sopt.dosopttemplate.data.dto.response.ResponseListUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresApiService {

    @GET("/api/users")
    suspend fun getListUsers(
        @Query("page") page: Int
    ): BaseResponseNullable<List<ResponseListUsers>>
}