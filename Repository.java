package my.apps.db;

import my.apps.web.Expences;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    // 1. define connection params to db
    final static String URL = "jdbc:postgresql://cccccc:5432/xxxxxxx";
    final static String USERNAME = "fasttrackit";
    final static String PASSWORD = "fasttrackit";

    public void insert(Expences expence) throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO expencesTable( produs,cantitate, categorie, pret, data) VALUES (?, ?, ?, ?, ?)");
        pSt.setString(1, expence.getProdus());
        pSt.setInt(2,expence.getCantitate());
        pSt.setString(3, expence.getCategorie());
        pSt.setInt(4, expence.getPret());
        pSt.setDate(5, expence.getData());

        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }

    public List<Expences> read() throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        // 4. execute a query
        ResultSet rs = st.executeQuery("SELECT id, produs ,cantitate, pret, categorie, data FROM expencesTable");

        // 5. iterate the result set and print the values
        List<Expences> expences = new ArrayList<>();
        while (rs.next()) {
            Expences expence = new Expences(
                    rs.getString("produs"),
                    rs.getInt("cantitate"),
                    rs.getDate("data"),
                    rs.getInt("pret"),
                    rs.getString("categorie"));

            expence.setId(rs.getLong("id"));
            expences.add(expence);
        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();
        return expences;
    }
}
