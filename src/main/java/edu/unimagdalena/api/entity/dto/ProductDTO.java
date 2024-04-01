package edu.unimagdalena.api.entity.dto;

public record ProductDTO(
        Long id,
        String name,
        Float price,
        Integer stock) {

}
