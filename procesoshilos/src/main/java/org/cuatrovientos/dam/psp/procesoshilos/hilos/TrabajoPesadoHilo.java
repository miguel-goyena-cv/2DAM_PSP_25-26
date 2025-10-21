package org.cuatrovientos.dam.psp.procesoshilos.hilos;

public class TrabajoPesadoHilo implements Runnable {
	
	private final int id;
	private long timestampInicio;

	public TrabajoPesadoHilo(int id) {
        this.id = id;
    }

    @Override
    public void run() {
    	
    	// Tareas iniciales
    	timestampInicio = System.currentTimeMillis();
    	logConTimestamp("Hilo " + id + " iniciado. con timestamp: "+timestampInicio);
    	
    	// Bucle consumidor de CPU
        long contador = 0;
        while (true) {
        	
            contador++;
            double x = Math.sin(contador) * Math.cos(contador);
            if (contador % 100_000_000 == 0) {
            	logConTimestamp("Contador: " + contador);
            }
   
        }  
        
        // ¿LLego aquí alguna vez?
    }
    
    /**
     * Escribe el log junto con los ms desde comienzo hilo
     * @param log
     */
    private void logConTimestamp(String log){
    	System.out.println("Hilo: "+id+"; Timestamp: "+(System.currentTimeMillis() - timestampInicio)+": "+log);
    }
    
    

}
