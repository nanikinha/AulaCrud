package br.com.industrial.aulacrud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;
    private Connection con;
    private ConnectionFactory(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/AulaCRUD?useTimezone=true&serverTimezone=UTC","root","root");
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex){
        }
    }
    
    public static Connection getConnection(){
        if(connectionFactory == null){
            connectionFactory = new ConnectionFactory();
        }
        
        return connectionFactory.con;
    }
}
