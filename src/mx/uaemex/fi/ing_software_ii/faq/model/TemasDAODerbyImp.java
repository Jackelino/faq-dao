package mx.uaemex.fi.ing_software_ii.faq.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.uaemex.fi.ing_software_ii.faq.model.error.PersistenciaException;
import mx.uaemex.fi.ing_software_ii.faq.model.error.TemaNoEncontrado;
import mx.uaemex.fi.ing_software_ii.faq.model.error.TemaRepetidoException;

public class TemasDAODerbyImp implements TemasDAO {

	private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public Tema consultar(Tema t) {
		Statement stmt;
		String query = "", n;
		ResultSet rs;
		Tema tResul = null;
		int talla;
		try {
			stmt = this.con.createStatement();
			if (t.getId() > 0) {
				query = "select * from temas where activo = 'true' and id=" + t.getId();
			} else {
				if (t.getNombre() != null) {
					query = "select * from temas where activo = 'true' and tema='" + t.getNombre() + "'";
				}
			}
			rs = stmt.executeQuery(query);
			talla = rs.getFetchSize();
			// System.out.println("Talla: "+talla);

			while (rs.next()) {
				tResul = new Tema();
				tResul.setId(rs.getInt("id"));
				n = rs.getString("tema");
				tResul.setNombre(n);
				talla++;
				// Si encuentra el tema regresa un tResul
				// return tResul;
			}
			// System.out.println("Talla: "+talla);
			if (talla == 0) {
				throw new TemaNoEncontrado("No existe un tema con id " + t.getId() + "y/o " + t.getNombre());
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		}
		return tResul;
	}

	@Override
	public Tema Crear(Tema t) {
		Statement stmt;
		PreparedStatement pst;
		String query = "", n;
		ResultSet rs;
		Tema tResul = null;
		int talla;
		try {
			stmt = this.con.createStatement();
			query = "select * from temas where activo = 'true' and tema='" + t.getNombre() + "'";
			rs = stmt.executeQuery(query);
			talla = rs.getFetchSize();

			while (rs.next()) {
				tResul = new Tema();
				tResul.setId(rs.getInt("id"));
				n = rs.getString("tema");
				tResul.setNombre(n);
				talla++;
			}
			if (talla > 0) {
				throw new TemaRepetidoException();
			} else {
				String sql = "";
				if (t.getPadre() > 0) {
					sql = "INSERT INTO TEMAS(id, tema, padre) VALUES(?, ?, ?)";
				} else {
					sql = "INSERT INTO TEMAS(id, tema) VALUES(?, ?)";
				}
				try {
					pst = this.con.prepareStatement(sql);
					pst.setInt(1, t.getId());
					pst.setString(2, t.getNombre());
					// pst.setInt(3, t.getPadre());
					pst.executeUpdate();
				} catch (Exception e) {
					throw new PersistenciaException(e);
				}
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		}

		return t;
	}

	@Override
	public void eliminar(Tema t) {
		PreparedStatement pst;
		String sql = "";
		sql = "UPDATE TEMAS SET ACTIVO=false WHERE id=?";
		try {
			pst = this.con.prepareStatement(sql);
			pst.setInt(1, t.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			throw new TemaNoEncontrado(e);
		}
	}

	@Override
	public void actualizar(Tema t) {
		PreparedStatement pst;
		String sql = "";
		if (t.getPadre() > 0) {
			sql = "UPDATE TEMAS SET tema=?, padre=? WHERE id=?";
			try {
				pst = this.con.prepareStatement(sql);
				pst.setString(1, t.getNombre());
				pst.setInt(2, t.getPadre());
				pst.setInt(3, t.getId());
				pst.executeUpdate();
			} catch (Exception e) {
				throw new TemaNoEncontrado(e);
			}
		} else {
			sql = "UPDATE TEMAS SET tema=? WHERE id=?";
			try {
				pst = this.con.prepareStatement(sql);
				pst.setString(1, t.getNombre());
				pst.setInt(2, t.getId());
				pst.executeUpdate();
			} catch (Exception e) {
				throw new TemaNoEncontrado(e);
			}
		}

	}

	@Override
	public List<Tema> consultarSubTemas(Tema t) {
		Statement stmt = null;
		String query = "", name;
		ResultSet rs = null;
		Tema tema;
		List<Tema> tResul = new ArrayList<>();
		int talla;
		try {
			stmt = this.con.createStatement();
			if (t == null) {
				query = "select * from temas where padre is null";
			} else {
				query = "select * from temas where padre=" + t.getPadre();

			}
			stmt = this.con.createStatement();
			rs = stmt.executeQuery(query);
			talla = rs.getFetchSize();

			while (rs.next()) {
				tema = new Tema();
				tema.setId(rs.getInt("id"));
				name = rs.getString("tema");
				tema.setNombre(name);
				tema.setPadre(t.getPadre());
				talla++;
				// Si encuentra el tema regresa un tResul
				// return tResul;
				tResul.add(tema);
			}
			for (int i = 0; i < tResul.size(); i++) {
				System.out.print(tResul.get(i).getId() + " ");
				System.out.print(tResul.get(i).getNombre() + " ");
				System.out.println(tResul.get(i).getPadre());
			}
			if (talla == 0) {
				throw new TemaNoEncontrado("No existe un padre " + t.getPadre());
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e2) {
				throw new PersistenciaException(e2);
			}

		}
		return tResul;
	}

	// @Override
	public int getNumeroSubTemas(Tema t) {
		Statement stmt;
		String query = "", n;
		ResultSet rs;
		Tema tResul = null;
		int talla;
		try {
			stmt = this.con.createStatement();
			if (t.getPadre() > 0) {
				query = "select * from temas where activo = 'true' and padre=" + t.getPadre();
			} else {
				System.exit(4);
			}
			rs = stmt.executeQuery(query);
			talla = rs.getFetchSize();
			// System.out.println("Talla: "+talla);

			while (rs.next()) {
				tResul = new Tema();
				tResul.setId(rs.getInt("id"));
				n = rs.getString("tema");
				tResul.setNombre(n);
				talla++;
				// Si encuentra el tema regresa un tResul
				// return tResul;
			}
			// System.out.println("Talla: "+talla);
			if (talla == 0) {
				throw new TemaNoEncontrado("No existe un tema con id " + t.getId() + "y/o " + t.getNombre());
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		}

		return talla;
	}

}
