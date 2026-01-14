package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.cenafilosofos.soluciongestortenedores;

public class Servicio {
	
	private Tenedor tenedorDrcha;
	private Tenedor tenedorIzq;
	
	public Servicio(Tenedor tenedorDrcha, Tenedor tenedorIzq) {
		super();
		this.tenedorDrcha = tenedorDrcha;
		this.tenedorIzq = tenedorIzq;
	}

	@Override
	public String toString() {
		return "Servicio [tenedorDrcha=" + tenedorDrcha + ", tenedorIzq=" + tenedorIzq + "]";
	}

	public void liberar() {

		this.tenedorDrcha.soltarTenedor();
		this.tenedorIzq.soltarTenedor();
		
	}
	
	
	

}
