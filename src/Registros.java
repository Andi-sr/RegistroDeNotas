import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registros extends JFrame {

    private JTable table;

    public Registros() {
        setTitle("Registro");
        setSize(600, 400);
        setVisible(true);
        setLayout(null);


        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        cargarDatos();
    }

    private void cargarDatos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Registro Alumno");
        model.addColumn("Nombre Completo");
        model.addColumn("Nombre Materia");

        String url = "jdbc:postgresql://localhost:5432/PrimerParcial";
        String usuario = "postgres";
        String pass = "andy";

        try (Connection connection = DriverManager.getConnection(url, usuario, pass)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT a.registro_alum, a.nombre_completo, a.carrera, m.nombre, n.id_notas\n" +
                    "   FROM alumnos a \n" +
                    "  LEFT JOIN notas n ON a.registro_alum = n.registro_alum\n" +
                    " LEFT JOIN materia m ON n.codigo_materia = m.codigo_materia\n" +
                    " ORDER BY a.registro_alum");

            while (resultSet.next()) {
                String registroAlumTabla = resultSet.getString("Registro alumno");
                String nombreAlum = resultSet.getString("Nombre Completo");
                String materia = resultSet.getString("Materia");

                model.addRow(new Object[]{registroAlumTabla, nombreAlum, materia});
            }

            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar datos desde la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

