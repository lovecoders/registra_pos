package ejercicio47.a47_registra_pos;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import modelo.GestionComunicacion;

public class MainActivity extends AppCompatActivity {
    String nombre;
    CambioLocalizacion loc=new CambioLocalizacion();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void activar(View v){
        EditText edtNombre=(EditText)this.findViewById(R.id.edtNombre);
        nombre=edtNombre.getText().toString();
        LocationManager lm=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        //dependiendo del texto que muestre el botón
        //se activa o desactiva el servicio
        if(((Button)v).getText().equals("Activar")){
            try {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, loc);
            }
            catch(SecurityException ex){
                ex.printStackTrace();
            }
            ((Button)v).setText("Desactivar");
        }else{
            try {
                lm.removeUpdates(loc);
            }
            catch(SecurityException ex){
                ex.printStackTrace();
            }
            ((Button)v).setText("Activar");
        }
    }
    class CambioLocalizacion implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            TareaCom tc=new TareaCom();
            tc.execute(nombre, String.valueOf(location.getLongitude()),String.valueOf(location.getLatitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    class TareaCom extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            GestionComunicacion gcom=new GestionComunicacion();
            gcom.enviarDatos(params[0],Double.parseDouble(params[1]),Double.parseDouble(params[1]));
            return null;
        }
    }
}
