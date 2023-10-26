package com.example.slave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.slave.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val helper = SqliteHelper(this,"numberBook",null,1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
    }
    private fun initBottomNavigation(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, PlusFragment())
            .commit()
        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.plusFragment -> {
                    val homeFragment = PlusFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frame, homeFragment)
                        .commit()
                    return@setOnItemSelectedListener true
                }

                R.id.menuFragment -> {
                    val lookFragment = MenuFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frame, lookFragment)
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}