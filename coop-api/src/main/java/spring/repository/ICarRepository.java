package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.CarEntity;
import spring.entity.TripEntity;

import java.util.Optional;

@Repository
public interface ICarRepository extends JpaRepository<CarEntity,String> {

    @Query("select a from CarEntity as a where a.id = :id")
    Optional<CarEntity> findById(@Param("id") String id);
}
