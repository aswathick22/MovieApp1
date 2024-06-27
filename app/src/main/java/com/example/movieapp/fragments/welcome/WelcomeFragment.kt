package com.example.movieapp.fragments.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var welcomeBinding : FragmentWelcomeBinding
    private val welcomeViewModel by viewModels<WelcomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //findNavController().navigateUp() - to navigate to the previously viewed page
        welcomeBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return welcomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        welcomeViewModel.nameValue.observe(viewLifecycleOwner){
            print(it)
        }
        welcomeBinding.bnLogin.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment2())
        }
        welcomeBinding.bnSignup.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToSignupFragment2())
        }
    }


}

