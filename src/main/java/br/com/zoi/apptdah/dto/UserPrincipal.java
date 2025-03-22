package br.com.zoi.apptdah.dto;

import java.util.UUID;

public record UserPrincipal(UUID userId, String email, String role) {}