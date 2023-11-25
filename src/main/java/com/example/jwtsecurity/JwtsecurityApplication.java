package com.example.jwtsecurity;

import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.enums.Role;
import com.example.jwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtsecurityApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JwtsecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User adminAccount=userRepository.findByRole(Role.ADMIN);
        if (adminAccount==null){
            User user=new User();
            user.setEmail("javidarkishiev@gmail.com");
            user.setFirstName("Javid");
            user.setSecondName("Arkishiev");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin1234"));
            userRepository.save(user);
        }


    }
}
