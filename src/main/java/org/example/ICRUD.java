package org.example;

import java.util.List;

public interface ICRUD {
    int create(Object one);
    List list();
    int update(Object one);
    int delete(int id);
}
