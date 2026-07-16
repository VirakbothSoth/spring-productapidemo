package co.istad.productapidemo.service.impl;

import ch.qos.logback.classic.Logger;
import co.istad.productapidemo.dto.auth.RegisterRequest;
import co.istad.productapidemo.dto.auth.RegisterResponse;
import co.istad.productapidemo.entity.User;
import co.istad.productapidemo.mapper.UserMapper;
import co.istad.productapidemo.repository.UserRepository;
import co.istad.productapidemo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final Keycloak keycloak;
    private final UserMapper userMapper;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.client-id}")
    private String clientId;

    private ClientRepresentation getClientById(String clientId) {
        return keycloak.realm(realm).clients()
                .findByClientId(clientId).stream().findFirst().orElseThrow(
                        ()->new NoSuchElementException("No client with id " + clientId)
                );
    }

    private RegisterResponse createUserInKeycloak(String realm, RegisterRequest req) {
        var userRep = new UserRepresentation();
        userRep.setUsername(req.username());
        userRep.setEmail(req.email());
        userRep.setFirstName(req.firstName());
        userRep.setLastName(req.lastName());

        userRep.setEnabled(true);
        userRep.setEmailVerified(true);

        Map<String, List<String>> attri = new HashMap<>();
        attri.put("email", List.of(req.email()));
        attri.put("biography", List.of(req.biography()));
        userRep.setAttributes(attri);

        var cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setType(CredentialRepresentation.PASSWORD);
        userRep.setCredentials(List.of(cred));

        var resRes = keycloak.realm(realm).users();
        try (var res = resRes.create(userRep)) {
            if (res.getStatus() == 201) {
                String userId = CreatedResponseUtil.getCreatedId(res);
                UserResource userResource = keycloak.realm(realm).users().get(userId);
                var client = getClientById(clientId);
                var roleRepresentation = keycloak.realm(realm).clients()
                        .get(client.getId()).roles().get("CUSTOMER").toRepresentation();

                userResource.roles().clientLevel(client.getId()).add(List.of(roleRepresentation));
                return userMapper.toRegisterResponse(userRep);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Error creating user in keycloak", ex);
        }

        return null;
    }

    @Override
    public RegisterResponse register(RegisterRequest req) {
        if (!req.password().equals(req.confirmedPassword())) {
            throw new RuntimeException("Passwords don't match");
        }
        var kcRes = createUserInKeycloak(req);
        User user = new User();
        user.setKeycloakId(kcRes.id());
        user.setEmail(kcRes.email());
        user.setUsername(kcRes.username());
        userRepository.save(user);
        return kcRes;
    }
}
