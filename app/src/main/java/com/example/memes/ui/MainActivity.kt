package com.example.memes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.memes.R
import com.example.memes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            val adapter = MemeAdapter()
            rvBosses.adapter = adapter

            //start observing our data

            viewModel.meme.observe(this@MainActivity){
                if (it.data?.isEmpty() == true) {
                    //show a progress bar if the list is empty
                    binding.pbBosses.visibility = View.VISIBLE
                } else {
                    //otherwise hide the progress bar
                    binding.pbBosses.visibility = View.GONE

                    adapter.submitList(it.data)
                }
            }
        }
    }

}