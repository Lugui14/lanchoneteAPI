package api.lanches.lanchonete.modules.user.useCases;

import api.lanches.lanchonete.infra.security.TokenService;
import api.lanches.lanchonete.modules.user.dtos.JWTtokenDTO;
import api.lanches.lanchonete.modules.user.dtos.AuthenticationDTO;
import api.lanches.lanchonete.modules.user.infra.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTtokenDTO> signIn(@RequestBody @Valid AuthenticationDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authToken);

        var JWTtoken = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTtokenDTO(JWTtoken));
    }

}
