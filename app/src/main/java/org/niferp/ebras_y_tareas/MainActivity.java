package org.niferp.ebras_y_tareas;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static Button b1;
    static Button b2;
    static TextView tv;
    static Thread hilo;
    private CalcularPrimos cp;

    private final long NUM_PRIMOS=1000000000;

    class CalcularPrimos extends AsyncTask<Void,Void,Long> {

        public void mostrarResultado(String mensaje){
           /* Log.i("PMDM","Comienzo el calculo");
            long resultado = cuantosPrimos(i);
            Log.i("PMDM",'('+id+",Numero de primos: "+resultado);
*/
            tv.setText(mensaje);
        }

        public  boolean esPrimo(long i) {
            if (i == 1) return false;
            if (i < 4) return true;
            if ((i % 2) == 0 || (i % 3) == 0) return false;
            if (i < 9) return true;
            long n = 5;
            while (n*n <= i && i % n != 0 && i % (n + 2) != 0)
                n += 6;
            return (n*n > i);
        }


        private long cuantosPrimos(int limite) {
            long result=0;
            for (long i=0; i <= limite; i++){
                if( esPrimo(i))
                    ++result;
                if (isCancelled()){ //comprobación de la cancelacion
                    return  result;
                }
                return result;
            }
            return result;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return cuantosPrimos((int) NUM_PRIMOS);
        }
        @Override
        protected void onPostExecute(Long res) {
            mostrarResultado("ToTAl: "+ res);
        }

        @Override
        protected void onCancelled(Long resultado) {
            mostrarResultado("CANCELADO: resultado parcial -> "+resultado);
        }
    }
/*------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hilo= new Thread();
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        tv=findViewById(R.id.textView);

    }

    public void onMensaje(View v) {
        Toast.makeText(this,"MENSAJE -->>!!",Toast.LENGTH_SHORT).show();
    }

    public void onLanzarCancelar(View v) {

        //EJERCICIO 10.2

        Button b= (Button) v;
        switch (b.getText().toString().toLowerCase()){
            case "lanzar:":
                b.setText("Cancelar");
                cp= new CalcularPrimos();
                cp.execute();
                break;
            case "cancalar":
                b.setText("Lanzar");
                //Cancelar opracion
                cp.cancel(true);
        }
/*//*
        //EJERCICIO 10.1  (onLanzar)
        //forma 2:
        CalcularPrimos cp= new CalcularPrimos();
        cp.execute();

        //forma 1:
        Thread h = new Thread(()-> calcularPrimos(1,NUM_PRIMOS));
        h.start();*/
    }

    /*    public void pulsoB2(View view) {
        //bucleInfinito();
        lanzarHebraBucleInfinito();
        lanzarHebraBuclePitidos();
    }*/
  /*  private void lanzarHebraBucleInfinito(){
        Thread h = new Thread(() ->bucleInfinito());
        h.start();
    }
    private void lanzarHebraBuclePitidos(){
        Thread h = new Thread(() ->pitarIndefinidamente());
        h.start();
    }*/
   /* private void bucleInfinito(){
        while(true){

        }
    }

    private void pitarIndefinidamente(){
        android.util.Log.i("SPY", "Comenzando pitidos");
        int i = 0;
        ToneGenerator tg;
        tg = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        while (true) {
            tg.startTone(ToneGenerator.TONE_CDMA_ALERT_INCALL_LITE, 20);
            android.util.Log.i("SPY", "MainActivity: " + i);
            i++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
            }
        }
    }*/
   /* public static long cuantosPrimos(long limite) {
        long result = 0;
        for (long i = 1; i <= limite; ++i)
            if (esPrimo(i))
                ++result;
        return result;
    }*/
}