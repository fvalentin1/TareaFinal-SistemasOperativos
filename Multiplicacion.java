public class Multiplicacion implements Runnable{
		int matriza[];
		int matrizb[];
		int matrizc[];
		int fila;

	public Multiplicacion(int matriza[], int matrizb[], int matrizc[],int fila){
		this.fila = fila;
		this.matriza = matriza;
		this.matrizb = matrizb;
		this.matrizc = matrizc;
	}

	@Override
	public synchronized void run(){
		for(int i = 0; i < matriza.length; i++){
			this.matrizc[i] = this.matriza[i] * this.matrizb[i];
		}
	}
}
