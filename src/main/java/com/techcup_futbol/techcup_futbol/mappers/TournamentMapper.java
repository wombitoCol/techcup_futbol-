package com.techcup_futbol.techcup_futbol.mappers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentLightning;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentLightningBuilder;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;

@Mapper(componentModel = "spring")
public interface TournamentMapper {


    // Esta es la unica forma que vi para instanciar una clase abstracta
    Tournament toEntity(TournamentRequestDTO dto);

    @ObjectFactory
    default Tournament createTournament(TournamentRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        String type = dto.getType();

        switch (type) {

            case "lightning":
                return new TournamentLightningBuilder()
                    .startDate(LocalDateTime.now())
                    .finishDate(LocalDateTime.now().plusDays(10))
                    .finishDateToRegister(LocalDateTime.now().plusDays(5))
                    .importantDates(LocalDateTime.now())
                    .costPerTeam(100.0)
                    .description("Torneo relámpago")
                    .build();

            default:
                throw new IllegalArgumentException("Tipo de torneo no válido: " + type);
        }
    }

    TournamentResponseDTO toDto(Tournament tournament);
}
