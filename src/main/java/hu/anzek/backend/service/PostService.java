package hu.anzek.backend.service;


import hu.anzek.backend.datalayer.model.Post;
import hu.anzek.backend.datalayer.repository.DBMUVELET;
import hu.anzek.backend.datalayer.repository.PostRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * D4, a negyedik osztálydefiníciónk<br>
 * A tényleges szerveroldali "munkák" üzleti logikája<br>
 * @author User
 */
@Service
public class PostService {
    
    @Autowired
    private PostRepo postRepo;
    
    /**
     * Kiolvassa egy listakollekcióba az összes posztolt rekorodt<br>
     * @return a lista kollekció, vagy üres lista (list.size() == 0)<br>
     */
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    /**
     * Kiolvas egy posztot, ha létezik, ha nem, akkor null értéket ad vissza<br>
     * @param id a primary -key<br>
     * @return a "post" entitás, vagy null érték!<br>
     */
    public Post getPost(Long id) {
        return postRepo.findById(id);
    }

    /** 
     * Egy új "post"-ot hozunk létre<br>
     * @param post az új post entitás<br>
     * @return visszaadja önmagát (a metést k9vetően automatikusan visszaolvassa - már kiegészítve az ID -vel!)<br>
     * @throws hu.anzek.backend.service.OwnException a JQL-MEGRE parancs végrehajtása problémába ütközik<br>
     */
    public Post createPost(Post post) throws OwnException {
        return postRepo.merge(post, DBMUVELET.INSERT);
    }

    /**
     * Kitöröl egy létező posztot<br>
     * @param id a poszt elsődleges kulcsa<br>
     * @throws hu.anzek.backend.service.OwnException a JQL-MEGRE parancs végrehajtása problémába ütközik<br>
     */
    public void deletePost(Long id) throws OwnException {
        Post post = postRepo.findById(id);
        postRepo.merge(post, DBMUVELET.DELETE);
    }     
}
