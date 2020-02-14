package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
//    String get
}
