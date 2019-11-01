package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Comment;
import ch.zli.m223.punchclock.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(long entryId, Comment comment) {

    }
}
