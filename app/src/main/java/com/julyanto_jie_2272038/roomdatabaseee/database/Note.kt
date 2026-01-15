package com.julyanto_jie_2272038.roomdatabaseee.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabel_catatan")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val judul: String,
    val isi: String
)
