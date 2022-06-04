package com.infigo.githubdemoappmain.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.infigo.githubdemoappmain.R

import com.infigo.githubdemoappmain.databinding.RegisterHomeFragmentBinding


class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: RegisterHomeFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.register_home_fragment, container, false
        )

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.myViewModel = registerViewModel
        binding.lifecycleOwner = this

        registerViewModel.navigateTo.observe(requireActivity(), Observer { hasFinished ->
            if (hasFinished == true) {
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        })

        registerViewModel.userDetailsLiveData.observe(requireActivity(), Observer {
            Log.i("TAG", it.toString() + "")
        })


        registerViewModel.errorToastMessage.observe(requireActivity(), Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoast()
            }
        })

        registerViewModel.errorToastUserName.observe(requireActivity(), Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoastUserName()
            }
        })

        return binding.root
    }

    private fun displayUsersList() {
        Log.i("TAG", "inside display users list")
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

}