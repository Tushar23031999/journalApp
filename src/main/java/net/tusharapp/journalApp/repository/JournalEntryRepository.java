package net.tusharapp.journalApp.repository;

import net.tusharapp.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}

// controller  --> service --> repository extends mongorepository
