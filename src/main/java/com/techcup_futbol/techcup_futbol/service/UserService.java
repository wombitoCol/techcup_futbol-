package com.techcup_futbol.techcup_futbol.service;

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

    // Crear o guardar un usuario
    public User create(User user) {
        return userRepository.save(user);
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