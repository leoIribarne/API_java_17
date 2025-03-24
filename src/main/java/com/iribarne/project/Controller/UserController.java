package com.iribarne.project.Controller;

import org.springframework.web.bind.annotation.*;
import com.iribarne.project.Controller.DTo.UserDto;
import com.iribarne.project.domain.model.User;
import com.iribarne.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import com.iribarne.project.service.exception.NotFoundException;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userDto.toModel();
        User savedUser = userService.save(user);
        return new UserDto(savedUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public UserDto getUser(@PathVariable Long id) {
        return userService.findById(id)
            .map(UserDto::new)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado com ID: " + id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<UserDto> getAllUsers() {
        return userService.findAll().stream()
            .map(UserDto::new)
            .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userDto.toModel();
        User updatedUser = userService.update(id, user);
        return new UserDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
