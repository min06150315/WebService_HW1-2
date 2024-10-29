package org.example;

import java.util.List;

public interface ICRUD<C> {
    int create(C one);
    List<C> list();
    int update(C one);
    int delete(int id);
}
