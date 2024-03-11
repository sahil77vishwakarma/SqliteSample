package com.example.loginsignupsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import com.example.loginsignupsqlite.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.signupButton.setOnClickListener{
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()

            signupDatabase(signupUsername,signupPassword)
        }

        binding.loginRedirect.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    //signup function
    private fun signupDatabase(username: String, password: String){
        val insertedRowId = databaseHelper.insertUser(username,password)
        if(insertedRowId != -1L){
            Toast.makeText(this, "SignUp Successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, "Signup UnSuccessful", Toast.LENGTH_SHORT).show()
        }
    }
}