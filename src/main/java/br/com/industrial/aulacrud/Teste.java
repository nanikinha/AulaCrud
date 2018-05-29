package br.com.industrial.aulacrud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Teste", urlPatterns = {"/Teste"})
public class Teste extends HttpServlet {
    
    public String message;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = 
               DriverManager.getConnection("jdbc:mysql://localhost/mysql?useTimezone=true&serverTimezone=UTC","root","");
            if(con != null){
                message = "STATUS--->Conectado com sucesso!";
                con.close();
            } else{
                message = "STATUS--->Não foi possível realizar a conexão";
            }
            
        } catch (ClassNotFoundException ex) {
            message = "ERRO Driver---> " + ex.getMessage();
        } catch (SQLException ex) {
            message = "ERRO Connection---> " + ex.getMessage();
        }
        
        request.setAttribute("message", message);
        request.getRequestDispatcher("/view/teste.jsp").forward(request, response);
               
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
