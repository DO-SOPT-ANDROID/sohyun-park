package org.sopt.dosopttemplate.data_remote.datasourceimpl

import org.sopt.dosopttemplate.data.datasource.ReqresDataSource
import org.sopt.dosopttemplate.data.dto.BaseResponseNullable
import org.sopt.dosopttemplate.data.dto.response.ResponseListUsers
import org.sopt.dosopttemplate.data_remote.api.ReqresApiService
import javax.inject.Inject

class ReqresDataSourceImpl @Inject constructor(
    private val reqresApiService: ReqresApiService
) : ReqresDataSource {
    override suspend fun getListUsers(page: Int): BaseResponseNullable<List<ResponseListUsers>> {
        return reqresApiService.getListUsers(page)
    }
}