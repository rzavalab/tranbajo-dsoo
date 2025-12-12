import java.io.Serializable;
import java.util.*;

public class tablero extends mapa implements Serializable{

    private static final long serialVersionUID = 1L;
    private transient gui Gui;
    private final int tamaño=10;// tamaño del tablero
    private int nacion0,nacion1;// tipo de nacion por ejercito
    private int tamañoejercitomod;// tamño de ejercito para los dos
    private int clasesoldado;// del 1 al 4 definira la clase de querrero
    private int[] posiciónquitada;// aqui se guarda la pocision de pocisiones quitada
    private int fila, columna;// eso
    private double sumadevidas1 = 0, sumadevidas0 = 0;
    private int muertes0 = 0, muertes1 = 0;

    private String nombresoldadoponer;// nombre del soldado a poner
    
    private soldado[][] tablero;// lugar donde situar al soldado

    private ArrayList<soldado> ejercito0;// array de soldados 0
    private ArrayList<soldado> ejercito1;// array de soldados 1
    private int creados0, creados1;// sirve para el while al crear soldados

    List<int[]> posiciones = new ArrayList<>();// pocisiones libres de soldados "vacias"

    private int arqeuro0=0, espadachin0=0, caballero0=0, lancero0=0;
    private int arqeuro1=0, espadachin1=0, caballero1=0, lancero1=0;

    public tablero(){
        inicializarejercitos();
    }

    public boolean inicializarejercitos(){
        creados0 = 0;// reinicio
        creados1 = 0;// reinicio
        nacion0 = (int)(Math.random()*5)+1;// Inglaterra->bosque, Francia->campo abierto, Castilla-Aragón->montaña, Moros->desierto, Sacro Imperio Romano-Germánico->bosque, playa, campo abierto
        nacion1 = (int)(Math.random()*5)+1;// Inglaterra->bosque, Francia->campo abierto, Castilla-Aragón->montaña, Moros->desierto, Sacro Imperio Romano-Germánico->bosque, playa, campo abierto
        if(nacion0==nacion1&&nacion0>0)// evita repeticion
            nacion0--;
        else
            nacion0++;
        tamañoejercitomod = (int)(Math.random()*10+1);
        
        tablero = new soldado[tamaño][tamaño];// aqui reinicia un lugar de 10 x 10 donde solo se guardan soldados

        ejercito0 = new ArrayList<>();// reinicia el array
        ejercito1 = new ArrayList<>();// reinicia el array

        posiciones.clear();// limpia las pociciones
        for (int x = 0; x < tamaño; x++) { // genera 100 pocisiones 
            for (int y = 0; y < tamaño; y++) {
            posiciones.add(new int[]{x, y});
            }
        }
        while (creados0 < tamañoejercitomod) {//c crear ejercito0
            clasesoldado=(int)(Math.random()*4)+1;
            posiciónquitada = posiciones.remove((int)(Math.random() * posiciones.size()));// saca una pocision del 0 al 99 y la quita deveulve dos num
            fila = posiciónquitada[0];
            columna = posiciónquitada[1];
            nombresoldadoponer = "0X" + (creados0 + 1);
            switch (clasesoldado) {
                case 1: 
                soldado espadachin = new espadachin(nombresoldadoponer, fila, columna, nacion0, terreno);// crear soldado
                tablero[fila][columna] = espadachin;// asiganr a la pocision
                ejercito0.add(espadachin);// asiganr al ejercito
                creados0++;// aumentar los creados
                espadachin0++;
                sumadevidas0 = espadachin.getnivelvida() + sumadevidas0;
                break;
                case 2:
                soldado lancero = new lancero(nombresoldadoponer, fila, columna, nacion0, terreno);// crear soldado
                tablero[fila][columna] = lancero;// asiganr a la pocision
                ejercito0.add(lancero);// asiganr al ejercito
                creados0++;// aumentar los creados
                lancero0++;
                sumadevidas0 = lancero.getnivelvida() + sumadevidas0;
                break;
                case 3:
                soldado caballero = new caballero(nombresoldadoponer, fila, columna, nacion0, terreno);// crear soldado
                tablero[fila][columna] = caballero;// asiganr a la pocision
                ejercito0.add(caballero);// asiganr al ejercito
                creados0++;// aumentar los creados
                caballero0++;
                sumadevidas0 = caballero.getnivelvida() + sumadevidas0;
                break;
                case 4:
                soldado arquero = new arquero(nombresoldadoponer, fila, columna, nacion0, terreno);// crear soldado
                tablero[fila][columna] = arquero;// asiganr a la pocision
                ejercito0.add(arquero);// asiganr al ejercito
                creados0++;// aumentar los creados
                arqeuro0++;
                sumadevidas0 = arquero.getnivelvida() + sumadevidas0;
                break;
            }
        }
        tamañoejercitomod = (int)(Math.random()*10+1);
        while (creados1 < tamañoejercitomod) {//c crear ejercito1
            clasesoldado=(int)(Math.random()*4)+1;
            posiciónquitada = posiciones.remove((int)(Math.random() * posiciones.size()));// saca una pocision del 0 al 99 y la quita deveulve dos num
            fila = posiciónquitada[0];
            columna = posiciónquitada[1];
            nombresoldadoponer = "1X" + (creados1 + 1);
            switch (clasesoldado) {
                case 1: 
                soldado espadachin = new espadachin(nombresoldadoponer, fila, columna, nacion0, terreno);// crear soldado
                tablero[fila][columna] = espadachin;// asiganr a la pocision
                ejercito1.add(espadachin);// asiganr al ejercito
                creados1++;// aumentar los creados
                espadachin1++;
                sumadevidas1 = espadachin.getnivelvida() + sumadevidas1;
                break;
                case 2:
                soldado lancero = new lancero(nombresoldadoponer, fila, columna, nacion0, terreno);// crear soldado
                tablero[fila][columna] = lancero;// asiganr a la pocision
                ejercito1.add(lancero);// asiganr al ejercito
                creados1++;// aumentar los creados
                lancero1++;
                sumadevidas1 = lancero.getnivelvida() + sumadevidas1;
                break;
                case 3:
                soldado caballero = new caballero(nombresoldadoponer, fila, columna, nacion0, terreno);// crear soldado
                tablero[fila][columna] = caballero;// asiganr a la pocision
                ejercito1.add(caballero);// asiganr al ejercito
                creados1++;// aumentar los creados
                caballero1++;
                sumadevidas1 = caballero.getnivelvida() + sumadevidas1;
                break;
                case 4:
                soldado arquero = new arquero(nombresoldadoponer, fila, columna, nacion0, terreno);// crear soldado
                tablero[fila][columna] = arquero;// asiganr a la pocision
                ejercito1.add(arquero);// asiganr al ejercito
                creados1++;// aumentar los creados
                arqeuro1++;
                sumadevidas1 = arquero.getnivelvida() + sumadevidas1;
                break;
            }
        }
        return false;
    }

