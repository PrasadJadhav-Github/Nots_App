package com.example.nots_app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.nots_app.model.Note
import com.example.nots_app.reposatory.NoteReposatory
import kotlinx.coroutines.launch

class NoteViewModel(app:Application,private  val  noteReposatory: NoteReposatory):AndroidViewModel(app) {

    fun addNote(note: Note)=
        viewModelScope.launch {
            noteReposatory.insertNote(note)
        }


    fun uodateNote(note: Note)=
        viewModelScope.launch {
            noteReposatory.updateNote(note)
        }


    fun deleteNote(note: Note)=
        viewModelScope.launch {
            noteReposatory.deleteNote(note)
        }

        fun getAllNotes()=noteReposatory.getAllNotes()

    fun searchNote(query: String)=
        noteReposatory.searchNote(query)

}
