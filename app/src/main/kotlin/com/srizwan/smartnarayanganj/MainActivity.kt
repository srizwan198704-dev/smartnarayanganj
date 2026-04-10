package com.srizwan.smartnarayanganj

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.srizwan.smartnarayanganj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = checkNotNull(_binding) { "Activity has been destroyed" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startTypingEffect()
    }

    private fun startTypingEffect() {
        val text = "স্মার্ট নারায়ণগঞ্জ"
        val handler = Handler(Looper.getMainLooper())
        var index = 0

        handler.post(object : Runnable {
            override fun run() {
                if (index <= text.length) {
                    binding.textTyping.text = text.substring(0, index)
                    index++
                    handler.postDelayed(this, 100) // টাইপিং স্পিড
                } else {
                    // টাইপিং শেষ হলে এখানে আসবে
                    navigateToSecondActivity()
                }
            }
        })
    }

    private fun navigateToSecondActivity() {
        // ১ সেকেন্ড বিরতি দিয়ে SecondActivity-তে যাবে যাতে ইউজার লেখাটা দেখতে পারে
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
            finish() // MainActivity বন্ধ করে দিবে যাতে ব্যাক বাটনে চাপলে এখানে আর না ফিরে আসে
        }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
