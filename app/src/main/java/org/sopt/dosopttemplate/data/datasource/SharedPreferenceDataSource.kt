package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.domain.entity.User


interface SharedPreferenceDataSource {
    var userInformation: User
    var checkSignIn: Boolean
}