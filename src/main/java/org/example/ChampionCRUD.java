package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChampionCRUD implements ICRUD {
    private final DBUtil dbUtil = new DBUtil();
    private final List<Champion> champions = dbUtil.loadChampionsFromDatabase();
    private final Scanner sc = new Scanner(System.in);
    private int nextId;
    public ChampionCRUD() {
        if (!champions.isEmpty()) {
            nextId = champions.stream()
                    .max(Comparator.comparingInt(Champion::getId))
                    .get().getId() + 1;  // 가장 큰 ID에 1을 더하여 시작
        } else {
            nextId = 1;  // 챔피언 목록이 비어 있다면 ID는 1부터 시작
        }
    }
    @Override
    public void addChampion() {
        String name, region, position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate;
        LocalDateTime now = LocalDateTime.now();

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

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        createDate = now.format(dateTimeFormatter);

        Champion newChampion = new Champion(nextId++, name, region, position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate);
        champions.add(newChampion);
        dbUtil.addChampionToDatabase(newChampion);
        System.out.println("✅ 새로운 챔피언이 추가되었습니다: " + newChampion.getName() + " [" + newChampion.getRegion() + "]");
    }

    @Override
    public void listChampions() {
        if (!champions.isEmpty()) {
            System.out.println("\n전체 챔피언 목록:");
            System.out.println("------------------------------------------------------------------------------------------------");
            for (Champion champion : champions) {
                System.out.println(champion);
            }
            System.out.println("------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("\n⚠️ 챔피언이 존재하지 않습니다.");
        }
    }

    @Override
    public void listChampionsByRegion() {
        System.out.println("소속 목록: 그림자 군도, 녹서스, 데마시아, 밴들 시티, 빌지워터, 슈리마, 아이오니아, 이쉬탈, 자운, 타곤, 프렐요드, 필트오버, 공허");
        System.out.print("조회할 챔피언의 소속을 입력하세요: ");
        String region = sc.nextLine();

        List<Champion> filteredChampions = champions.stream()
                .filter(champion -> champion.getRegion().equalsIgnoreCase(region))
                .collect(Collectors.toList());

        if (!filteredChampions.isEmpty()) {
            System.out.println("\n" + region + "에 속한 챔피언 목록:");
            System.out.println("------------------------------------------------------------------------------------------------");
            filteredChampions.forEach(System.out::println);
            System.out.println("------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("\n⚠️ 해당 소속 챔피언이 존재하지 않습니다.");
        }
    }

    @Override
    public void listChampionsByPosition() {
        System.out.println("포지션 목록: 암살자, 전사, 원거리, 마법사, 탱커, 서포터");
        System.out.print("조회할 챔피언의 포지션을 입력하세요: ");
        String position = sc.nextLine();

        List<Champion> filteredChampions = champions.stream()
                .filter(champion -> champion.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());

        if (!filteredChampions.isEmpty()) {
            System.out.println("\n" + position + " 포지션에 해당하는 챔피언 목록:");
            System.out.println("------------------------------------------------------------------------------------------------");
            filteredChampions.forEach(System.out::println);
            System.out.println("------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("\n⚠️ 해당 포지션에 챔피언이 존재하지 않습니다.");
        }
    }

    @Override
    public void searchChampion() {
        System.out.print("검색할 챔피언 이름을 입력하세요: ");
        String name = sc.nextLine();
        Champion champion = champions.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);

        if (champion != null) {
            System.out.println("챔피언 정보: " + champion);
        } else {
            System.out.println("\n⚠️ 해당 이름의 챔피언이 존재하지 않습니다.");
        }
    }

    @Override
    public void updateChampion() {
        System.out.print("수정할 챔피언의 이름을 입력하세요: ");
        String name = sc.nextLine();
        Champion champion = champions.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);

        if (champion != null) {
            System.out.println("1. 이름 2. 소속 지역 3. 포지션 4. 데미지 유형 5. 패시브 스킬 6. Q 스킬 7. W 스킬 8. E 스킬 9. R 스킬");
            System.out.print("👉 수정할 항목의 번호를 선택하세요: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("새로운 이름을 입력하세요: ");
                    champion.setName(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("새로운 소속 지역을 입력하세요: ");
                    champion.setRegion(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("새로운 포지션을 입력하세요: ");
                    champion.setPosition(sc.nextLine());
                }
                case 4 -> {
                    System.out.print("새로운 데미지 유형을 입력하세요: ");
                    champion.setDamageType(sc.nextLine());
                }
                case 5 -> {
                    System.out.print("새로운 패시브 스킬을 입력하세요: ");
                    champion.setSkillPassive(sc.nextLine());
                }
                case 6 -> {
                    System.out.print("새로운 Q 스킬을 입력하세요: ");
                    champion.setSkillQ(sc.nextLine());
                }
                case 7 -> {
                    System.out.print("새로운 W 스킬을 입력하세요: ");
                    champion.setSkillW(sc.nextLine());
                }
                case 8 -> {
                    System.out.print("새로운 E 스킬을 입력하세요: ");
                    champion.setSkillE(sc.nextLine());
                }
                case 9 -> {
                    System.out.print("새로운 R 스킬을 입력하세요: ");
                    champion.setSkillR(sc.nextLine());
                }
                default -> {
                    System.out.println("⚠️ 잘못된 입력입니다.");
                    return;
                }
            }
            dbUtil.updateChampionInDatabase(champion);
            System.out.println("✅ 챔피언 정보가 업데이트되었습니다.");
        } else {
            System.out.println("\n⚠️ 해당 이름의 챔피언이 존재하지 않습니다.");
        }
    }

    @Override
    public void deleteChampion() {
        System.out.print("삭제할 챔피언의 이름을 입력하세요: ");
        String name = sc.nextLine();
        Champion champion = champions.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);

        if (champion != null) {
            champions.remove(champion);
            dbUtil.deleteChampionFromDatabase(champion.getId());
            resetChampionIds();
            System.out.println("✅ 챔피언이 삭제되었습니다.");
            nextId--;
        } else {
            System.out.println("\n⚠️ 해당 이름의 챔피언이 존재하지 않습니다.");
        }
    }

    private void resetChampionIds() {
        // 필요한 경우에만 ID 정렬 후 데이터베이스 업데이트
        champions.sort(Comparator.comparingInt(Champion::getId));
        for (int i = 0; i < champions.size(); i++) {
            champions.get(i).setId(i + 1);
            dbUtil.updateChampionInDatabase(champions.get(i));
        }
    }

    @Override
    public void saveToFile() {
        String filename = "champions.txt";
        try (FileWriter writer = new FileWriter(filename)) {
            for (Champion champion : champions) {
                writer.write(champion.getId() + " / ");
                writer.write(champion.getName() + " / ");
                writer.write(champion.getRegion() + " / ");
                writer.write(champion.getPosition() + " / ");
                writer.write(champion.getDamageType() + " / ");
                writer.write(champion.getSkillPassive() + " / ");
                writer.write(champion.getSkillQ() + " / ");
                writer.write(champion.getSkillW() + " / ");
                writer.write(champion.getSkillE() + " / ");
                writer.write(champion.getSkillR() + " / ");
                writer.write(champion.getCreateDate() + "\n");
            }
            System.out.println("✅ 챔피언 목록이 " + filename + " 파일에 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("⚠️ 파일 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
