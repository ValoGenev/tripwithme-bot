package spring.service.user;


import spring.dto.PasswordAfterResetDto;
import spring.dto.PasswordResetDto;
import spring.dto.car.CarWithoutRelationsDto;
import spring.dto.searches.SearchWithoutUserDto;
import spring.dto.trip.TripWithoutPassengersDto;
import spring.dto.user.*;

import java.util.Set;

public interface IUserService {

    Set<UserAllPropertiesDto> findAll();

    UserAllPropertiesDto findOne(String userName);

    void delete(String userName);

    UserAllPropertiesDto create(UserAllPropertiesDto user);

    UserAllPropertiesDto update(UserAllPropertiesDto user, String userName);

    Set<TripWithoutPassengersDto> getTrips(String userName);

    Set<CarWithoutRelationsDto> getCars(String userName);

    UserProfileDto login(UserLoginDto user);


    Set<SearchWithoutUserDto> getSearches(String userName);

    UserProfileDto register(UserRegisterDto user);

    UserProfileDto loginAsGuest();

    UserProfileDto loginWithFacebook(UserLoginWithFacebookDto user);

    void resetPassword(PasswordResetDto passwordResetDto);

    void changePasswordAfterReset(PasswordAfterResetDto passwordResetDto);

    UserProfileDto update(UserEditProfileDto editProfileDto,String userName);

}
