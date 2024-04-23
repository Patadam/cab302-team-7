package com.wellnessapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MoodDAO implements IMoodDAO {
    @Override
    public void Create(MoodEntry entry) {

    }

    @Override
    public void Delete(MoodEntry entry) {

    }

    @Override
    public List<MoodEntry> getAll() {
        return null;
    }
//    public MoodDAO() {
//        Connection cn = DatabaseConnection.getInstance();
//        //createTable(cn);
//    }
//
//    private void createTable() {
//        try {
//            Statement createTable = connection.createStatement();
//            createTable.execute(
//                    "CREATE TABLE IF NOT EXISTS moodEntries ("
//                            + "id int NOT NULL AUTOINCREMENT, "
//                            + "timestamp VARCHAR NOT NULL "
//                            + "mood VARCHAR NOT NULL "
//                            + ")"
//            );
//            System.out.println("Created table moodEntries");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void Create(MoodEntry entry) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSERT INTO moodEntries (timestamp, mood) VALUES (?,?)"
//            );
//            statement.setString(1, entry.getTimestamp().toString());
//            statement.setString(2, entry.getMood().toString());
//            statement.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void Delete(MoodEntry entry) {
//
//    }
//
//    @Override
//    public List<MoodEntry> getAll() {
//        return null;
//    }
}
