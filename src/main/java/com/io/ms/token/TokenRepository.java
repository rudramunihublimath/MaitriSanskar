package com.io.ms.token;

import com.io.ms.entities.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

  @Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(Long id);

  /*
  @Modifying
  @Query(value = "DELETE FROM token t where t.user_id = :id and t.expired=1 and t.revoked=1 ")
  void deleteUserTokenByID(@Param("id") Long id);  */

  Optional<Token> findByToken(String token);

  Token findByUser(User user);
}
