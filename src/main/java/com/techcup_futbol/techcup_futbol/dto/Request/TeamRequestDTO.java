package com.techcup_futbol.techcup_futbol.dto.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamRequestDTO {
    
    private Long id;
    
    private String name;

}