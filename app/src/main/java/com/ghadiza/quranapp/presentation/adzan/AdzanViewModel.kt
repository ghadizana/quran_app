package com.ghadiza.quranapp.presentation.adzan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ghadiza.quranapp.network.Resource
import com.ghadiza.quranapp.network.adzan.AdzanDataResult
import com.ghadiza.quranapp.network.adzan.AdzanRepository

class AdzanViewModel(
    private val adzanRepository: AdzanRepository
): ViewModel() {
    fun getDailyAdzanTime():
            LiveData<Resource<AdzanDataResult>> = adzanRepository
                .getResultDailyAdzanTime()
}