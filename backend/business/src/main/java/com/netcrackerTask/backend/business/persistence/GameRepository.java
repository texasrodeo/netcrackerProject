package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
    /**
     * Gets game by id
     * @param id game id
     * @return game
     * */
    Game getGameById(Long id);
}
