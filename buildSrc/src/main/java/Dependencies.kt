object AndroidX {
    const val ACTIVITY = "androidx.activity:activity-ktx:1.1.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"

    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VIEWMODEL_KTX}"

    const val CONSTRAINT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT}"
}

object TestDependencies {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"

}

object Google {
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
}

object ThirdParty {
    const val COIL = "io.coil-kt:coil:${Versions.COIL}"
}

object Jakewharton {
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
}