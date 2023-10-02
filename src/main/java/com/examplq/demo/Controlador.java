package com.examplq.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controlador {
    @PostMapping("/crear-usuario")
    public String createuser(@RequestBody Personas personas) throws SQLException, ClassNotFoundException {

        String code = personas.getCode_admin();

        String newdocument = Select_admin(code);
        if (newdocument.equalsIgnoreCase("Administrador")) {

            code = personas.getCode();
            String name = personas.getName();
            String lastname = personas.getLastname();
            String charge = personas.getCharge();
            String date = personas.getDate();
            String bussines = personas.getBussines();

            Register(code, name, lastname, charge, date, bussines);
        }

        String answer = " Usuario registrado de manera exitosa";

        return answer;
    }

    private String Select_admin(String code) throws SQLException, ClassNotFoundException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/api_rest";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM api WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, code); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            String codeadmin = resultSet.getString("code");
            String charge = resultSet.getString("charge");

            // Cerrar recursos
            resultSet.close();
            statement.close();
            connection.close();

            return charge;

        } else {
            System.out.println("Este funcionario no existe o no se encuentra registrado");
        }

        return "";
    }

    private void Register(String code, String name, String lastname, String charge, String date, String bussines) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/api_rest";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM api");


            // Sentencia INSERT
            String sql = "INSERT INTO api (code, name, lastname, charge, date, bussines) VALUES (?, ?, ?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, charge);
            preparedStatement.setString(5, date);
            preparedStatement.setString(6, bussines);

            // Ejecutar la sentencia
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario registrado de manera exitosa.");
            } else {
                System.out.println("No se pudo registrar el usuario");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/buscar-registros")
    public List<Personas> searchrecord() throws SQLException, ClassNotFoundException {

        List<Personas> list = Selectconsul();

        String answer = "Estoy en el metodo de buscar";


        return list;
    }

    private List<Personas> Selectconsul() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/api_rest";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM api");

        List<Personas> list = new ArrayList<>();

        while (resultSet2.next()) {
            String code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String lastname = resultSet2.getString("lastname");
            String charge = resultSet2.getString("charge");
            String date = resultSet2.getString("date");
            String bussines = resultSet2.getString("bussines");

            Personas personas = new Personas(null, code, name, lastname, charge, date, bussines);

            list.add(personas);

        }
        return list;
    }

    @PostMapping("/editar-usuario")
    public String edituser(@RequestBody Personas personas) throws SQLException, ClassNotFoundException {

        String code = personas.getCode_admin();

        String newdocument = Select_admin(code);
        if (newdocument != "Administrador") {

            code = personas.getCode();
            String name = personas.getName();
            String lastname = personas.getLastname();
            String charge = personas.getCharge();
            String date = personas.getDate();
            String bussines = personas.getBussines();

            Edit(code, name, lastname, charge, date, bussines);
        }


        String answer = " Usuario editado de manera exitosa";

        return answer;

    }

    private void Edit(String code, String name, String lastname, String charge, String date, String bussines) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/api_rest";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE api SET name = ?, lastname = ?, charge = ?, date = ?, bussines = ? WHERE code = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastname);
        preparedStatement.setString(3, charge);
        preparedStatement.setString(4, date);
        preparedStatement.setString(5, bussines);
        preparedStatement.setString(6, code);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Registro actualizado de manera exitosa");
        } else {
            System.out.println("No se encontro el registro para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }

    @GetMapping("/buscar-usuario")
    public Personas searchuser(@RequestBody Personas personas) throws SQLException, ClassNotFoundException {

        String documento = personas.getCode();

        personas = Select_funcionary(documento);

        return personas;
    }

    private Personas Select_funcionary(String documento) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/api_rest";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM api WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, documento); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {

            String code = resultSet.getString("code");
            String name = resultSet.getString("name");
            String lastname = resultSet.getString("lastname");
            String charge = resultSet.getString("charge");
            String date = resultSet.getString("date");
            String bussines = resultSet.getString("bussines");

            Personas personas = new Personas(null, code, name, lastname, charge, date, bussines);
            return personas;
        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();

        return null;
    }


}

