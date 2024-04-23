import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.ParseException;

public class Notas extends JFrame {

    private JTextField primerP;
    private JTextField segundoP;
    private JTextField examFinal;
    private JTextField tarea;
    private JTextField registroAlum;
    private JTextField codigoMateria;
    private JButton button;

    public Notas() {
        super("REGISTRO NOTAS");
        setLocationRelativeTo(null);


        JLabel uParcialLabel = new JLabel("1erParcial");
        uParcialLabel.setBounds(10, 100, 80, 30);

        primerP = new JTextField();
        primerP.setBounds(50, 150, 200, 30);

        JLabel dParcial = new JLabel("2doParcial:");
        dParcial.setBounds(10, 200, 120, 30);

        segundoP = new JTextField();
        segundoP.setBounds(140, 200, 200, 30);

        JLabel finalLabel = new JLabel("Examen Final:");
        finalLabel.setBounds(10, 250, 130, 30);

        examFinal = new JTextField();
        examFinal.setBounds(150, 250, 200, 30);
        JLabel tareasLabel = new JLabel("Tarea:");
        tareasLabel.setBounds(10, 300, 90, 30);

        tarea = new JTextField();
        tarea.setBounds(100, 300, 200, 30);


        JLabel registroLabel = new JLabel("Registro Estudiante:");
        registroLabel.setBounds(10, 350, 60, 30);

        registroAlum = new JTextField();
        registroAlum.setBounds(70, 350, 200, 30);

        JLabel codigoLabelLabel = new JLabel("Codigo Materia:");
        codigoLabelLabel.setBounds(10, 400, 130, 30);

        codigoMateria = new JTextField();
        codigoMateria.setBounds(10, 400, 130, 30);

        button = new JButton("Guardar");
        button.setBounds(150, 550, 100, 30);
        button.addActionListener(e -> {
            try {
                GuardarDatos();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        });

        setLayout(null);
        setSize(400, 650);
        setVisible(true);

        add(uParcialLabel);
        add(primerP);
        add(dParcial);
        add(segundoP);
        add(finalLabel);
        add(examFinal);
        add(tareasLabel);
        add(tarea);
        add(registroLabel);
        add(registroAlum);
        add(codigoLabelLabel);
        add(codigoMateria);

        add(button);

    }
    

    private void GuardarDatos() throws ParseException {
        String url = "jdbc:postgresql://localhost:5432/PrimerParcial";
        String usuario = "postgres";
        String pass = "andy";



        try (Connection connection = DriverManager.getConnection(url, usuario, pass)) {

            String sqlAlumno = "SELECT registro_alum, nombre_Completo FROM ALUMNOS";
            PreparedStatement pstmtAlumnos = connection.prepareStatement(sqlAlumno);
            ResultSet rsAlumnos = pstmtAlumnos.executeQuery();

            String sqlMateria = "SELECT codigo_materia,nombre FROM MATERIA";
            PreparedStatement pstmtMateria = connection.prepareStatement(sqlMateria);
            ResultSet rsMateria = pstmtMateria.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Registro_alum");
            model.addColumn("Nombre_Completo");
            model.addColumn("Codigo_Materia");
            model.addColumn("Nombre");

            while (rsAlumnos.next() && rsMateria.next()) {
                model.addRow(new Object[]{rsAlumnos.getInt("Registro_alum"), rsAlumnos.getString("Nombre_Completo"),
                        rsMateria.getInt("Codigo_Materia"), rsMateria.getString("Nombre")});
            }

            String query = "INSERT INTO notas (unoparcial, dosparcial, examinal, tareas, registro_alum, codigo_materia) " +
                    "VALUES (?, ?, ?,?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, Integer.parseInt(primerP.getText()));
                statement.setInt(2, Integer.parseInt(segundoP.getText()));
                statement.setInt(3, Integer.parseInt(examFinal.getText()));
                statement.setInt(4, Integer.parseInt(tarea.getText()));
                statement.setInt(5, Integer.parseInt(registroAlum.getText()));
                statement.setInt(6, Integer.parseInt(codigoMateria.getText()));
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
