package com.example.movieapp.fragments.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.database.DatabaseHandler
import com.example.movieapp.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private lateinit var signupBinding: FragmentSignupBinding
    private lateinit var dbHandler: DatabaseHandler
    private val signupViewModel by viewModels<SignupViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signupBinding = FragmentSignupBinding.inflate(inflater, container, false)
        /*signupViewModel.nameValue1.observe(viewLifecycleOwner){
            print(it)
        }
        binding.bnSubmit.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }*/
        dbHandler = DatabaseHandler(requireContext())
        return signupBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signupViewModel.username.observe(viewLifecycleOwner) { username ->
            signupBinding.etName.setText(username)
        }

        signupViewModel.password.observe(viewLifecycleOwner) { password ->
            signupBinding.etPassword.setText(password)
        }

        signupViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        signupBinding.bnSubmit.setOnClickListener {
            signupViewModel.setUsername(signupBinding.etName.text.toString())
            signupViewModel.setPassword(signupBinding.etPassword.text.toString())
            signupViewModel.setPhone(signupBinding.etPhone.text.toString())
            signupViewModel.setEmail(signupBinding.etEmail.text.toString())
            signupViewModel.signup(dbHandler)
            /*if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val result = dbHandler.insertUser(username, password)
                if (result > -1) {
                    Toast.makeText(requireContext(), "Signup successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Signup failed", Toast.LENGTH_SHORT).show()
                }
            }*/
            signupViewModel.signupSuccess.observe(viewLifecycleOwner) { success ->
                if (success){
                    Toast.makeText(requireContext(), "Signup successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(SignupFragmentDirections.actionSignupFragment2ToLoginFragment2())
                    /*loginViewModel.loginSuccess.value = false*/
                }
            }
        }
    }
}


