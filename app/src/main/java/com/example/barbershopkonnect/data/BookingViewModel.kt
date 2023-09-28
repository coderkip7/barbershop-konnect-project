package com.example.barbershopkonnect.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barbershopkonnect.ui.theme.screens.products.Barber
import com.example.barbershopkonnect.ui.theme.screens.products.Service
import com.example.barbershopkonnect.ui.theme.screens.products.barbers
import com.example.barbershopkonnect.ui.theme.screens.products.barbershops
import com.example.barbershopkonnect.ui.theme.screens.products.services
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference


data class Booking(
    val barberShop: String,
    val barber: Barber,
    val service: Service,
    val date: String,
    val time: String
)

data class BookingData(
    val barberShop: String = "",
    val barberName: String = "",
    val serviceName: String = "",
    val date: String = "",
    val time: String = ""
)

class BookingViewModel : ViewModel() {
    private val _selectedBarberShop = MutableLiveData<String>()
    val selectedBarberShop: LiveData<String> = _selectedBarberShop

    private val _selectedBarber = MutableLiveData<Barber>()
    val selectedBarber: LiveData<Barber> = _selectedBarber

    private val _selectedService = MutableLiveData<Service>()
    val selectedService: LiveData<Service> = _selectedService

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate

    private val _selectedTime = MutableLiveData<String>()
    val selectedTime: LiveData<String> = _selectedTime

    private val _bookingConfirmed = MutableLiveData<Boolean>()
    val bookingConfirmed: LiveData<Boolean> = _bookingConfirmed

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val bookingsRef: DatabaseReference =
        database.getReference("bookings") // Replace with your Firebase Realtime Database reference

    init {
        _selectedBarberShop.value = barbershops[0]
        _selectedBarber.value = barbers[0]
        _selectedService.value = services[0]
        _selectedDate.value = ""
        _selectedTime.value = ""
        _bookingConfirmed.value = false
    }

    fun setSelectedBarberShop(barberShop: String) {
        _selectedBarberShop.value = barberShop
    }

    fun setSelectedBarber(barber: Barber) {
        _selectedBarber.value = barber
    }

    fun setSelectedService(service: Service) {
        _selectedService.value = service
    }

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }

    fun setSelectedTime(time: String) {
        _selectedTime.value = time
    }

    fun setBookingConfirmed(confirmed: Boolean) {
        _bookingConfirmed.value = confirmed
    }

    fun saveBookingToFirebase() {
        val booking = BookingData(
            barberShop = selectedBarberShop.value ?: "",
            barberName = selectedBarber.value?.name ?: "",
            serviceName = selectedService.value?.name ?: "",
            date = selectedDate.value ?: "",
            time = selectedTime.value ?: ""
        )

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userBookingRef = bookingsRef.child(currentUser.uid).push()
            userBookingRef.setValue(booking)
                .addOnSuccessListener {
                    setBookingConfirmed(true)
                }
                .addOnFailureListener {
                    setBookingConfirmed(false)
                }
        }
    }
}
