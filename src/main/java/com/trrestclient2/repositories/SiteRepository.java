package com.trrestclient2.repositories;

import com.trrestclient2.entities.Site;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SiteRepository extends JpaRepository<Site, Integer>{

    Site findOne(Integer id);

    List<Site> findAll();

    void save(Site location);
}
