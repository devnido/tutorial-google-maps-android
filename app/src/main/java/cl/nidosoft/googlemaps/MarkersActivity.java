package cl.nidosoft.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

/**
 * Created by Nido on 15/09/2016.
 */
public class MarkersActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnInfoWindowClickListener {

    public static final String EXTRA_LATITUD = "LATITUD";
    public static final String EXTRA_LONGITUD = "LONGITUD";

    private Marker markerColombia;
    private Marker markerArgentina;
    private Marker markerEcuador;

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Markers
        LatLng colombia = new LatLng(4.6, -74.08);
        markerColombia = googleMap.addMarker(new MarkerOptions()
                        .position(colombia)
                        .title("Colombia")
        );

        LatLng ecuador = new LatLng(-0.217, -78.51);
        markerEcuador = googleMap.addMarker(new MarkerOptions()
                        .position(ecuador)
                        .title("Ecuador")
                        .draggable(true)
        );

        LatLng argentina = new LatLng(-34.6, -58.4);
        markerArgentina = googleMap.addMarker(
                new MarkerOptions()
                        .position(argentina)
                        .title("Argentina")
        );

        // Cámara
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(argentina));

        // Eventos
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnInfoWindowClickListener(this);

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.equals(markerColombia)) {

            map.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()), new GoogleMap.CancelableCallback() {
                @Override
                public void onFinish() {
                    Intent intent = new Intent(MarkersActivity.this, MarkerDetailActivity.class);
                    intent.putExtra(EXTRA_LATITUD, marker.getPosition().latitude);
                    intent.putExtra(EXTRA_LONGITUD, marker.getPosition().longitude);
                    startActivity(intent);
                }

                @Override
                public void onCancel() {

                }
            });

            return true;

        }

        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(markerEcuador)) {
            Toast.makeText(this, "START", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(markerEcuador)) {
            String newTitle = String.format(Locale.getDefault(),
                    getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);

            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerEcuador)) {
            Toast.makeText(this, "END", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(markerArgentina)) {

            ArgentinaDialogFragment.newInstance(marker.getTitle(),
                    getString(R.string.argentina_full_snippet))
                    .show(getSupportFragmentManager(), null);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}