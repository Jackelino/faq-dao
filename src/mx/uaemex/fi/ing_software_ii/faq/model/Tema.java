package mx.uaemex.fi.ing_software_ii.faq.model;

import java.util.ArrayList;
import java.util.List;

import mx.uaemex.fi.ing_software_ii.faq.model.error.TemaRepetidoException;

public class Tema {
	private int id;
	public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }
    private String nombre;
	private int padre;
	private List<Tema> subTemas;
	
	public void addTema(Tema t) {
		if(!this.subTemas.contains(t)){
			this.subTemas.add(t);
		}
		else {
			throw new TemaRepetidoException();
		}
	}

	public void delTema(Tema t) {
		this.subTemas.remove(t);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		try {
			Tema t = (Tema)obj;
			if(this.id==t.id || this.nombre.compareToIgnoreCase(t.nombre)==0) {
				return true;
			}
		}catch(ClassCastException e){
			return false;
		}
		return super.equals(obj);
	}
	
	
	public Tema() {
		super();
		this.subTemas = new ArrayList<Tema>();
	}
	
	public Tema(int id) {
        super();
        this.id = id;
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Tema> getSubTemas() {
		return subTemas;
	}
	public void setSubTemas(List<Tema> subTemas) {
		this.subTemas = subTemas;
	}
	
}
