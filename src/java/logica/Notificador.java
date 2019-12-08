package logica;

import com.google.gson.Gson;
import dto.Paciente;
import java.util.Observable;
import java.util.Observer;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

public class Notificador implements Observer {

    public static void enviar(DatosParaEnviar dato) throws Exception {
        String URL = "https://fcm.googleapis.com/fcm/send";
        
        HttpPost httpPost = new HttpPost(URL);
        
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "key=AAAABDjj2UM:APA91bGrjdK-KrS5_PYJGecOLcG3zIkccniS3k-NbgoFK9zk3v2j_JD5ODZ58_nA8PJBTkw4R-q9UtNcVu7MV_1Oq-KSU2ngU3UnwBxikUQxjZY4l3hDXqPLuOQwuQjxC0SbLVYYUjRM");
        
        Gson gson = new Gson();
        String parametros = gson.toJson(dato);
        
        System.out.println("Enviando...");
        System.out.println(parametros);
        System.out.println("");
        
        StringEntity entity = new StringEntity(parametros);
        
        httpPost.setEntity(entity);
        
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        BasicResponseHandler responseHandler = new BasicResponseHandler();
        String respuesta = (String) httpClient.execute(httpPost,
                responseHandler);
        System.out.println("Respuesta");
        System.out.println(respuesta);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Paciente) {
            Paciente obj = (Paciente) arg;
            
            String token = obj.getToken();
            String ID = Integer.toString(obj.getCodigoID());
            String nombre = obj.getNombre();
            
            DatosParaEnviar datos = new DatosParaEnviar(token, ID, nombre);
//            DatosParaEnviar datos = new DatosParaEnviar(token, ID, nombre, obj.getSexo());
            try {
                enviar(datos);
            } catch (Exception ex) {
                
            }
        }
    }
    
}