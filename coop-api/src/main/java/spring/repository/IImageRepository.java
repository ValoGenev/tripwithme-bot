package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.entity.ImageEntity;

@Repository
public interface IImageRepository extends JpaRepository<ImageEntity,String> {
}
