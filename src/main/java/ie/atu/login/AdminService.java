package ie.atu.login;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository ) {
        this.adminRepository = adminRepository;
    }

    public boolean isValidAdmin(String username, String password) {
        AdminCredentials admin = adminRepository.findByUsername(username);
        if (admin != null) {
            System.out.println(admin);
            return BCrypt.checkpw(password, admin.getPassword());
        }
        return false;
    }

    public boolean existsByUsername(String username) {
        return adminRepository.findByUsername(username) != null;
    }

    public void createOrUpdateAdmin(String username, String rawPassword) {
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        AdminCredentials admin = new AdminCredentials(username, hashedPassword);
        adminRepository.save(admin);
    }

}
