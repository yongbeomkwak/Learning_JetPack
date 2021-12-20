package com.yongbeom.sstviewmodel.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.yongbeom.sstviewmodel.DB.Entities.Todo
import com.yongbeom.sstviewmodel.DB.model.TodoListViewModel
import com.yongbeom.sstviewmodel.R
import com.yongbeom.sstviewmodel.databinding.FragmentEditBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment() {

    private var binding:FragmentEditBinding?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentEditBinding.inflate(layoutInflater,container,false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(TodoListViewModel::class.java)
        binding!!.btnDone.setOnClickListener {
            val  title=binding!!.etTodo.text.toString()
            val date=binding!!.calendarView.date
            viewModel.insert(Todo(title,date))
            it.findNavController().popBackStack()
        }

    }

    override fun onDestroy() {
        binding=null
        super.onDestroy()
    }
}