package vistacontrolador;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.*;
import javax.swing.border.LineBorder;
import modelo.tablero;

public class gui extends JFrame {
    
    private String textodelinput;// es el texto de linput aqui se almacena
    private final int tamañotablero = 10; // tamaño del tablero
    private final int cellSize = 70;   // tamaño de cada cuadro
    private final int startX = 50;     // posición inicial X
    private final int startY = 50;     // posición inicial Y
    private final Object lock = new Object();// para recibir
    private String guardarlog = "\n";
    private String guardarranking = "";
    private JPanel[][] cuadros;
    private JLabel[][] labels;

    private JTextArea consola;// consola
    private JScrollPane scroll;
    private JTextField input;

    private tablero Tablero; // referencia al tablero recibido
    
    public void iniciargui() {
        crearmenu();// crea menu
        crearconsola();// crea consola
        crearinput();// crea input

        setTitle("Juego hoi");
        setSize(600, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel columnas = new JLabel("    A         B         C         D         E         F         G        H          I         J");//agregar columnas
        columnas.setBounds(70, 20, 2000, 20);
        columnas.setFont(new Font("Arial", Font.PLAIN, 22));
        add(columnas);
        for (int fila = 0; fila < tamañotablero; fila++) {// crear filas
            JLabel filas = new JLabel(fila + "");
            filas.setBounds(25, 70 + fila * 72, 150, 30);
            filas.setFont(new Font("Arial", Font.PLAIN, 22));
            add(filas);
        }
        crearcuadrosylabels();

        input.addActionListener(e -> {
            synchronized (lock) {
                textodelinput = input.getText(); // guarda el texto
                input.setText("");      // limpiar
                lock.notify();          // despierta al que está esperando
            }
        });
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("guardarconfiguracion.dat"));
            out.writeObject(Tablero);
            out.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            DataOutputStream out = new DataOutputStream(
                new FileOutputStream("guardarranking.bin")
            );
            out.writeUTF(guardarranking);
            out.close();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        setVisible(true);
    }

