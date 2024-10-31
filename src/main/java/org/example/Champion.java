package org.example;

// Lombok Library를 사용해서 코드의 양을 줄이고 가독성을 높임
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드를 초기화하는 생성자 추가
public class Champion {
    private int id; // 고유 id
    private String name; // 챔피언 이름
    private String region; // 챔피언의 소속 (ex. 데미시아, 프렐요드, 필트오버, 아이오니아...)
    private String position; // 챔피언 역할 (암살자, 전사, 원거리, 마법사, 탱커, 서포터)
    private String damageType; // 데미지 유형 (물리, 마법, 혼합)
    private String skillPassive; // 패시브 스킬 이름
    private String skillQ; // Q 스킬 이름
    private String skillW; // W 스킬 이름
    private String skillE; // E 스킬 이름
    private String skillR; // R 스킬 이름
    private String createDate; // 데이터가 생성된 날짜

    @Override
    public String toString() {
        return String.format("%d. %s  (%s / %s / %s)\t[패시브: %s / Q: %s / W: %s / E: %s / R: %s]  -  %s", id, name, region, position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate);
    }
}
