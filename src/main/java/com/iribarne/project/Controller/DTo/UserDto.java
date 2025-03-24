package com.iribarne.project.Controller.DTo;

import com.iribarne.project.domain.model.User;
import java.util.List;
import static java.util.Optional.ofNullable;

public record UserDto(
    Long id,
    String name,
    AccounteDto account
) {
    public UserDto(User model) {
        this(
            model.getId(),
            model.getName(),
            ofNullable(model.getAccount()).map(AccounteDto::new).orElse(null)
        );
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setAccount(ofNullable(this.account).map(AccounteDto::toModel).orElse(null));
        return model;
    }
}
