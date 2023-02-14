package org.niferp.ebras_y_tareas;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static Button b1;
    static Button b2;
    static TextView tv;
    static Thread hilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hilo= new Thread();
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        tv=findViewById(R.id.textView);

    }

    public void pulsoB1(View view) {
        Toast.makeText(this,"MENSAJE -->>!!",Toast.LENGTH_SHORT).show();
    }

    public void pulsoB2(View view) {
        Thread h = new Thread(()-> calcularPrimos(1,100000000));
        h.start();
        Thread h2 = new Thread(()-> calcularPrimos(2,100000000*10));
        h2.start();
        Thread h3 = new Thread(()-> calcularPrimos(3,00100000000/10));
        h3.start();
    }

    private void calcularPrimos(int id, int i) {
        Log.i("PMDM","Comienzo el calculo");
        long resultado = cuantosPrimos(i);
        Log.i("PMDM",'('+id+",Numero de primos: "+resultado);
        tv.setText("RES: "+id+resultado);
    }

    private long cuantosPrimos(int limite) {
        long result=0;
        for (long i=0; i <= limite; i++){
            if( esPrimo(i))
                result++;
            return result;
        }
        return result;
    }

    public static boolean esPrimo(long i) {
        if (i == 1) return false;
        if (i < 4) return true;
        if ((i % 2) == 0 || (i % 3) == 0) return false;
        if (i < 9) return true;
        long n = 5;
        while (n*n <= i && i % n != 0 && i % (n + 2) != 0)
            n += 6;
        return (n*n > i);
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