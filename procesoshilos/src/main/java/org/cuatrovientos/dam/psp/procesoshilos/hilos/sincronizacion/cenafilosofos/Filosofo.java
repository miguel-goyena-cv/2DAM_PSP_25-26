package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.cenafilosofos;

public class Filosofo implements Runnable{
	
	private static final long MIN_MILLIS_PENSANDO = 1000;
	private static final long MAX_MILLIS_PENSANDO = 4000;
	
	private static final long MIN_MILLIS_COMIENDO = 1000;
	private static final long MAX_MILLIS_COMIENDO = 4000;
	
	private String nombre;
	private EstadoFilosofo estado;
	private Tenedor tenedorDrcha;
	private Tenedor tenedorIzq;
	
	private long timestampInicio = 0;
	
	
	public Filosofo(String nombre, Tenedor tenedorDrcha, Tenedor tenedorIzq) {
		super();
		this.nombre = nombre;
		this.tenedorDrcha = tenedorDrcha;
		this.tenedorIzq = tenedorIzq;
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
		
		this.estado = EstadoFilosofo.COMIENDO;
		logConTimestamp("El filosofo se sienta a la mesa");
		
		// Primero tiene que coger los tenedores
		tenedorDrcha.cogerTenedor();
		logConTimestamp("El filosofo ha cogido el tenedor Derecho: "+tenedorDrcha);
		tenedorIzq.cogerTenedor();
		logConTimestamp("El filosofo ha cogido el tenedor Izquierdo y empieza a comer: "+tenedorIzq);
		
		
		// Come porque ya esta listo
		Thread.sleep((long) (Math.random() * (MAX_MILLIS_COMIENDO - MIN_MILLIS_COMIENDO) + MIN_MILLIS_COMIENDO)); 
		
		// Libera los tenedores
		tenedorDrcha.soltarTenedor();
		tenedorIzq.soltarTenedor();
		logConTimestamp("El filosofo ha acabado de comer y libera tenedorDrcho: "+tenedorDrcha+ " y tenedorIzq: "+tenedorIzq);
		
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
