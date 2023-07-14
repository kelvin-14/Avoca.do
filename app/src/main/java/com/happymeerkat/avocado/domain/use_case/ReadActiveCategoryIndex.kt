package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.data.preferences.ListFocusPreferenceRepository
import kotlinx.coroutines.flow.Flow

class ReadActiveCategoryIndex(
    private val listFocusPreferenceRepository: ListFocusPreferenceRepository

) {
    operator fun invoke(): Flow<Int> {
        return listFocusPreferenceRepository.readFocusPreference
    }
}