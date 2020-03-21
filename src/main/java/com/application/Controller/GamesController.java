package com.application.Controller;

import com.application.Entity.Games;
import com.application.Models.GamesObject;
import com.application.Repository.GamesRepository;
import com.application.Utils.NameGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GamesController {
    private final GamesRepository gamesRepository;

    @Autowired
    public GamesController(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @PostMapping("/add")
    public GamesObject addColors(@Valid @RequestBody GamesObject gamesObject) throws Exception {
        Games games = new Games(gamesObject);
        games.setDate(new Date(System.currentTimeMillis()));
        try {
            return new GamesObject(gamesRepository.save(games));
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public List<GamesObject> getAllColors(@RequestParam(name = "Patient id") Integer patientId,
                                          @RequestParam(name = "Game name") NameGame gameName) {
        List<Games> games = gamesRepository.findAllByPatientIdAndName(patientId, gameName);
        return games.stream().map(GamesObject::new).collect(Collectors.toList());
    }

    @GetMapping("/get/allFromMonth")
    public List<GamesObject> getLastMonthAllColors(@RequestParam(name = "Patient id") Integer patientId,
                                          @RequestParam(name = "Game name") NameGame gameName) {
        Date date = new Date(System.currentTimeMillis());
        date.setMonth(date.getMonth()-1);
        List<Games> games = gamesRepository.findAllByDateAfterAndPatientIdAndNameOrderByDate(date, patientId, gameName);
        return games.stream().map(GamesObject::new).collect(Collectors.toList());
    }
}
