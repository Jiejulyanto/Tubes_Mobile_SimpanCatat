package com.julyanto_jie_2272038.roomdatabaseee.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert
    suspend fun tambahCatatan(note: Note)

    @Update
    suspend fun updateCatatan(note: Note)

    @Delete
    suspend fun hapusCatatan(note: Note)

    @Query("SELECT * FROM tabel_catatan ORDER BY id DESC")
    suspend fun ambilSemuaCatatan(): List<Note>
}