    public String recibir() {
        consola.append("> " + "ingrese primero fila columna y direccion en este formato 1 b ar" + "\n");
        synchronized (lock) {
            try {
                lock.wait(); // el programa SE DETIENE aquí
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return textodelinput; // devuelve cuando presionas Enter
        }
    }

    public void poner(String texto){
        consola.append("> " + texto + "\n");
        guardarlog = guardarlog +"> " + texto + "\n";
    }

    public void settablero(tablero Tablero){
        this.Tablero=Tablero;
    }

    private void crearmenu() {
        JMenuBar barra = new JMenuBar();

        JMenu archivo = new JMenu("Archivo");// archivo

        JMenuItem nuevo = new JMenuItem("Nuevo");
        nuevo.addActionListener(e -> { 
            Tablero.inicializarejercitos();
            actualizar();
            try {
                ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("guardarconfiguracion.dat"));
                out.writeObject(Tablero);
                out.close();
                Tablero.imprimirdatos0();
                Tablero.imprimirdatos1();
                Tablero.probabilidades();
                Tablero.imprimirganador();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });
        archivo.add(nuevo);

        JMenuItem abrir = new JMenuItem("Abrir");
        abrir.addActionListener(e -> {
            try {
                ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("guardar.dat"));
                Tablero = (tablero) in.readObject();
                in.close();
                Tablero.setgui(this); 
                JOptionPane.showMessageDialog(this, "cargado correctamente");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            actualizar();
         });
        archivo.add(abrir);
        

        JMenuItem abrirLogs = new JMenuItem("Abrir logs");
        abrirLogs.addActionListener(e -> {try {
                DataInputStream in = new DataInputStream(
                    new FileInputStream("guardarlog.bin")
                );
                guardarlog = in.readUTF();
                in.close();
                poner(guardarlog);
                JOptionPane.showMessageDialog(this, "Cargado correctamente");
            } catch (Exception e3) {
                e3.printStackTrace();
            }});
        archivo.add(abrirLogs);

        JMenuItem guardar = new JMenuItem("Guardar");
        guardar.addActionListener(e -> { 
            try {
                ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("guardar.dat"));
                out.writeObject(Tablero);
                out.close();
                JOptionPane.showMessageDialog(this, "guardado correctamente");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });
        archivo.add(guardar);

        JMenuItem guardar_logs = new JMenuItem("Guardar logs");
        guardar_logs.addActionListener(e -> {
            try {
                DataOutputStream out = new DataOutputStream(
                    new FileOutputStream("guardarlog.bin")
                );
                out.writeUTF(guardarlog);
                out.close();
                JOptionPane.showMessageDialog(this, "guardado correctamente");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
         });
        archivo.add(guardar_logs);

        JMenuItem guardar_ranking = new JMenuItem("Guardar ranking");
        guardar_ranking.addActionListener(e -> { 
        try {
            DataOutputStream out = new DataOutputStream(
                new FileOutputStream("guardarranking.bin")
            );
            guardarranking = "jugador 0 total de bajas enemigas: " + Tablero.getmuertes0() + "\n" + "   jugador 1 total de bajas enemigas: " + Tablero.getmuertes1() + "\n";
            out.writeUTF(guardarranking);
            out.close();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            DataInputStream in = new DataInputStream(
                new FileInputStream("guardarranking.bin"));
            guardarranking = in.readUTF();
            in.close();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        poner(guardarranking);
        });
        archivo.add(guardar_ranking);

        JMenuItem guardar_config = new JMenuItem("Guardar configuracion");
        guardar_config.addActionListener(e -> { 
            Path guardarconfiguracion = Path.of("guardarconfiguracion.dat");
            Path guardaranteriror = Path.of("guardar.dat");
            try {
                Files.copy(guardarconfiguracion, guardaranteriror, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        });
        archivo.add(guardar_config);

        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(e -> {
            System.exit(0);
        });
        archivo.add(salir);

        JMenu ver = new JMenu("Ver");// ver

        JMenuItem mostrarConsola = new JMenuItem("Mostrar consola");
        mostrarConsola.addActionListener(e -> {mostrarocultarconsola(); });
        ver.add(mostrarConsola);

        JMenu ayuda = new JMenu("Ayuda");// ayuda

        JMenuItem sobre = new JMenuItem("Sobre el juego");
        sobre.addActionListener(e -> { });
        ayuda.add(sobre);



        barra.add(archivo);
        barra.add(ver);
        barra.add(ayuda);

        setJMenuBar(barra);
    }
    
    public void crearconsola(){// crea una consola
        consola = new JTextArea();
        consola.setEditable(false);
        consola.setFont(new Font("Arial", Font.PLAIN, 20));//letra modificaciones
        scroll = new JScrollPane(consola);
        scroll.setBounds(820, 50, 680, 500);
        consola.append("Consola:\n");
        add(scroll);
    }

    public void mostrarocultarconsola(){// muestra o oculta la consola
        scroll.setVisible(!scroll.isVisible());
        repaint();
    }

    public void crearinput(){// crea un input
        input = new JTextField();
        input.setFont(new Font("Arial", Font.PLAIN, 20));//letra modificaciones
        input.setBounds(820, 580, 680, 160);
        add(input);
    }

    public void actualizar() {//actualia los labels
        for (int fila = 0; fila < tamañotablero; fila++) {
            for (int columna = 0; columna < tamañotablero; columna++) {
                labels[fila][columna].setText(
                    Tablero.mostrarcomostring(fila, columna)
                );
            }
        }

        revalidate();
        repaint();
    }
    public void crearcuadrosylabels(){
        cuadros = new JPanel[tamañotablero][tamañotablero];
        labels = new JLabel[tamañotablero][tamañotablero];

        for (int fila = 0; fila < tamañotablero; fila++) {
            for (int columna = 0; columna < tamañotablero; columna++) {

                JPanel cuadro = new JPanel();
                cuadro.setBounds(
                    startX + 20 + columna * (cellSize + 2),
                    startY + fila * (cellSize + 2),
                    cellSize,
                    cellSize
                );

                cuadro.setBorder(new LineBorder(Color.BLACK, 2));
                cuadro.setLayout(new BorderLayout());

                JLabel texto = new JLabel(Tablero.mostrarcomostring(fila, columna), SwingConstants.CENTER);

                cuadro.add(texto, BorderLayout.CENTER);

                cuadros[fila][columna] = cuadro;
                labels[fila][columna] = texto;

                add(cuadro);
            }
        }
        setVisible(true);
    }
}

