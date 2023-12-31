package ie.atu.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminCredentials, Long> {

    AdminCredentials findByUsername(String username);
}
