package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ChampionManager {
    private final ChampionCRUD championCRUD;
    Scanner sc = new Scanner(System.in);
    int id = 1;
    public ChampionManager() {
        championCRUD = new ChampionCRUD();
        // 시작 문구
        System.out.println("🎉 환영합니다, 소환사님! 리그 오브 레전드: 챔피언 관리 시스템에 오신 것을 환영합니다. ⚔️");
        // 데이타베이스에서 챔피언 데이터 불러오기
        loadChampionsFromDataBase();
    }
    public void start() {
        int menu;
        while (true) {
            // 사용자에게 기능 알려주기
            System.out.println("\n🔍 어떤 기능을 원하시나요?");
            System.out.println("1. 전체 리스트 2. 소속 리스트 3. 포지션 리스트 4. 챔피언 검색 5. 챔피언 추가 6. 챔피언 수정 7. 챔피언 삭제 8. 텍스트 파일로 저장 9. 데이터베이스로 저장 0. 종료");
            // 원하는 기능 입력 받아서 실행하기
            System.out.print("👉 원하는 메뉴는? ");
            menu = sc.nextInt();
            sc.nextLine();

            if (menu == 1) listChampions(); // 기능1. 모든 챔피언 리스트 보기
            else if (menu == 2) listChampionsByRegion(); // 기능2. 소속 별 챔피언 리스트 보기
            else if (menu == 3) listChampionsByPosition(); // 기능3. 포지션 별 챔피언 리스트 보기
            else if (menu == 4) searchChampions(); // 기능4. 챔피언 검색하기
            else if (menu == 5) addChampion(); // 기능5. 챔피언 추가하기
            else if (menu == 6) modifyChampion(); // 기능6. 챔피언 수정하기
            else if (menu == 7) deleteChampion(); // 기능7. 챔피언 삭제하기
            else if (menu == 8) saveTextFile(); // 기능8. 텍스트 파일로 저장하기
            else if (menu == 9) saveDataBase(); // 기능9. 데이타베이스 파일로 저장하기
            else if (menu == 0) { // 프로그램 종료
                System.out.println("\n✋ 챔피언 관리 시스템을 이용해 주셔서 감사합니다! 다음에 또 만나요~ 🌟");
                break;
            } else {
                System.out.println("❌ 잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }
    private Connection connectToDatabase() { // 데이터베이스 연결 함수
        Connection con = null;
        try {
            // DataBase 파일 연결하기
            Class.forName("org.sqlite.JDBC");
            String dbFile = "mychampion.db";
            con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
        } catch (ClassNotFoundException | SQLException e) { // 예외처리
            throw new RuntimeException(e);
        }
        return con;
    }
    public void loadChampionsFromDataBase() { // 데이터베이스에서 챔피언 데이터 불러오기
        // DataBase 에서 리스트로 데이터 가져오기
        Connection con = connectToDatabase();

        if (con == null) {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            return;
        }
        
        try {
            // 데이터 조회를 위한 SQL Query 생성
            String sql = "SELECT * FROM champions"; // *을 활용하여 모든 열을 선택
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            // DataBase 에서 Champion Data 읽어와서 CRUD 객체에 ArrayList 로 저장
            while (rs.next()) {
                // 각 Field 값을 가져와서 Champion 객체 생성 후 championCRUD 에 추가
                Champion newChampion = new Champion(
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
                );
                championCRUD.create(newChampion); // ArrayList 에 추가하기
            }
        } catch (SQLException e) { // 예외처리
            throw new RuntimeException(e);
        } finally { // 데이터베이스 연결 종료하기
            try {
                con.close(); // 연결 종료
            } catch (SQLException e) {
                e.printStackTrace();
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
            System.out.println("\n⚠️ 챔피언이 존재하지 않습니다.");
        }
    }
    public void listChampionsByRegion() { // 기능2. 소속 별 챔피언 리스트 보기

    }
    public void listChampionsByPosition() { // 기능3. 포지션 별 챔피언 리스트 보기

    }
    public void searchChampions() { // 기능4. 챔피언 검색하기

    }
    public void addChampion() { // 기능5. 챔피언 추가하기
        // 새롭게 추가할 챔피언의 데이터 필드 변수
        String name, region, position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate;

        // createDate 를 위한 현재시간 가져오기
        LocalDateTime now = LocalDateTime.now();
        
        // 데이터 필드 입력받기
        System.out.println("\n🎮 새로운 챔피언을 추가해볼까요!");

        System.out.print("🤖 챔피언의 이름을 입력하세요: ");
        name = sc.nextLine();

        System.out.print("🌍 이 챔피언의 소속은 어디인가요? ");
        region = sc.nextLine();

        System.out.print("⚔️ 이 챔피언의 포지션은 무엇인가요? ");
        position = sc.nextLine();

        System.out.print("💥 데미지 타입은 어떤 건가요? ");
        damageType = sc.nextLine();

        System.out.print("🌀 패시브 스킬의 이름을 알려주세요: ");
        skillPassive = sc.nextLine();

        System.out.print("🔵 Q 스킬의 이름은 무엇인가요? ");
        skillQ = sc.nextLine();

        System.out.print("🟡 W 스킬의 이름은 무엇인가요? ");
        skillW = sc.nextLine();

        System.out.print("🟢 E 스킬의 이름은 무엇인가요? ");
        skillE = sc.nextLine();

        System.out.print("🔴 R 스킬의 이름은 무엇인가요? ");
        skillR = sc.nextLine();

        // createDate (생성 시간) 생성하기
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        createDate = now.format(dateTimeFormatter);

        // 입력받은 정보로 새로운 객체 생성하기
        Champion newChampion = new Champion(id++, name, region, position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate);

        // 새로운 챔피언 객체를 목록에 추가하고 Database 에도 추가하기
        championCRUD.create(newChampion); // ArrayList 에 추가
        addChampionToDatabase(newChampion); // Database 에 추가

        // 추가 확인문구 출력
        System.out.println("✅ 새로운 챔피언이 추가되었습니다: " + newChampion.getName() + " [" + newChampion.getRegion() + "]");
    }
    public void modifyChampion() {
        System.out.print("수정할 챔피언의 이름을 입력하세요: ");
        String name = sc.nextLine();
        Champion champion = championCRUD.searchByName(name);

        if (champion != null) {
            System.out.println("1. 이름 2. 소속 지역 3. 포지션 4. 데미지 유형 5. 패시브 스킬 6. Q 스킬 7. W 스킬 8. E 스킬 9. R 스킬");
            System.out.print("👉 수정할 항목의 번호를 선택하세요: ");
            int choice = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1 -> {
                    System.out.print("새로운 이름을 입력하세요 (현재: " + champion.getName() + "): ");
                    champion.setName(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("새로운 소속 지역을 입력하세요 (현재: " + champion.getRegion() + "): ");
                    champion.setRegion(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("새로운 포지션을 입력하세요 (현재: " + champion.getPosition() + "): ");
                    champion.setPosition(sc.nextLine());
                }
                case 4 -> {
                    System.out.print("새로운 데미지 유형을 입력하세요 (현재: " + champion.getDamageType() + "): ");
                    champion.setDamageType(sc.nextLine());
                }
                case 5 -> {
                    System.out.print("새로운 패시브 스킬을 입력하세요 (현재: " + champion.getSkillPassive() + "): ");
                    champion.setSkillPassive(sc.nextLine());
                }
                case 6 -> {
                    System.out.print("새로운 Q 스킬을 입력하세요 (현재: " + champion.getSkillQ() + "): ");
                    champion.setSkillQ(sc.nextLine());
                }
                case 7 -> {
                    System.out.print("새로운 W 스킬을 입력하세요 (현재: " + champion.getSkillW() + "): ");
                    champion.setSkillW(sc.nextLine());
                }
                case 8 -> {
                    System.out.print("새로운 E 스킬을 입력하세요 (현재: " + champion.getSkillE() + "): ");
                    champion.setSkillE(sc.nextLine());
                }
                case 9 -> {
                    System.out.print("새로운 R 스킬을 입력하세요 (현재: " + champion.getSkillR() + "): ");
                    champion.setSkillR(sc.nextLine());
                }
                default -> System.out.println("❌ 잘못된 선택입니다.");
            }

            championCRUD.update(champion); // 리스트에서 챔피언 업데이트
            modifyChampionInDatabase(champion); // 데이터베이스 업데이트
            System.out.println("✅ " + champion.getName() + " 챔피언이 수정되었습니다.");
        } else {
            System.out.println("⚠️ 해당 챔피언을 찾을 수 없습니다.");
        }
    }

    public void deleteChampion() {
        System.out.print("\n⚔️ 삭제할 챔피언의 이름을 입력하세요: ");
        String name = sc.nextLine();
        Champion champion = championCRUD.searchByName(name);

        if (champion != null) {
            championCRUD.delete(champion.getId()); // 리스트에서 삭제
            deleteChampionFromDatabase(champion); // 데이터베이스에서 삭제
            resetChampionIds(); // ID 재설정
            System.out.println("✅ " + champion.getName() + " 챔피언이 삭제되었습니다.");
        } else {
            System.out.println("⚠️ 해당 챔피언을 찾을 수 없습니다.");
        }
    }
    public void saveTextFile() { // 기능8. 텍스트 파일로 저장하기

    }
    public void saveDataBase() { // 기능9. 데이타베이스 파일로 저장하기

    }
    public void addChampionToDatabase(Champion champion) {
        try (Connection con = connectToDatabase();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO champions (name, region, position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setString(1, champion.getName());
            pstmt.setString(2, champion.getRegion());
            pstmt.setString(3, champion.getPosition());
            pstmt.setString(4, champion.getDamageType());
            pstmt.setString(5, champion.getSkillPassive());
            pstmt.setString(6, champion.getSkillQ());
            pstmt.setString(7, champion.getSkillW());
            pstmt.setString(8, champion.getSkillE());
            pstmt.setString(9, champion.getSkillR());
            pstmt.setString(10, champion.getCreateDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void modifyChampionInDatabase(Champion champion) {
        try (Connection con = connectToDatabase();
             PreparedStatement pstmt = con.prepareStatement("UPDATE champions SET name = ?, region = ?, position = ?, damageType = ?, skillPassive = ?, skillQ = ?, skillW = ?, skillE = ?, skillR = ? WHERE id = ?")) {

            pstmt.setString(1, champion.getName());
            pstmt.setString(2, champion.getRegion());
            pstmt.setString(3, champion.getPosition());
            pstmt.setString(4, champion.getDamageType());
            pstmt.setString(5, champion.getSkillPassive());
            pstmt.setString(6, champion.getSkillQ());
            pstmt.setString(7, champion.getSkillW());
            pstmt.setString(8, champion.getSkillE());
            pstmt.setString(9, champion.getSkillR());
            pstmt.setInt(10, champion.getId()); // ID를 기준으로 업데이트
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChampionFromDatabase(Champion champion) {
        try (Connection con = connectToDatabase();
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM champions WHERE id = ?")) {

            pstmt.setInt(1, champion.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void resetChampionIds() {
        try (Connection con = connectToDatabase()) {
            // 현재 데이터베이스에서 모든 챔피언을 ID 순서대로 가져와 재설정
            String selectSql = "SELECT * FROM champions ORDER BY id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectSql);

            int newId = 1; // 새로운 ID 시작 값
            while (rs.next()) {
                int oldId = rs.getInt("id");
                // ID를 재설정하기 위한 업데이트 쿼리
                String updateSql = "UPDATE champions SET id = ? WHERE id = ?";
                try (PreparedStatement pstmt = con.prepareStatement(updateSql)) {
                    pstmt.setInt(1, newId);
                    pstmt.setInt(2, oldId);
                    pstmt.executeUpdate();
                }
                newId++; // 다음 ID로 증가
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
