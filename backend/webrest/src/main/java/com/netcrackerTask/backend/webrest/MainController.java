package com.netcrackerTask.backend.webrest;

import java.util.List;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.implementations.StoreServiceImpl;
import com.netcrackerTask.backend.business.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    private final StoreServiceImpl storeService;

    /**
     * Constructor.
     * @param storeService service associated with store
     */
    @Autowired
    public MainController(final StoreServiceImpl storeService){
        this.storeService = storeService;
    }

    /**
     * Gets list of game stores
     *@return list of game stores
     */
    @GetMapping("/gamestores")
    public List<Game> main(){
        return (List<Game>) storeService.findAllGames();
    }


}
