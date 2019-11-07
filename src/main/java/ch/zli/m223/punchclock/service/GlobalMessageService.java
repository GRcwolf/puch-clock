package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.GlobalMessage;
import ch.zli.m223.punchclock.repository.GlobalMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalMessageService {
    private GlobalMessageRepository globalMessageRepository;

    public GlobalMessageService(GlobalMessageRepository globalMessageRepository) {
        this.globalMessageRepository = globalMessageRepository;
    }

    public List<GlobalMessage> getAll() {
        return globalMessageRepository.findAll();
    }

    public GlobalMessage create(GlobalMessage globalMessage) {
        globalMessageRepository.saveAndFlush(globalMessage);
        return globalMessage;
    }

    public GlobalMessage patch(long id, GlobalMessage globalMessage) {
        GlobalMessage origMessage = globalMessageRepository.getOne(id);
        origMessage.setCreatorId(globalMessage.getCreatorId());
        origMessage.setExpiresAt(globalMessage.getExpiresAt());
        origMessage.setMessageContent(globalMessage.getMessageContent());
        globalMessageRepository.save(globalMessage);
        return  globalMessage;
    }

    public boolean delete(long id) {
        if (globalMessageRepository.existsById(id)) {
            globalMessageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
