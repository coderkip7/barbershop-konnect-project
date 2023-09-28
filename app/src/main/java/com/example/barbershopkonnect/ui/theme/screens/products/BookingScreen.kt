package com.example.barbershopkonnect.ui.theme.screens.products

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

data class Barber(val name: String)
data class Service(val name: String, val price: Int)

data class Booking(
    val barberShop: String,
    val barber: Barber,
    val service: Service,
    val date: String,
    val time: String
)

val barbershops = listOf("ShaveFisa", "Klean Kutz", "Bellerio")
val barbers = listOf(
    Barber("Timo"), Barber("Edu"),
    Barber("Dan"), Barber("Frank"),
    Barber("Jay"), Barber("Rick")
)
val services = listOf(
    Service("Clean Cut", 500),
    Service("Fade", 100),
    Service("Full Dye", 2500),
    Service("Facial Steaming", 700)
)

@Composable
fun BookingScreen(
    onBookingConfirmed: NavHostController
) {
    var selectedBarberShop by remember { mutableStateOf(barbershops[0]) }
    var selectedBarber by remember { mutableStateOf(barbers[0]) }
    var selectedService by remember { mutableStateOf(services[0]) }
    var selectedDate by remember { mutableStateOf(TextFieldValue()) }
    var selectedTime by remember { mutableStateOf(TextFieldValue()) }
    var bookingConfirmed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (bookingConfirmed) {
            BookingConfirmation(
                selectedBarberShop,
                selectedBarber,
                selectedService,
                selectedDate.text,
                selectedTime.text
            )
        } else {
            Text(text = "Select Barber Shop:")
            RadioGroup(
                options = barbershops,
                selectedOption = selectedBarberShop,
                onOptionSelected = { selectedBarberShop = it }
            )

            Text(text = "Select Barber:")
            RadioGroup(
                options = barbers.map { it.name },
                selectedOption = selectedBarber.name,
                onOptionSelected = { selectedBarber = barbers.first { barber -> barber.name == it } }
            )

            Text(text = "Select Service:")
            RadioGroup(
                options = services.map { "${it.name} - ${it.price} KES" },
                selectedOption = "${selectedService.name} - ${selectedService.price} KES",
                onOptionSelected = { selectedService = services.first { service -> "${service.name} - ${service.price} KES" == it } }
            )

            Text(text = "Select Date:")
            BasicTextField(
                value = selectedDate.text,
                onValueChange = {
                    selectedDate = TextFieldValue(it)
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, color = Color.Gray)
                    .padding(4.dp)
            )

            Text(text = "Select Time:")
            BasicTextField(
                value = selectedTime.text,
                onValueChange = {
                    selectedTime = TextFieldValue(it)
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, color = Color.Gray)
                    .padding(4.dp)
            )

            Button(
                onClick = {
                    val booking = Booking(
                        selectedBarberShop,
                        selectedBarber,
                        selectedService,
                        selectedDate.text,
                        selectedTime.text
                    )
                    bookingConfirmed = true
                }
            ) {
                Text(text = "Book Now")
            }
        }
    }
}

@Composable
fun BookingConfirmation(
    barberShop: String,
    barber: Barber,
    service: Service,
    date: String,
    time: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Booking Confirmation")
        Text(text = "Barber Shop: $barberShop")
        Text(text = "Barber: ${barber.name}")
        Text(text = "Service: ${service.name} - ${service.price} KES")
        Text(text = "Date: $date")
        Text(text = "Time: $time")

        Button(
            onClick = {
            }
        ) {
            Text(text = "Confirm Booking")
        }
    }
}

@Composable
fun RadioGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    options.forEach { option ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) },
                modifier = Modifier.padding(4.dp)
            )
            Text(text = option)
        }
    }
}

@Preview
@Composable
fun BkScr() {
    BookingScreen(rememberNavController())
}
