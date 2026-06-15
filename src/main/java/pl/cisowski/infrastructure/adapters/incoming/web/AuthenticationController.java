package pl.cisowski.infrastructure.adapters.incoming.web;

import pl.cisowski.domain.model.User;
import pl.cisowski.domain.ports.outgoing.UserAuthPort;
import pl.cisowski.infrastructure.adapters.incoming.models.LoginRequest;
import pl.cisowski.infrastructure.adapters.incoming.models.LoginResponse;
import pl.cisowski.infrastructure.adapters.incoming.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final UserAuthPort userAuthPort;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginUserDto){
        User authenticatedUser = userAuthPort.authenticate(loginUserDto.getEmail(), loginUserDto.getPassword());
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getJwtExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
