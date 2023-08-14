package com.ncs.authenticationapp.di

import com.ncs.authenticationapp.firebaseauth.repository.AuthRepository
import com.ncs.authenticationapp.firebaseauth.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesFirebaseAuthRepository(
        repo:AuthRepositoryImpl
    ):AuthRepository
}