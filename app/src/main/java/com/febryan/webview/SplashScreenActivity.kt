package com.febryan.webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.febryan.webview.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sessionManager = SessionManager(this)

        supportActionBar?.hide()  //Buat ilangin Bar di splash animasi

        binding.lottieAnim.setAnimation("AndroidWave.json")
        binding.lottieAnim.playAnimation()

        Handler().postDelayed(Runnable {

            val intent : Intent?

            val sessionManager = SessionManager(this@SplashScreenActivity)
            if (sessionManager.isLogin()){
                intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            }else{
                intent = Intent(this@SplashScreenActivity, loginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 3000)
    }
}