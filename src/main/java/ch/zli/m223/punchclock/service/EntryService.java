package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    private EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) {
        return entryRepository.saveAndFlush(entry);
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public boolean deleteEntry(long id) {
        boolean deleted = false;
        if (entryRepository.existsById(id)) {
            entryRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    public Entry patchEntry(Entry entry, long id) {
        Entry currentEntry = entryRepository.getOne(id);
        currentEntry.setCheckIn(entry.getCheckIn());
        currentEntry.setCheckOut(entry.getCheckOut());
        entryRepository.save(entry);
        return entry;
    }
}
