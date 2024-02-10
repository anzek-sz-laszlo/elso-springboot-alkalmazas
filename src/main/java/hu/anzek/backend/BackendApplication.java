package hu.anzek.backend;

import hu.anzek.backend.datalayer.model.Post;
import hu.anzek.backend.service.OwnException;
import hu.anzek.backend.service.PostService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "hu.anzek.backend")
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private PostService postService;
     
    /**
     * Main eljárás<br>
     * @param args argumentum (halenne, de nincs...)
     */
    public static void main(String[] args) {
            SpringApplication.run(BackendApplication.class, args);
    }
    
    @Override
    public void run(String ... args) throws Exception, OwnException{
        
        // előkészítések:
        // this.postService = new PostService();        
        List<Post> posztList = new ArrayList<>();
        Post newPost = new Post();
        
        // Új adatok bevitele:
        newPost.setTitle("az első:");
        newPost.setContent("ez a minta-program bemutat egy back-end alkalmazásmintát");
        posztList.add(newPost);
       
        newPost = new Post();
        newPost.setTitle("a második:"); 
        newPost.setContent("egyben bemutatja a spring keretrendszert");
        posztList.add(newPost);
        
        newPost = new Post();
        newPost.setTitle("a harmadik:"); 
        newPost.setContent("bemutatja továbbá a közvelen-, ill, a konstruktor-paraméter injektálást");
        posztList.add(newPost);

        // adatok kiíratása az adatbázisba:
        try{
            posztList.forEach(e -> {
                try {
                    this.postService.createPost(e);
                } catch (OwnException ex) {
                    Logger.getLogger(BackendApplication.class.getName()).log(Level.SEVERE, "Hiba az adatbázis feltöltéskor", ex);
                }
            });
        }finally{}
        /// adatok visszaolvasása az adatbzisból:
        posztList = new ArrayList<>();
        posztList = postService.getAllPosts();
        // adatok kiíratása konzolra:
        posztList.forEach(System.out::println);
                
        System.out.println("Hello Világ működik! ... várom, hogy valamelyik URL-re 'hivatkozás' vagy 'adat' érkezzen!");        
    }
}