    public void setgui(gui Gui){
        this.Gui=Gui;
    }

    public boolean  movimientoejercito0() {
        Gui.poner("turno ejercito 0");
        String modificar = Gui.recibir();// lo agrega a un string 
        int filainicial;
        try {
            filainicial = Integer.parseInt(modificar.substring(0, 1));// saca fila
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            Gui.poner("ingrese una fila valida");
            return true;
        }
        int columnainicial = 0;
        int filafinal = 0, columnafinal = 0;
        switch (modificar.substring(2, 3)) {// saca columna
            case "a":columnainicial=0; break;
            case "b":columnainicial=1; break;
            case "c":columnainicial=2; break;
            case "d":columnainicial=3; break;
            case "e":columnainicial=4; break;
            case "f":columnainicial=5; break;
            case "g":columnainicial=6; break;
            case "h":columnainicial=7; break;
            case "i":columnainicial=8; break;
            case "j":columnainicial=9; break;
            default: Gui.poner("ingrese una columna valida");return true;
        }
        for (soldado soldado : ejercito0){
            if (ejercito1.contains(tablero[filainicial][columnainicial])) {
                    Gui.poner("Ingrese una pocision de tu ejercito");
                    return true;
                }
            if (soldado.getfila() == filainicial && soldado.getcolumna() == columnainicial) {
                filafinal=soldado.getfila();
                columnafinal=soldado.getcolumna();
                switch (modificar.substring(4, 6)) {
                    case "ar":filafinal--; break;
                    case "ab":filafinal++; break;
                    case "de":columnafinal++; break;
                    case "iz":columnafinal--; break;
                    default:
                        Gui.poner("Ingrese una direccion correcta");
                        return true;
                }
                if (filafinal < 0 || filafinal>= tamaño || columnafinal < 0 || columnafinal >= tamaño) {
                    Gui.poner("Ingrese una pocision que no se salga del mapa");
                    return true;
                }
                if (tablero[filafinal][columnafinal] != null && ejercito0.contains(tablero[filafinal][columnafinal])) {
                    Gui.poner("Ingrese una pocision no ocupada");
                    return true;
                }
                posiciones.add(10*filafinal+columnafinal,new int[]{filafinal,columnafinal});//llena espacio donde estaba
                switch (modificar.substring(4, 6)) {//vacia soldado anterior
                    case "ar": tablero[filainicial][columnainicial]=null; break;
                    case "ab": tablero[filainicial][columnainicial]=null; break;
                    case "de": tablero[filainicial][columnainicial]=null; break;
                    case "iz": tablero[filainicial][columnainicial]=null; break;
                }
                if(ejercito1.contains(tablero[filafinal][columnafinal])){
                    if(batalla(filafinal,columnafinal)==0){
                        posiciones.remove(filafinal*10+columnafinal);//remover donde estara
                        tablero[filafinal][columnafinal] = soldado;//poner al nuevo soldado
                        soldado.setfilascolumnas(filafinal, columnafinal);
                        Gui.actualizar();
                        return false;
                    }
                    else{
                        return false;
                    }
                }
                posiciones.remove(filafinal*10+columnafinal);//remover donde estara
                tablero[filafinal][columnafinal]=soldado;//poner al nuevo soldado
                soldado.setfilascolumnas(filafinal, columnafinal);
                Gui.actualizar();
                return false;
            }
        }
        return true;
    }

