package cl.nidosoft.googlemaps;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

/**
 * Created by Nido on 15/09/2016.
 */
public class EventsActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Configuración UI
        mMap.getUiSettings().setAllGesturesEnabled(false);

        // Eventos
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        String formatLatLng = String.format(Locale.getDefault(),
                "Lat/Lng = (%f,%f)", latLng.latitude, latLng.longitude);

        Point screentPoint = mMap.getProjection().toScreenLocation(latLng);

        String formatScreenPoint = String.format(Locale.getDefault(),
                "\nPoint = (%d,%d)", screentPoint.x, screentPoint.y);

        Toast.makeText(this, formatLatLng + formatScreenPoint, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        // Añadir marker en la posición
        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng));

        // Obtener pixeles reales
        Point point = mMap.getProjection().toScreenLocation(latLng);

        // Determinar el ancho total de la pantalla
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int width = display.widthPixels;

        float hue;

        // ¿La coordenada de pantalla es menor o igual que la mitad del ancho?
        if (point.x <= width / 2) {
            hue = BitmapDescriptorFactory.HUE_YELLOW;

        } else {
            hue = BitmapDescriptorFactory.HUE_ORANGE;
        }

        // Cambiar color del marker según el grupo
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(hue));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
