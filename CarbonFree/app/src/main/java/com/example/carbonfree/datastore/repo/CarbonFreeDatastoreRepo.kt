package com.example.carbonfree.datastore.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.carbonfree.datastore.model.CarbonFreeDataStore
import com.example.carbonfree.datastore.model.CarbonFreeDatastoreInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DataStore_NAME = "Carbon_Free_DataStore"
val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DataStore_NAME)


class CarbonFreeDatastoreRepo(private val context: Context) : CarbonFreeDatastoreInterface {

    companion object {
        val FINISHED = booleanPreferencesKey("FINISHED")

    }

    override suspend fun saveDataStore(carbonFreeDataStore: CarbonFreeDataStore) {
        context.datastore.edit { ds ->
            ds[FINISHED] = carbonFreeDataStore.finished

        }
    }

    override suspend fun getDataStore(): Flow<CarbonFreeDataStore> =
        context.datastore.data.map { ds ->
            CarbonFreeDataStore(
                finished = ds[FINISHED] ?: false
            )

        }

}