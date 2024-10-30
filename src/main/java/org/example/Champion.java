package org.example;

// Lombok Library 를 사용해서 코드의 양을 줄이고 가독성을 높임 (Lombok 이 자동으로 Getter 와 Setter 를 생성해준다)
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Champion {
    private int id; // 고유 id
    private String name; // 챔피언 이름
    private String region; // 챔피언의 소속 (ex. 데미시아, 프렐요드, 필트오버, 아이오니아...)
    private String position; // 챔피언 역할 (암살자, 전사, 마법사, 원거리 딜러, 서포터, 탱커)
    private String damageType; // 데미지 유형 (물리, 마법, 혼합)
    private String skillPassive; // 패시브 스킬 이름
    private String skillQ; // Q 스킬 이름
    private String skillW; // W 스킬 이름
    private String skillE; // E 스킬 이름
    private String skillR; // R 스킬 이름
    private String createDate; // 데이터가 생성된 날짜

    public Champion(int id, String name, String region, String position, String damageType, String skillPassive, String skillQ, String skillW, String skillE, String skillR, String createDate) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.position = position;
        this.damageType = damageType;
        this.skillPassive = skillPassive;
        this.skillQ = skillQ;
        this.skillW = skillW;
        this.skillE = skillE;
        this.skillR = skillR;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return String.format("%d. %s\t(%s  %s  %s)\t[%s\t%s\t%s\t%s\t%s]  -  %s", id, name, region, position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate);
    }
}
