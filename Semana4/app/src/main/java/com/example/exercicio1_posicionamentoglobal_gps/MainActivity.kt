package com.example.exercicio1_posicionamentoglobal_gps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.example.exercicio1_posicionamentoglobal_gps.ui.theme.Exercicio1PosicionamentoGlobalGPSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
    }

    fun buscarInformacoesGPS(view: View) {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener = MinhaLocalizacaoListener() as LocationListener

        if (
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_NETWORK_STATE),
                1
            )

            return
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0L,
            0f,
            locationListener
        )

        val localAtual = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) as Location

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            val lat = localAtual.latitude;
            val lon = localAtual.longitude

            val texto = "Latitude: $lat\nLongitude: $lon"
            Toast.makeText(this, texto, Toast.LENGTH_LONG).show()

            mostrarGoogleMaps(lat, lon)

        } else {
            Toast.makeText(this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show()
        }
    }


    fun mostrarGoogleMaps(latitude: Double, longitude: Double) {
        val wv = findViewById<WebView>(R.id.webv)

        wv.settings.javaScriptEnabled = true
        wv.loadUrl(
            "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
        )
    }

}
