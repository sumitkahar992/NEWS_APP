package com.example.paging_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paging_app.data.paging.UserPagingSource
import com.example.paging_app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val userPager = Pager(
        PagingConfig(pageSize = 10)
    ){
        UserPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}