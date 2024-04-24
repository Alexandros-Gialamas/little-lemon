package com.alexandros.p.gialamas.littlelemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)