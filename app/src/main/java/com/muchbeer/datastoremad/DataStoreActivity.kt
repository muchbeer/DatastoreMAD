package com.muchbeer.datastoremad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.muchbeer.datastoremad.databinding.ActivityDataStoreBinding
import com.muchbeer.datastoremad.presentation.UserViewModel
import com.muchbeer.datastoremad.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataStoreActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDataStoreBinding

    private val viewModel : UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            submit.setOnClickListener {
                viewModel.saveNames(binding.editTextTextPersonName.text.toString())

                val intent = Intent(this@DataStoreActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}