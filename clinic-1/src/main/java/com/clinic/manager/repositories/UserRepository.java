package com.clinic.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clinic.manager.dto.UserDto;
import com.clinic.manager.entities.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;


public interface UserRepository extends JpaRepository<UserEntity, Long>   {


    @Query(value = "SELECT User.id AS id, User.id_role AS idRole, User.name AS name, User.gender AS gender, User.address AS address, User.phone AS phone, User.email AS email, User.password AS password FROM user as User "
                    +"ORDER BY User.id "
                    +"LIMIT :offset, :limit", nativeQuery = true)
    List<UserEntity> getListUser(
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);

    @Query(value = "SELECT User.id AS id, User.name AS name, User.gender AS gender, User.address AS address, User.phone AS phone, User.email AS email, User.password AS password FROM user as User "
            +"WHERE User.email = :email AND User.password = :password", nativeQuery = true)
    UserEntity getUserByEmailAndPassword(
            @Param("email") String email,
            @Param("password") String password);

}
