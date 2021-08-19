package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.domain.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String username;
    private String nickname;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getName();
        this.nickname = user.getNickname();
    }
}
