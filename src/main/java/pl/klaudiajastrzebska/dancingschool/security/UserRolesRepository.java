package pl.klaudiajastrzebska.dancingschool.security;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserRolesEntity;

import java.util.Optional;

interface UserRolesRepository extends JpaRepository<UserRolesEntity, Long> {
    Optional<UserRolesEntity> findByName(String name);
}
