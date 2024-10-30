package org.example;

import java.sql.*;
import java.util.Scanner;

public class ChampionManager {
    Scanner sc = new Scanner(System.in);
    public ChampionManager() {
        ChampionCRUD championCRUD = new ChampionCRUD();
        System.out.println("환영합니다, 소환사님! 리그 오브 레전드: 챔피언 관리 시스템에 오신 것을 환영합니다.");
        loadChampionsFromDataBase();
    }
    public void start() {
        int menu;
        while (true) {
            // 사용자에게 기능 알려주기
            System.out.println("\n1.List 2.List(Region) 3.List(Position) 4.Search 5.Add 6.Modify 7.Delete 8.Save file 9.Save DB 0.Exit\n");
            // 원하는 기능 입력 받아서 실행하기
            System.out.print("=> 원하는 메뉴는? ");
            menu = sc.nextInt();
            sc.nextLine();

            if (menu == 1) listChampions();
            else if (menu == 2) listChampionsByRegion();
            else if (menu == 3) listChampionsByPosition();
            else if (menu == 4) searchChampions();
            else if (menu == 5) addChampions();
            else if (menu == 6) modifyChampions();
            else if (menu == 7) deleteChampions();
            else if (menu == 8) saveTextFile();
            else if (menu == 9) saveDataBase();
            else if (menu == 0) {
                System.out.println("\n챔피언 관리 시스템을 이용해 주셔서 감사합니다! 다음에 또 만나요~");
                break;
            }
        }
    }
    public void loadChampionsFromDataBase() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbFile = "mychampion.db";
            con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
            System.out.println("\nDataBase가 연결되었습니다.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void listChampions() {

    }
    public void listChampionsByRegion() {

    }
    public void listChampionsByPosition() {

    }
    public void searchChampions() {

    }
    public void addChampions() {

    }
    public void modifyChampions() {

    }
    public void deleteChampions() {

    }
    public void saveTextFile() {

    }
    public void saveDataBase() {

    }
}
