package com.sandiarta.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sandiarta.todoapp.model.Todo
import com.sandiarta.todoapp.model.TodoDatabase
import com.sandiarta.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application),CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLD.value = true
        todoLoadErrorLD.value = false

        launch {
            val db = buildDb(getApplication())
            todoLD.postValue(db.todoDao().selectAllTodo())
//            loadingLD.postValue(false)
        }
    }
    fun clearTask(todo: Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().deleteTodo(todo)

            todoLD.postValue(db.todoDao().selectAllTodo())
        }
    }
    fun updateIsDone(todo: Todo){
        launch {
            var isDone = 0
            if (todo.is_done == 0){
                isDone = 1
            }
            val uuid = todo.uuid

            val db = buildDb(getApplication())
            db.todoDao().updateIsDone(isDone,uuid)

            todoLD.postValue(db.todoDao().selectAllTodo())
        }
    }

}