package com.example.movieapp.fragments.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movieapp.fragments.signup.SignupViewModel.SignupValidator.validateSignup
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignupValidationTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var _username: MutableLiveData<String>
    private lateinit var _password: MutableLiveData<String>
    private lateinit var _phone: MutableLiveData<String>
    private lateinit var _email: MutableLiveData<String>

    @Before
    fun setUp() {
        _username = MutableLiveData()
        _password = MutableLiveData()
        _phone = MutableLiveData()
        _email = MutableLiveData()
    }

    @Test(expected = IllegalArgumentException::class)
    fun testEmptyUsernameThrowsException() {
        _username.value = ""
        _password.value = "Password123!"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testShortUsernameThrowsException() {
        _username.value = "ab"
        _password.value = "Password123!"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testLongUsernameThrowsException() {
        _username.value = "abcdefghijklmnop"
        _password.value = "Password123!"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUsernameWithInvalidCharactersThrowsException() {
        _username.value = "user name!"
        _password.value = "Password123!"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testEmptyPasswordThrowsException() {
        _username.value = "username"
        _password.value = ""
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testShortPasswordThrowsException() {
        _username.value = "username"
        _password.value = "Pass1!"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPasswordMissingUppercaseThrowsException() {
        _username.value = "username"
        _password.value = "password1!"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPasswordMissingLowercaseThrowsException() {
        _username.value = "username"
        _password.value = "PASSWORD1!"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPasswordMissingNumberThrowsException() {
        _username.value = "username"
        _password.value = "Password!"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPasswordMissingSpecialCharacterThrowsException() {
        _username.value = "username"
        _password.value = "Password1"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testEmptyPhoneNumberThrowsException() {
        _username.value = "username"
        _password.value = "Password123!"
        _phone.value = ""
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPhoneNumberNot10DigitsThrowsException() {
        _username.value = "username"
        _password.value = "Password123!"
        _phone.value = "12345678"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPhoneNumberContainsNon_DigitsThrowsException() {
        _username.value = "username"
        _password.value = "Password123!"
        _phone.value = "12345abcde"
        _email.value = "test@example.com"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testEmptyEmailThrowsException() {
        _username.value = "username"
        _password.value = "Password123!"
        _phone.value = "1234567890"
        _email.value = ""
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidEmailThrowsException() {
        _username.value = "username"
        _password.value = "Password123!"
        _phone.value = "1234567890"
        _email.value = "invalid-email"
        validateSignup(_username, _password, _phone, _email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testValidInputs() {
        _username.value = "username"
        _password.value = "Password123!"
        _phone.value = "1234567890"
        _email.value = "test@example.com"
        assertTrue(validateSignup(_username, _password, _phone, _email))
    }
}
