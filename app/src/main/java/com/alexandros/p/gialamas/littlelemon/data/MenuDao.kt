package com.alexandros.p.gialamas.littlelemon.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(vararg menu: MenuItemEntity)

    @Query("SELECT * FROM MenuItemEntity")
    fun getMenu(): LiveData<List<MenuItemEntity>>
}