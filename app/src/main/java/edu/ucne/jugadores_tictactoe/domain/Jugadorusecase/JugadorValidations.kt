package edu.ucne.jugadores_tictactoe.domain.usecase

data class ValidationResult(
    val isValid: Boolean,
    val nombresError: String? = null,
    val partidasError: String? = null
)

fun validateJugadorUi(nombre: String, partidas: String): ValidationResult {
    if (nombre.isBlank()) return ValidationResult(false, nombresError = "El nombre es obligatorio")
    if (partidas.isBlank()) return ValidationResult(false, partidasError = "Las partidas son obligatorias")
    val partidasNum = partidas.toIntOrNull()
    if (partidasNum == null || partidasNum <= 0) return ValidationResult(false, partidasError = "Debe ser mayor que 0")
    return ValidationResult(true)
}