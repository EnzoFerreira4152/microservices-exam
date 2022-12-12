package com.dh.catalog.repository;

import com.dh.catalog.model.series.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SerieRepository extends MongoRepository<Serie, Long> {

    @Query("{'genre': ?0}}")
    List<Serie> findAllByGenre(String genre);

}
