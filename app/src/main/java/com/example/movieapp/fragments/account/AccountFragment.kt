package com.example.movieapp.fragments.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.database.DatabaseHandler
import com.example.movieapp.databinding.FragmentAccountBinding
import com.example.movieapp.fragments.signup.SignupViewModel

class AccountFragment : Fragment() {

    private lateinit var accountBinding : FragmentAccountBinding
    private lateinit var dbHandler: DatabaseHandler/*<Any?>*/
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

        accountViewModel.fetchUser(requireContext())

        accountViewModel.user.observe(viewLifecycleOwner) { user ->
            accountBinding.nameText.text = user?.username
            accountBinding.mobileText.text = user?.phone
            accountBinding.mailText.text = user?.email
            accountBinding.passwordText.text = user?.password
        }
        /*accountViewModel.username.observe(viewLifecycleOwner){username ->
            accountBinding.nameText.text = username
        }
        accountViewModel.phone.observe(viewLifecycleOwner){phone ->
            accountBinding.mobileText.text = phone
        }
        accountViewModel.email.observe(viewLifecycleOwner){email ->
            accountBinding.mailText.text = email
        }*/

        /*accountViewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                // User is authenticated, update UI accordingly
                *//*accountBinding.tvLoggedInStatus.text = "Logged In"*//*
                accountBinding.bnLogout.visibility = View.VISIBLE
            } else {
                // User is not authenticated, update UI accordingly
                *//*accountBinding.tvLoggedInStatus.text = "Not Logged In"*//*
                accountBinding.bnLogout.visibility = View.VISIBLE
            }
        }*/

        // Handle logout button click
        accountBinding.bnLogout.setOnClickListener {
            /*accountViewModel.logout()*/
            requireActivity().findNavController(R.id.navHostFragmentContainerView).navigate(R.id.welcomeFragment)
        }
    }

}