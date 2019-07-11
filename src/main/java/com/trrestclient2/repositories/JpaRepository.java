package com.trrestclient2.repositories;

import com.trrestclient2.entities.Site;

import java.util.List;

public interface JpaRepository<T, T1> {
    List<Site> findByNameLatitudeAndLongitude(String name, double latitude, double longitude);
}
