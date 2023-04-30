package api.lanches.lanchonete.modules.user.useCases;

import api.lanches.lanchonete.modules.user.dtos.AuthenticationDTO;
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

    @PostMapping
    public ResponseEntity signIn(@RequestBody @Valid AuthenticationDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentitacion = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
