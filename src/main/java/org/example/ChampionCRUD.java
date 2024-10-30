package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChampionCRUD implements ICRUD<Champion> {
    // Champion 객체를 저장하는 리스트. 참조 변경 방지를 위해 final 로 선언
    private final List<Champion> championList = new ArrayList<>();

    // 새로운 Champion 객체를 리스트에 추가하고 성공 시 1 반환
    @Override
    public int create(Champion champion) {
        // 리스트 크기를 이용해 새로운 Champion 의 ID 설정
        champion.setId(championList.size() + 1);
        championList.add(champion); // 리스트에 챔피언 추가
        return 1; // 성공적으로 추가된 경우 1 반환
    }

    // Champion 리스트를 반환. 외부에서 수정하지 못하도록 읽기 전용으로 반환
    @Override
    public List<Champion> list() {
        // Collections.unmodifiableList 를 사용하여 읽기 전용 리스트 반환
        return Collections.unmodifiableList(championList);
    }

    // ID를 기반으로 Champion 객체를 찾아 업데이트하고 성공 시 1 반환
    @Override
    public int update(Champion champion) {
        // 리스트를 순회하며 ID가 일치하는 Champion 객체 찾기
        for (int i = 0; i < championList.size(); i++) {
            if (championList.get(i).getId() == champion.getId()) {
                // ID가 일치하면 해당 위치에 새로운 Champion 객체 설정
                championList.set(i, champion);
                return 1; // 성공 시 1 반환
            }
        }
        return 0; // ID가 일치하는 Champion 이 없을 경우 0 반환
    }

    // ID를 기반으로 Champion 객체를 삭제하고 성공 시 1 반환
    @Override
    public int delete(int id) {
        int indexToRemove = -1; // 삭제할 인덱스를 저장할 변수

        // 리스트를 순회하여 삭제할 Champion 의 인덱스를 찾기
        for (int i = 0; i < championList.size(); i++) {
            if (championList.get(i).getId() == id) {
                indexToRemove = i; // 일치하는 ID의 인덱스를 저장
                break;
            }
        }

        // 해당 ID의 Champion 이 존재할 경우 삭제 진행
        if (indexToRemove != -1) {
            championList.remove(indexToRemove); // Champion 삭제

            // 삭제한 인덱스 이후의 Champion ID 갱신
            for (int i = indexToRemove; i < championList.size(); i++) {
                championList.get(i).setId(i + 1); // ID를 1씩 감소
            }
            return 1; // 성공 시 1 반환
        }
        return 0; // 해당 ID의 Champion 이 없을 경우 0 반환
    }
}
