package com.example.nots_app.reposatory

import androidx.room.Query
import com.example.nots_app.database.NoteDatabase
import com.example.nots_app.model.Note

class NoteReposatory(private val db:NoteDatabase) {
    suspend fun insertNote(note: Note)=db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note)=db.getNoteDao().deleteNotes(note)
    suspend fun updateNote(note: Note)=db.getNoteDao().updateNote(note)

    fun  getAllNotes()=db.getNoteDao().getAllNotes()
    fun  searchNote(query: String?)=db.getNoteDao().searchNote(query)

}