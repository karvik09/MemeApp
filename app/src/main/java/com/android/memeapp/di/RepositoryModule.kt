package com.android.memeapp.di

import com.android.memeapp.data.repository.IMemeRepository
import com.android.memeapp.data.repository.MemeRepositoryImpl
import com.android.memeapp.data.repository.source.db.DatabaseSourceImpl
import com.android.memeapp.data.repository.source.db.IDatabaseSource
import com.android.memeapp.data.repository.source.network.INetworkSource
import com.android.memeapp.data.repository.source.network.NetworkSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(
        repository: MemeRepositoryImpl
    ): IMemeRepository

    @Singleton
    @Binds
    abstract fun bindNetworkSource(
        source: NetworkSourceImpl
    ): INetworkSource

    @Singleton
    @Binds
    abstract fun bindDatabaseSource(
        source: DatabaseSourceImpl
    ): IDatabaseSource
}