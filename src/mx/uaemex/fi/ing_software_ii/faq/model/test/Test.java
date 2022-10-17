package mx.uaemex.fi.ing_software_ii.faq.model.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import mx.uaemex.fi.ing_software_ii.faq.model.*;

public class Test {
    public static void main(String[] args) {
        insertar();
    }

    public static void insertar() {
        Connection con;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/faq";
        TemasDAO dao;
        TemasDAODerbyImp realDao;
        Tema t;
        try {
            con = DriverManager.getConnection(url);
            realDao = new TemasDAODerbyImp();
            t = new Tema();
            t.setId(3);
            t.setNombre("Matematicas");
            //t.setPadre(1);
            //t.setSubTemas(null);
            realDao.setCon(con);
            dao = realDao;
            t = dao.Crear(t);
            // System.out.println(t.getId() + " -> " + t.getNombre());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void actualizar() {
        Connection con;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/faq";
        TemasDAO dao;
        TemasDAODerbyImp realDao;
        Tema t;
        try {
            con = DriverManager.getConnection(url);
            realDao = new TemasDAODerbyImp();
            t = new Tema();
            t.setId(4);
            t.setNombre("Programacion");
            t.setPadre(1);
            realDao.setCon(con);
            dao = realDao;
            dao.actualizar(t);
            // System.out.println(t.getId() + " -> " + t.getNombre());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void eliminar() {
        Connection con;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/faq";
        TemasDAO dao;
        TemasDAODerbyImp realDao;
        Tema t;  
        try {
            con = DriverManager.getConnection(url);
            realDao = new TemasDAODerbyImp();
            t = new Tema();
            t.setId(3);
            realDao.setCon(con);
            dao = realDao;
            dao.eliminar(t);
            // System.out.println(t.getId() + " -> " + t.getNombre());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void consultar() {
        Connection con;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/faq";
        TemasDAO dao;
        TemasDAODerbyImp realDao;
        Tema t;
        try {
            con = DriverManager.getConnection(url);
            realDao = new TemasDAODerbyImp();
            t = new Tema();
            // t.setId(1);
            t.setNombre("Servlet");
            realDao.setCon(con);
            dao = realDao;
            t = dao.consultar(t);
            System.out.println(t.getId() + " -> " + t.getNombre());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public static void consultarSubtemas() {
        Connection con;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/faq";
        TemasDAO dao;
        TemasDAODerbyImp realDao;
        Tema t ;
        List<Tema> tlista = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url);
            realDao = new TemasDAODerbyImp();
            t = new Tema();
            t.setPadre(1);
            t.setNombre("Servlet");
            realDao.setCon(con);
            dao = realDao;
            tlista = dao.consultarSubTemas(t);
            //System.out.println(t.getId() + " -> " + t.getNombre());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public static void contarSubtemas() {
        Connection con;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/faq";
        TemasDAO dao;
        TemasDAODerbyImp realDao;
        Tema t;
        try {
            con = DriverManager.getConnection(url);
            realDao = new TemasDAODerbyImp();
            t = new Tema();
            t.setPadre(1);
            realDao.setCon(con);
            dao = realDao;
            int tam = dao.getNumeroSubTemas(t);
            System.out.println("Subtemas contados "+tam);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
