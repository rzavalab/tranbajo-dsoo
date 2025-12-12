import java.io.Serializable;
public class arquero extends soldado implements Serializable{
    private static final long serialVersionUID = 1L;
    private int flechas;
    public arquero(String ejer, int fila, int columna, int nacion, int terreno){
        super("a"+ejer,fila,columna,nacion,terreno);
        int variacionvida=(int)(Math.random()*3);
        setnivelataque(7);
        setniveldefensa(3);
        setnivelvida(3+variacionvida);
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
    public void disparar(){
        flechas--;
    } 
}