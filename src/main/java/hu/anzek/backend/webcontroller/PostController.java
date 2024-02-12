/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package hu.anzek.backend.controller;


import hu.anzek.backend.datalayer.model.Post;
import hu.anzek.backend.service.OwnException;
import hu.anzek.backend.service.PostService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * D5, az ötödik deklarált osztályunk<br>
 * @author User<br>
 * a @Controller annotáció: Spring MVC keretrendszer által kezelt webes kéréseket kezel<br>
 * HTML oldalakat vagy más webes nézeteket generál<br>
 * a @RestController -> JSON adatok JPA-entity <-> DTO közötti adatforgalomhoz kellene<br>
 */
@Controller
@RequestMapping("/api/posts")
public class PostController {

    // ún. konstruktor injektálás:
    private final PostService postService;
    
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }        
    
    /**
     * Megjelenít egy adatbeteli formot (ami egy thymeleaf sablon lesz)<br>
     * @param model injektáltunk egy Model interfész implementációt<br>
     * @return a "createPost" a html adatbeviteli sablon neve, amit a SPRING stringként kap meg.<br>
     */
    @GetMapping("/create")
    public String adatbeviteliSablonMegjelenites(Model model) {
        model.addAttribute("post", new Post());
        return "createPost";
    }

    /**
     * A "ModelAttribute("post")" annotáció segítségével a metódus automatikusan kitölti a Post objektumot a beérkező űrlapadatokkal.<br>
     * @param post az URL-en utazó entitás-csomag (a kitöltött "post" példány<br>
     * @return egy "redirect:" ami egy GET http kérést átirányít a "/api/posts/list" útvonalra!<br>
     * @throws OwnException a saját hibakezelőnk, amely az esetleges adatmentéskor beálló hibát lekezeli<br>
     */
    @PostMapping("/create")
    public String ujPostLetrehozas( @ModelAttribute("post") 
                                    Post post              ) throws OwnException {
        postService.createPost(post);
        // továbbítunk egy GET http kérést az alábbi módon (mintha az URL-be gépelte volna valaki):
        return "redirect:/api/posts/list";
    }
    
    /**
     * @param model automtikus paraméter injektálással a Spring MVC hozza létre a model-t<br>
     * @return visszaadja a kliens felé történő renderelendő HTML oldal elnevezeset<br>
     * fontos!<br>
     * Ne használd a "fájlnév+kiterjesztés"-t, csak féjlnév,<br> 
     * de az útvonal megadása kötelező, ha nem a "resurces" alatt van a html fájl<br>
     */
    @GetMapping("/list")
    public String listPosts(Map<String,List<Post>> model) {
        
        // ha nem injektálnénk, példányosítanunk kellene:
        //                      Map<String,List<Post>> model = new HashMap<>();
        List<Post> adatokLista = this.postService.getAllPosts();
                
        model.put("posztok", this.postService.getAllPosts());
        return "postSablon";
    }        
}
