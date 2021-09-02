package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.TripEntity;

import java.util.Optional;

@Repository
public interface ITripRepository extends JpaRepository<TripEntity, String> {

    @Query("select a from TripEntity as a where a.id = :id")
    Optional<TripEntity> findById(@Param("id") String id);

}
