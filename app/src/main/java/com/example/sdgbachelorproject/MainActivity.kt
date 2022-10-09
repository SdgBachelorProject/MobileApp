package com.example.sdgbachelorproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sdgbachelorproject.view.SignInActivity
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btn_log_out)

        button.setOnClickListener {
            signOut()
        }

        btn_test_repo.setOnClickListener {
            signInViewModel.printToConsole()
        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                val intent = Intent(this@MainActivity, SignInActivity::class.java)
                startActivity(intent)
            }
    }

    private fun delete() {
        AuthUI.getInstance()
            .delete(this)
            .addOnCompleteListener {
                // ...
            }
    }
}