
package com.techcup_futbol.techcup_futbol.mappers;
 
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
 
import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
 
@Mapper(componentModel = "spring")
public interface TournamentMapper {
 
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finishDate", ignore = true)
    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "winner", ignore = true)
    @Mapping(target = "teamsNumber", ignore = true)
    Tournament toEntity(TournamentRequestDTO dto);
 
    TournamentResponseDTO toDto(Tournament tournament);
}
 