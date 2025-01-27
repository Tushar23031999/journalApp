package net.tusharapp.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.tusharapp.journalApp.entity.JournalEntry;
import net.tusharapp.journalApp.entity.User;
import net.tusharapp.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now()); //to add time from here to the response coming from post request it will add time to db
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
//            user.setUsername(null); only to understand @Transaction anootation
            userService.saveUser(user);

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error ocurred while saving the entry", e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
            return journalEntryRepository.findById(id);
    }
    //optional is suggested by spring so that if noting is there it will return null

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error",e);
            throw new RuntimeException("An error ocurred while delete the entry.", e);
        }
        return removed;
    }
}
