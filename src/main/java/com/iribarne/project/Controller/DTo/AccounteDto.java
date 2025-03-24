package com.iribarne.project.Controller.DTo;

import com.iribarne.project.domain.model.Account;
import static java.util.Optional.ofNullable;

public record AccounteDto(
    Long id,
    String accountCode,
    String accountBalance
) {
    public AccounteDto(Account model) {
        this(
            model.getId(),
            model.getAccountCode(),
            model.getAccountBalance()
        );
    }

    public Account toModel() {
        Account model = new Account();
        model.setId(this.id);
        model.setAccountCode(this.accountCode);
        model.setAccountBalance(this.accountBalance);
        return model;
    }
}
