package com.infigo.githubdemoappmain.login

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infigo.githubdemoappmain.database.RegisterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: RegisterRepository) :
    ViewModel(),Observable {


    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val _navigateToRegister = MutableLiveData<Boolean>()

    val navigateToRegister: LiveData<Boolean> get() = _navigateToRegister

    private val _navigateToUserDetails = MutableLiveData<Boolean>()

    val navigateToUserDetails: LiveData<Boolean> get() = _navigateToUserDetails

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean> get() = _errorToast

    private val _errorToastUserName = MutableLiveData<Boolean>()

    val errorToastUserName: LiveData<Boolean> get() = _errorToastUserName

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean> get() = _errorToastInvalidPassword


    fun signUP() {
        _navigateToRegister.value = true
    }

    fun loginButton() {
        if (inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            viewModelScope.launch {
                val usersNames = repository.getUserName(inputUsername.value!!)
                if (usersNames != null) {
                    if (usersNames.passwrd == inputPassword.value) {
                        inputUsername.value = ""
                        inputPassword.value = ""
                        _navigateToUserDetails.value = true
                    } else {
                        _errorToastInvalidPassword.value = true
                    }
                } else {
                    _errorToastUserName.value = true
                }
            }
        }
    }


    fun doneNavigatingRegister() {
        _navigateToRegister.value = false
    }

    fun doneNavigatingUserDetails() {
        _navigateToUserDetails.value = false
    }


    fun showMessage() {
        _errorToast.value = false
        Log.i("TAG", "Done toasting ")
    }


    fun showErrorUserName() {
        _errorToastUserName.value = false
        Log.i("TAG", "Done toasting ")
    }

    fun donetoastInvalidPassword() {
        _errorToastInvalidPassword.value = false
        Log.i("TAG", "Done toasting ")
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }


}