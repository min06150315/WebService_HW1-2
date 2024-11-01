package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ChampionManager {
    private final ChampionCRUD championCRUD;
    private final DBUtil dbUtil = new DBUtil();
    Scanner sc = new Scanner(System.in);
    public ChampionManager() {
        championCRUD = new ChampionCRUD();
        // 시작 문구
        System.out.println("🎉 환영합니다, 소환사님! 리그 오브 레전드: 챔피언 관리 시스템에 오신 것을 환영합니다. ⚔️");
        // 데이타베이스에서 챔피언 데이터 불러오기
        dbUtil.loadChampionsFromDatabase();
    }
    public void start() {
        int menu;
        while (true) {
            // 사용자에게 기능 알려주기
            System.out.println("\n🔍 어떤 기능을 원하시나요?");
            System.out.println("1. 전체 리스트 2. 소속 리스트 3. 포지션 리스트 4. 챔피언 검색 5. 챔피언 추가 6. 챔피언 수정 7. 챔피언 삭제 8. 텍스트 파일로 저장 0. 종료");
            // 원하는 기능 입력 받아서 실행하기
            System.out.print("👉 원하는 메뉴는? ");
            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1 -> championCRUD.listChampions();
                case 2 -> championCRUD.listChampionsByRegion();
                case 3 -> championCRUD.listChampionsByPosition();
                case 4 -> championCRUD.searchChampion();
                case 5 -> championCRUD.addChampion();
                case 6 -> championCRUD.updateChampion();
                case 7 -> championCRUD.deleteChampion();
                case 8 -> championCRUD.saveToFile();
                case 0 -> {
                    System.out.println("\n✋ 이용해 주셔서 감사합니다!");
                    return;
                }
                default -> System.out.println("❌ 잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }
}
