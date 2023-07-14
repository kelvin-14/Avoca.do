package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.data.preferences.ListFocusPreferenceRepository

class SaveActiveCategoryIndex(
    private val listFocusPreferenceRepository: ListFocusPreferenceRepository
) {
    suspend operator fun invoke(lastActiveCategoryIndex: Int) {
        listFocusPreferenceRepository.saveFocusPreference(lastActiveCategoryIndex)
    }
}