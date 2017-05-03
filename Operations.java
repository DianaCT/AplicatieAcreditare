package my.apps.db;
import java.sql.*;


public class Operations {

    final static String URL = "jdbc:postgresql://:5432/xxxxxx";
    final static String USERNAME = "fasttrackit";
    final static String PASSWORD = "fasttrackit";

    public static void main(String[] args) {
        System.out.println("Hello database users! We are going to call DB from Java");
        try {

            Create();
            Read();
            Update();
            Delete();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void Create() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");


        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("INSERT INTO expencesTable(produs, cantitate, pret, categorie) VALUES (?,?,?,?)");
        pSt.setString(1, "mere");
        pSt.setInt(2, 1);
        pSt.setInt(3,5);
        pSt.setString(4,"food");


        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");


        pSt.close();
        conn.close();
    }

    private static void Read() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT produs, categorie FROM expencesTable");

        while (rs.next()) {
            System.out.print(rs.getString("produs").trim());
            System.out.print("---");
            System.out.println(rs.getString("categorie").trim());
        }

        rs.close();
        st.close();
        conn.close();
    }

    private static void Update() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("UPDATE expencesTable SET produs=?, categorie=? WHERE produs=?");
        pSt.setString(1, "acvariu");
        pSt.setString(2, "nonfood");
        pSt.setString(3, "aa");

        int rowsUpdated = pSt.executeUpdate();
        System.out.println("Updated " + rowsUpdated + " rows.");

        pSt.close();
        conn.close();
    }


    private static void Delete() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("DELETE FROM expencesTable WHERE produs=?");
        pSt.setString(1, "khh");

        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");

        pSt.close();
        conn.close();
    }
}