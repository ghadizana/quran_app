package com.ghadiza.quranapp.network.adzan

import com.ghadiza.quranapp.network.Resource

data class AdzanDataResult(
    val listLocation: List<String>,
    val dailyAdzan: Resource<DailyAdzan>,
    val listCalendar: List<String>
)
