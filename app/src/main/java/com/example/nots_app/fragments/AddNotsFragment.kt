package com.example.nots_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.notesroompractice.R
import com.example.notesroompractice.databinding.FragmentAddNotsBinding
import com.example.nots_app.MainActivity
import com.example.nots_app.model.Note
import com.example.nots_app.viewModel.NoteViewModel


class AddNotsFragment : Fragment(R.layout.fragment_add_nots ) ,MenuProvider{

    private  var addNoteBinding: FragmentAddNotsBinding?=null
    private  val binding get()=addNoteBinding!!
    private lateinit var  notesViewModel:NoteViewModel
    private  lateinit var addNoteView:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addNoteBinding=FragmentAddNotsBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
        notesViewModel=(activity as MainActivity).noteViewModel
        addNoteView=view
    }
    private  fun saveNotes(view: View){
        val noteTitle =binding.addNoteTitle.text.toString().trim()
        val noteDescription=binding.addNoteDesc.text.toString().trim()

        if (noteTitle.isNotEmpty()){
            val note =Note(0,noteTitle,noteDescription)
            notesViewModel.addNote(note)

            Toast.makeText(addNoteView.context,"Note Saved",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)
        }else{
            Toast.makeText(addNoteView.context,"Please enter note title",Toast.LENGTH_SHORT).show()

        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.add_nots_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return  when(menuItem.itemId){
            R.id.searchMenu->{
                saveNotes(addNoteView)
                true
            }else->
                false

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding=null
    }

}