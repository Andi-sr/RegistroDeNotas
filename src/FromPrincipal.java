import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class FromPrincipal extends JFrame {

    public static void mostrarFormulario() {
         JButton buttonAlumnos;
         JButton buttonMateria;
         JButton buttonNotas;
         JButton buttonLista;


        JFrame frame = new JFrame("REGISTRO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);


        buttonAlumnos = new JButton("Registro Alumnos");
        buttonAlumnos.setBounds(70,80,300,50);
        buttonAlumnos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Alumnos alumnos = new Alumnos();
                alumnos.setVisible(true);
            }
        });

        buttonMateria = new JButton("Registro Materias");
        buttonMateria.setBounds(70,150,300,50);
        buttonMateria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Materias materias = new Materias();
                materias.setVisible(true);
            }
        });
        buttonNotas = new JButton("Registro Notas");
        buttonNotas.setBounds(70,220,300,50);
        buttonNotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Notas notas = new Notas();
                notas.setVisible(true);
            }
        });

        buttonLista = new JButton("Registro Lista");
        buttonLista.setBounds(70,290,300,50);
        buttonLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    Registros registros = new Registros();
                    registros.setVisible(true);
            ;}

        });

        frame.add(buttonAlumnos);
        frame.add(buttonMateria);
        frame.add(buttonNotas);
        frame.add(buttonLista);



        frame.setVisible(true);
    }

    public static void main(String[] args) {
         mostrarFormulario();
        //Alumnos alumnos = new Alumnos();
        //Materias materias  = new Materias();
     //  Notas notas = new Notas();


    }

}
