package com.febryan.webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.febryan.webview.databinding.ActivityLoginBinding

class loginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sessionManager = SessionManager(this)

        binding.btnLogin.setOnClickListener {
            val  name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()

            if (name.isNullOrEmpty() || email.isNullOrEmpty()){
                Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Simpan nama dan email ke sessionManager / SharedPreferences
            sessionManager.sessionLogin(name, email)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}