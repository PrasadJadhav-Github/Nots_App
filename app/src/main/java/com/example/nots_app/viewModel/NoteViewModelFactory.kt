package com.example.nots_app.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nots_app.reposatory.NoteReposatory

class NoteViewModelFactory(val app:Application ,private val noteReposatory: NoteReposatory):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  NoteViewModel(app,noteReposatory) as T
    }

}