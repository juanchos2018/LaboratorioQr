package com.example.laboratorioqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {


    Button botonir,btncopiar;

    TextView txtdireccion;
    String WebUrl = "^((ftp|http|https):\\/\\/)?(www.)?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_-]+(\\.[a-zA-Z]+)+((\\/)[\\w#]+)*(\\/\\w+\\?[a-zA-Z0-9_]+=\\w+(&[a-zA-Z0-9_]+=\\w+)*)?$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configurarLector();
        txtdireccion =(TextView)findViewById(R.id.tvresult);

        botonir=(Button)findViewById(R.id.idbotonir);
        btncopiar=(Button)findViewById(R.id.idbotoncopiar);

        botonir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccion=txtdireccion.getText().toString();
                if (txtdireccion.getText()==""){
                    Toast.makeText(getBaseContext(), "no hay nada", Toast.LENGTH_SHORT).show();
                }

                else{

                    Uri uri =Uri.parse(direccion);
                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);

                }
            }
        });

        btncopiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String di="https";
                char p1,p2,p3,p4,p5;
                String di2;

                String WebUrl = "^((ftp|http|https):\\/\\/)?(www.)?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_-]+(\\.[a-zA-Z]+)+((\\/)[\\w#]+)*(\\/\\w+\\?[a-zA-Z0-9_]+=\\w+(&[a-zA-Z0-9_]+=\\w+)*)?$";
                String direccion=txtdireccion.getText().toString();
               p1= direccion.charAt(0);
               p2= direccion.charAt(1);
               p3= direccion.charAt(2);
                p4=direccion.charAt(3);
                p5=direccion.charAt(4);
                di2= String.valueOf(p1+p2+p3+p4+p5);
                if (di.equals(di2)){
                    Toast.makeText(MainActivity.this, "es una pagina", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "es un codigo", Toast.LENGTH_SHORT).show();
                }


                */

            /*  if (validae(txtdireccion.getText().toString()))
              {
                  Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
              }
              else
              {
                  Toast.makeText(MainActivity.this, "Negativo", Toast.LENGTH_SHORT).show();
              }*/

                if (txtdireccion.getText()==""){
                    Toast.makeText(getBaseContext(), "no hay nada", Toast.LENGTH_SHORT).show();
                }

                else{

                    Toast.makeText(MainActivity.this, txtdireccion.getText().toString(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public boolean validae(String url)

    {
        url =txtdireccion.getText().toString().trim();
        if (url.trim().length()>0){
                if (!url.matches(WebUrl))
                {
                return false;
                }
        }
        return true;

    }




    private void configurarLector(){

        final ImageButton imageButton= (ImageButton)findViewById(R.id.imageView);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new IntentIntegrator(MainActivity.this).initiateScan(); //integrar una nueva intencion a nuesta actividad


            }
        });
    }

    private  void actualiarUITextViews(String resultadoScaneo,String formatoResultado){
        ((TextView)findViewById(R.id.tvFormat)).setText(formatoResultado);
        ((TextView)findViewById(R.id.tvresult)).setText(resultadoScaneo);

    }
    private  void manipularResultado(IntentResult intentResult){
        if(intentResult != null){

            actualiarUITextViews(intentResult.getContents(),intentResult.getFormatName());
        }else{
            Toast.makeText(getApplicationContext(),"No se leyó nada",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        final IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        manipularResultado(intentResult);
    }
}
