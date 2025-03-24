package com.iribarne.project.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.iribarne.project.domain.model.Account;
import com.iribarne.project.domain.model.User;
import com.iribarne.project.service.UserService;

@Configuration
@Profile("dev") // Só executa no perfil "dev"
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se deve criar dados de teste
        if (shouldInitializeData()) {
            try {
                createTestData();
                logger.info("Dados de teste criados com sucesso!");
            } catch (Exception e) {
                logger.error("Erro ao criar dados de teste: " + e.getMessage());
            }
        } else {
            logger.info("Inicialização de dados de teste desativada.");
        }
    }

    private boolean shouldInitializeData() {
        // Verifica se existem usuários
        return userService.findAll().isEmpty();
    }

    private void createTestData() {
        // Criar usuário 1 - Cliente Regular
        User user1 = new User();
        user1.setName("João Silva");
        Account account1 = new Account();
        account1.setAccountCode("123456");
        account1.setAccountBalance("1000.00");
        user1.setAccount(account1);
        userService.save(user1);

        // Criar usuário 2 - Cliente Premium
        User user2 = new User();
        user2.setName("Maria Santos");
        Account account2 = new Account();
        account2.setAccountCode("654321");
        account2.setAccountBalance("2500.00");
        user2.setAccount(account2);
        userService.save(user2);

        // Criar usuário 3 - Cliente Básico
        User user3 = new User();
        user3.setName("Pedro Oliveira");
        Account account3 = new Account();
        account3.setAccountCode("987654");
        account3.setAccountBalance("500.00");
        user3.setAccount(account3);
        userService.save(user3);
    }
} 