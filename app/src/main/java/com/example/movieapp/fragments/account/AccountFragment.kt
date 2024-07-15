package com.example.movieapp.fragments.account

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
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
        setEditButtons()
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

    private fun setEditButtons() {
        accountBinding.editNameIcon.setOnClickListener {
            showEditDialog("Username", accountViewModel.username.value.orEmpty()) { newValue ->
                accountViewModel.updateUserDetails(
                    newValue,
                    accountViewModel.phone.value.orEmpty(),
                    accountViewModel.email.value.orEmpty(),
                    accountViewModel.password.value.orEmpty()
                )
            }
        }

        accountBinding.editPhoneIcon.setOnClickListener {
            showEditDialog("Phone", accountViewModel.phone.value.orEmpty()) { newValue ->
                accountViewModel.updateUserDetails(
                    accountViewModel.username.value.orEmpty(),
                    newValue,
                    accountViewModel.email.value.orEmpty(),
                    accountViewModel.password.value.orEmpty()
                )
            }
        }

        accountBinding.editMailIcon.setOnClickListener {
            showEditDialog("Email", accountViewModel.email.value.orEmpty()) { newValue ->
                accountViewModel.updateUserDetails(
                    accountViewModel.username.value.orEmpty(),
                    accountViewModel.phone.value.orEmpty(),
                    newValue,
                    accountViewModel.password.value.orEmpty()
                )
            }
        }

        accountBinding.editPasswordIcon.setOnClickListener {
            showEditDialog("Password", accountViewModel.password.value.orEmpty()) { newValue ->
                accountViewModel.updateUserDetails(
                    accountViewModel.username.value.orEmpty(),
                    accountViewModel.phone.value.orEmpty(),
                    accountViewModel.email.value.orEmpty(),
                    newValue
                )
            }
        }
    }

    private fun showEditDialog(field: String, currentValue: String, updateFunction: (String) -> Unit) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_user_detail, null)
        val editText = dialogView.findViewById<EditText>(R.id.editText)
        val dialog = AlertDialog.Builder(requireContext())
        editText.setText(currentValue)
        dialog.setTitle("Edit $field")
        dialog.setView(dialogView)

        dialog.setPositiveButton("Save") { _, _ ->
            val newValue = editText.text.toString()
            updateFunction(newValue)
        }

        dialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        dialog.show()
    }

}