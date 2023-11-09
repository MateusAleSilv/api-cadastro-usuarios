package br.com.testapi.cadastrousuario.model;

import br.com.testapi.cadastrousuario.utils.AgeConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo 'nome' é obrigatório.")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "O campo 'nome' não deve conter símbolos.")
    private String nome;

    @NotBlank(message = "O campo 'email' é obrigatório.")
    @Email(message = "O campo 'email' deve ser um endereço de e-mail válido.")
    @Pattern(regexp = "^(.+)@(gmail|hotmail|outlook|yahoo)\\.(com|com\\.br)$", message = "O campo 'email' deve ser de um provedor suportado.")
    private String email;

    @Past(message = "O campo 'dataNascimento' deve ser uma data passada.")
    @NotNull(message = "O campo 'dataNascimento' é obrigatório.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @AgeConstraint(value = 18, message = "O usuário deve ter pelo menos 18 anos de idade.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo 'endereco' é obrigatório.")
    @Pattern(regexp = "^[\\w\\s]+$", message = "O campo 'endereco' não deve conter caracteres especiais.")
    private String endereco;

    @NotNull(message = "O campo 'habilidades' é obrigatório.")
    @Size(min = 1, message = "Pelo menos uma habilidade deve ser especificada.")
    @ElementCollection
    private List<String> habilidades;
}