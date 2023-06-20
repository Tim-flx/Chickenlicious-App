package com.dicoding.chicken

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.chicken.ui.navigation.NavigationItem
import com.dicoding.chicken.ui.navigation.Screen
import com.dicoding.chicken.ui.screen.detail.DetailScreen
import com.dicoding.chicken.ui.screen.favorite.FavoriteScreen
import com.dicoding.chicken.ui.screen.home.HomeScreen
import com.dicoding.chicken.ui.screen.about.AboutScreen
import com.dicoding.chicken.ui.theme.ChickenTheme

@Composable
fun ChickenApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailChicken.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { chickenId ->
                        navController.navigate(Screen.DetailChicken.createRoute(chickenId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { chickenId ->
                        navController.navigate(Screen.DetailChicken.createRoute(chickenId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailChicken.route,
                arguments = listOf(navArgument("chickenId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("chickenId") ?: 1
                DetailScreen(
                    id = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(modifier = modifier) {
        val items = listOf(
            NavigationItem(
                title = stringResource(R.string.home_page),
                icon = Icons.Default.Home,
                screen = Screen.Home,
                contentDescription = "home_page"
            ),
            NavigationItem(
                title = stringResource(R.string.favorite_page),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite,
                contentDescription = "favorite_page"
            ),
            NavigationItem(
                title = stringResource(R.string.about_page),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile,
                contentDescription = "about_page"
            )
        )
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.contentDescription
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChickenAppPreview() {
    ChickenTheme {
        ChickenApp()
    }
}
