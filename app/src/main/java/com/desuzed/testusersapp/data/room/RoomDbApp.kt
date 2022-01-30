package com.desuzed.testusersapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.desuzed.testusersapp.User

@Database(entities = [UserDTO::class], version = 1)
abstract class RoomDbApp : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDbApp? = null

        fun getDatabase(context: Context): RoomDbApp {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDbApp::class.java,
                    "room_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}