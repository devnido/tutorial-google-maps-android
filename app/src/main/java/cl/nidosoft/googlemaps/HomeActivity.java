package cl.nidosoft.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class HomeActivity extends AppCompatActivity implements SamplesAdapter.OnItemClickListener{

    public final static Class[] options = {
            FirstMapActivity.class,
            MarkersActivity.class,
            MapTypesActivity.class,
            CameraActivity.class,
            EventsActivity.class,
            PolylineActivity.class,
            PolygonActivity.class,
            CircleActivity.class,
            GesturesActivity.class,
            ControlsActivity.class
    };

    private SamplesAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAdapter = new SamplesAdapter(this, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.lista);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onClick(SamplesAdapter.ViewHolder holder, int opcion) {
        mostrarOpcion(opcion);
    }

    private void mostrarOpcion(int indice) {
        Intent i = new Intent(this, options[indice]);
        startActivity(i);
    }

}
