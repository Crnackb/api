package edu.unimagdalena.api.entities.dto;

public record CustomerDTO(
        Long id,
        String name,
        String email,
        String address) {
}
