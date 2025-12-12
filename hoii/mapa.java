public class mapa {
    public int terreno;
    public mapa() {
        terreno=(int)(Math.random()*5)+1;// bosque, campo abierto, montaña, desierto, playa 0-6
    }
    public String getterrenonombre(){
        String terrenonnombre="0";
        switch (terreno) {
            case 1:terrenonnombre="Bosque";break;
            case 2:terrenonnombre="Campo abierto";break;
            case 3:terrenonnombre="Montaña";break;
            case 4:terrenonnombre="Desierto";break;
            case 5:terrenonnombre="Playa";break;
        }
        return terrenonnombre;}
}