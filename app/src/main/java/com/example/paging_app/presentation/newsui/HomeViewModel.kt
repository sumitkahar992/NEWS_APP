package com.example.paging_app.presentation.newsui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paging_app.common.Resource
import com.example.paging_app.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {


    private val _state = mutableStateOf(HomeDataState())
    val state : State<HomeDataState> = _state

    val categories = newsRepository.getNewsCategories()

    init {
        getTopHeadLines()
    }


    private fun getTopHeadLines(){
        val topHeadlines = newsRepository.getTopHeadlines()
        val everything = newsRepository.getEverything()

        val homeNews = merge(topHeadlines, everything)


        homeNews.onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _state.value = HomeDataState(
                        news = HomeData(
                            banners = result.data?.articles?.take(5) ?: emptyList(),
                            everything = result.data?.articles?.take(20) ?: emptyList()
                        )
                    )
                }
                is Resource.Error -> {
                    _state.value = HomeDataState(error = result.message ?: "An unexpected error occured_?")
                }
                is Resource.Loading -> {
                    _state.value = HomeDataState(isLoading = true)
                }


            }
        }.launchIn(viewModelScope)




    }

































}