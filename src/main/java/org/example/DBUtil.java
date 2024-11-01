package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private final String loadSql = "SELECT * FROM champions";
    private final String addSql = "INSERT INTO champions (name, region, position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE champions SET name = ?, region = ?, position = ?, damageType = ?, skillPassive = ?, skillQ = ?, skillW = ?, skillE = ?, skillR = ? WHERE id = ?";
    private final String deleteSql = "DELETE FROM champions WHERE id = ?";
    private final String resetSql1 = "SELECT * FROM champions ORDER BY id";
    private final String resetSql2 = "UPDATE champions SET id = ? WHERE id = ?";

    private Connection connectToDatabase() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbFile = "mychampion.db";
            con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public List<Champion> loadChampionsFromDatabase() {
        List<Champion> champions = new ArrayList<>();
        try (Connection conn = connectToDatabase();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(loadSql)) {
            while (rs.next()) {
                champions.add(new Champion(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("region"),
                        rs.getString("position"),
                        rs.getString("damageType"),
                        rs.getString("skillPassive"),
                        rs.getString("skillQ"),
                        rs.getString("skillW"),
                        rs.getString("skillE"),
                        rs.getString("skillR"),
                        rs.getString("createDate")
                ));
            }
            resetChampionIds();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return champions;
    }

    public void addChampionToDatabase(Champion champion) {
        try (Connection conn = connectToDatabase();
             PreparedStatement pstat = conn.prepareStatement(addSql)) {
            pstat.setString(1, champion.getName());
            pstat.setString(2, champion.getRegion());
            pstat.setString(3, champion.getPosition());
            pstat.setString(4, champion.getDamageType());
            pstat.setString(5, champion.getSkillPassive());
            pstat.setString(6, champion.getSkillQ());
            pstat.setString(7, champion.getSkillW());
            pstat.setString(8, champion.getSkillE());
            pstat.setString(9, champion.getSkillR());
            pstat.setString(10, champion.getCreateDate());
            pstat.executeUpdate();
            resetChampionIds();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateChampionInDatabase(Champion champion) {
        try (Connection conn = connectToDatabase();
             PreparedStatement pstat = conn.prepareStatement(updateSql)) {
            pstat.setString(1, champion.getName());
            pstat.setString(2, champion.getRegion());
            pstat.setString(3, champion.getPosition());
            pstat.setString(4, champion.getDamageType());
            pstat.setString(5, champion.getSkillPassive());
            pstat.setString(6, champion.getSkillQ());
            pstat.setString(7, champion.getSkillW());
            pstat.setString(8, champion.getSkillE());
            pstat.setString(9, champion.getSkillR());
            pstat.setInt(10, champion.getId());
            pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChampionFromDatabase(int championId) {
        try (Connection conn = connectToDatabase();
             PreparedStatement pstat = conn.prepareStatement(deleteSql)) {
            pstat.setInt(1, championId);
            pstat.executeUpdate();
            resetChampionIds();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetChampionIds() {
        try (Connection con = connectToDatabase();
             Statement stat = con.createStatement();
             ResultSet rs = stat.executeQuery(resetSql1)) {
            int newId = 1;
            while (rs.next()) {
                int oldId = rs.getInt("id");
                try (PreparedStatement pstat = con.prepareStatement(resetSql2)) {
                    pstat.setInt(1, newId);
                    pstat.setInt(2, oldId);
                    pstat.executeUpdate();
                }
                newId++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
