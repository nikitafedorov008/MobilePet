package ru.com.jetbrainsresearch.ido

import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style

class MapActivity : AppCompatActivity() {
    private var mapView: MapView? = null
    private var mapCloseBtn: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, "pk.eyJ1IjoibmlraXRhZmVkb3JvdjAwOCIsImEiOiJjam42M2ZnMjcwMHNpM3FxbTZsMXM0MGxlIn0.WdK9BwaLmmt8iVTbT61vkg")
        setContentView(R.layout.activity_map)
        mapView = findViewById(R.id.mapView)
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync { mapboxMap ->
            mapboxMap.setStyle(Style.MAPBOX_STREETS) {
                // Map is set up and the style has loaded. Now you can add data or make other map adjustments
            }
        }
        mapCloseBtn = findViewById<View>(R.id.map_close_btn) as FloatingActionButton
        mapCloseBtn!!.setOnClickListener {
            val intent = Intent(this@MapActivity, MainActivity::class.java)
            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    public override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    public override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    public override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView!!.onSaveInstanceState(outState)
    }
}
