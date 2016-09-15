package cl.nidosoft.googlemaps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by Nido on 15/09/2016.
 */
public class PolylineActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polyline);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Ejemplo: Delimitar a Sudamérica con un rectángulo
        PolylineOptions sudamericaRect = new PolylineOptions()
                .add(new LatLng(12.897489, -82.441406)) // P1
                .add(new LatLng(12.897489, -32.167969)) // P2
                .add(new LatLng(-55.37911, -32.167969)) // P3
                .add(new LatLng(-55.37911, -82.441406))  // P4
                .add(new LatLng(12.897489, -82.441406)) // P1
                .color(Color.parseColor("#f44336"));    // Rojo 500

        // Instancia para posteriores usos
        Polyline polyline = mMap.addPolyline(sudamericaRect);

        // Mover cámara
        mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(-20.96144, -61.347656)));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
