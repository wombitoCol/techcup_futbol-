/* 
package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public UserService() {
        users.add(new User(idGenerator.getAndIncrement(), "demo@tournament.com", "1234"));
        log.info("UserService initialized with dummy data.");
    }

    public List<User> findAll() {
        log.debug("Fetching all users");
        return new ArrayList<>(users);
    }

    public User create(User user) {
        user.setId(idGenerator.getAndIncrement());
        user.setRole("player");
        user.setActive(true);
        users.add(user);
        log.info("User successfully created: {}", user.getEmail());
        return user;
    }

    public Optional<User> updateRole(Long id, String role, boolean isAdmin) {
        if (!isAdmin) {
            log.warn("Unauthorized role change attempt for user ID: {}", id);
            return Optional.empty();
        }
        for (User u : users) {
            if (u.getId().equals(id)) {
                u.setRole(role);
                log.info("Role updated for user ID: {}", id);
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    public boolean inactiveUser(Long id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                u.setActive(false);
                log.info("User deactivated ID: {}", id);
                return true;
            }
        }
        log.warn("User to deactivate not found ID: {}", id);
        return false;
    }
}
*/