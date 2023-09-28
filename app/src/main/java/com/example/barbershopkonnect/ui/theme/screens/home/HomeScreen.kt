package com.example.barbershopkonnect.ui.theme.screens.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.barbershopkonnect.R
import com.example.barbershopkonnect.navigation.ROUTE_BOOKING
import com.example.barbershopkonnect.navigation.ROUTE_BOOKING_CONFIRMATION
import com.example.barbershopkonnect.ui.theme.Pink40
import com.example.barbershopkonnect.ui.theme.PurpleGrey40


@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Pink40),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        var context= LocalContext.current

        Text(text = "Welcome to Barbershop Konnect,The Home of Dapper Gents ",
            color = Color.Black,
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center,
            fontSize = 30.sp)
        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            navController.navigate(ROUTE_BOOKING)
        },modifier = Modifier.fillMaxWidth()) {
            Text(text = "Booking")
        }
        Spacer(modifier = Modifier.height(100.dp))
        Image(painter = painterResource(id = R.drawable.bbshop2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            contentScale = ContentScale.Fit)


    }



}

@Preview
@Composable
fun Homepreview() {
    HomeScreen(rememberNavController())
}