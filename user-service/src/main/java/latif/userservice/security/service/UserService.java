package latif.userservice.security.service;

import latif.userservice.model.User;
import latif.userservice.security.dto.AuthenticatedUserDto;
import latif.userservice.security.dto.RegistrationRequest;
import latif.userservice.security.dto.RegistrationResponse;

public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
