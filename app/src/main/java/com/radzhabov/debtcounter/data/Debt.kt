package com.radzhabov.debtcounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Debt(
    @PrimaryKey val id:Int? = null,
    val name: String,
    val price: String,
    val description: String?,
    val isDone: Boolean,
)
