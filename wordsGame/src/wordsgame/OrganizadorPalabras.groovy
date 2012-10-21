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
        agregarSubpalabra(palabra, palDic)
    }
}

palDic.each{ ltrs, sps ->
    sps.sort(new ComparadorPalabras())
    print "$ltrs:"
    sps.each { print " $it"}
    println ""
}
