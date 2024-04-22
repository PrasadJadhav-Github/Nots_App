package com.example.nots_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.nots_app.model.Note

@Dao
interface NoteDao {
    @Query("select * from notes order by id desc")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun  updateNote(note: Note)

    @Delete
    suspend fun  deleteNotes(note: Note)

    @Query("select * from notes where noteTitle like :query or noteDescription like :query")
    fun searchNote(query: String?): LiveData<List<Note>>


}