package ie.atu.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Login {
    public static void main(String[] args) {
        SpringApplication.run(Login.class, args);
    }
}