    public boolean  movimientoejercito1() {
        Gui.poner("turno ejercito 1");
        String modificar = Gui.recibir();// lo agrega a un string 
        int filainicial;
        try {
            filainicial = Integer.parseInt(modificar.substring(0, 1));// saca fila
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            Gui.poner("ingrese una fila valida");
            return true;
        }
        int columnainicial = 0;
        int filafinal = 0, columnafinal = 0;
        switch (modificar.substring(2, 3)) {// saca columna
            case "a":columnainicial=0; break;
            case "b":columnainicial=1; break;
            case "c":columnainicial=2; break;
            case "d":columnainicial=3; break;
            case "e":columnainicial=4; break;
            case "f":columnainicial=5; break;
            case "g":columnainicial=6; break;
            case "h":columnainicial=7; break;
            case "i":columnainicial=8; break;
            case "j":columnainicial=9; break;
            default: Gui.poner("ingrese una columna valida");return true;
        }
        for (soldado soldado : ejercito1){
            if (ejercito0.contains(tablero[filainicial][columnainicial])) {
                    Gui.poner("Ingrese una pocision de tu ejercito");
                    return true;
                }
            if (soldado.getfila() == filainicial && soldado.getcolumna() == columnainicial) {
                filafinal=soldado.getfila();
                columnafinal=soldado.getcolumna();
                switch (modificar.substring(4, 6)) {
                    case "ar":filafinal--; break;
                    case "ab":filafinal++; break;
                    case "de":columnafinal++; break;
                    case "iz":columnafinal--; break;
                    default:
                        Gui.poner("Ingrese una direccion correcta");
                        return true;
                }
                if (filafinal < 0 || filafinal>= tamaño || columnafinal < 0 || columnafinal >= tamaño) {
                    Gui.poner("Ingrese una pocision que no se salga del mapa");
                    return true;
                }
                if (tablero[filafinal][columnafinal] != null && ejercito1.contains(tablero[filafinal][columnafinal])) {
                    Gui.poner("Ingrese una pocision no ocupada");
                    return true;
                }
                posiciones.add(10*filafinal+columnafinal,new int[]{filafinal,columnafinal});//llena espacio donde estaba
                switch (modificar.substring(4, 6)) {//vacia soldado anterior
                    case "ar": tablero[filainicial][columnainicial]=null; break;
                    case "ab": tablero[filainicial][columnainicial]=null; break;
                    case "de": tablero[filainicial][columnainicial]=null; break;
                    case "iz": tablero[filainicial][columnainicial]=null; break;
                }
                if(ejercito0.contains(tablero[filafinal][columnafinal])){
                    if(batalla(filafinal,columnafinal)==0){
                        return false;
                    }
                    else{
                        posiciones.remove(filafinal*10+columnafinal);//remover donde estara
                        tablero[filafinal][columnafinal] = soldado;//poner al nuevo soldado
                        soldado.setfilascolumnas(filafinal, columnafinal);
                        Gui.actualizar();
                        return false;
                    }
                }
                posiciones.remove(filafinal*10+columnafinal);//remover donde estara
                tablero[filafinal][columnafinal]=soldado;//poner al nuevo soldado
                soldado.setfilascolumnas(filafinal, columnafinal);
                Gui.actualizar();
                return false;
            }
        }
        return true;
    }

