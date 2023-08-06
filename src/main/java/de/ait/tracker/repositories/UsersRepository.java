package de.ait.tracker.repositories;

import de.ait.tracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    // extends JpaRepository<Event, Long>
    List<User> findAll();
}
