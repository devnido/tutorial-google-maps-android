package cl.nidosoft.googlemaps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

/**
 * Created by Nido on 15/09/2016.
 */
public class PolygonActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polygon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Ejemplo: Encerrar a Cuba con un polígono de bajo detalle
        LatLng p1 = new LatLng(21.88661065803621, -85.01541511562505);
        LatLng p2 = new LatLng(22.927294359193038, -83.76297370937505);
        LatLng p3 = new LatLng(23.26620799401109, -82.35672370937505);
        LatLng p4 = new LatLng(23.387267854439315, -80.79666511562505);
        LatLng p5 = new LatLng(22.496957602618004, -77.98416511562505);
        LatLng p6 = new LatLng(20.20512046753661, -74.16092292812505);
        LatLng p7 = new LatLng(19.70944706110937, -77.65457527187505);


        Polygon cubaPolygon = mMap.addPolygon(new PolygonOptions()
                .add(p1, p2, p3, p4, p5, p6, p7, p1)
                .strokeColor(Color.parseColor("#7B1FA2"))
                .fillColor(Color.argb(32, 156, 39, 176)));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(21.5034305704608, -78.95096199062505), 5));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
