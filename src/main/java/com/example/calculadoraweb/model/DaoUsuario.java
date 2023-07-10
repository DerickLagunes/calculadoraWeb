package com.example.calculadoraweb.model;

import com.example.calculadoraweb.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario implements DaoRepository{
    @Override
    public List findAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        MysqlConector con = new MysqlConector();
        Connection conexion = con.connect();
        try {
           PreparedStatement stmt = conexion.prepareStatement("select * from usuarios");
           ResultSet res = stmt.executeQuery();
           while(res.next()){
               Usuario usr = new Usuario();
               usr.setId(res.getInt("id"));
               usr.setNombre(res.getString("nombre"));
               usr.setCorreo(res.getString("correo"));
               usr.setContra(res.getString("contra"));
               listaUsuarios.add(usr);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    @Override
    public Object findOne(int id) {
        Usuario usr = new Usuario();
        MysqlConector con = new MysqlConector();
        Connection connection = con.connect();
        try {
            PreparedStatement stmt =
                    connection.prepareStatement("select * from usuarios where id=?");
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                usr.setNombre(res.getString("nombre"));
                usr.setCorreo(res.getString("correo"));
                usr.setContra(res.getString("contra"));
            } else {
                usr.setNombre("No existe el usuario con el id: "+id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }

    public Object findOne(String correo, String contra){
        Usuario usr = new Usuario();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try{
            PreparedStatement stmt =
                    con.prepareStatement("select * from usuarios " +
                            "where correo = ? AND contra = sha2(?,224)");
            stmt.setString(1,correo);
            stmt.setString(2,contra);
            ResultSet res = stmt.executeQuery();
            if(res.next()) {
                usr.setId(res.getInt("id"));
                usr.setNombre(res.getString("nombre"));
                usr.setCorreo(res.getString("correo"));
                usr.setContra(res.getString("contra"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return usr;
    }

    public boolean findOne(String correo){
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try{
            PreparedStatement stmt =
                    con.prepareStatement("select * from usuarios " +
                            "where correo = ?");
            stmt.setString(1,correo);
            ResultSet res = stmt.executeQuery();
            if(res.next()) {
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean update(int id, Usuario usr) {
        boolean res = false;
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt =
                    con.prepareStatement("update usuarios " +
                    "set nombre = ?, correo = ?, " +
                            "contra = sha2(?,224)" +
                    " where id = ?");
            stmt.setString(1,usr.getNombre());
            stmt.setString(2, usr.getCorreo());
            stmt.setString(3, usr.getContra());
            stmt.setInt(4,usr.getId());
            if(stmt.executeUpdate() > 0) res = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public boolean delete(int id) {
        MysqlConector con = new MysqlConector();
        Connection conection = con.connect();
        try {
            PreparedStatement stmt =
                    conection.prepareStatement(
                            "delete from usuarios where id=?");
            stmt.setInt(1,id);
            int resultado = stmt.executeUpdate();
            if(resultado != 0){
                //Si se hizo
                return true;
            }else{ // no se hizo
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(Usuario usr){
        boolean resultado = false;
        MysqlConector con = new MysqlConector();
        Connection conection = con.connect();
        try {
            PreparedStatement stmt =
                    conection.prepareStatement(
                            "insert into usuarios(nombre,correo,contra) " +
                                    "values (?,?,sha2(?,224))");
            stmt.setString(1,usr.getNombre());
            stmt.setString(2,usr.getCorreo());
            stmt.setString(3,usr.getContra());
            int res = stmt.executeUpdate();
            if(res>=1) resultado=true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
