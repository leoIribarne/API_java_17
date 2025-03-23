package com.iribarne.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import com.iribarne.project.domain.model.User;
import com.iribarne.project.domain.repository.Userrepository;
import com.iribarne.project.service.UserService;
import com.iribarne.project.service.exception.NotFoundException;
import com.iribarne.project.service.exception.BusinessException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Userrepository userRepository;

    @Override
    public User save(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new BusinessException("Nome do usuário não pode ser vazio");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Usuário não encontrado com ID: " + id);
        }
        
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado com ID: " + id));
        
        existingUser.setName(user.getName());
        existingUser.setAccount(user.getAccount());
        
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Usuário não encontrado com ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
} 