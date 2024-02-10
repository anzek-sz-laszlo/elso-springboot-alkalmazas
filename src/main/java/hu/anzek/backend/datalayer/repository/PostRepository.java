package hu.anzek.backend.datalayer.repository;


import hu.anzek.backend.datalayer.model.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * D2. második deklaráció<br>
 * @author User
 */
@Repository
@Service
public interface PostRepository extends JpaRepository<Post, Long> { 
      
    // ezek generikus absztrakt metódusok:    
    // FONTOS!
    //
    // ha semmi más nem indokolja, akkor teljesen elhagyható az implementációjuk!
    //
    // mert a JPA "megírja" helyettünk - úgy, mintha "default metódusok" lennének...
    // de felülírhatóak, 
    // - ez esetben a felülértékelési annotációt viszont rájuk kell tenni:    
    
    @Override
    public List<Post> findAll();

    @Override
    Optional<Post> findById(Long id);
    
    @Override
            // egy kis generikus magyarázat:
            // <S extends Post> S ez EGYETLEN kifgejezés! 
            // Ez egy meta-leírása annak, hogy az "S" - mint egy be/kimeneti paraméter milyen referencia típust vehet fel.
            // vagyis:
            // S csakis "Post", vagy a "Post" -osztályból származtatott valamilyen gyermekosztály lehet, 
            //                  viszont azok mindegyikét elfogadja!
            //      amúgy ebből kifolyólag vlójában a:
            // public Post save(Post post);
            //      is helyes megoldás, csak egy figyelmeztetéssel jár együtt: azzal, hogy itt egy "felsőhatáros" referencia-definíció van!
    <S extends Post> S save(S post);
    
    // adatmanupulatív műveletnél
    // a Transactional
    // és a Modifying annotációk is kellenek.
    @Transactional
    @Modifying
    @Override
    void deleteById(Long id);
    
    @Transactional
    @Modifying
    // Fontos:
    // amikor nem JPA "kompatibilis" parancsot adunk ki, hanem nyev specifkusat (pl mysql) akkor 
    // akkor szükséges a natív lekérdezés opciót igazra állítani!
    @Query(value = "DELETE FROM post WHERE id > 0", nativeQuery = true)
    
    // Pldák natív SQL/JPQL lekérdezésekre: 
    //SELECT minden rekord:
    //SQL: SELECT * FROM user;
    //JPQL: SELECT u FROM User u;
    //
    //Feltételes lekérdezés:
    //SQL: SELECT * FROM user WHERE age > 18;
    //JPQL: SELECT u FROM User u WHERE u.age > 18;
    //
    //Több tábla/entitás JOIN:
    //SQL: SELECT * FROM user JOIN orders ON user.id = orders.user_id;
    //JPQL: SELECT u FROM User u JOIN u.orders o;    
    
    @Override
    void deleteAll();
}
