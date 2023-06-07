package com.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT * from user_table WHERE eid = ?1", nativeQuery = true)
    List<UserEntity> getAllUsersEventId(int eventId);

    UserEntity findByEmail(String email);
}
