package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.TripEntity;
import spring.entity.VerificationTokenEntity;

import java.util.Optional;

@Repository
public interface IVerificationRepository extends JpaRepository<VerificationTokenEntity, String> {

    @Query("select a from VerificationTokenEntity as a where a.emailConfirmationToken=:token")
    Optional<VerificationTokenEntity> getVerification(@Param("token") String token);


    Optional<VerificationTokenEntity> findFirstByEmailConfirmationTokenLike(String token);

}