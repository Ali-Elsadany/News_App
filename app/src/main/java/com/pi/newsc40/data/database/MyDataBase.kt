package com.pi.newsc40.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pi.newsc40.data.api.model.Source
import com.pi.newsc40.data.database.dao.SourcesDao

@Database(entities = [Source::class], version = 1)
abstract class MyDataBase: RoomDatabase() {

    abstract fun getSourcesDao(): SourcesDao

    companion object{
        const val DATABASE_NAME = "database_name"
        private var myDataBase: MyDataBase? = null

         fun init(context: Context){
             if(myDataBase == null){
                 myDataBase =
                     Room.databaseBuilder(context, MyDataBase::class.java, DATABASE_NAME)
                         .fallbackToDestructiveMigration()
                       //  .allowMainThreadQueries() No need for this since we are kotlin coroutines
                         .build()
             }
         }
         fun getInstance(): MyDataBase = myDataBase!!
    }
}