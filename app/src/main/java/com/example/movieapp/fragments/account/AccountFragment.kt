package com.example.movieapp.fragments.account

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.movieapp.R
import com.example.movieapp.database.DatabaseHandler
import com.example.movieapp.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private lateinit var accountBinding : FragmentAccountBinding
    private lateinit var dbHandler: DatabaseHandler
    private val accountViewModel by viewModels<AccountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        accountBinding = FragmentAccountBinding.inflate(inflater,container,false)
        dbHandler = DatabaseHandler(requireContext())
        return accountBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val loggedInUsername = sharedPreferences.getString("loggedInUsername", null)

        if (loggedInUsername != null) {
            Log.d("AccountFragment", "Calling fetchUser with username: $loggedInUsername")
            accountViewModel.fetchUser(requireContext(), loggedInUsername)
        }

        accountViewModel.username.observe(viewLifecycleOwner){username ->
            Log.d("AccountViewModel", username)
            accountBinding.nameText.text = username
        }
        accountViewModel.phone.observe(viewLifecycleOwner){phone ->
            Log.d("AccountViewModel", phone)
            accountBinding.mobileText.text = phone
        }
        accountViewModel.email.observe(viewLifecycleOwner){email ->
            Log.d("AccountViewModel", email)
            accountBinding.mailText.text = email
        }
        accountViewModel.password.observe(viewLifecycleOwner){password ->
            Log.d("AccountViewModel", password)
            accountBinding.passwordText.text = password
        }

        // Handle logout button click
        accountBinding.bnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.putString("loggedInUsername", null)
            editor.apply()
            requireActivity().findNavController(R.id.navHostFragmentContainerView).navigate(R.id.welcomeFragment)
        }
    }

}