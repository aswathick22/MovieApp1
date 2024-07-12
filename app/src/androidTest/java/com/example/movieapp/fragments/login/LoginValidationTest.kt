package com.example.movieapp.fragments.login

import com.example.movieapp.fragments.login.LoginViewModel.LoginValidator.validateLogin
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class LoginValidationTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var _username: MutableLiveData<String>
    private lateinit var _password: MutableLiveData<String>

    @Before
    fun setUp() {
        _username = MutableLiveData()
        _password = MutableLiveData()
    }

    @Test(expected = IllegalArgumentException::class)
    fun testEmptyUsernameThrowsException() {
        _username.value = ""
        _password.value = "Password123!"
        validateLogin(_username, _password)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testEmptyPasswordThrowsException() {
        _username.value = "username@1"
        _password.value = ""
        validateLogin(_username, _password)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPasswordMissingUppercaseThrowsException() {
        _username.value = "username@1"
        _password.value = "password1!"
        validateLogin(_username, _password)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPasswordMissingLowercaseThrowsException() {
        _username.value = "username@1"
        _password.value = "PASSWORD1!"
        validateLogin(_username, _password)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPasswordMissingNumberThrowsException() {
        _username.value = "username@1"
        _password.value = "Password!"
        validateLogin(_username, _password)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPasswordMissingSpecialCharacterThrowsException() {
        _username.value = "username@1"
        _password.value = "Password1"
        validateLogin(_username, _password)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testValidInputs() {
        _username.value = "username@1"
        _password.value = "Password123!"
        assertTrue(validateLogin(_username, _password))
    }

}
