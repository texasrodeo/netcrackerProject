package com.netcrackerTask.backend.webrest;

import java.util.List;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    private final GameRepository gameRepository;

    @Autowired
    public MainController(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/gamestores")
    public List<Game> main() {
        return (List<Game>) gameRepository.findAll();
    }


}
