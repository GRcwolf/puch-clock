package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {
    private EntryService entryService;

    /**
     * Constructs a new EntryController.
     *
     * @param entryService
     *  The entry service.
     */
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    /**
     * Returns all entries.
     *
     * @return
     *  A list of entries.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    /**
     * Creates a new entry.
     *
     * @param entry
     *  The entry object to be saved.
     * @return
     *  The created entry.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }

    /**
     * Deletes entries.
     *
     * @param id
     *  The id of the entry to delete.
     * @return
     *  A boolean indicating if the entry has been deleted.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteEntry(@PathVariable("id") long id) {
        return entryService.deleteEntry(id);
    }

    /**
     * Patches an entry.
     *
     * @param id
     *  The id of the entry to be patched.
     * @param entry
     *  The object with the new entry values.
     * @return
     *  The updated entry.
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Entry patchEntry(@PathVariable("id") long id, @Valid @RequestBody Entry entry) {
        return entryService.patchEntry(entry, id);
    }
}
