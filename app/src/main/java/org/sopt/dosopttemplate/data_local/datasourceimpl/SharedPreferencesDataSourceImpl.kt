package org.sopt.dosopttemplate.data_local.datasourceimpl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import org.sopt.dosopttemplate.data.datasource.SharedPreferenceDataSource
import org.sopt.dosopttemplate.domain.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPreferenceDataSource {

    private val gson = GsonBuilder().create()

    override var userInformation: UserEntity?
        get() = gson.fromJson(
            sharedPreferences.getString(USER_INFORMATION, null), UserEntity::class.java
        )
        set(value) = sharedPreferences.edit { putString(USER_INFORMATION, gson.toJson(value)) }

    override var checkSignIn: Boolean
        get() = sharedPreferences.getBoolean(CHECK_SIGN_IN, false)
        set(value) = sharedPreferences.edit { putBoolean(CHECK_SIGN_IN, value) }

    override fun removeUserInformation() {
        sharedPreferences.edit { remove(USER_INFORMATION) }
    }

    companion object {
        const val CHECK_SIGN_IN = "CheckSignIn"
        const val USER_INFORMATION = "UserInformation"
    }
}