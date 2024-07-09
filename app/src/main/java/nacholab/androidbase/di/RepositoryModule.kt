package nacholab.androidbase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nacholab.androidbase.domain.MainRepository
import nacholab.androidbase.domain.MainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun repositoryBinding(mainRepositoryImpl: MainRepositoryImpl): MainRepository

}