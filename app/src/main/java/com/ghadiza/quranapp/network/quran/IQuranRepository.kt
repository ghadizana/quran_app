package com.ghadiza.quranapp.network.quran

import com.ghadiza.quranapp.network.Resource
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    fun getListSurah() : Flow<Resource<List<Surah>>>
    fun getDetailSurahWithQuranEditions(number: Int) : Flow<Resource<List<QuranEdition>>>
}