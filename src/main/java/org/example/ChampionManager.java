package org.example;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class ChampionManager {
    private final ChampionCRUD championCRUD;
    Scanner sc = new Scanner(System.in);
    public ChampionManager() {
        championCRUD = new ChampionCRUD();
        // 시작 문구
        System.out.println("환영합니다, 소환사님! 리그 오브 레전드: 챔피언 관리 시스템에 오신 것을 환영합니다.");
        // 데이타베이스에서 챔피언 데이터 불러오기
        loadChampionsFromDataBase();
    }
    public void start() {
        int menu;
        while (true) {
            // 사용자에게 기능 알려주기
            System.out.println("\n1.List(전체) 2.List(소속) 3.List(포지션) 4.Search 5.Add 6.Modify 7.Delete 8.Save file 9.Save DB 0.Exit\n");
            // 원하는 기능 입력 받아서 실행하기
            System.out.print("=> 원하는 메뉴는? ");
            menu = sc.nextInt();
            sc.nextLine();

            if (menu == 1) listChampions(); // 기능1. 모든 챔피언 리스트 보기
            else if (menu == 2) listChampionsByRegion(); // 기능2. 소속 별 챔피언 리스트 보기
            else if (menu == 3) listChampionsByPosition(); // 기능3. 포지션 별 챔피언 리스트 보기
            else if (menu == 4) searchChampions(); // 기능4. 챔피언 검색하기
            else if (menu == 5) addChampions(); // 기능5. 챔피언 추가하기
            else if (menu == 6) modifyChampions(); // 기능6. 챔피언 수정하기
            else if (menu == 7) deleteChampions(); // 기능7. 챔피언 삭제하기
            else if (menu == 8) saveTextFile(); // 기능8. 텍스트 파일로 저장하기
            else if (menu == 9) saveDataBase(); // 기능9. 데이타베이스 파일로 저장하기
            else if (menu == 0) { // 프로그램 종료
                System.out.println("\n챔피언 관리 시스템을 이용해 주셔서 감사합니다! 다음에 또 만나요~");
                break;
            }
        }
    }
    public void loadChampionsFromDataBase() { // 데이타베이스에서 챔피언 데이터 불러오기
        // DataBase 에서 리스트로 데이터 가져오기
        Connection con = null;
        try {
            // DataBase 파일 연결하기
            Class.forName("org.sqlite.JDBC");
            String dbFile = "mychampion.db";
            con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
            System.out.println("\nDataBase가 연결되었습니다.");

            // 데이터 조회를 위한 SQL Query 생성
            String sql = "SELECT * FROM champions"; // *을 활용하여 모든 열을 선택
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            // DataBase 에서 Champion Data 읽어와서 CRUD 객체에 ArrayList 로 저장
            while (rs.next()) {
                // 각 Field 별 데이터 가져오기
                int id = rs.getInt("id"); // id
                String name = rs.getString("name"); // 이름
                String region = rs.getString("region"); // 소속
                String position = rs.getString("position"); // 포지션
                String damageType = rs.getString("damageType"); // 데미지 유형
                String skillPassive = rs.getString("skillPassive"); // 패시브 스킬
                String skillQ = rs.getString("skillQ"); // Q 스킬
                String skillW = rs.getString("skillW"); // W 스킬
                String skillE = rs.getString("skillE"); // E 스킬
                String skillR = rs.getString("skillR"); // R 스킬
                String createDate = rs.getString("createDate"); // 생성 날짜

                // Champion 객체 생성 후 championCRUD 에 추가
                Champion newChampion = new Champion(id, name, region ,position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate);
                championCRUD.create(newChampion);
            }

        } catch (ClassNotFoundException | SQLException e) { // 예외처리
            throw new RuntimeException(e);
        } finally { // 데이터베이스 연결 종료하기
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void listChampions() { // 기능1. 모든 챔피언 리스트 보기
        List<Champion> champions = championCRUD.list();
        if (!champions.isEmpty()) { // 리스트에 챔피언이 있을 때
            System.out.println("--------------------------------");
            for (Champion i : champions) { // 리스트를 돌면서 toString 을 사용해서 출력
                System.out.println(i.toString());
            }
            System.out.println("--------------------------------");
        } else {
            System.out.println("\n* 챔피언이 존재하지 않습니다.");
        }
    }
    public void listChampionsByRegion() { // 기능2. 소속 별 챔피언 리스트 보기

    }
    public void listChampionsByPosition() { // 기능3. 포지션 별 챔피언 리스트 보기

    }
    public void searchChampions() { // 기능4. 챔피언 검색하기

    }
    public void addChampions() { // 기능5. 챔피언 추가하기

    }
    public void modifyChampions() { // 기능6. 챔피언 수정하기

    }
    public void deleteChampions() { // 기능7. 챔피언 삭제하기

    }
    public void saveTextFile() { // 기능8. 텍스트 파일로 저장하기

    }
    public void saveDataBase() { // 기능9. 데이타베이스 파일로 저장하기

    }
}
