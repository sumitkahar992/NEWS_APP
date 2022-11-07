package com.example.paging_app.presentation.newsui.detail

import androidx.lifecycle.ViewModel
import com.example.paging_app.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
}