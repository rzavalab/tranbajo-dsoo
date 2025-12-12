import java.io.Serializable;
public class caballero extends soldado implements Serializable{
    private static final long serialVersionUID = 1L;
    private int arma;//1=lanza,0=espada
    private int montar;//1=si,0=no
    public caballero(String ejer, int fila, int columna, int nacion, int terreno){
        super("c"+ejer,fila,columna,nacion,terreno);
        int variacionvida=(int)(Math.random()*3);
        setnivelataque(13);
        setniveldefensa(7);
        setnivelvida(10+variacionvida);
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
    public void montar(){
        if (montar==0) {
            arma=1;
            defender();
        }
    }
    public void desmontar(){
        if (montar==1) {
            arma=0;
            envestir();
        }
    }
    public void envestir(){
        if (montar==1) {
            for(int i=0;i<3;i++){
                atacar();    
            }
        }
        else{
            for(int i=0;i<2;i++){
                atacar();    
            }
        }
    }
}