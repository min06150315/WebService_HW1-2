package org.example;

public interface ICRUD {
    void addChampion();
    void listChampions();
    void listChampionsByRegion();
    void listChampionsByPosition();
    void searchChampion();
    void updateChampion();
    void deleteChampion();
    void saveToFile();
}
