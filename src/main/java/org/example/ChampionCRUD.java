package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChampionCRUD implements ICRUD {
    private List<Champion> championList;
    public ChampionCRUD() {
        championList = new ArrayList<>();
    }
    @Override
    public int create(Object one) {
        return 0;
    }

    @Override
    public List list() {
        return null;
    }

    @Override
    public int update(Object one) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
