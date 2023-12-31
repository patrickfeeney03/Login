package ie.atu.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountCreationData {
    AdminCredentials newDetails;
    AdminCredentials adminLogin;
}
