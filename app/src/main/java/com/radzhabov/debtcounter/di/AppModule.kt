package com.radzhabov.debtcounter.di

import android.app.Application
import androidx.room.Room
import com.radzhabov.debtcounter.data.DebtDatabase
import com.radzhabov.debtcounter.data.DebtRepository
import com.radzhabov.debtcounter.data.DebtRepositoryImpl
import com.radzhabov.debtcounter.data.DeptDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDebtDatabase(app: Application): DebtDatabase =
        Room.databaseBuilder(
            app,
            DebtDatabase::class.java,
            "debt_db"
        ).build()

    @Provides
    @Singleton
    fun provideDebtRepository(db: DebtDatabase): DebtRepository {
        return DebtRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideDeptDao(appDatabase: DebtDatabase): DeptDao =
        appDatabase.dao
}