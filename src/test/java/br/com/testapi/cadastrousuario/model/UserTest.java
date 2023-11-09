package br.com.testapi.cadastrousuario.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidUser() {
        User user = User.builder()
                .nome("John Doe")
                .email("john.doe@gmail.com")
                .dataNascimento(LocalDate.of(1990, 1, 15))
                .endereco("123 Main Street")
                .habilidades(Arrays.asList("Java", "Spring Boot"))
                .build();

        assertThat(validator.validate(user)).isEmpty();
    }

    @Test
    public void testInvalidEmail() {
        User user = User.builder()
                .nome("John Doe")
                .email("john.doe@invalid.com")
                .dataNascimento(LocalDate.of(1990, 1, 15))
                .endereco("123 Main Street")
                .habilidades(Arrays.asList("Java", "Spring Boot"))
                .build();

        assertThat(validator.validate(user)).hasSize(1);
    }

    @Test
    public void testInvalidNomeWithSymbol() {
        User user = User.builder()
                .nome("John@Doe")
                .email("john.doe@gmail.com")
                .dataNascimento(LocalDate.of(1990, 1, 15))
                .endereco("123 Main Street")
                .habilidades(Arrays.asList("Java", "Spring Boot"))
                .build();

        assertThat(validator.validate(user)).hasSize(1);
    }

    @Test
    public void testInvalidIdadeMinima() {
        User user = User.builder()
                .nome("John Doe")
                .email("john.doe@gmail.com")
                .dataNascimento(LocalDate.now().minusYears(17))
                .endereco("123 Main Street")
                .habilidades(Arrays.asList("Java", "Spring Boot"))
                .build();

        assertThat(validator.validate(user)).hasSize(1);
    }

    @Test
    public void testInvalidHabilidadesEmpty() {
        User user = User.builder()
                .nome("John Doe")
                .email("john.doe@gmail.com")
                .dataNascimento(LocalDate.of(1990, 1, 15))
                .endereco("123 Main Street")
                .habilidades(Arrays.asList())
                .build();

        assertThat(validator.validate(user)).hasSize(1);
    }

    @Test
    public void testInvalidEnderecoWithSpecialCharacters() {
        User user = User.builder()
                .nome("John Doe")
                .email("john.doe@gmail.com")
                .dataNascimento(LocalDate.of(1990, 1, 15))
                .endereco("123 Main Street #$%")
                .habilidades(Arrays.asList("Java", "Spring Boot"))
                .build();

        assertThat(validator.validate(user)).hasSize(1);
    }
}
