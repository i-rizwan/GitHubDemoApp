package com.infigo.githubdemoappmain.userDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infigo.githubdemoappmain.database.RegisterRepository
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(private val repository: RegisterRepository) :
    ViewModel() {

    val users = repository.users


    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo: LiveData<Boolean>
        get() = _navigateTo

    fun doneNavigating() {
        _navigateTo.value = false
    }

    fun backButtonClicked() {
        _navigateTo.value = true
    }

}