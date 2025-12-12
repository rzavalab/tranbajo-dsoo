import java.io.Serializable;

public class soldado implements Serializable{
    private String nombre;
    private int nivelataque, niveldefensa, nivelvida, velocidad=0, nacion, terreno;
    private int fila,columna;
    private boolean vive=true;

    public soldado(String nombre, int fila, int columna, int nacion, int terreno) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
        this.nacion = nacion;
        this.terreno=terreno;
    }
    public String getnombre() {return nombre;}
    public int getfila() {return fila;}
    public int getcolumna() {return columna;}
    public int getnivelvida(){return nivelvida;}
    public int getnivelataque(){return nivelataque;}
    public int getniveldefensa(){return niveldefensa;}
    public String getnacion(){
        String nacionnombre="0";
        switch (nacion) {
            case 1:nacionnombre="Inglaterra";break;
            case 2:nacionnombre="Francia";break;
            case 3:nacionnombre="Castilla-Aragon";break;
            case 4:nacionnombre="Moros";break;
            case 5:nacionnombre="Sacro imperio Romano-Germanico";break;
        }
        return nacionnombre;}

    public void setnivelataque(int ataque){nivelataque=ataque;}
    public void setniveldefensa(int defensa){niveldefensa=defensa;}
    public void setnivelvida(int vida) {nivelvida=vida;}
    public void setfilascolumnas(int f,int c) {fila=f;columna=c;}

    public void atacar(){velocidad++;}
    public void defender(){velocidad=0;}
    public void retroceder(){if(velocidad>0)velocidad=0;defender();}
    public void ser_atacado(){nivelvida--;}
    public void huir(){velocidad=velocidad+2;}
    public void morir(){vive=false;}
}
