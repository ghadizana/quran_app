package com.ghadiza.quranapp.network.quran

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET("surah")
    fun getListSurah(): SurahResponse

    @GET("surah/{number}/editions/quran-uthmani,ar.alafasy,id.indonesian")
    suspend fun getDetailSurahWithQuranEditions(
        @Path("number") numberSurah: Int
    ): AyahResponse
}