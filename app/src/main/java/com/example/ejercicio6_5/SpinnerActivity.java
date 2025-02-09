package com.example.ejercicio6_5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {
    //Definimos los objetos
    private Spinner lista;
    private TextView texto;
    private RadioButton radioButton_pulsado;

    //Cramo sel menú para ver los diferetnes tipos de view
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.ejer6_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        // Obtener el ActionBar predeterminado
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Modificar el título del ActionBar
            actionBar.setTitle("PROGRAMACIÓN MULTIMEDIA");

            // Opcional: Mostrar u ocultar el icono de navegación
            actionBar.setDisplayHomeAsUpEnabled(false);
        }


        //Asociamos los objetos
        lista = findViewById(R.id.lista);
        texto = findViewById(R.id.texto);


        ArrayList<Encapsulador> datos = new ArrayList<>();

        //Datos
        datos.add(new Encapsulador(R.drawable.ima1, "DONUTS", "El 15 de septiembre de 2009, fue lanzado el SDK de Android 1.6 Donut, basado en el núcleo Linux 2.6.29. En la actualización se incluyen numerosas características nuevas.", true));
        datos.add(new Encapsulador(R.drawable.ima2, "FROYO", "El 20 de mayo de 2010, El SDK de Android 2.2 Froyo (Yogur helado) fue lanzado, basado en el núcleo Linux 2.6.32.", false));
        datos.add(new Encapsulador(R.drawable.ima3, "GINGERBREAD", "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread (Pan de Jengibre) fue lanzado, basado en el núcleo Linux 2.6.35.", false));
        datos.add(new Encapsulador(R.drawable.ima4, "HONEYCOMB", "El 22 de febrero de 2011, sale el SDK de Android 3.0 Honeycomb (Panal de Miel). Fue la primera actualización exclusiva para TV y tableta, lo que quiere decir que sólo es apta para TV y tabletas y no para teléfonos Android.", false));
        datos.add(new Encapsulador(R.drawable.ima5, "ICE CREAM", "El SDK para Android 4.0.0 Ice Cream Sandwich (Sándwich de Helado), basado en el núcleo de Linux 3.0.1, fue lanzado públicamente el 12 de octubre de 2011.", false));
        datos.add(new Encapsulador(R.drawable.ima6, "JELLY BEAN", "Google anunció Android 4.1 Jelly Bean (Gomita Confitada o Gominola) en la conferencia del 30 de junio de 2012. Basado en el núcleo de linux 3.0.31, Bean fue una actualización incremental con el enfoque primario de mejorar la funcionalidad y el rendimiento de la interfaz de usuario.", false));
        datos.add(new Encapsulador(R.drawable.ima7, "KITKAT", "Su nombre se debe a la chocolatina KitKat, de la empresa internacional Nestlé. Posibilidad de impresión mediante WIFI. WebViews basadas en el motor de Chromium.", false));
        datos.add(new Encapsulador(R.drawable.ima8, "LOLLIPOP", "Incluye Material Design, un diseño intrépido, colorido, y sensible interfaz de usuario para las experiencias coherentes e intuitivos en todos los dispositivos. Movimiento de respuesta natural, iluminación y sombras realistas y familiares elementos visuales hacen que sea más fácil de navegar su dispositivo.", false));

        lista.setAdapter(new Adaptador(this, R.layout.entrada,datos){
            @Override
            public void onEntrada (Object entrada, View view){
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen);
                RadioButton miRadio = (RadioButton) view.findViewById(R.id.boton);

                texto_superior_entrada.setText(((Encapsulador) entrada).get_textoTitulo());
                texto_inferior_entrada.setText(((Encapsulador) entrada).get_textoContenido());
                imagen_entrada.setImageResource(((Encapsulador) entrada).get_idImagen());

                miRadio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (radioButton_pulsado != null) radioButton_pulsado.setChecked(false);
                        radioButton_pulsado = (RadioButton) view;
                        texto.setText(R.string.op_marc);
                        Log.d("RADIO", "Click en RadioButton");
                    }
                });

            }
        });

        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Encapsulador elegido = (Encapsulador) parent.getItemAtPosition(position);
                CharSequence textoElegido = "Selecionado: " + elegido.get_textoContenido();
                texto.setText(elegido.get_textoContenido());
                Log.d("Spinner", "textoElegido: " + textoElegido);
                Log.d("Spinner", "elegido: " + elegido.toString());
                Log.d("Spinner", "elegido: " + elegido.get_textoContenido());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                texto.setText("Toca una opción");
            }
        });
    }

    //Opciones para cambiar la vista con el menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.litsView:
                Intent abrirList = new Intent(this, MainActivity.class);
                this.startActivity(abrirList);
                return false;
            case R.id.gridView:
                Intent abrirGrid = new Intent(this, GridActivity.class);
                this.startActivity(abrirGrid);
                return false;
            case R.id.spinner:
                Intent abrirSpinner = new Intent(this, SpinnerActivity.class);
                this.startActivity(abrirSpinner);
                return false;
            default:
                return false;
        }
    }


    public class Encapsulador{
        private int imagen;
        private String titulo;
        private String texto;
        private boolean dato1;

        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, boolean favorito){
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.texto = textoContenido;
            this.dato1 = favorito;
        }

        public String get_textoTitulo(){return titulo;}
        public String get_textoContenido(){return texto;}
        public int get_idImagen(){return imagen;}
        public boolean get_checkBox1(){return dato1;}
    }
}


