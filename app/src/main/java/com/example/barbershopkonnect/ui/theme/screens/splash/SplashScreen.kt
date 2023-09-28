package com.example.barbershopkonnect.ui.theme.screens.splash
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.barbershopkonnect.R
import com.example.barbershopkonnect.navigation.ROUTE_BOOKING
import com.example.barbershopkonnect.navigation.ROUTE_LOGIN

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.reg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentScale = ContentScale.Crop
        )
    }

    LaunchedEffect(key1 = true) {
        kotlinx.coroutines.delay(3000) // 2000 milliseconds = 2 seconds
        navController.navigate(ROUTE_LOGIN)
    }
}
@Preview
@Composable
fun SplashScreen() {
    SplashScreen(rememberNavController())

}