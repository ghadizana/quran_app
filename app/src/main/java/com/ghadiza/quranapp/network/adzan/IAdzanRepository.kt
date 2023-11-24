package com.ghadiza.quranapp.network.adzan

import androidx.lifecycle.LiveData
import com.ghadiza.quranapp.network.Resource
import java.util.concurrent.Flow

interface IAdzanRepository {
    fun getLastKnownLocation() : LiveData<List<String>>
    fun searchCity(city: String): kotlinx.coroutines.flow.Flow<Resource<List<City>>>

    fun getDailyAdzanTime(
        id: String,
        year: String,
        month: String,
        date: String
    ): kotlinx.coroutines.flow.Flow<Resource<DailyAdzan>>
}