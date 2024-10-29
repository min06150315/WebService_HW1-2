package org.example;

public class Champion {
    private int id; // 고유 id
    private String name; // 챔피언 이름
    private String position; // 챔피언 역할 (암살자, 전사, 마법사, 원거리 딜러, 서포터, 탱커)
    private String damageType; // 데미지 유형 (물리, 마법, 혼합)
    private String skillPassive; // 패시브 스킬 이름
    private String skillQ; // Q 스킬 이름
    private String skillW; // W 스킬 이름
    private String skillE; // E 스킬 이름
    private String skillR; // R 스킬 이름
    private String createDate; // 데이터가 생성된 날짜
    public Champion(int id, String name, String position, String damageType, String passive, String q, String w, String e, String r, String createDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.damageType = damageType;
        this.skillPassive = passive;
        this.skillQ = q;
        this.skillW = w;
        this.skillE = e;
        this.skillR = r;
        this.createDate = createDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getDamageType() {
        return damageType;
    }
    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }
    public String getPassive() {
        return skillPassive;
    }
    public void setPassive(String passive) {
        this.skillPassive = passive;
    }
    public String getQ() {
        return skillQ;
    }
    public void setQ(String q) {
        this.skillQ = q;
    }
    public String getW() {
        return skillW;
    }
    public void setW(String w) {
        this.skillW = w;
    }
    public String getE() {
        return skillE;
    }
    public void setE(String e) {
        this.skillE = e;
    }
    public String getR() {
        return skillR;
    }
    public void setR(String r) {
        this.skillR = r;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    @Override
    public String toString() {
        return String.format("%d %s\t(%s %s)\t[%s\t%s\t%s\t%s\t%s]  -  %s", id, name, position, damageType, skillPassive, skillQ, skillW, skillE, skillR, createDate);
    }
}
