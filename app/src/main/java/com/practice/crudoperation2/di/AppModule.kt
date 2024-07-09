package com.practice.crudoperation2.di

import android.content.Context
import androidx.room.Room
import com.practice.crudoperation2.MyApp
import com.practice.crudoperation2.data.dao.ContactDao
import com.practice.crudoperation2.data.database.ContactDatabase
import com.practice.crudoperation2.data.repository.ContactRepositoryImpl
import com.practice.crudoperation2.domain.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContactDatabase(@ApplicationContext context: Context): ContactDatabase {
        return  Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contact_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideContactDao(db: ContactDatabase): ContactDao {
        return db.dao()
    }

    @Singleton
    @Provides
    fun provideContactRepository(db: ContactDatabase): ContactRepository{
        return ContactRepositoryImpl(db.dao())
    }

}