package com.rubenshardt.yelpgettycenter.utils.viewholders

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rubenshardt.yelpgettycenter.R
import com.rubenshardt.yelpgettycenter.model.business.Coordinates
import com.rubenshardt.yelpgettycenter.model.business.Location
import com.rubenshardt.yelpgettycenter.utils.extensions.inflate
import com.rubenshardt.yelpgettycenter.utils.listeners.MapListener
import kotlinx.android.synthetic.main.item_map.view.*

class MapViewHolder(parent: ViewGroup, private val listener: MapListener) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.item_map)),
    OnMapReadyCallback {

    private var mapFragment: SupportMapFragment =
        (itemView.context as AppCompatActivity).supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    var map: GoogleMap? = null

    init {
        mapFragment.getMapAsync(this)
    }

    var coordinates: Coordinates? = null
        set(value) {
            field = value
            addMarker(value)
        }

    var location: Location? = null
        set(value) {
            field = value
            itemView.addressTextView.text = value?.displayAddress?.joinToString("\n")
        }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        map?.setOnMapClickListener {
            coordinates?.let { listener.onMapClicked(it) }
        }
        coordinates?.let {
            addMarker(it)
        }
    }

    private fun addMarker(coordinates: Coordinates?) {
        val latitude = coordinates?.latitude ?: return
        val longitude = coordinates.longitude ?: return
        val position = LatLng(latitude, longitude)
        map?.addMarker(
            MarkerOptions().position(position).title(itemView.context.getString(R.string.marker_title))
        )
        map?.moveCamera(CameraUpdateFactory.newLatLng(position))
        map?.animateCamera(CameraUpdateFactory.zoomTo(15F))
    }
}