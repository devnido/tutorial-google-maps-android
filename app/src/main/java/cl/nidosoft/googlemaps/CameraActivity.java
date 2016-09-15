package cl.nidosoft.googlemaps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class CameraActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mMapFragment.getMapAsync(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.add("Inicio").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();

        if (title != null && title.equals("Inicio")) {
            LatLng zero = new LatLng(80, 80);
            mMap.animateCamera(
                    CameraUpdateFactory.newLatLng(zero), // update
                    2000, // durationMs
                    new GoogleMap.CancelableCallback() { // callback
                        @Override
                        public void onFinish() {
                            Toast.makeText(CameraActivity.this, "Animación finalizada",
                                    Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
