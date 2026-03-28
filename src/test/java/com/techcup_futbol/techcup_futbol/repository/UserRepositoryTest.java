package com.techcup_futbol.techcup_futbol.repository;

import com.techcup_futbol.techcup_futbol.model.User.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") 
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Debe guardar un usuario correctamente")
    public void testCreateAndSaveUser() {
        User user = new User();
        user.setEmail("nuevo@techcup.com");
        user.setPassword("1234");
        
        User savedUser = userRepository.save(user);
        
        assertNotNull(savedUser.getId());
        assertEquals("nuevo@techcup.com", savedUser.getEmail());
    }

    @Test
    @DisplayName("Debe buscar un usuario por ID")
    public void testFindUser() {
        User user = new User();
        user.setEmail("buscar@techcup.com");
        user.setPassword("abcd");
        User savedUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("buscar@techcup.com", foundUser.get().getEmail());
    }

    @Test
    @DisplayName("Debe actualizar y eliminar un usuario")
    public void testUpdateAndDeleteUser() {
        User user = new User();
        user.setEmail("borrar@techcup.com");
        user.setPassword("0000");
        User savedUser = userRepository.save(user);

        // Prueba de eliminación
        userRepository.deleteById(savedUser.getId());
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());
        
        assertFalse(deletedUser.isPresent()); 
    }
}