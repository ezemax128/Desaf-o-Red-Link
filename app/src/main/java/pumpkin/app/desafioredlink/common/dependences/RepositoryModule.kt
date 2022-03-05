package pumpkin.app.desafioredlink.common.dependences

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pumpkin.app.desafioredlink.data.repository.Repository
import pumpkin.app.desafioredlink.data.repository.RepositoryImpl

@Module
@InstallIn(SingletonComponent::class)

abstract class RepositoryModule {

    @Binds
    abstract fun bindingInterface(repositoryImpl: RepositoryImpl): Repository
}