    public String mostrarcomostring(int i,int j){
        if (tablero[i][j] == null) {
            return"  ";
        } 
        else {
            soldado soldado = tablero[i][j];
            return soldado.getnombre();
        }
    }
    
    public int batalla (int fila, int columna){
        int vida0=0,vida1=0;
        for (soldado soldado : ejercito0) {
            if (soldado.getfila() == fila && soldado.getcolumna() == columna) {}
                vida0=soldado.getnivelvida();
        }
        for (soldado soldado : ejercito1) {
            if (soldado.getfila() == fila && soldado.getcolumna() == columna) {}
                vida1=soldado.getnivelvida();
        }
        double sumavidas=vida0+vida1,numeroazar=(int)(Math.random()*sumavidas)+1;
        if (numeroazar<=vida0){
            for (soldado soldado : ejercito0) {
                if (soldado.getfila() == fila && soldado.getcolumna() == columna) {
                    soldado.setnivelvida(vida0++);
                }
            }
            for (soldado soldado : ejercito1) {
                if (soldado.getfila() == fila && soldado.getcolumna() == columna) {
                    soldado.setnivelvida(0);
                    soldado.morir();
                    muertes0++;
                }
            }
            return 0;
        }
        else{
            for (soldado soldado : ejercito0) {
                if (soldado.getfila() == fila && soldado.getcolumna() == columna) {
                    soldado.setnivelvida(0);
                    soldado.morir();
                    muertes1++;
                }
            }
            for (soldado soldado : ejercito1) {
                if (soldado.getfila() == fila && soldado.getcolumna() == columna) {
                    soldado.setnivelvida(vida1++);
                }
            }
            return 1;
        }
    }

    public void imprimirdatos0(){
        String nacion = "";
        switch (nacion0) {
            case 1:nacion="Inglaterra";break;
            case 2:nacion="Francia";break;
            case 3:nacion="Castilla-Aragon";break;
            case 4:nacion="Moros";break;
            case 5:nacion="Sacro imperio Romano Germanico";break;
        }
        Gui.poner("Ejercito 0: " + nacion + "\n" 
        + "   Soldados creados: " + creados0 + "\n" 
        + "   Espadachines" + espadachin0 + "\n"
        + "   Lanceros" + lancero0 + "\n"
        + "   Caballeros" + caballero0 + "\n"
        + "   Arqueros" + arqeuro0 + "\n");
    }
    public void imprimirdatos1(){
        String nacion = "";
        switch (nacion1) {
            case 1:nacion="Inglaterra";break;
            case 2:nacion="Francia";break;
            case 3:nacion="Castilla-Aragon";break;
            case 4:nacion="Moros";break;
            case 5:nacion="Sacro imperio Romano Germanico";break;
        }
        Gui.poner("Ejercito 1: " + nacion + "\n" 
        + "   Soldados creados: " + creados1 + "\n" 
        + "   Espadachines" + espadachin1 + "\n"
        + "   Lanceros" + lancero1 + "\n"
        + "   Caballeros" + caballero1 + "\n"
        + "   Arqueros" + arqeuro1 + "\n");
    }

    public void probabilidades(){
        Gui.poner("Ejercito 0: " + 100*sumadevidas0/(sumadevidas0 + sumadevidas1) + "% probabilidad de victoria" + "\n");
        Gui.poner("Ejercito 1: " + 100*sumadevidas1/(sumadevidas0 + sumadevidas1) + "% probabilidad de victoria" + "\n");
    }

    public void imprimirganador(){
        double probabilidad = 100*sumadevidas0/(sumadevidas0 + sumadevidas1);
        double aleatorioganador = (Math.random()*100) + 1;int ganador;String nacionganador = "";
        if(aleatorioganador <= probabilidad){ 
            ganador = 0;
            for (soldado soldado : ejercito0) {
                nacionganador = soldado.getnacion();}
            }
        else{
            ganador = 1;
            for (soldado soldado : ejercito1) {
                nacionganador = soldado.getnacion();}
        } 
        Gui.poner("El ganador es el ejercito " + ganador + " de: " + nacionganador + ".\n   Ya que al generar los porcentajes de probabilidad de victoria basada\n   en los niveles de vida de sus soldados y aplicando un experimento \n   aleatorio salió vencedor. (Aleatorio generado: " + aleatorioganador + ")");
    }

    public int getmuertes0(){return muertes0;}
    public int getmuertes1(){return muertes1;}
}