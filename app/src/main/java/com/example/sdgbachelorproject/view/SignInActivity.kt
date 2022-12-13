package com.google.firebase.quickstart.auth.kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.view.MainActivity
import com.example.sdgbachelorproject.view.MyApplication
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class SignInActivity : AppCompatActivity() {

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private lateinit var auth: FirebaseAuth

    @Inject
    lateinit var signInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_ui)

        // SignIn Intent
        createSignInIntent()
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        connectToTheDb()
        updateUI(auth.currentUser)
    }

    private fun createSignInIntent() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setIsSmartLockEnabled(false)
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            failedSignIn(response)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun connectToTheDb() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            signInViewModel.getCurrentUser(currentUser)
            signInViewModel.getCurrentUsersFirebaseToken(currentUser)
            signInViewModel.postUserToDb(currentUser)
        }
    }

    private fun failedSignIn(response: IdpResponse?) {
        if (response == null) {
            Toast.makeText(this, "Sign in canceled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this,
                "Sign in failed: ${response.error?.errorCode}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}