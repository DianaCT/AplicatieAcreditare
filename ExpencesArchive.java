package my.apps.web;
import my.apps.db.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/expencesArchive")
public class ExpencesArchive extends HttpServlet {

    private int counter;

    private Repository expRepository = new Repository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        //get input as string
        String produs = request.getParameter("produs");
        String cantitate = request.getParameter("cantitate");
        String data = request.getParameter("data");
        String pret = request.getParameter("pret");
        String categorie = request.getParameter("categorie") != null ? request.getParameter("categorie") : "no";

        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        addStyle(out);
        try {
            Date validDate = data != null ? Date.valueOf(data) : new Date(System.currentTimeMillis());
            Integer PretValid = Integer.valueOf(pret);
            Integer CantitateValid = Integer.valueOf(cantitate);
            Expences expence = new Expences(produs,CantitateValid, validDate, PretValid, categorie);
            out.println("<h3>New expence...</h3>");
            expRepository.insert(expence);
            out.println("<b>" + expence.toString() +  "</b><br />");
        } catch (ClassNotFoundException e) {
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
        } catch (IllegalArgumentException e) {
            out.println("<dif class='error'><b>Unable to parse date! Expected format is yyyy-MM-dd but was " + data);
        }

        addGoBack(out);


        out.close();

    }

    private void addGoBack(PrintWriter out) {
        out.println("<a href='/'>Go Back</a>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> Expences </title>");
        addStyle(out);
        out.println("</head>");
        try {
            out.println("<h3>Expences...</h3>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>Data</th>");
            out.println("<th>Produs</th>");
            out.println("<th>Cantitate</th>");
            out.println("<th>Pret</th>");
            out.println("<th>Categorie</th>");

            out.println("</tr>");
            List<Expences> expences = expRepository.read();
            for (Expences expence : expences) {
                out.println("<tr>");
                out.println("<td>"+expence.getId()+"</td>");
                out.println("<td>"+expence.getData()+"</td>");
                out.println("<td>"+expence.getProdus()+"</td>");
                out.println("<td>"+expence.getCantitate()+"</td>");
                out.println("<td>"+expence.getPret()+"</td>");
                out.println("<td>"+expence.getCategorie()+"</td>");



                out.println("</tr>");
            }
            out.println("</table>");

        } catch (ClassNotFoundException e) {
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
        }
        addGoBack(out);
        out.close();
    }

    private void addStyle(PrintWriter out) {
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() called. Counter is: " + counter);
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet! Counter is:" + counter);
        super.destroy();
    }
}