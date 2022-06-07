package com.muchbeer.datastoremad.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.muchbeer.datastoremad.R
import com.muchbeer.datastoremad.databinding.ActivityMainBinding
import com.muchbeer.datastoremad.presentation.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

            binding.btnClear.setOnClickListener {
                viewModel.clearValues()
            }
        viewModel.readNameByLogs()
            viewModel.readNameValue()
      /*      viewModel.readName.collectLatest {
                binding.displayName.text = it            }*/
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}