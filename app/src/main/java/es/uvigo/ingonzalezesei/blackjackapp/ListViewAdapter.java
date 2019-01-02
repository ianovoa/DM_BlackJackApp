package es.uvigo.ingonzalezesei.blackjackapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

class ListViewAdapter extends BaseAdapter {

    // Declaramos las Variables
    private Context context; //Contexto, a donde esta llamando
    private List<Carta> cartas;
    private LayoutInflater inflater; //Crea una instancia e un archivo XML de diseño en sus correspondientes objetos de vista.Nuca se usa directamente


    //Constructor; No suele ser necesario, peor lo recomendaban en el tutorial
    public ListViewAdapter(Context context, List<Carta> cartas) {
        this.context = context;
        this.cartas=cartas;
    }

    @Override
    public int getCount() {
        return cartas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Este inflanter definido arriba, es como un intermediario.
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.estilo, parent, false);

        // Locate the TextViews in estilo_item.xml
        TextView txtTitle = itemView.findViewById(R.id.estilo_title);
        ImageView imgImg = itemView.findViewById(R.id.estilo_image);

        // Capture position and set to the TextViews
        txtTitle.setText(this.cartas.get(position).getName());
        imgImg.setImageResource(this.context.getResources().getIdentifier(this.cartas.get(position).getImage(),"drawable",this.context.getPackageName()));

        return itemView;
    }
}