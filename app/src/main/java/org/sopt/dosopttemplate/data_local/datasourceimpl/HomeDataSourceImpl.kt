package org.sopt.dosopttemplate.data_local.datasourceimpl

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.sopt.dosopttemplate.data.datasource.HomeDataSource
import org.sopt.dosopttemplate.data.dto.MockProfileDto
import org.sopt.dosopttemplate.data.dto.MyProfileDto
import org.sopt.dosopttemplate.features.util.AssetLoader
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val assetLoader: AssetLoader
) : HomeDataSource {
    override fun getProfile(): MockProfileDto {
        return assetLoader.getJsonString(MOCK_PROFILE_DATA)?.let { jsonString ->
            Json.decodeFromString<MockProfileDto>(jsonString)
        } ?: MockProfileDto(
            MyProfileDto("", ""),
            emptyList(), emptyList()
        )
    }

    companion object {
        const val MOCK_PROFILE_DATA = "profile.json"
    }
}