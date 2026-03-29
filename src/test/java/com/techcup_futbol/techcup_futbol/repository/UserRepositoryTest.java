package com.techcup_futbol.techcup_futbol.repository;

import com.techcup_futbol.techcup_futbol.model.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest; // <-- Cambiamos a SpringBootTest
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest // <-- Nueva etiqueta súper poderosa
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateAndSaveUser() {
        // 1. Preparamos un usuario de prueba
        User user = new User();
        user.setEmail("prueba@techcup.com");
        user.setPassword("secreta123");

        // 2. Lo guardamos en la base de datos H2
        User savedUser = userRepository.save(user);

        // 3. Verificamos que se guardó correctamente (debería tener un ID autogenerado)
        assertNotNull(savedUser.getId());
        assertEquals("prueba@techcup.com", savedUser.getEmail());
    }
}