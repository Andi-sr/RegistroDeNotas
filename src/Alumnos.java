import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Alumnos  extends JFrame {

    private JTextField registroAlumno;
    private JTextField nombreCompletoField;
    private JTextField fechaNacimientoField;
    private JTextField Carrera;
    private JButton button;

    public Alumnos() {
        super("REGISTRO DE ALUMNO");
        setLocationRelativeTo(null);


        JLabel registroLabel = new JLabel("Nro.Registro");
        registroLabel.setBounds(10, 100, 80, 30);

        registroAlumno = new JTextField();
        registroAlumno.setBounds(50, 150, 200, 30);

        JLabel nombreCompletoLabel = new JLabel("Nombre Completo:");
        nombreCompletoLabel.setBounds(10, 200, 120, 30);

        nombreCompletoField = new JTextField();
        nombreCompletoField.setBounds(140, 200, 200, 30);

        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaNacimientoLabel.setBounds(10, 250, 130, 30);


        fechaNacimientoField = new JTextField();
        fechaNacimientoField.setBounds(150, 250, 200, 30);

        JLabel carreraLabel = new JLabel("Carrera:");
        carreraLabel.setBounds(10, 300, 90, 30);

        Carrera = new JTextField();
        Carrera.setBounds(100, 300, 200, 30);


        button = new JButton("Guardar");
        button.setBounds(150, 400, 100, 30);
        button.addActionListener(e -> {
            try {
                GuardarDatos();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        });

        setLayout(null);
        setSize(400, 500);
        setVisible(true);

        add(registroLabel);
        add(registroAlumno);
        add(nombreCompletoLabel);
        add(nombreCompletoField);
        add(fechaNacimientoLabel);
        add(fechaNacimientoField);
        add(carreraLabel);
        add(Carrera);
        add(button);

    }


    public JTextField getRegistroAlumno() {return registroAlumno;}
    public void setRegistroAlumno(JTextField registroAlumno) {this.registroAlumno = registroAlumno;}

    public JTextField getNombreCompletoField() {return nombreCompletoField;}
    public void setNombreCompletoField(JTextField nombreCompletoField) {this.nombreCompletoField = nombreCompletoField;}

    public JTextField getFechaNacimientoField() {return fechaNacimientoField;}
    public void setFechaNacimientoField(JTextField fechaNacimientoField) {this.fechaNacimientoField = fechaNacimientoField;}

    public JTextField getCarrera() {return Carrera;}
    public void setCarrera(JTextField carrera) {Carrera = carrera;}


    private void GuardarDatos() throws ParseException {
        String url = "jdbc:postgresql://localhost:5432/PrimerParcial";
        String usuario = "postgres";
        String pass = "andy";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaNacimiento = dateFormat.parse(fechaNacimientoField.getText());
        java.sql.Date fechaSQL = new java.sql.Date(fechaNacimiento.getTime());


        try (Connection connection = DriverManager.getConnection(url, usuario, pass)) {
            String query = "INSERT INTO alumnos (registro_alum, nombre_completo, fecha_nac, carrera) " +
                    "VALUES (?, ?, ?, ?)";

            String insertAlumnoMateriaQuery = "INSERT INTO ALUMNOS_HAS_MATERIA (Registro_alum, Codigo_Materia) VALUES (?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, Integer.parseInt(registroAlumno.getText()));
                statement.setString(2, nombreCompletoField.getText());
                statement.setDate(3, fechaSQL);
                statement.setString(4, Carrera.getText());
                statement.executeUpdate();
                System.out.println("Datos guardados exitosamente en la base de datos.");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
