package com.adva.project.repository;

import com.adva.project.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Repozytorium do rejestrowania użytkowników DB
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //Głównym identyfikatorem będzie e-mail Opcjonalny zwróci null lub obiekt
    Optional<UserEntity> findByEmail(String email);
}