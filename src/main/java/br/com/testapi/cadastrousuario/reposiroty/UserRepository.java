package br.com.testapi.cadastrousuario.reposiroty;

import br.com.testapi.cadastrousuario.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

