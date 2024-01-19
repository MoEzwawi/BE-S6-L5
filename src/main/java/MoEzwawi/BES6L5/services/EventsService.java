package MoEzwawi.BES6L5.services;

import MoEzwawi.BES6L5.entities.Event;
import MoEzwawi.BES6L5.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsService {
    @Autowired
    private EventsRepository eventsRepository;
    public void save(Event event){
        eventsRepository.save(event);
        System.out.println("Event saved correctly");
    }
    public List<Event> findAll(){
        return eventsRepository.findAll();
    }
}
