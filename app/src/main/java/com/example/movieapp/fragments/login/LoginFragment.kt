package com.example.movieapp.fragments.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.database.DatabaseHandler
import com.example.movieapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var dbHandler: DatabaseHandler
    private lateinit var loginBinding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        dbHandler = DatabaseHandler(requireContext())
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*dbHandler.deleteLastFourEntries()*/

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
            /*loginViewModel.setUsername(loginBinding.etName.text.toString())
            loginViewModel.setPassword(loginBinding.etPassword.text.toString())*/
            val enteredUsername = loginBinding.etName.text.toString()
            val password = loginBinding.etPassword.text.toString()
            loginViewModel.setUsername(enteredUsername)
            loginViewModel.setPassword(password)
            loginViewModel.login(requireContext(), dbHandler)

            loginViewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
                if (success) {
                    // Login successful
                    loginViewModel.saveLoginState(requireContext(), true)
                    loginViewModel.saveLoggedInUsername(requireContext(), enteredUsername)
                    // Assuming you use Navigation Component
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                    // Navigate to another fragment or activity
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragment2ToHomeFragment())
                } else {
                    // Login failed
                    Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}


/*findNavController().navigate(R.id.action_loginFragment_to_movieListFragment)*/
/*loginViewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
    if (success){
        Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
        findNavController().navigate(LoginFragmentDirections.actionLoginFragment2ToHomeFragment())
        *//*loginViewModel.loginSuccess.value = false*//*
                }
            }*/

/*
val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
with(sharedPreferences.edit()) {
    putString("loggedInUsername", username)
    apply()
}*/
