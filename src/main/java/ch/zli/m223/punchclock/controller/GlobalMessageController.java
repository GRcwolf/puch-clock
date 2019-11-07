package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.GlobalMessage;
import ch.zli.m223.punchclock.service.GlobalMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class GlobalMessageController {
    private GlobalMessageService globalMessageService;

    /**
     * Contructs a GlobalMessageController.
     *
     * @param globalMessageService
     *  The global message service.
     */
    public GlobalMessageController(GlobalMessageService globalMessageService) {
        this.globalMessageService = globalMessageService;
    }

    /**
     * Gets all global messages.
     *
     * @return
     *  A list of messages.
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<GlobalMessage> getAll() {
        return this.globalMessageService.getAll();
    }

    /**
     * Creates a new global message.
     *
     * @param globalMessage
     *  The global message to save.
     * @return
     *  The created object.
     */
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public GlobalMessage create(@RequestBody @Valid GlobalMessage globalMessage) {
        return this.globalMessageService.create(globalMessage);
    }

    /**
     * Patches a global message.
     *
     * @param globalMessage
     *  The new values of the global message.
     * @param id
     *  The id of the global message to patch.
     * @return
     *  The patched object.
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GlobalMessage patch(GlobalMessage globalMessage, @PathVariable @Valid long id) {
        return this.globalMessageService.patch(id, globalMessage);
    }

    /**
     * Deletes a specific global message.
     *
     * @param id
     *  The id of the message to delete.
     * @return
     *  A boolean indicating if the message has been deleted.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@PathVariable @Valid long id) {
        return this.globalMessageService.delete(id);
    }
}
