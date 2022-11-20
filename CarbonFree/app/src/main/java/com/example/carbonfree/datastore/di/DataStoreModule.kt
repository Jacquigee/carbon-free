package com.example.carbonfree.datastore.di

import android.content.Context
import com.example.carbonfree.datastore.repo.CarbonFreeDatastoreRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context)= CarbonFreeDatastoreRepo(context)

}