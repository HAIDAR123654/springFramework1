package com.codejava;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@Validated
@PropertySource("classpath:ValidationMessages.properties")
public class UserController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    BookEntityRepo bookEntityRepo;
    @Autowired
    Environment environment;
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody User user) throws SeatNotLeftException, WrongEventException, DateException, UserAlreadyExist {
        UserEntity existUser = userRepo.findByEmail(user.getEmail());

        if(existUser != null){
            throw new UserAlreadyExist("user already exist, try with another email");
        }

        if(!user.isDateTomorrow()){
            throw new DateException(environment.getProperty(EventStallBookingConstrains.CUSTOMER_REGISTRATION_FAILED_DUE_TO_DATE.toString()));
        }
        String en = user.getBookEvent().getEventName();
        List<String> allevent = bookEntityRepo.findAllEventName();
        if(!allevent.contains(en)){
            throw new WrongEventException("pls choose a valid event " + "for " + en + " there is no booking happening");
        }
        BookEventEntity book = bookEntityRepo.findByEventName(en);

        UserEntity uent = new UserEntity();
        uent.setName(user.getName());
        uent.setEmail(user.getEmail());
        uent.setCity(user.getCity());
        uent.setDate(user.getDate());
        uent.setBookEventEntity(book);

        int seatLeft = book.getSeatLeft() - 1;
        book.setSeatLeft(book.getSeatLeft()-1);
        if(seatLeft < 0){
            throw new SeatNotLeftException("No seat are available for the " + en + " event");
        }
        userRepo.save(uent);

        Map<String , String> response = new HashMap<>();
        response.put("Event Book ID", String.valueOf(book.getEid()));
        response.put("message", "your event booked successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @Positive(message = "Number should be positive") int id) {
        Optional<UserEntity> u = userRepo.findById(id);
        if (u.isPresent()) {
            UserEntity user = u.get();
            user.getBookEventEntity().setSeatLeft(user.getBookEventEntity().getSeatLeft()+1);
            user.setBookEventEntity(null);
            userRepo.deleteById(id);

        }
        return new ResponseEntity<>(environment.getProperty(EventStallBookingConstrains.CUSTOMER_DELETED_PASS.toString()), HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "getAllUserEmail", produces = "application/json")
    public ResponseEntity<List<String>> getAllUserByEventName(@RequestParam("event") String event) throws EventStallBookingException {
        BookEventEntity b = bookEntityRepo.findByEventName(event);
        List<UserEntity> allUsers = userRepo.getAllUsersEventId(b.getEid());

        if(allUsers.isEmpty()){
            throw new EventStallBookingException("no customer booking has happened for this event "+ event);
        }
        List<String> emails = new ArrayList<>();
        for(UserEntity u : allUsers){
            emails.add(u.getEmail());
        }
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }
}
