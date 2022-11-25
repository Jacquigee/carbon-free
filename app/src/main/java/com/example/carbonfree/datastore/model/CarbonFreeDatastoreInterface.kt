package com.example.carbonfree.datastore.model

import kotlinx.coroutines.flow.Flow

interface CarbonFreeDatastoreInterface {

    suspend fun saveDataStore(carbonFreeDataStore: CarbonFreeDataStore)

    suspend fun getDataStore(): Flow<CarbonFreeDataStore>

}