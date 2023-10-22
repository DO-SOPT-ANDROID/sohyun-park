package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.domain.entity.UserEntity


interface SharedPreferenceDataSource {
    var userInformation: UserEntity?
    var checkSignIn: Boolean
}