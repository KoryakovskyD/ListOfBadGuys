package com.example.listofbadguys.javafxmltable2;

import com.example.listofbadguys.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DbServer implements AutoCloseable {
    private static String url;
    private static String user;
    private static String psw;
    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;


    public DbServer(String url, String user, String psw) {
        this.url = url;
        this.user = user;
        this.psw = psw;
    }

    private void info() {
        Enumeration<Driver> e = DriverManager.getDrivers();
        Driver drv = null;
        while (e.hasMoreElements()) {
            drv = e.nextElement();
            System.out.println(drv.getClass().getCanonicalName());
        }
    }

    private void init() throws SQLException {
        con = DriverManager.getConnection(url,user,psw);
        if (con == null)
            throw new IllegalArgumentException("Connection is null");
    }

    public static void start() {
        try (DbServer dbServer = new DbServer(url, user, psw)) {
            dbServer.info();
            dbServer.init();
        } catch (SQLException e) {
            System.out.println("Bad connection with database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addPerson(String lastName, String comment) {

        try (Connection conn = createConnection()) {
                    System.out.println("add");
                    try (PreparedStatement pstmt = conn.prepareStatement(
                            "insert into LIST (LASTNAME, COMMENT)\n" +
                                    "values (?, ?)")) {
                        pstmt.setString(1, lastName);
                        pstmt.setString(2, comment);
                        pstmt.executeUpdate();
                    }
                    return true;
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Person> getCars() {

        try (Connection conn = createConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(
                    "select *\n" +
                            "from LIST\n"
            )) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    return createCarArray(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Person> createCarArray(ResultSet rs) throws SQLException {
        List<Person> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Person(rs.getString(1), rs.getString(2)));
        }
        return (ArrayList<Person>) list;
    }


    public static void main(String[] args) {

    }

    @Override
    public void close() throws Exception {

    }

    public int countPersons(String lastName) {

        String sql = String.valueOf(new StringBuilder("select COUNT(*) as Res from LIST where LASTNAME = '").append(lastName).append("'\n"));

        try (Connection conn = createConnection();
            PreparedStatement pstmt = conn.prepareStatement(
            sql ))
        {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, psw);
    }
}