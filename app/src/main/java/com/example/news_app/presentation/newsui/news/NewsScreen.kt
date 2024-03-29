package com.example.news_app.presentation.newsui.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news_app.domain.model.news.NewsResponse
import com.example.news_app.presentation.navigation.Screen
import com.example.news_app.presentation.newsui.home.NewsItem

@Composable
fun NewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        val category =
            navController.previousBackStackEntry?.arguments?.getString("category") ?: "technology"
        newsViewModel.getNewsByCategory(category)
        Log.d("SelectedCategory", "NewsScreen: $category")
    }

    val news = newsViewModel.allNews.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            NewsAppBar {
                navController.navigateUp()
            }
        },
    ) {
        ListContent(
            modifier = Modifier.padding(it),
            news = news,
            navController = navController
        )
    }
}

@Composable
fun NewsAppBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "NEWS") },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    modifier = Modifier.size(38.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun NewsAppBarPreview() {
    NewsAppBar { }
}

@Composable
fun ListContent(
    news: LazyPagingItems<NewsResponse.Article>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val result = handlePagingResult(news = news)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                count = news.itemCount,
                key = { index ->
                    news[index]?.url ?: ""
                }
            ) { index ->
                news.let {
                    NewsItem(news = news[index]!!) {
                        navController.currentBackStackEntry?.arguments?.putParcelable(
                            "news",
                            news[index]
                        )
                        navController.navigate(Screen.Detail.route)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .height(1.dp)
                            .background(Color.Black)
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    news: LazyPagingItems<NewsResponse.Article>,
): Boolean {
    news.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                false
            }

            error != null -> {
                EmptyScreen(error)
                false
            }

            news.itemCount < 1 -> {
                false
            }

            else -> true
        }
    }
}

@Composable
fun EmptyScreen(error: LoadState.Error? = null) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "EMPTY")
    }
}
