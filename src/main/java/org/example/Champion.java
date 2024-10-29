package org.example;

public class Champion {
    private int id; // 고유 id
    private String name; // 챔피언 이름
    private String position; // 챔피언 역할 (암살자, 전사, 마법사, 원거리 딜러, 서포터, 탱커)
    private String damageType; // 데미지 유형 (물리, 마법, 혼합)
    private String passive; // 패시브 스킬 이름
    private String Q; // Q 스킬 이름
    private String W; // W 스킬 이름
    private String E; // E 스킬 이름
    private String R; // R 스킬 이름
    private String createDate; // 데이터가 생성된 날짜
    public Champion(int id, String name, String position, String damageType, String passive, String q, String w, String e, String r, String createDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.damageType = damageType;
        this.passive = passive;
        this.Q = q;
        this.W = w;
        this.E = e;
        this.R = r;
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
        return passive;
    }
    public void setPassive(String passive) {
        this.passive = passive;
    }
    public String getQ() {
        return Q;
    }
    public void setQ(String q) {
        this.Q = q;
    }
    public String getW() {
        return W;
    }
    public void setW(String w) {
        this.W = w;
    }
    public String getE() {
        return E;
    }
    public void setE(String e) {
        this.E = e;
    }
    public String getR() {
        return R;
    }
    public void setR(String r) {
        this.R = r;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    @Override
    public String toString() {
        return String.format("%d %s\t(%s %s)\t[%s\t%s\t%s\t%s\t%s]  -  %s", id, name, position, damageType, passive, Q, W, E, R, createDate);
    }
}
