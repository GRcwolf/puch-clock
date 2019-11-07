package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Comment;
import ch.zli.m223.punchclock.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    /**
     * Constructs a CommentController.
     *
     * @param commentService
     *  The service of the comments.
     */
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Returns all existing comments.
     *
     * @return
     *  A list of comments.
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getComments() {
        return this.commentService.getAll();
    }

    /**
     * Patch a specific comments.
     *
     * @param id
     *  The id of the comment to patch.
     * @param comment
     *  The new Comment values.
     * @return
     *  The updated Comment.
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment patchComment(@PathVariable @Valid long id, Comment comment) {
        return this.commentService.patch(id, comment);
    }

    /**
     * Deletes a specific comment.
     *
     * @param id
     *  The id of the comment to be deleted.
     * @return
     *  The a bool indicating if teh user has been deleted.
     */
    @DeleteMapping("/{id}")
    public boolean deleteComment(@PathVariable @Valid long id) {
        return this.commentService.delete(id);
    }
}
