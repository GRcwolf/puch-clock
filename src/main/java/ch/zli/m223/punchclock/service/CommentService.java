package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Comment;
import ch.zli.m223.punchclock.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment create(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    public boolean delete(long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Comment patch(long id, Comment comment) {
        Comment origComment = commentRepository.getOne(id);
        origComment.setContent(comment.getContent());
        origComment.setCreateTime(comment.getCreateTime());
        origComment.setCreatorId(comment.getCreatorId());
        origComment.setEntryId(comment.getEntryId());
        commentRepository.save(origComment);
        return origComment;
    }
}