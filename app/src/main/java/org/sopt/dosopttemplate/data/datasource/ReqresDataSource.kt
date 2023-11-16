package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.dto.BaseResponseNullable
import org.sopt.dosopttemplate.data.dto.response.ResponseListUsers

interface ReqresDataSource {

    suspend fun getListUsers(page: Int): BaseResponseNullable<List<ResponseListUsers>>
}