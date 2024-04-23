import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Materias extends JFrame {


    private JTextField codigoMateria;
    private JTextField nombreMateria;
    private JTextField nroCreditos;
    private JButton button;

    public Materias() {
        super("REGISTRO MATERIA");
        setLocationRelativeTo(null);

        JLabel codigoLabel = new JLabel("Codigo Carrera:");
        codigoLabel.setBounds(10, 100, 80, 30);

        codigoMateria = new JTextField();
        codigoMateria.setBounds(50, 150, 200, 30);

        JLabel nombre = new JLabel("Nombre:");
        nombre.setBounds(10, 200, 120, 30);

        nombreMateria = new JTextField();
        nombreMateria.setBounds(140, 200, 200, 30);

        JLabel creditosLabel = new JLabel("Creditos necesarios:");
        creditosLabel.setBounds(10, 250, 130, 30);


        nroCreditos = new JTextField();
        nroCreditos.setBounds(150, 250, 200, 30);

        button = new JButton("Guardar");
        button.setBounds(150, 350, 100, 30);
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

        add(codigoLabel);
        add(codigoMateria);
        add(nombre);
        add(nombreMateria);
        add(creditosLabel);
        add(nroCreditos);
        add(button);

    }

    public JTextField getNroCreditos() {return nroCreditos;}
    public void setNroCreditos(JTextField nroCreditos) {this.nroCreditos = nroCreditos;}

    public JTextField getNombreMateria() {return nombreMateria;}
    public void setNombreMateria(JTextField nombreMateria) {this.nombreMateria = nombreMateria;}

    public JTextField getCodigoMateria() {return codigoMateria;}
    public void setCodigoMateria(JTextField codigoMateria) {this.codigoMateria = codigoMateria;}


    private void GuardarDatos() throws ParseException {
        String url = "jdbc:postgresql://localhost:5432/PrimerParcial";
        String usuario = "postgres";
        String pass = "andy";


        try (Connection connection = DriverManager.getConnection(url, usuario, pass)) {
            String query = "INSERT INTO materia (codigo_materia,nombre,numerocreditos) " +
                    "VALUES (?, ?, ? )";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, Integer.parseInt(codigoMateria.getText()));
                statement.setString(2, nombreMateria.getText());
                statement.setString(3, nroCreditos.getText());
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
