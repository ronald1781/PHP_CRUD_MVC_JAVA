
package modelo;
public class Persona {
    int id;
    String nom;
    String correo;
    String telf;

    public Persona() {
    }

    public Persona(int id, String nom, String correo, String telf) {
        this.id = id;
        this.nom = nom;
        this.correo = correo;
        this.telf = telf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }
    
}
