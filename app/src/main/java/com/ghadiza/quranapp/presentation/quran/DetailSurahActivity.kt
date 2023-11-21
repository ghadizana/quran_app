package com.ghadiza.quranapp.presentation.quran

import android.media.MediaPlayer
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghadiza.quranapp.adapter.SurahAdapter
import com.ghadiza.quranapp.databinding.ActivityDetailSurahBinding
import com.ghadiza.quranapp.network.Resource
import com.ghadiza.quranapp.network.quran.Ayah
import com.ghadiza.quranapp.network.quran.Surah
import com.ghadiza.quranapp.network.quran.SurahItem

class DetailSurahActivity : AppCompatActivity() {
    private var _binding: ActivityDetailSurahBinding? = null
    private val binding get() = _binding as ActivityDetailSurahBinding

    private var _surah: Surah? = null
    private val surah get() = _surah as Surah

    private var _mediaPlayer: MediaPlayer? = null
    private val mediaPlayer get() = _mediaPlayer as MediaPlayer

    private var quranViewModel: QuranViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _surah = intent.getParcelableExtra(EXTRA_DATA, Surah::class.java)

        initView()

        val mAdapter = SurahAdapter()
        mAdapter.setOnItemClickCallback(object : SurahAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Ayah) {
                showCustomAlertDialog(data, surah)
            }
        })

        val numberSurah = surah.number
        if (numberSurah != null) {
            quranViewModel.getDetailSurahWithQuranEdition(numberSurah).observe(this) {
                when (it) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        binding.rvSurah.apply {
                            mAdapter.setData(it.data?.get(0)?.listAyahs, it.data)
                            adapter = mAdapter
                            layoutManager = LinearLayoutManager(this@DetailSurahActivity)
                        }
                        showLoading(false)
                    }

                    is Resource.Error -> {
                        Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                        showLoading(false)
                    }
                }
            }
        } else {
            Toast.makeText(this,"Number Surah Not Found.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBar
            }
        }
    }


    companion object {
        const val EXTRA_DATA = "number"
    }
}