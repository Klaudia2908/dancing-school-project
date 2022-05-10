package pl.klaudiajastrzebska.dancingschool.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;

import java.util.List;
import java.util.Optional;

interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLoginAndRemovalDateNull(String login);

    @Query(value = "SELECT * FROM USERS US " +
            "JOIN S_UPRAWNIENIA S_UPR ON US.ID_S_UPRAWNIENIA = S_UPR.ID " +
            "WHERE S_UPR.NAZWA = :name " +
            "AND US.DATA_USUNIECIA IS NULL", nativeQuery = true)
    List<UserEntity> findAllActiveUsers(String name);
}
