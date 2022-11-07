package com.example.paging_app.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object News : Screen("news_screem")
    object Detail : Screen("detail_screen")
}
