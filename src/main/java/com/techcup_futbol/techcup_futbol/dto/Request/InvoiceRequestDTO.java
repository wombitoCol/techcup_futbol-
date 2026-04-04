package com.techcup_futbol.techcup_futbol.dto.Request;

import java.io.Serializable;

import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InvoiceRequestDTO implements Serializable {
    private Double amount;
    private String description;
    private Tournament tournament;
    private Team team;

}