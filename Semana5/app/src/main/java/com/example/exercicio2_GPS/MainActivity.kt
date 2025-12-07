package com.example.exercicio2_GPS

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.exercicio2_GPS.ui.theme.Exercicio2GPSTheme

class   MainActivity : ComponentActivity() {
    private var edtPto: EditText? = null;
    var p1: Ponto? = Ponto()
    var p2: Ponto? = Ponto()
    
    val PROVIDER: String = LocationManager.GPS_PROVIDER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
    }


    fun reset(view: View){
        edtPto = findViewById(R.id.editPontoA)
        edtPto?.setText("")
        edtPto = findViewById(R.id.editPontoB)
        edtPto?.setText("")
    }

    fun mostrarGoogleMaps(latitude: Double, longitude: Double) {
        val wv = findViewById<WebView>(R.id.webv)

        wv.settings.javaScriptEnabled = true
        wv.loadUrl(
            "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
        )
    }

    fun verPontoA(view: View){ mostrarGoogleMaps(p1!!.getLatitude(), p1!!.getLongitude())}
    fun verPontoB(view: View){ mostrarGoogleMaps(p2!!.getLatitude(), p2!!.getLongitude())}

    fun lerPontoA(view: View){
        p1 = this.getPonto()
        edtPto = findViewById(R.id.editPontoA)
        edtPto?.setText(p1?.imprimir2())
    }

    fun lerPontoB(view: View){
        p2 = this.getPonto()
        edtPto = findViewById(R.id.editPontoB)
        edtPto?.setText(p2?.imprimir2())
    }

    fun getPonto(): Ponto? {
        val mLocManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val mLocListener = MinhaLocalizacaoListener() as LocationListener

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

            return null
        }

        mLocManager.requestLocationUpdates(
            PROVIDER,
            0L,
            0f,
            mLocListener
        )


        val localAtual = mLocManager.getLastKnownLocation(PROVIDER) as Location

        if (!mLocManager.isProviderEnabled(PROVIDER)) {
            Toast.makeText(this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show()
        }
        return Ponto(localAtual.latitude, localAtual.longitude, localAtual.altitude)
    }

    fun calcularDistancia(view: View){
        val mLocManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val resultado = FloatArray(1)

        if (p1 != null && p2 != null) {
            Location.distanceBetween(
                p1!!.getLatitude(),
                p1!!.getLongitude(),
                p2!!.getLatitude(),
                p2!!.getLongitude(),
                resultado
            )

        }

        if (mLocManager.isProviderEnabled(PROVIDER)){
            val texto = "*** Distância: " + resultado[0] + "\n";
            Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show()
        }

    }

}
