package io.korostenskyi.chestnut.extensions

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import io.korostenskyi.chestnut.di.DiNames.SETTINGS_DATA_STORE

val Context.dataStore by preferencesDataStore(
    name = SETTINGS_DATA_STORE
)
