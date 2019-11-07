package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
