package cl.nidosoft.googlemaps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nido on 14/09/2016.
 */
public class SamplesAdapter extends RecyclerView.Adapter<SamplesAdapter.ViewHolder> {
    private final Context mContext;

    private OnItemClickListener callback;

    interface OnItemClickListener {
        public void onClick(ViewHolder holder, int opcion);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Referencias UI
        public TextView opcion;

        public ViewHolder(View v) {
            super(v);
            opcion = (TextView) v.findViewById(android.R.id.text1);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            callback.onClick(this, getAdapterPosition());
        }
    }

    public SamplesAdapter(Context contexto, OnItemClickListener escucha) {
        this.mContext = contexto;
        this.callback = escucha;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(android.R.layout.simple_selectable_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Asignación UI
        holder.opcion.setText(ITEMS[position]);
    }

    @Override
    public int getItemCount() {
        return ITEMS.length;
    }


    public static String[] ITEMS = {
            "1. Mapa básico",
            "2. Marcadores",
            "3. Tipos de mapa",
            "4. Manejo de cámara",
            "5. Manejo de Eventos",
            "6. Polilíneas",
            "7. Polígonos",
            "8. Circulos",
            "9. Gestos",
            "10. Controles"
    };

}
