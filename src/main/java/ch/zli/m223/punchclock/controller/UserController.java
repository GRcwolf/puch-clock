package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.UserRepository;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructs a new UserController.
     *
     * @param userRepository
     *  The user repository.
     * @param bCryptPasswordEncoder
     *  BCrypt encoder.
     * @param userService
     *  The user service.
     */
    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    /**
     * Creates a new sign up.
     *
     * @param user
     *  The user object to be created.
     */
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    /**
     * Returns a list of all users.
     *
     * @return
     *  A list of all users.
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * Returns a specific user by its id.
     *
     * @param id
     *  The id of the user to return.
     * @return
     *  The user object with the specified id.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    /**
     * Deletes a specific user based on its id.
     *
     * @param id
     *  The id of the user to delete.
     * @return
     *  Au boolean indicating
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    /**
     * Patches a specific user.
     *
     * @param id
     *  The id of the user to be patched.
     * @param user
     *  The user object with the new user values.
     * @return
     *  The new User object.
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User patchUser(@PathVariable long id, @RequestBody @Valid User user) {
        return userService.patchUser(id, user);
    }
}
