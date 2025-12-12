package modelo;
import java.io.Serializable;
public class espadachin extends soldado implements Serializable{
    private static final long serialVersionUID = 1L;
    private int largoespada;
    public espadachin(String ejer, int fila, int columna, int nacion, int terreno){
        super("e"+ejer,fila,columna,nacion,terreno);
        int variacionvida=(int)(Math.random()*3);
        setnivelataque(10);
        setniveldefensa(8);
        setnivelvida(8+variacionvida);
        int vidaporterreno=getnivelvida();
        if(nacion==1 && terreno==1)
        setnivelvida(vidaporterreno++);
        if(nacion==2 && terreno==2)
        setnivelvida(vidaporterreno++);
        if(nacion==3 && terreno==3)
        setnivelvida(vidaporterreno++);
        if(nacion==4 && terreno==4)
        setnivelvida(vidaporterreno++);
        if(nacion==5 && terreno==1 || terreno==2 || terreno==5)
        setnivelvida(vidaporterreno++);
    }
    public void crear_muro_escudos(){

    }
    
}