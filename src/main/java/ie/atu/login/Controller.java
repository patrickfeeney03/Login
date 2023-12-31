package ie.atu.login;

import feign.Response;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final AdminService adminService;

    public Controller(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenthicateAdmin(@RequestBody AdminCredentials adminCredentials) {
        if (adminService.isValidAdmin(adminCredentials.getUsername(), adminCredentials.getPassword())) {
            return ResponseEntity.ok("Admin authenticated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin credentials");
        }
    }

    @PostMapping("/createaccount")
    public ResponseEntity<String> createAdmin(@RequestBody AccountCreationData accountCreationData) {
        AdminCredentials newAccount = accountCreationData.getNewDetails();
        AdminCredentials loginData = accountCreationData.getAdminLogin();
        if (loginData == null || newAccount == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }
        if (newAccount.getUsername() == null || newAccount.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New Details Invalid");
        }
        if (loginData.getPassword() == null || loginData.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login Data Invalid");
        }
        if (!adminService.isValidAdmin(loginData.getUsername(), loginData.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin credentials");
        }
        if (adminService.existsByUsername(newAccount.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("That username already exists");
        }

        adminService.createOrUpdateAdmin(newAccount.getUsername(), newAccount.getPassword());
        return ResponseEntity.ok("Created");
    }
}
