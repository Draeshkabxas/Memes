package com.example.memes.ui

import android.graphics.Insets.add
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.memes.R
import com.example.memes.databinding.ActivityMainBinding
import com.example.memes.model.Meme
import com.example.memes.ui.MemeAdapter.OnLoad
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var adapter: MemeAdapter

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            adapter.onLoad= object : OnLoad {
                override fun onLoadItem(count: Int) {
                    viewModel.getMeme(count)
                }
            }
            var list = mutableListOf<Meme>()

            rvBosses.adapter = adapter

            //start observing our data

            viewModel.meme.observe(this@MainActivity){
                if (it.data?.isEmpty() == true) {
                    //show a progress bar if the list is empty
                    binding.pbBosses.visibility = View.VISIBLE
                } else {
                    //otherwise hide the progress bar
                    binding.pbBosses.visibility = View.GONE
                    it.data?.let { it1 -> list.addAll(it1) }
                    adapter.submitList(list)
                }
            }
        }
    }

}