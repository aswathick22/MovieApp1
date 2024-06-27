package com.example.movieapp.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var loginBinding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        /*loginViewModel.nameValue2.observe(viewLifecycleOwner){
            print(it)
        }
        loginBinding.bnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }*/
        return loginBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.username.observe(viewLifecycleOwner) { username ->
            loginBinding.etName.setText(username)
        }

        loginViewModel.password.observe(viewLifecycleOwner) { password ->
            loginBinding.etPassword.setText(password)
        }

        loginViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        loginBinding.bnLogin.setOnClickListener {
            loginViewModel.setUsername(loginBinding.etName.text.toString())
            loginViewModel.setPassword(loginBinding.etPassword.text.toString())
            loginViewModel.login()
            /*findNavController().navigate(R.id.action_loginFragment_to_movieListFragment)*/
            loginViewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
                if (success){
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragment2ToHomeFragment())
                    /*loginViewModel.loginSuccess.value = false*/
                }
            }
        }
    }
}


