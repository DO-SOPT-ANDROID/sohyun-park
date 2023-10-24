package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.dto.MockProfileDto

interface HomeDataSource {
    fun getProfile(): MockProfileDto
}