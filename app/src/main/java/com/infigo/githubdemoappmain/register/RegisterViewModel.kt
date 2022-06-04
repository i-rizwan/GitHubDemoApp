package com.infigo.githubdemoappmain.register

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infigo.githubdemoappmain.database.RegisterEntity
import com.infigo.githubdemoappmain.database.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository) :
    ViewModel(),Observable {

    var userDetailsLiveData = MutableLiveData<Array<RegisterEntity>>()

    @Bindable
    val inputFirstName = MutableLiveData<String>()

    @Bindable
    val inputLastName = MutableLiveData<String>()

    @Bindable
    val inputUserName = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()


    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo: LiveData<Boolean> get() = _navigateTo

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToastMessage: LiveData<Boolean> get() = _errorToast

    private val _errorToastUserName = MutableLiveData<Boolean>()

    val errorToastUserName: LiveData<Boolean> get() = _errorToastUserName


    fun submitButton() {

        if (inputFirstName.value == null || inputLastName.value == null || inputUserName.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            viewModelScope.launch {
                val usersNames = repository.getUserName(inputUserName.value!!)
                Log.i("TAG", usersNames.toString() + "------------------")
                if (usersNames != null) {
                    _errorToastUserName.value = true
                    Log.i("TAG", "Inside if Not null")
                } else {

                    Log.i("TAG", "OK im in")
                    val firstName = inputFirstName.value!!
                    val lastName = inputLastName.value!!
                    val email = inputUserName.value!!
                    val password = inputPassword.value!!
                    Log.i("TAG", "inside Submit")
                    insert(RegisterEntity(0, firstName, lastName, email, password))
                    inputFirstName.value = ""
                    inputLastName.value = ""
                    inputUserName.value = ""
                    inputPassword.value = ""
                    _navigateTo.value = true
                }
            }
        }
    }


    fun doneNavigating() {
        _navigateTo.value = false
        Log.i("TAG", "Done ")
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("TAG", "Done  ")
    }

    fun donetoastUserName() {
        _errorToast.value = false
        Log.i("TAG", "username")
    }

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

}





