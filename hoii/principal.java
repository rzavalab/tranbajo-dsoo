public class principal {
    private tablero Tablero;
    private gui Gui;
    private boolean turno = true, resultado;

    public principal(){
        Tablero = new tablero();
        Gui = new gui();
        Gui.settablero(Tablero);
        Tablero.setgui(Gui);
        Gui.iniciargui();
        Tablero.imprimirdatos0();
        Tablero.imprimirdatos1();
        Tablero.probabilidades();
        Tablero.imprimirganador();
        while (true) { 
            if (turno) {
                resultado = Tablero.movimientoejercito0();
                if (!resultado) turno = false; // pasa al jugador 0
            } else {
                resultado = Tablero.movimientoejercito1();
                if (!resultado) turno = true; // pasa al jugador 1
            }
        }
    }
}
