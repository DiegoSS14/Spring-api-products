package com.dev.diego.backend.products.dto;

import java.math.BigDecimal;

import com.dev.diego.backend.products.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank(message = "Field name is required")
    @Size(min = 3, max = 255, message = "The name must have between 3 and 255 characters")
    private String name;

    @NotBlank(message = "Field description is required")
    private String description;
    
    @NotNull(message = "Field price is required")
    @Positive(message = "The price should be positive")
    private BigDecimal price;
    
    @NotNull(message = "Field category is required")
    private Category category;
}
