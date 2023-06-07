package com.codejava;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface BookEntityRepo extends JpaRepository<BookEventEntity, Integer> {
    BookEventEntity findByEventName(String en);
    @Modifying
    @Query(value = "UPDATE book_entity SET seat_left = ?1 WHERE eid = ?2", nativeQuery = true)
    void updateSeatLeft(int seatLeft, int eventId);

    @Query(value = "SELECT e.event_name from book_entity e", nativeQuery = true)
    List<String> findAllEventName();




}
