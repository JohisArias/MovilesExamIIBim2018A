package com.example.daro.carritocompras

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity :AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener {

    var usuarioTienePermiososLocalizacion = false

    var mMarkerArray = ArrayList<Marker>()

    val zoom = 17f

    val materiaLatLang = LatLng(-0.200946,-78.4935697)

    private val materia: CameraPosition = CameraPosition.Builder()
            .target(materiaLatLang)
            .zoom(zoom)
            .build()

    override fun onCameraMoveStarted(p0: Int) {

    }

    override fun onCameraMove() {
    }

    override fun onCameraMoveCanceled() {
    }

    override fun onCameraIdle() {
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        solicitarPermisosLocalizacion()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        with(googleMap) {


            buttonAutor.setOnClickListener { v ->
                anadirMarcador(materiaLatLang, "Marcador 1")
                moverCamaraPorPosicion(materia)
            }

        }
        // Add a marker in Sydney and move the camera
    }

    fun solicitarPermisosLocalizacion() {
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            usuarioTienePermiososLocalizacion = true
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    private fun establecerSettings(googleMap: GoogleMap) {
        with(googleMap) {
            uiSettings.isZoomControlsEnabled = false
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    private fun establecerListeners(googleMap: GoogleMap) {
        with(googleMap) {
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnCameraMoveCanceledListener(this@MapsActivity)
        }
    }

    private fun anadirMarcador(latitudLongitud: LatLng, titulo: String) {
        mMarkerArray.forEach { marker: Marker ->
            marker.remove()
        }
        mMarkerArray = ArrayList<Marker>()
        val marker = mMap.addMarker(MarkerOptions().position(latitudLongitud).title(titulo))
        mMarkerArray.add(marker)
        Log.i("map-wilson", "$mMarkerArray")
    }

    private fun moverCamaraPorLatLongZoom(latitudLongitud: LatLng, zoom: Float) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudLongitud, zoom))
    }

    private fun moverCamaraPorPosicion(posicionCamara: CameraPosition) {
        changeCamera(CameraUpdateFactory.newCameraPosition(posicionCamara))
    }

    private fun changeCamera(update: CameraUpdate, callback: GoogleMap.CancelableCallback? = null) {
        mMap.moveCamera(update)
    }
}
