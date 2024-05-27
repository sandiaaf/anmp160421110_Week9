package com.sandiarta.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sandiarta.todoapp.R
import com.sandiarta.todoapp.databinding.FragmentCreateTodoBinding
import com.sandiarta.todoapp.databinding.FragmentEditTodoBinding
import com.sandiarta.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(), RadioClickListener {
    private lateinit var binding:FragmentEditTodoBinding
    private  lateinit var viewModel: DetailTodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        binding.textViewJudul.text = "Edit Todo"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        binding.btnAdd.setOnClickListener {
            val radioID = binding.radioGroupPriority.checkedRadioButtonId
            val radio = view.findViewById<RadioButton>(radioID)
            val priority = radio.tag.toString().toInt()

            viewModel.update(
                binding.txtTitle.text.toString(),
                binding.txtNotes.text.toString(),
                priority,
                uuid
            )
            Toast.makeText(context,"Todo Update", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
//            binding.txtTitle.setText(it.title)
//            binding.txtNotes.setText(it.title)
//
//            when(it.priority){
//                1 -> binding.radioButtonLow.isChecked = true
//                2 -> binding.radioButtonMedium.isChecked = true
//                else -> binding.radioButtonHigh.isChecked = true
//            }

            binding.todo = it
        })

    }
}