package com.techcup_futbol.techcup_futbol.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.techcup_futbol.techcup_futbol.dto.Request.MatchRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.MatchResponseDTO;
import com.techcup_futbol.techcup_futbol.mappers.MatchMapper;
import com.techcup_futbol.techcup_futbol.model.Match.Match;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.repository.MatchRepository;
import com.techcup_futbol.techcup_futbol.repository.TeamRepository;
import com.techcup_futbol.techcup_futbol.repository.TournamentRepository;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final MatchMapper matchMapper;

    public MatchServiceImpl(MatchRepository matchRepository,
                            TournamentRepository tournamentRepository,
                            TeamRepository teamRepository,
                            MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.matchMapper = matchMapper;
    }

    @Override
    public MatchResponseDTO createMatch(MatchRequestDTO request) {
        Tournament tournament = tournamentRepository.findById(request.getTournamentId())
            .orElseThrow(() -> new RuntimeException("Torneo no encontrado con id: " + request.getTournamentId()));

        Team homeTeam = teamRepository.findById(request.getHomeTeamId())
            .orElseThrow(() -> new RuntimeException("Equipo local no encontrado con id: " + request.getHomeTeamId()));

        Team awayTeam = teamRepository.findById(request.getAwayTeamId())
            .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado con id: " + request.getAwayTeamId()));

        if (homeTeam.equals(awayTeam)) {
            throw new RuntimeException("El equipo local y visitante no pueden ser el mismo");
        }

        Match match = new Match(tournament, homeTeam, awayTeam,
                                request.getMatchDate(), request.getRound());
        match.setFieldName(request.getFieldName());

        // Si el request ya trae resultado, se registra directamente
        if (request.hasResult()) {
            match.registerResult(request.getHomeTeamGoals(), request.getAwayTeamGoals());
            registrarGoleador(match, request);
        }

        return matchMapper.toDto(matchRepository.save(match));
    }

    @Override
    public MatchResponseDTO getMatchById(Long id) {
        Match match = matchRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));
        return matchMapper.toDto(match);
    }

    @Override
    public List<MatchResponseDTO> getMatchesByTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new RuntimeException("Torneo no encontrado con id: " + tournamentId));
        return matchRepository.findByTournament(tournament)
            .stream()
            .map(matchMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<MatchResponseDTO> getMatchesByTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + teamId));
        return matchRepository.findByHomeTeamOrAwayTeam(team, team)
            .stream()
            .map(matchMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public MatchResponseDTO registerResult(Long matchId, MatchRequestDTO request) {
        Match match = matchRepository.findById(matchId)
            .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + matchId));

        if (!request.hasResult()) {
            throw new RuntimeException("Debes proporcionar los goles de ambos equipos para registrar el resultado");
        }

        match.registerResult(request.getHomeTeamGoals(), request.getAwayTeamGoals());
        registrarGoleador(match, request);

        return matchMapper.toDto(matchRepository.save(match));
    }

    @Override
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Partido no encontrado con id: " + id);
        }
        matchRepository.deleteById(id);
    }

    // ─── Método privado helper ─────────────────────────────────────────────────

    private void registrarGoleador(Match match, MatchRequestDTO request) {
        if (request.getTopScorerName() != null && request.getTopScorerTeamId() != null) {
            Team topScorerTeam = teamRepository.findById(request.getTopScorerTeamId())
                .orElseThrow(() -> new RuntimeException("Equipo del goleador no encontrado"));
            match.setTopScorerTeam(topScorerTeam);
            match.setTopScorerGoals(request.getTopScorerGoals());
            // Nota: topScorer (PlayerComponent) se setea aparte cuando Player sea entidad JPA
        }
    }
}