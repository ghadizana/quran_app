package com.ghadiza.quranapp.network.di

import android.content.Context
import com.ghadiza.quranapp.local.CalendarPreferences
import com.ghadiza.quranapp.local.LocationPreferences
import com.ghadiza.quranapp.network.ApiConfig
import com.ghadiza.quranapp.network.RemoteDataSource
import com.ghadiza.quranapp.network.adzan.AdzanRepository
import com.ghadiza.quranapp.network.quran.QuranRepository

object Injection {
    private val quranApiService = ApiConfig.getQuranService
    private val adzanApiService = ApiConfig.getAdzanTimeService
    private val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)

    fun provideQuranRepository(): QuranRepository {
        return QuranRepository(remoteDataSource)
    }

    fun provideAdzanRepository(context: Context): AdzanRepository {
        val locationPreferences = LocationPreferences(context)
        val calendarPreferences = CalendarPreferences()
        return AdzanRepository(remoteDataSource, locationPreferences, calendarPreferences)
    }
}