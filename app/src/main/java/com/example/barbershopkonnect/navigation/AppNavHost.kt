package com.example.barbershopkonnect.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.barbershopkonnect.ui.theme.screens.home.HomeScreen
import com.example.barbershopkonnect.ui.theme.screens.login.LoginScreen
import com.example.barbershopkonnect.ui.theme.screens.products.BookingScreen
import com.example.barbershopkonnect.ui.theme.screens.registration.RegisterScreen
import com.example.barbershopkonnect.ui.theme.screens.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_SPLASHSCREEN) {

    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){
        composable(ROUTE_SPLASHSCREEN){
            SplashScreen(navController)
        }
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(ROUTE_BOOKING) {
            BookingScreen(navController)
        }
    }

}