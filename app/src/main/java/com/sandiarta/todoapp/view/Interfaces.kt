package com.sandiarta.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.sandiarta.todoapp.model.Todo

interface TodoCheckedChangeListener{
    fun onCheckedChanged(cb: CompoundButton,isChecked:Boolean,obj:Todo)

//    bisa taruh fun lagi
//    fun onClickImageView
}
interface TodoEditClickListener{
    fun onTodoEditClick(v:View)
}
interface RadioClickListener{
//    fun onRadioClick(v:View, priority:Int, obj:Todo)
    fun onRadioClick(v:View)

}
//interface TodoSaveChangesClick {
//    fun onTodoSaveChangesClick(v: View, obj: Todo)
//}
