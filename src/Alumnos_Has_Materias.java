import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

public class Alumnos_Has_Materias  {
     Alumnos alumno = new Alumnos();
     Materias materia = new Materias();

    // Constructor
    public Alumnos_Has_Materias(Alumnos alumno, Materias materia) {
        this.alumno = alumno;
        this.materia = materia;
    }

    public Alumnos_Has_Materias() {

        String url = "jdbc:postgresql://localhost:5432/PrimerParcial";
        String usuario = "postgres";
        String pass = "andy";


        try (Connection connection = DriverManager.getConnection(url, usuario, pass)) {
            String query = "INSERT INTO alumnos_has_materia (registro_alum,codigo_materia) " +
                    "VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, String.valueOf((alumno.getRegistroAlumno())));
                statement.setString(2, String.valueOf((materia.getCodigoMateria())));

                System.out.println("Datos guardados exitosamente en la base de datos.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}







