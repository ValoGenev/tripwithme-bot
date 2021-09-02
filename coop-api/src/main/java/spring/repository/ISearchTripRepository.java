package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.SearchEntity;

import java.util.Optional;

@Repository
public interface ISearchTripRepository extends JpaRepository<SearchEntity,String> {

    @Query("select a from SearchEntity as a where a.id = :id")
    Optional<SearchEntity> findById(@Param("id") String id);
}
