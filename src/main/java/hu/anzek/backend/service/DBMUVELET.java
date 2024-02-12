package hu.anzek.backend.service;

/**
 * Az ENUM (enumerált == felsorolási állandó)
 * @author User
 */
public enum DBMUVELET {

    DELETE(-1),UPDATE(0),INSERT(1);
    
    private final int level;

    private DBMUVELET(int level) {
        this.level = level;
    }    
    
    /**
     * Ez az egyetlen olyan dolog, amely nincs alapértelmezetten deklarálva, DE!<br>
     * kizárólag NEM STATIKUS környezetben!<br>
     * @return A "beérkező" felsorolási állandó integer értéke<br>
     */
    public int getLevel() {
        return this.level;
    }
  
    // Ezek itt alább ugyan megírhatóak ilyen formában, 
    // de lényegében alapértelmezett funkciók:
    //
    //    public String getName(){
    //        return this.name();
    //    }
    //    
    //    public DBMUVELET getDELETE() {
    //        return this.DELETE;
    //    }
    //    // lehetne statikus is, pl így:
    //    // akkor nem használhatod a "this" kulcsszót, hiszen nem példányosított!
    //    // igazából egy ENUM-ot sohasem példányosítunk a hagyományos értelemben.
    //    public static DBMUVELET getStaticDELETE() {
    //        return DELETE;
    //    }
    //    
    //    public DBMUVELET getUPDATE() {
    //        return this.UPDATE;
    //    }
    //
    //    public DBMUVELET getINSERT() {
    //        return this.INSERT;
    //    }
}
