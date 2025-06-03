package backend.artee_group.dto.RegisterDTO;

import backend.artee_group.dto.UserDTO.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private UserResponse user;
}
