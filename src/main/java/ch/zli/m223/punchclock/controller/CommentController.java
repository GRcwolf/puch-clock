package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.service.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
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
}
