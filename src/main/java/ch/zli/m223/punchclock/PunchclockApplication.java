package ch.zli.m223.punchclock;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.EntryRepository;
import ch.zli.m223.punchclock.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootApplication
public class PunchclockApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(PunchclockApplication.class, args);
	}

	/**
	 * Create some sample data.
	 *
	 * @param entryRepository
	 * 	The entry repository.
	 * @param userRepository
	 * 	The user repository.
	 * @return
	 * 	ApplicationRunner.
	 */
	@Bean
	public ApplicationRunner sampleData(EntryRepository entryRepository, UserRepository userRepository) {
		return args ->  {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

			// Create sample user.
			User user = new User();
			user.setPassword(bCryptPasswordEncoder.encode("toor"));
			user.setUsername("root");
			userRepository.save(user);

			// Create sample entry.
			Entry entry = new Entry();
			LocalDateTime checkIn = LocalDateTime.of(2019, Month.NOVEMBER, 29, 9, 30, 40);
			LocalDateTime checkOut = LocalDateTime.of(2019, Month.NOVEMBER, 29, 19, 30, 40);
			entry.setCheckIn(checkIn);
			entry.setCheckOut(checkOut);
			entry.setUserId(1);
			entryRepository.save(entry);
		};
	}
}
