package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.cenafilosofos.soluciongestortenedores;

import java.time.Duration;

public class Filosofo implements Runnable{
	
	// Constantes de configuracion
	private static final Duration TIEMPO_ESPERA_CUBIERTOS = Duration.ofSeconds(1);
	private static final long MIN_MILLIS_PENSANDO = 1000;
	private static final long MAX_MILLIS_PENSANDO = 4000;
	private static final long MIN_MILLIS_COMIENDO = 1000;
	private static final long MAX_MILLIS_COMIENDO = 4000;
	
	// Atributos del filosofo
	private String nombre;
	private EstadoFilosofo estado;
	private Camarero camareroAsignado;
	
	// Variables necesarias
	private long timestampInicio = 0;
	
	
	public Filosofo(String nombre, Camarero camarero) {
		super();
		this.nombre = nombre;
		this.camareroAsignado = camarero;
		this.estado = EstadoFilosofo.PENSANDO; // Comienza pensando
	}

	@Override
	public void run() {
		
		timestampInicio = System.currentTimeMillis();
		
		try {
			
			while (this.estado != EstadoFilosofo.MUERTO) {
				
				// Si estaba pensando lo mando comer, 
				// Si estaba comiendo lo mando pensar,
				switch (this.estado) {
				
					case PENSANDO:
						sientaAComer();
						break;
						
					case COMIENDO:
						pensar();
						break;
						
					default:
						break;
				}
				
				// Si esta muerto, entonces interrumpo el hilo
				if (this.estado == EstadoFilosofo.MUERTO) {
					logConTimestamp("Se muere el filosofo");
					break;
				}
			}
			
		}
		catch (Exception ex){
			logConTimestamp("ERROR Gordo en un filosofo, interrumpimos el hilo: "+ex);
			this.estado = EstadoFilosofo.MUERTO;
			Thread.currentThread().interrupt();
		}
		
	}

	/**
	 * EL filosofo piensa
	 * @throws InterruptedException
	 */
	private void pensar() throws InterruptedException {
		
		this.estado = EstadoFilosofo.PENSANDO;
		logConTimestamp("El filosofo está pensando");
		Thread.sleep((long) (Math.random() * (MAX_MILLIS_PENSANDO - MIN_MILLIS_PENSANDO) + MIN_MILLIS_PENSANDO)); 
		logConTimestamp("El filosofo acabó de pensar");
		
	}

	/**
	 * EL filosofo se sienta a la mesa y come
	 * @throws InterruptedException
	 */
	private void sientaAComer() throws InterruptedException {
		
		this.estado = EstadoFilosofo.ESPERANDO_COMER;
		logConTimestamp("El filosofo se sienta a la mesa");
		
		// Primero tiene que pedirle los tenedores al camarero, pero si no hay tenemos que esperar hasta que haya
		Servicio cubiertos = camareroAsignado.pedirServicio();
		while (cubiertos == null) {
			cubiertos = camareroAsignado.pedirServicio();
			Thread.sleep(TIEMPO_ESPERA_CUBIERTOS); // Esperamos un tiempo para no agobiar al camarero!!!
		}
		logConTimestamp("El filosofo ha cogido su servicio y se presta a comer: "+cubiertos.toString());
		this.estado = EstadoFilosofo.COMIENDO; // Solo lo pondremos comiendo cuando realmente tenga cubiertos
		
		// Come porque ya esta listo
		Thread.sleep((long) (Math.random() * (MAX_MILLIS_COMIENDO - MIN_MILLIS_COMIENDO) + MIN_MILLIS_COMIENDO)); 
		
		// Devuelve el servicio
		camareroAsignado.devolverServicio(cubiertos);
		logConTimestamp("El filosofo ha acabado de comer y libera el servicio: "+cubiertos.toString());
		
	}

	@Override
	public String toString() {
		return "Filosofo [nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	public boolean estaVivo() {
		return (!this.estado.equals(EstadoFilosofo.MUERTO));
	}
	
	/**
     * Escribe el log junto con los ms desde comienzo hilo
     * @param log
     */
    private void logConTimestamp(String log){
    	System.out.println("\t["+Thread.currentThread().threadId()+"]["+this.nombre+"]; Timestamp: "+(System.currentTimeMillis() - timestampInicio)+": "+log);
    }


}
