package com.example.paging_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.paging_app.domain.model.news.NewsResponse
import com.example.paging_app.presentation.newsui.detail.DetailScreen
import com.example.paging_app.presentation.newsui.home.HomeScreen
import com.example.paging_app.presentation.newsui.news.NewsScreen
import com.example.paging_app.utils.parcelable


@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){

        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(route = Screen.Detail.route)
        {
            val newsItem =
                navController.previousBackStackEntry?.arguments?.parcelable<NewsResponse.Article>("news")
            DetailScreen(navController,newsItem)
        }


        composable(route = Screen.News.route) {
            NewsScreen(navController = navController)
        }


    }






}