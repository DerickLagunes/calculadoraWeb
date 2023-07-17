package com.example.calculadoraweb.model;

import com.example.calculadoraweb.utils.MysqlConector;
import com.sun.jdi.connect.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoIngredientes {

    public List findAll(){

        Connection con = new MysqlConector().connect();
        List<Ingrediente> lista = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "select * from ingredientes"
            );
            ResultSet res = stmt.executeQuery();

            while(res.next()) {
                Ingrediente i = new Ingrediente();
                i.setId(res.getInt("id"));
                i.setNombre(res.getString("nombre"));
                lista.add(i);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;

    }

    public boolean insert(Ingrediente i) {
        boolean flag = false;
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into ingredientes(nombre) values(?)"
            );
            stmt.setString(1,i.getNombre());
            flag = stmt.executeUpdate() == 0 ? false:true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
}
