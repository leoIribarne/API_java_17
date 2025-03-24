package com.iribarne.project.Controller.DTo;

import static java.util.Optional.ofNullable;

import com.iribarne.project.domain.model.User;

public record UserDto(
    Long id,
    String name,
    AccounteDto account,
    CartSectionDto cartSection
) {
    public UserDto(User model) {
        this(
            model.getId(),
            model.getName(),
            ofNullable(model.getAccount()).map(AccounteDto::new).orElse(null),
            ofNullable(model.getCartSection()).map(CartSectionDto::new).orElse(null)
        );
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setAccount(ofNullable(this.account).map(AccounteDto::toModel).orElse(null));
        model.setCartSection(ofNullable(this.cartSection).map(CartSectionDto::toModel).orElse(null));
        return model;
    }
}
