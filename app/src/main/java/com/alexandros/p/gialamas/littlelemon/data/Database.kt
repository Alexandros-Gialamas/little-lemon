package com.alexandros.p.gialamas.littlelemon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuItemEntity::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {
        private lateinit var INSTANCE: MenuDatabase

        fun getDatabase(context: Context): MenuDatabase {
            synchronized(MenuDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(context, MenuDatabase::class.java, "menu-db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}

