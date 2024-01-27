package com.dlubera.grave.manager.service.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    User createUser(User user) {
        var encryptedPassword = passwordEncoder.encode(user.getPassword());
        var entity = UserEntity.from(user, encryptedPassword);
        return User.from(userRepository.save(entity));
    }

    @Override
    public User getUser(String email) {
        var user = findByEmailOrThrow(email);
        return User.from(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = findByEmailOrThrow(email);
        return User.from(userEntity);
    }

    private UserEntity findByEmailOrThrow(String email) {
        return userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email=%s not found".formatted(email)));
    }

}
