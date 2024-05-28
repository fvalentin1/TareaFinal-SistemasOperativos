public class Primos implements Runnable{
	int matriza[];
	int matrizb[];
	int primosa[];
	int primosb[];
	int fila;

	public Primos(int matriza[], int matrizb[], int primosa[],int primosb[], int fila){
		this.fila = fila;
		this.primosa = primosa;
		this.primosb = primosb;
		this.matriza = matriza;
		this.matrizb = matrizb;
	}

	@Override
	public synchronized void run(){
		for(int i = 0; i < matriza.length; i++){
			if(this.matriza[i]==2 || this.matriza[i]==3 || this.matriza[i]==5){
				this.primosa[i] = this.matriza[i];
			}
			if(this.matrizb[i]==2 || this.matrizb[i]==3 || this.matrizb[i]==5){
				this.primosb[i] = this.matrizb[i];
 			}
		}
	}
}
