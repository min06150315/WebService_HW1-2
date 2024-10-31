package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private Connection connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:mychampion.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Champion> loadChampions() {
        List<Champion> champions = new ArrayList<>();
        // DB에서 챔피언 목록 가져오기
        return champions;
    }

    public void addChampionToDatabase(Champion champion) {
        // DB에 챔피언 추가
    }

    public void updateChampionInDatabase(Champion champion) {
        // DB의 챔피언 수정
    }

    public void deleteChampionFromDatabase(int championId) {
        // DB에서 챔피언 삭제
    }
}
