package com.yongbeom.sstviewmodel.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yongbeom.sstviewmodel.DB.Entities.Todo
import com.yongbeom.sstviewmodel.DB.model.TodoListViewModel
import com.yongbeom.sstviewmodel.R
import com.yongbeom.sstviewmodel.databinding.FragmentMainBinding
import com.yongbeom.sstviewmodel.databinding.ItemTodoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(
            TodoListViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            //nav_todo_graph에 설정한 mainFragment -> editFragment로 이동


            it.findNavController().navigate(R.id.action_mainFragment_to_editFragment3)

        }
    }

}