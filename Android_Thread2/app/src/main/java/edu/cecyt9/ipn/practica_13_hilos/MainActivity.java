package edu.cecyt9.ipn.practica_13_hilos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText entrada;
    private TextView salida;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada = (EditText) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);
    }

    public void calcularOperacion(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        MiThread thread = new MiThread(n);
        thread.start();
        TR2 fibo = new TR2(n);
        fibo.start();
    }

    public int fibonacci(int n) {
        int n1 = 1;
        int n2 = 2;
        for (int i = 2; i <= n; i++) {
            n2 = n1 + n2;
            n1 = n2 - n1;
        }
        return n2;
    }

    public int factorial(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
            SystemClock.sleep(1000);
        }

        return res;

    }

    class MiThread extends Thread {
        private int n, res, fib;

        public MiThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            res = factorial(n);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    salida.append("Java-Factorial: " + res + "\n");
                }
            });
        }
    }

    class TR2 extends Thread {
        private int n, fib;

        public TR2(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            fib = fibonacci(n);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    salida.append("Java-Fibonacci: " + fib + "\n");
                    Toast.makeText(MainActivity.this, "JuanDanielCR 6IM7 JAVA-Thread", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void calcularOperacionAsync(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        MiTarea tarea = new MiTarea();
        tarea.execute(n);
        AsyncFibo tare2= new AsyncFibo();
        tare2.execute(n);
    }


//    ejemplo AsyncTask
//    class MiTarea extends AsyncTask<Integer, Void, Integer> {
//
//        @Override
//
//        protected Integer doInBackground(Integer... n) {
//
//            return factorial(n[0]);
//
//        }
//
//        @Override
//
//        protected void onPostExecute(Integer res) {
//
//            salida.append(res + "\n");
//
//        }
//
//    }

    //    ejemplo AsyncTask whit progressdialog
//    class MiTarea extends AsyncTask<Integer, Integer, Integer> {
//
//        private ProgressDialog progreso;
//
//        @Override protected void onPreExecute() {
//
//            progreso = new ProgressDialog(MainActivity.this);
//
//            progreso.setProgressStyle(ProgressDialog.
//                    STYLE_HORIZONTAL);
//
//            progreso.setMessage("Calculando...");
//
//            progreso.setCancelable(false);
//
//            progreso.setMax(100);
//
//            progreso.setProgress(0);
//
//            progreso.show();
//
//        }
//
//        @Override protected Integer doInBackground(Integer... n) {
//
//            int res = 1;
//
//            for (int i = 1; i <= n[0]; i++) {
//
//                res *= i;
//
//                SystemClock.sleep(1000);
//
//                publishProgress(i*100 / n[0]);
//
//            }
//
//            return res;
//
//        }
//
//        @Override protected void onProgressUpdate(Integer... porc) {
//
//            progreso.setProgress(porc[0]);
//
//        }
//
//        @Override protected void onPostExecute(Integer res) {
//
//            progreso.dismiss();
//
//            salida.append(res + "\n");
//
//        }
//
//    }

    //    ejemplo AsyncTask whit progressdialog cancel
    class MiTarea extends AsyncTask<Integer, Integer, Integer> {

        private ProgressDialog progreso;

        @Override
        protected void onPreExecute() {

            progreso = new ProgressDialog(MainActivity.this);

            progreso.setProgressStyle(ProgressDialog.
                    STYLE_HORIZONTAL);

            progreso.setMessage("JCDR 6IM7 Factorial...");

            progreso.setCancelable(true);

            progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {

                    MiTarea.this.cancel(true);

                }

            });

            progreso.setMax(100);

            progreso.setProgress(0);

            progreso.show();

        }

        @Override
        protected Integer doInBackground(Integer... n) {

            int res = 1;

            for (int i = 1; i <= n[0] && !isCancelled(); i++) {
                res *= i;

                SystemClock.sleep(1000);

                publishProgress(i * 100 / n[0]);

            }

            return res;

        }

        @Override
        protected void onProgressUpdate(Integer... porc) {

            progreso.setProgress(porc[0]);

        }

        @Override
        protected void onPostExecute(Integer res) {

            progreso.dismiss();

            salida.append("Async-Factorial: "+res + "\n");

        }

        @Override
        protected void onCancelled() {

            salida.append("cancelado\n");

        }

    }

class AsyncFibo extends AsyncTask<Integer, Integer, Integer>{

    private ProgressDialog cargador;

    @Override
    protected Integer doInBackground(Integer... params) {
        super.onPreExecute();
        int n1 = 1;
        int n2 = 2;
        for (int i = 2; i <= params[0]; i++) {
            n2 = n1 + n2;
            n1 = n2 - n1;
        }
        return n2;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        salida.append("Async-Fibonnaci"+integer+"\n");
    }
}
}