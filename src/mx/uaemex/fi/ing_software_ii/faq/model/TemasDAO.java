package mx.uaemex.fi.ing_software_ii.faq.model;

import java.util.List;

public interface TemasDAO {
	/*
	 * Siguiendo las ideas de QueryByExample la consulta se hara
	 * utilizando los parametros establecidos en el objeto
	 * @param t Objeto con los datos conocidos
	 * @return Tema leido de la base de datos con los atributos simples llenos.
	 * Utilizaremos lazy loading, por lo que NO se cargaran los subtemas de la base
	 * @throws TemaNoEncontrado si los parametros de consulta no generan resultados
	*/
	public Tema consultar(Tema t);
	
	public Tema Crear(Tema t);
	
	/*
	 * Eliminacion logica en la base
	 * @param t Tema a eliminar
	 */
	
	public void eliminar(Tema t);
	
	/*
	 * Localizar el registro mediante ID, y sobreescribir todas las propiedades establecidas en el parametro 
	 * @param t Tema a actualizar
	 */
	
	public void actualizar(Tema t);
	
	public List<Tema> consultarSubTemas(Tema t);

    public int getNumeroSubTemas(Tema t);
	
}
