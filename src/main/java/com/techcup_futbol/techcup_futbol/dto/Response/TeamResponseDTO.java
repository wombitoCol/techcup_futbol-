package com.techcup_futbol.techcup_futbol.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResponseDTO {
    
    private Long id;
    
    private String name;


}