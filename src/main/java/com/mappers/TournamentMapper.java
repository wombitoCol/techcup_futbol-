package com.mappers;

import org.springframework.stereotype.Component;

import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;

@Component
public class TournamentMapper {

    public TournamentResponseDTO toDto(Tournament tournament) {

        if (tournament == null) {
            return null;
        }

        TournamentResponseDTO dto = new TournamentResponseDTO();

        dto.setId(tournament.getId());
        dto.setName(tournament.getName());
        dto.setState(tournament.getState().name()); // enum → String

        return dto;
    }
}
