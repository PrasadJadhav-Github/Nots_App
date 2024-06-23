package com.example.nots_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesroompractice.R
import com.example.nots_app.database.NoteDatabase
import com.example.nots_app.reposatory.NoteReposatory
import com.example.nots_app.viewModel.NoteViewModel
import com.example.nots_app.viewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var  noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()
    }
    private fun setViewModel(){
        val noteRepository=NoteReposatory(NoteDatabase(this))
        val viewModelProviderFactory=NoteViewModelFactory(application,noteRepository)
        noteViewModel=ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]
    }
}