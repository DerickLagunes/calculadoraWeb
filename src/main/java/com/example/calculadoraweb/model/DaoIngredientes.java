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
}
