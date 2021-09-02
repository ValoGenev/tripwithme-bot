package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.UserEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,String> {


    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findUserByFacebook(String facebook);

    @Query("select case when count(u)> 0 then true else false end from UserEntity as u where lower(u.userName) like lower(:userName)")
    boolean checkIfUserNameExists(@Param("userName") String username);

    @Query("select case when count(u)> 0 then true else false end from UserEntity as u where lower(u.email) like lower(:email)")
    boolean checkIfEmailExists(@Param("email") String email);

    @Query("select u from UserEntity as u where u.email = :email")
    Optional<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity as u inner join VerificationTokenEntity " +
            "as v on u.verification.id=v.id where v.resetPassWordConfirmationToken like(:token)")
    Optional<UserEntity> findUserByResetPasswordToken(String token);



}
