import com.espertech.esper.client.EPRuntime;

public class ThreadEnviarEventos extends Thread {
	
	private final String[] data = {"Eddie", "Silvano", "Matheus", "Joao", "Wedson",
			                       "Patricia", "Lucas2", "Lucas", "Jackson", "Sostenes", "Democrito",
			                       "Rubens"}; 

	private EPRuntime cep;
	public ThreadEnviarEventos(EPRuntime r) {
		this.cep = r;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		int idx = 0;
		int id = 0;
		while(true) {
			try {
				Thread.sleep(3000);
				idx=(idx>=data.length)?0:idx; //dados infinitos
				System.out.println("\nT1 enviando: " + data[idx]);

				this.cep.sendEvent(new Produto(++id, data[idx]));
				idx++;
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
