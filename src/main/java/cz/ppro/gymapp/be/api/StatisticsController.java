package cz.ppro.gymapp.be.api;

        import cz.ppro.gymapp.be.exception.ResourceNotFoundException;
        import cz.ppro.gymapp.be.model.Account;
        import cz.ppro.gymapp.be.model.Entrance;
        import cz.ppro.gymapp.be.model.Role;
        import cz.ppro.gymapp.be.model.Statistics;
        import cz.ppro.gymapp.be.repository.AccountRepository;
        import cz.ppro.gymapp.be.repository.EntranceRepository;
        import cz.ppro.gymapp.be.repository.RoleRepository;
        import cz.ppro.gymapp.be.repository.StatisticsRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.lang.NonNull;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.server.ResponseStatusException;

        import javax.validation.Valid;
        import java.util.ArrayList;
        import java.util.List;

@CrossOrigin
@RequestMapping("/statistics")
@RestController
public class StatisticsController {
    private AccountRepository accountRepository;
    private EntranceRepository entranceRepository;

    @Autowired
    public StatisticsController(AccountRepository accountRepository, EntranceRepository entranceRepository) {
        this.accountRepository = accountRepository;
        this.entranceRepository = entranceRepository;
    }

    @RequestMapping(value = "/ticketcount/{id}", method = RequestMethod.GET)
    public int getTicketCount(@PathVariable(value = "id") Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account", "id", id));
        Statistics statistics = new Statistics(account);
        return  statistics.getTicketCount();
    }

    @RequestMapping(value = "/purchasesprice/{id}", method = RequestMethod.GET)
    public double getPurchasesPrice(@PathVariable(value = "id") Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account", "id", id));
        Statistics statistics = new Statistics(account);
        return  statistics.getPurchasesPrice();
    }

    @RequestMapping(value = "/purchasescount/{id}", method = RequestMethod.GET)
    public int getPurchasesCount(@PathVariable(value = "id") Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account", "id", id));
        Statistics statistics = new Statistics(account);
        return  statistics.getPurchasesCount();
    }
    //@RequestMapping(value = "/coursesvisited/{id}", method = RequestMethod.GET)
    /*public int getCoursesVisited(@PathVariable(value = "id") Long id,
                                 @Valid @RequestBody Account accountDetails){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account", "id", id));
        Statistics statistics = new Statistics(account);
        return  statistics.getCoursesVisited();
    }*/
    @RequestMapping(value = "/coursescreated/{id}", method = RequestMethod.GET)
    public int getCoursesCreated(@PathVariable(value = "id") Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account", "id", id));
        Statistics statistics = new Statistics(account);
        return  statistics.getCoursesCreated();
    }

    @RequestMapping(value = "/getCountOfEntrancesOfLastSevenDays/{id}", method = RequestMethod.GET)
    public int getCountOfEntrancesOfLastSevenDays(@PathVariable(value = "id") Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account", "id", id));
        Statistics statistics = new Statistics(account);
        return  statistics.getCountOfEntrancesOfLastSevenDays();
    }

    @RequestMapping(value = "/getAverageNumberOFEntrances", method = RequestMethod.GET)
    public double getAverageNumberOFEntrances(){
        Statistics statistics = new Statistics();
        List<Entrance> entrances = new ArrayList<>();
        entrances.addAll(entranceRepository.findAll());
        return  statistics.getAverageNumberOFEntrances(entrances);
    }

    @RequestMapping(value = "/getFavouriteDay", method = RequestMethod.GET)
    public List<String> getFavouriteDay(){
        Statistics statistics = new Statistics();
        List<Entrance> entrances = new ArrayList<>();
        entrances.addAll(entranceRepository.findAll());
        List<String> favouriteDay = new ArrayList<>();
        favouriteDay.add(statistics.getFavouriteDay(entrances));
        return  favouriteDay;
    }

    @RequestMapping(value = "/getCountOFEntrances", method = RequestMethod.GET)
    public int getCountOFEntrances(){
        Statistics statistics = new Statistics();
        List<Entrance> entrances = new ArrayList<>();
        entrances.addAll(entranceRepository.findAll());
        return  statistics.getCountOFEntrances(entrances);
    }


}

