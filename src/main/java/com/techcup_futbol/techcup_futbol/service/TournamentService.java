package com.techcup_futbol.techcup_futbol.service;


import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    // Conectamos el servicio con el repositorio de la base de datos
    @Autowired
    private TournamentRepository tournamentRepository;

    // Crear o guardar un torneo
    public Tournament create(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    // Listar todos los torneos
    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    // Buscar un torneo por su ID
    public Optional<Tournament> getById(Long id) {
        return tournamentRepository.findById(id);
    }

    // Buscar torneos por nombre (usando el método que creamos en el repositorio)
    public List<Tournament> getByName(String name) {
        return tournamentRepository.findAll().stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .toList();
    }

    // Actualizar un torneo
    public Tournament update(Long id, Tournament tournament) {
        tournament.setId(id);
        return tournamentRepository.save(tournament);
    }

    // Eliminar un torneo
    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }
}