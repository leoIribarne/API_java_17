package com.iribarne.project.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iribarne.project.Controller.DTo.CartSectionDto;
import com.iribarne.project.domain.model.CartSection;
import com.iribarne.project.domain.repository.CartSectionRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cart-sections")
@Tag(name = "Carrinho", description = "API para gerenciamento do carrinho de compras")
public class CartSectionController {

    @Autowired
    private CartSectionRepository repository;

    @GetMapping
    @Operation(summary = "Listar todos os carrinhos")
    public ResponseEntity<List<CartSectionDto>> findAll() {
        List<CartSectionDto> dtos = repository.findAll().stream()
                .map(CartSectionDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar carrinho por ID")
    public ResponseEntity<CartSectionDto> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(CartSectionDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo carrinho")
    public ResponseEntity<CartSectionDto> create(@RequestBody CartSectionDto dto) {
        CartSection model = dto.toModel();
        model = repository.save(model);
        return ResponseEntity.ok(new CartSectionDto(model));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar carrinho")
    public ResponseEntity<CartSectionDto> update(@PathVariable Long id, @RequestBody CartSectionDto dto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        CartSection model = dto.toModel();
        model.setId(id);
        model = repository.save(model);
        return ResponseEntity.ok(new CartSectionDto(model));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar carrinho")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 