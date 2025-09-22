package org.cuatrovientos.dam.psp.herencia.ejercicio1;

public class Trabajador {

    private String nombre;
    private String puesto;
    private String direccion;
    private String nSS;
    
    
	public Trabajador(String nombre, String puesto, String direccion, String nSS) {
		super();
		this.nombre = nombre;
		this.puesto = puesto;
		this.direccion = direccion;
		this.nSS = nSS;
	}

	

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPuesto() {
		return puesto;
	}


	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getnSS() {
		return nSS;
	}


	public void setnSS(String nSS) {
		this.nSS = nSS;
	}



	@Override
	public String toString() {
		return "Trabajador [nombre=" + nombre + ", puesto=" + puesto + ", direccion=" + direccion + ", nSS=" + nSS
				+ "]";
	}


}
