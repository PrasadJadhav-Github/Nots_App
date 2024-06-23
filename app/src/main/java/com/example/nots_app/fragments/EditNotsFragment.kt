package com.example.nots_app.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.notesroompractice.R
import com.example.notesroompractice.databinding.FragmentEditNotsBinding
import com.example.nots_app.MainActivity
import com.example.nots_app.model.Note
import com.example.nots_app.viewModel.NoteViewModel

class EditNotsFragment : Fragment(R.layout.fragment_edit_nots),MenuProvider {

    private  var editNoteBinding : FragmentEditNotsBinding?=null
    private  val binding get()=editNoteBinding!!

    private  lateinit var  noteViewModel: NoteViewModel
    private  lateinit var  currentNote:Note

    private  val args :EditNotsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        editNoteBinding = FragmentEditNotsBinding.inflate(inflater,container,false)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteViewModel=(activity as MainActivity).noteViewModel
        currentNote = args.note!!
        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteDesc.setText(currentNote.noteDescription)

        binding.editNoteFab.setOnClickListener {
            val noteTitle =binding.editNoteTitle.text.toString().trim()
            val noteDesc =binding.editNoteDesc.text.toString().trim()

            if(noteTitle.isNotEmpty()){
                val note =Note(currentNote.id,noteTitle,noteDesc)
                noteViewModel.uodateNote(note)
                view.findNavController().popBackStack(R.id.homeFragment,false)
            }else{
                Toast.makeText(context,"Please enter note title",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private  fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Do you want to delete this note?")
            setPositiveButton("Delete") { dialog, _ ->
                noteViewModel.deleteNote(currentNote)
                Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment, false)
                dialog.dismiss()
            }
            setNegativeButton("Cancel",null)
            }.create().show()
        }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.edit_nots_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return  when(menuItem.itemId){
            R.id.deleteMenu->{
                deleteNote()
                true
            }else -> false
    }

}

    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null

    }
}