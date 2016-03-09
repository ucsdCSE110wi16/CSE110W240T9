package com.cse110.apk404.myCalendar.eventListHandler;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class SQLServerAdapter {

    public static Connection getConnection() {

        Log.e("fuck", "i am here");

        Connection con = null;

        String server = "zjia.database.window.net";
        String port = "1433";
        String database = "MyCalendarProject";
        String user = "zjia@zjia";
        String password = "Sean@369";
        String otherSettings = "loginTimeout=30;encrypt=true;trustServerCertificate=false;" +
                "hostNameInCertificate=*.database.windows.net;integratedSecurity=true;";

        String connectQuery = "jdbc:sqlserver://" + server + ":" + port + ";" + "database=" + database + ";" +
                     "user=" + user + ";" + "password=" + password + ";" + otherSettings;

        String well = "jdbc:sqlserver://zjia.database.windows.net:1433;databaseName=MyCalendarProject;user=zjia@zjia;password=Sean@369;encrypt=false";
        String good = "jdbc:postgresql://ec2-54-83-204-228.compute-1.amazonaws.com:5432/dep11vdba21deg";
        String w = "jdbc:postgresql://calendaramazon.cwnkqxmlu01p.us-west-2.rds.amazonaws.com:5432/calendardb";
        try {
            Class.forName("org.postgresql.Driver");
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();;
            //con = DriverManager.getConnection(good, "zjplehmcgdycdh", "4UY3H9npFynVvhsQT4_vY7GxUX");
            con = DriverManager.getConnection(w, "zjia", "JiaZhiWei123");
        } catch (Exception e) {
            Log.e("DatabaseHere", e.toString());
        }

        return con;
    }

    public static void createTableIfNotExist(Connection con) {

        String tableName = "calendar_user";
        Statement st = null;

        String createQuery = "IF NOT EXISTS (SELECT * FROM " + tableName + ") " +
                "CREATE TABLE " + tableName + " (email VARCHAR(100) PRIMARY KEY, " +
                "password VARCHAR(20), data VARBINARY(2000000)) " + "GO";

        try {
            st = con.createStatement();
            st.executeUpdate(createQuery);
        } catch (SQLException e) {
            Log.e("Database1", e.getMessage());
        }
    }

    public static boolean checkUserProfile(String email, Connection con) {

        String tableName = "calendar_user";
        Statement st = null;
        ResultSet res = null;
        boolean notFound = false;

        ArrayList<String> userList = new ArrayList<String>();
        String selectQuery = "SELECT email FROM " + tableName + " WHERE email = '" + email + "'";

        try {
            st = con.createStatement();
            res = st.executeQuery(selectQuery);
            if (!res.next()) {
                notFound = true;
            }
        } catch (SQLException e) {
            Log.e("Database2", e.getMessage());
        }

        return notFound;
    }

    public static void addUser(CalendarUser user, Connection con) {

        String tableName = "calendar_user";
        String email = user.getEmail();
        String password = user.getPassword();
        String insertQuery = "INSERT INTO TABLE " + tableName + " (email, password, data) VALUES (?, ?, ?)";
        ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> data =  user.getData();

        try {
            PreparedStatement st = con.prepareStatement(insertQuery);
            st.setString(1, "'" + email + "'");
            st.setString(2, "'" + password + "'");
            st.setBytes(3, Serializer.serialize(data));
            st.executeUpdate();
        } catch (Exception e) {
            Log.e("Database3", e.getMessage());
        }
    }

    public static ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> retrieveLists(String email, Connection con) {
        String tableName = "calendar_user";
        String selectQuery = "SELECT data FROM " + tableName + " WHERE email = '" + email + "'";
        Statement st = null;
        ResultSet res = null;

        try {
            st = con.createStatement();
            res = st.executeQuery(selectQuery);
            byte[] bytes = res.getBytes(1);
            Object obj = Serializer.deserialize(bytes);
            if (obj instanceof ArrayList<?>) {
                Log.e("YES", "COOL!!!!");
                return (ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>>) obj;
            }
        } catch (Exception e) {
            Log.e("Database4", e.getMessage());
        }

        return null;
    }

    public static boolean checkPassword(String email, String password, Connection con) {
        String tableName = "calendar_user";
        String selectQuery = "SELECT password FROM " + tableName + " WHERE email = '" + email + "'";

        Statement st = null;
        ResultSet res = null;
        boolean correct = false;

        try {
            st = con.createStatement();
            res = st.executeQuery(selectQuery);
            String pass = res.getString(1);
            if (pass.equals(password))
                correct = true;
        } catch (Exception e) {
            Log.e("Database5", e.getMessage());
        }

        return correct;
    }

    public static void updateLists(String email, Connection con, ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> data) {
        String tableName = "calendar_user";
        String updateQuery = "UPDATE " + tableName + " SET data = ? WHERE email = ?";

        try {
            PreparedStatement st = con.prepareStatement(updateQuery);
            st.setString(2, "'" + email + "'");
            st.setBytes(1, Serializer.serialize(data));
            st.executeUpdate();
        } catch (Exception e) {
            Log.e("Database6", e.getMessage());
        }
    }
}
