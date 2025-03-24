package com.iribarne.project.Controller.DTo;

import com.iribarne.project.domain.model.CartSection;

public record CartSectionDto(
    Long id,
    int productCount,
    double totalValue
) {
    public CartSectionDto(CartSection model) {
        this(
            model.getId(),
            model.getProductCount(),
            model.getTotalValue()
        );
    }

    public CartSection toModel() {
        CartSection model = new CartSection();
        model.setId(this.id);
        model.setProductCount(this.productCount);
        model.setTotalValue(this.totalValue);
        return model;
    }
}
