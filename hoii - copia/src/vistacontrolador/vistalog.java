package vistacontrolador;
import java.awt.Font;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class vistalog extends JFrame{
    private JTextArea consola;
    private JScrollPane scroll;
    public vistalog(){

    setTitle("visualizar");
    setResizable(false);
    setSize(700, 350);
    setLayout(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    consola = new JTextArea();
    consola.setEditable(false);
    consola.setFont(new Font("Arial", Font.PLAIN, 20));//letra modificaciones
    scroll = new JScrollPane(consola);
    scroll.setBounds(20, 20, 650, 250);
    add(scroll);
    }
    
    public void leerlogs(){
        setVisible(true);
        try {
            consola.append(Files.readString(Path.of("guardarlogs.txt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leerconfig(){
        setVisible(true);
        try {
            consola.append(Files.readString(Path.of("guardarconfiguracion.txt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
