package com.iribarne.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.iribarne.project.domain.model.User;
import com.iribarne.project.domain.model.Account;
import com.iribarne.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Verificar se já existem usuários
            if (userService.findAll().isEmpty()) {
                // Criar usuário 1
                User user1 = new User();
                user1.setName("João Silva");
                Account account1 = new Account();
                account1.setAccountCode("123456");
                account1.setAccountBalance("1000.00");
                user1.setAccount(account1);
                userService.save(user1);

                // Criar usuário 2
                User user2 = new User();
                user2.setName("Maria Santos");
                Account account2 = new Account();
                account2.setAccountCode("654321");
                account2.setAccountBalance("2500.00");
                user2.setAccount(account2);
                userService.save(user2);

                // Criar usuário 3
                User user3 = new User();
                user3.setName("Pedro Oliveira");
                Account account3 = new Account();
                account3.setAccountCode("987654");
                account3.setAccountBalance("500.00");
                user3.setAccount(account3);
                userService.save(user3);

                logger.info("Dados de teste criados com sucesso!");
            } else {
                logger.info("Dados de teste já existem no banco de dados.");
            }
        } catch (Exception e) {
            logger.error("Erro ao criar dados de teste: " + e.getMessage());
            throw e;
        }
    }
} 