package com.example.calculadoraweb.model;

import com.example.calculadoraweb.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoPizza {

    public boolean insert(Pizza p){
        boolean flag = false;

        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into pizzas(tipo, precio, tamano, especialidad) " +
                            "values (?,?,?,?)"
            );
            stmt.setInt(1,p.getTipo());
            stmt.setDouble(2,p.getPrecio());
            stmt.setString(3,p.getTamano());
            stmt.setString(4,p.getEspecialidad());
            stmt.executeUpdate();// registro de pizza

            PreparedStatement select = con.prepareStatement(
                    "select MAX(id) from pizzas"
            );
            ResultSet res = select.executeQuery();
            int id = 0;
            if(res.next()){
                id = res.getInt(1);
            }
            List<Ingrediente> lista = p.getIngredientes();
            for (Ingrediente i: p.getIngredientes()) {
                PreparedStatement stmt2 = con.prepareStatement(
                        "insert into pizzas_ingredientes(id_pizza,id_ingrediente) values " +
                                "(?, ?)"
                );
                stmt2.setInt(1,id);
                stmt2.setInt(2,i.getId());
                stmt2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return flag;
    }

}
