package modelo;
import java.io.Serializable;
public class lancero extends soldado implements Serializable{
    private static final long serialVersionUID = 1L;
    private int largolanza;
    public lancero(String ejer, int fila, int columna, int nacion, int terreno){
        super("l"+ejer,fila,columna,nacion,terreno);
        int variacionvida=(int)(Math.random()*3);
        setnivelataque(5);
        setniveldefensa(10);
        setnivelvida(5+variacionvida);
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
    public void schiltrom(){
        int defensa=getniveldefensa();
        setniveldefensa(defensa++);
    }
}