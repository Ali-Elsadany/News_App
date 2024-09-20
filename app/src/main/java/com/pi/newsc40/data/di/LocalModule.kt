package com.pi.newsc40.data.di

import android.content.Context
import androidx.room.Room
import com.pi.newsc40.data.database.MyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context{
        return context;
    }

    @Provides
    @Singleton
    fun provideDataBase(context: Context):MyDataBase{
        return Room.databaseBuilder(context, MyDataBase::class.java, MyDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            //  .allowMainThreadQueries() No need for this since we are kotlin coroutines
            .build()
    }
}