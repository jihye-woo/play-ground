package com.example.querydsl;

import java.util.List;

public interface AcademyRepositoryCustom {
    List<Academy> findByName(String name);
}
