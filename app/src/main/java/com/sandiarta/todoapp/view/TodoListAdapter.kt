package com.sandiarta.todoapp.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sandiarta.todoapp.databinding.TodoItemLayoutBinding
import com.sandiarta.todoapp.model.Todo
import com.sandiarta.todoapp.viewmodel.DetailTodoViewModel

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Todo)->Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),TodoCheckedChangeListener,TodoEditClickListener {
    class TodoViewHolder(var binding:TodoItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var binding = TodoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return TodoViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.todo =todoList[position]

        holder.binding.checkBoxTask.isChecked = false
        if(todoList[position].is_done == 1){
            holder.binding.checkBoxTask.isChecked = true
        }


        holder.binding.listener = this
        holder.binding.editlistener = this

//        holder.binding.checkBoxTask.text = todoList[position].title
//        holder.binding.checkBoxTask.isChecked = false
//        holder.binding.checkBoxTask.setOnCheckedChangeListener { compoundButton, b ->
//            if(compoundButton.isPressed){
//                adapterOnClick(todoList[position])
//            }
//
//        }
//
//        holder.binding.imageViewEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionToEditTodoFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
    }
    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckedChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(cb.isPressed){
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionToEditTodoFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }


}