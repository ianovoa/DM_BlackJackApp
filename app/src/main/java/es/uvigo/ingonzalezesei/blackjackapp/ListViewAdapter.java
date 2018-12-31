package es.uvigo.ingonzalezesei.blackjackapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ListViewAdapter extends BaseAdapter {

    // Declaramos las Variables
    Context context; //Contexto, a donde esta llamando
    String[] titulos; //son los titulos creados en Turnomaquina
    int[] imagenes;  //son las imagenes llamdas en TurnoMaquina
    LayoutInflater inflater; //Crea una instancia e un archivo XML de diseño en sus correspondientes objetos de vista.Nuca se usa directamente


    //Constructor; No suele ser necesario, peor lo recomendaban en el tutorial
    public ListViewAdapter(Context context, String[] titulos, int[] imagenes) {
        this.context = context;
        this.titulos = titulos;
        this.imagenes = imagenes;
    }

    @Override
    public int getCount() {
        return titulos.length;
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

        // Declare Variables
        TextView txtTitle;
        ImageView imgImg;

        //Este inflanter definido arriba, es como un intermediario.
        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.estilo, parent, false);

        // Locate the TextViews in estilo_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.estilo_title);
        imgImg = (ImageView) itemView.findViewById(R.id.estilo_image);

        // Capture position and set to the TextViews
        txtTitle.setText(titulos[position]);
        imgImg.setImageResource(imagenes[position]);

        return itemView;
    }
}
