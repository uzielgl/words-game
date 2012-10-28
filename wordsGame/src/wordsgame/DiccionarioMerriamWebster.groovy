package wordsgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.methods.HttpGet;

/**
*
* @author jaguilar modified by uzielgl
*/
class DiccionarioMerriamWebster {
    // El primer valor a reemplazar es la palabra a buscar, el segundo es la llave que
    // se define abajo
    private static String url = "http://www.dictionaryapi.com/api/v1/references/collegiate/xml/%s?key=%s"
    private static String llave = "3519ef05-6e61-46a6-a5f5-34a160b85774"
    
    public static void main(String[] args){
        
        def a = new DiccionarioMerriamWebster()
        println a.obtenerDefinicion("owns");
        
    }
    
    
    /*Método que se la pasa una url y regresa el contenido*/
    public String getContent(String url){
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        
        // Get the response
        def textView = new StringBuffer();
        BufferedReader rd = new BufferedReader
          (new InputStreamReader(response.getEntity().getContent()));

        def line = "";
        while ((line = rd.readLine()) != null) {
            textView.append(line)
        } 
        return textView
    }
    
    /**
* Método que debe invocar al servicio web de www.dictionaryapi.com y regresar
* la definición de una palabra.
*/
    public String obtenerDefinicion(String palabra) {
        def url = System.out.sprintf(url, palabra, llave)
        def content = getContent( url )
        
        return extraerDefinicion(content)
    }
    
    /***
* Metodo que extrae la definición del XML resultante de una consulta al
* diccionario de Merriam Webster.
* Una llamada puede regresar una definición o una serie de sugerencias si
* no se encuentra la palabra. Este método maneja ambos escenarios.
*/
    public static String extraerDefinicion(String xml) {
        def entry_list = new XmlParser().parseText(xml)
        if (entry_list["entry"] != null && entry_list["entry"].size() > 0) {
            return "Definición: " + entry_list["entry"][0]["def"][0].value().collect{ it.value()[0] }.join(";")
        }
        else if (entry_list["suggestion"] != null && entry_list["suggestion"].size() > 0) {
            return "Sugerencias: " + entry_list["suggestion"].collect{ it.value()[0]}.join(",")
        }
    }
}
