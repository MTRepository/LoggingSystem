package pl.michal.logger.controllers.services;

// do podtrzymywania sesji

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.michal.logger.controllers.repositories.UserRepository;
import pl.michal.logger.models.UserModel;

// scope - ustawianie zasiegu ziarna
// session tworzy ziarno dla sesji http

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    public enum RegisterStatus {
        OK, BUSY_LOGIN, ERROR;
    }

    // wstrzykniecie zaleznosci dla repozytorium - w konstruktorze
    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // tutaj trzyma sobie status
    @Getter @Setter
    private boolean isLogged;

    @Getter @Setter
    private int falseLoginCounter;

    // tutaj ziarno trzyma uzytkownika
    @Getter @Setter
    private UserModel user;

}
