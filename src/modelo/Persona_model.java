package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Persona_model {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        List<Persona> datos = new ArrayList<>();
        String sql = "select * from tbpersona where estper='A'";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelf(rs.getString(4));
                datos.add(p);
            }
            
        } catch (Exception e) {
            System.out.println("Error en modelo listar " + e);
        }
        return datos;
    }

    public int agregar(Persona p) {
        String sql = "insert into tbpersona (nomper,emlper,telper) values(?,?,?)";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTelf());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }

    public int actualizar(Persona p) {
        int r = 0;
        String sql = "update tbpersona set nomper=?, emlper=?, telper=? where codper=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTelf());
            ps.setInt(4, p.getId());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }

        } catch (Exception e) {
        }
        return r;
    }
public void eleminar(int id){
    String sql="update tbpersona set estper='I' where codper="+id;
    try{
    con=conexion.getConnection();
    ps=con.prepareStatement(sql);
    ps.executeUpdate();    
    }catch(Exception e){
    
    }
}

}
