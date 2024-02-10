/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package hu.anzek.backend.datalayer.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * D1. Az első deklarált osztályunk!<br>
 * @author User
 */
@Entity
public class Post {

    // Azonosítók generálásnak ENUM stratégiája:
    //
    //AUTO: a JPA automatikusan választ egy megfelelő azonosítógenerálási stratégiát a használt adatbázis típusa alapján. 
    //      Például, ha egy MySQL adatbázist használsz, akkor IDENTITY stratégiát választ.
    //      Támogatja a H2 és a MySQL is!    
    //IDENTITY: maga az adatbázis generálja az azonosítót. (AUTOINCREMENT) 
    //      Ez a stratégia leginkább azoknál az adatbázisoknál használatos, 
    //      amelyek támogatják az automatikusan növekvő azonosítókat, mint például az MySQL 
    //      Támogatja a H2 és a MySQL is!    
    //SEQUENCE: itt az adatbázis szekvenciáját használja az azonosító generálásához. 
    //      Ezt például Oracle adatbázisoknál alkalmazzák, de a MySQL is kezeli. 
    //      A szekvenciák külön sequence táblák álatl biztosított azonosítók lesznek, kapcsolt táblák által
    //      pl: az egyedi számokat a kapcsolt seq tábla adja: cikk - cikk_seq, vevo - vevo_seq, stb....
    //      Támogatja a H2 és a MySQL is!
    //TABLE: egy tábla segítségével generáljuk az azonosítókat. 
    //      A JPA külön táblát hoz létre az azonosítók tárolására (sequence néven), és innen veszi az egyedi értékeket.
    //      Támogatja a MySQL (én ezt gyakran használom)!    
    //UUID: az azonosítókat véletlenszerűen generálja az UUID (Universally Unique Identifier - szabvány) specifikáció alapján 
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String title;
    private String content; 
    
    // Azért van üres konstruktor, 
    // mert a JPA-nak szüksége van rá a példányok létrehozásához !! 
    // - nem keverendő a spring- injektálással!
    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        
    }    
}
