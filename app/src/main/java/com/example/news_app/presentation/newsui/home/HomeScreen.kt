package com.example.news_app.presentation.newsui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.news_app.R
import com.example.news_app.domain.model.news.NewsResponse
import com.example.news_app.presentation.navigation.Screen
import com.example.news_app.presentation.newsui.components.AnimatedShimmerItem
import com.example.news_app.presentation.newsui.components.AnimatedSliderShimmerItem
import com.example.news_app.utils.dummyNewsItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val pagerState =
        rememberPagerState(initialPage = 1, pageCount = { state.value.news.everything.size })

    val topHeadLines = state.value.news.banners
    val everything = state.value.news.everything

    Scaffold(
        topBar = { HomeAppBar() }
    ) {
        LazyColumn(modifier = Modifier.padding(paddingValues = it)) {
            item {
                HomeCategory(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 12.dp),
                    categories = viewModel.categories,
                ) {
                    navController.currentBackStackEntry?.arguments?.putString(
                        "category",
                        it
                    )
                    navController.navigate(Screen.News.route)
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 8.dp, end = 8.dp)
                        .height(1.dp)
                        .background(Black)
                )
                Box {
                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        state = pagerState,
                        pageSize = PageSize.Fill,
                        verticalAlignment = Alignment.Top
                    ) {
                        val news = topHeadLines[4]
                        NewsSlider(news = news) {
                            navController.currentBackStackEntry?.arguments?.putParcelable(
                                "news",
                                news
                            )
                            navController.navigate(Screen.Detail.route)
                        }
                    }
                    if (state.value.isLoading) {
                        AnimatedSliderShimmerItem()
                    }
                    if (!state.value.error.isNullOrEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = state.value.error!!)
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                        .height(1.dp)
                        .background(Black)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.size(22.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                    /*    HorizontalPagerIndicator(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            pagerState = pagerState,
                            activeColor = Color.Red,
                            inactiveColor = Black.copy(alpha = 0.5f),
                            indicatorWidth = 12.dp,
                            spacing = 8.dp,
                        )*/
                    Image(
                        modifier = Modifier.size(22.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }
            }

            // Shimmer for Home News
            if (state.value.isLoading) {
                items(8) {
                    AnimatedShimmerItem()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .height(1.dp)
                            .background(Black)
                    )
                }
            }

            itemsIndexed(everything) { index, news ->
                NewsItem(news = news) {
                    navController.currentBackStackEntry?.arguments?.putParcelable("news", news)
                    navController.navigate(Screen.Detail.route)
                }
                if (index < everything.lastIndex) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .height(1.dp)
                            .background(Black)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeCategory(
    modifier: Modifier = Modifier,
    categories: List<String>,
    onCategorySelected: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Black)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .height(50.dp)
                .background(Color.Blue)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            categories.forEach { category ->
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    modifier = Modifier.clickable {
                        onCategorySelected(category)
                    },
                    text = category,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = White
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Black)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeCategoryPreview(
    categories: List<String> = listOf("Category", "Science", "Technology"),
) {
    HomeCategory(categories = categories, onCategorySelected = {})
}

@Composable
fun NewsSlider(
    modifier: Modifier = Modifier,
    news: NewsResponse.Article,
    onNewsClick: () -> Unit,
) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    Row(
        modifier = modifier
            .fillMaxWidth(fraction = 1f)
            .height(180.dp)
            .clickable { onNewsClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Text(
                text = "TRENDING",
                color = Color.Red,
                fontFamily = FontFamily.SansSerif
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .padding(top = 4.dp),
                text = news.title,
                lineHeight = 22.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                color = Color.Gray,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Read More",
                    color = Black,
                    fontSize = 11.sp,
                )
                Image(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f)
                .padding(end = 8.dp)

        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 4.dp, bottom = 4.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = news.urlToImage)
                    .placeholder(R.drawable.newspaper)
                    .build(),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(matrix),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewsSliderPreview() {
    NewsSlider(
        news = NewsResponse.Article(
            title = "NASA Just Detected the Most Powerful Gamma-ray Bursts Close to Earth - News18\",",
            content = "Gamma-ray-bursts (GRB) were unintentionally found by American military satellites in the 1960s. Their creation most likely took place when a massive star exploded towards the end of their lives and turned into a black hole.",
            author = null,
            description = "",
            publishedAt = "",
            source = null,
            url = "",
            urlToImage = "https://images.news18.com/ibnlive/uploads/2022/10/gamma-rays-166607875216x9.png",
        )
    ) {}
}

@Composable
fun NewsItem(
    news: NewsResponse.Article,
    onNewsItemClick: () -> Unit,
) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 1f)
            .height(140.dp)
            .clickable { onNewsItemClick() }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(8.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = news.urlToImage)
                .placeholder(R.drawable.newspaper)
                .build(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(matrix),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f)
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, end = 4.dp),
                text = news.title,
                maxLines = 2,
                lineHeight = 18.sp,
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                modifier = Modifier.padding(top = 6.dp, end = 4.dp),
                text = news.content ?: "",
                maxLines = 4,
                color = Black.copy(alpha = 0.7f),
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsItemPreview() {
    NewsItem(news = dummyNewsItem) {}
}

@Preview
@Composable
fun HomeAppBar() {
    TopAppBar(
        title = {
            Text(text = "Search News", fontFamily = FontFamily.Monospace)
        },
        backgroundColor = White,
        navigationIcon = {
            IconButton(onClick = {
            }) {
                Icon(
                    modifier = Modifier.size(38.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
    )
}
