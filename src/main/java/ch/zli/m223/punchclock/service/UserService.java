package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableSortedSet;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = userRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

    /**
     * Returns a user by its id.
     *
     * @param id
     *  The id of the user.
     * @return
     *  The user object with the required id.
     */
    public User getUser(long id) {
        return userRepository.getOne(id);
    }

    /**
     * Deletes a user by its id.
     *
     * @param id
     *  The id of the user.
     * @return
     *  A boolean indicating whether the user has been deleted or not.
     */
    public boolean deleteUser(long id) {
        boolean userExists = userRepository.existsById(id);
        if (userExists) {
            userRepository.deleteById(id);
        }
        return userExists;
    }

    /**
     * Patches a user.
     *
     * @param id
     *  The id of the user.
     * @param user
     *  The new user object.
     * @return
     *  The modified user object.
     */
    public User patchUser(long id, User user) {
        User currentUser = userRepository.getOne(id);
        currentUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        currentUser.setUsername(user.getUsername());
        userRepository.save(currentUser);
        return currentUser;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
