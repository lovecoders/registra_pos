package modelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;



public class GestionComunicacion {
    public void enviarDatos(String nombre, double longitud, double latitud){
        try{
            Socket sc=new Socket("192.168.0.187",8000);
            PrintStream salida=new PrintStream(sc.getOutputStream());
            JSONObject job=new JSONObject();
            job.put("nombre",nombre);
            job.put("longitud",longitud);
            job.put("latitud",latitud);
            salida.println(job.toString());
            sc.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
