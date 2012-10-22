/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsgame
import com.google.gson.Gson
import java.util.Random


class ColeccionPalabras{
    public words 
    
    public ColeccionPalabras(){
        words = loadWords() //Will be the words of seven letters
    }
    
    def loadWords(){
        Gson gson = new Gson()
        //Abrimos el archivo
        def myScanner = new Scanner( new File( "diccionario_7.txt" ) )
        def text = "";
        while( myScanner.hasNext()){
            text += myScanner.next() 

        }
        def dic = [:]
        def midic = gson.fromJson( text, dic.getClass() )
        return midic
    }
    
    public String getRandomWord(){
        def keys = words.keySet() as String[]
        
        def r = new Random()
        def ran = r.nextInt( keys.size() )
        
        return keys[ 0 ]
        //return keys[ ran ]
    }
    
    public String[] getSubStrings( word ){
        return words[ word ]
    }
    
    public String getDefinition( word ){
        return "Some definition for word"
    }
    
    public String mixWord( String w ){
        def b = w.split("");
        b = b[1..-1];
        Collections.shuffle( b );
        return b.join("");
    }
}



