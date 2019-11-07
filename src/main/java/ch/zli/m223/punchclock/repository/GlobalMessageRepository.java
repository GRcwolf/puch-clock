package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.GlobalMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalMessageRepository extends JpaRepository<GlobalMessage, Long> {
}
