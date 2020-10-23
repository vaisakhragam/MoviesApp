package com.example.moviesapplication.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.moviesapplication.data.local.db.dao.MoviesDAO
import com.example.moviesapplication.data.local.db.entities.Movies
import com.example.moviesapplication.utils.DATABASE_NAME

@Database(entities = [Movies::class], version = 1, exportSchema = false)

abstract class MoviesDB : RoomDatabase() {
    /**
     * Connects the database to the DAO.
     */
    abstract val moviesDAO: MoviesDAO

    companion object {

        @Volatile
        private var INSTANCE: MoviesDB? = null
        var TEST_MODE = false
        fun getInstance(context: Context): MoviesDB {

            synchronized(this) {


                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {




                        instance = Room.databaseBuilder(
                                context.applicationContext,
                                MoviesDB::class.java,
                                DATABASE_NAME
                        )
                                // Wipes and rebuilds instead of migrating if no Migration object.
                                .fallbackToDestructiveMigration()


                                .build()



                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}