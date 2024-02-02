package com.dlubera.grave.manager.service.domain.core.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class UserDto {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String email;

    static UserDto from(User user) {
        return UserMapper.INSTANCE.toUserDto(user);
    }
}
