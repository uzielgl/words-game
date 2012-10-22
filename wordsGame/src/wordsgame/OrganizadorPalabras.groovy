package wordsgame;

def agregarSubpalabra(sp, diccionario) {
    diccionario.each { pal, sps ->
        def spArr = sp.toCharArray()
        Arrays.sort(spArr)
        if (ComparadorPalabras.esSubconjunto(spArr,pal.toCharArray())) {
            sps.add(sp)
        }
    }
}

def agregarPalabra(palabra, diccionario) {
    def letras = palabra.toList().sort().join("")
    if (diccionario.containsKey(letras)) {
        diccionario[letras].add(palabra)
        return
    }
    diccionario[letras] = [palabra]
}

def archivo = "list-long.txt"
def palDic = [:]
def entrada = new Scanner(new File(archivo))
while (entrada.hasNext()) {
    def palabra = entrada.next()
    if (palabra.size() == 7) {
        agregarPalabra(palabra, palDic)
    }
}
entrada.close()

entrada = new Scanner(new File(archivo))
while (entrada.hasNext()) {
    def palabra = entrada.next()
    if (palabra.size() < 7 && palabra.size() > 2) {
        agregarSubpalabra( palabra, palDic )
    }
}

//Cambiamos el codigo para generar los que se requieren
diccionario_7 = [:]

palDic.each{ ltrs, sps ->
    if ( sps.size() > 9 ) {
        sps.sort(new ComparadorPalabras())
        diccionario_7["$ltrs"] = sps
        //diccionario_7.add( [ "$ltrs" : sps ] )
    }
}


//Generamos JSON
import com.google.gson.Gson    
Gson gson = new Gson()
mi_json = gson.toJson( diccionario_7, diccionario_7.getClass() )

//Guardamos al disco duro
file = new File("diccionario_7.txt")
file.write( mi_json )

//Abrimos el archivo
myScanner = new Scanner( new File( "diccionario_7.txt" ) )
text = "";
while( myScanner.hasNext()){
    text += myScanner.next() 
    
}

midic = gson.fromJson( text, diccionario_7.getClass() )
//*println midic.keySet()
miotrdic = loadWords()
println miotrdic.keySet()

def loadWords(){
    Gson gson = new Gson()
    //Abrimos el archivo
    myScanner = new Scanner( new File( "diccionario_7.txt" ) )
    text = "";
    while( myScanner.hasNext()){
        text += myScanner.next() 

    }
    dic = [:]
    midic = gson.fromJson( text, dic.getClass() )

}









