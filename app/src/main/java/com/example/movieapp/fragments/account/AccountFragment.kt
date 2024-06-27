package com.example.movieapp.fragments.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentAccountBinding
import com.example.movieapp.fragments.signup.SignupViewModel

class AccountFragment : Fragment() {

    private lateinit var accountBinding : FragmentAccountBinding
    /*private val accountViewModel by viewModels<AccountViewModel>()*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        accountBinding = FragmentAccountBinding.inflate(inflater,container,false)
        return accountBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountBinding.bnLogout.setOnClickListener {
            //findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }

    }

}