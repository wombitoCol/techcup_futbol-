package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // El servicio llama al repositolistorio (la base de datos)
    @Autowired
    private UserRepository userRepository;

    // Método para registrar un nuevo usuario aplicando las reglas de negocio
    public UserResponseDTO createUser(UserRequestDTO request) {
        
        // 1. Transformamos el DTO de entrada en un Modelo real (Entidad)
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());

        // 2. Le decimos al Repositorio que lo guarde en PostgreSQL
        User savedUser = userRepository.save(newUser);

        // 3. Transformamos el Modelo guardado en un DTO de salida (¡Sin contraseña!)
        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());

        // 4. Devolvemos la respuesta segura
        return response;
    }

    // Listar todos los usuarios
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Buscar un usuario por su ID
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    // Buscar por email (usando el método especial que hicimos en el repositorio)
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // Eliminar un usuario
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}