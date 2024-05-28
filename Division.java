public class Division implements Runnable{
	
	int matriza[];
	int matrizb[];
	int fila;
	
	public Division(int matriza[], int matrizb[], int fila){
		this.fila = fila;
		this.matriza = matriza;
		this.matrizb = matrizb;
	}

	@Override
	public synchronized void run(){
		for (int i = 0; i < matriza.length; i++){
			if(this.matriza[i]==2 || this.matriza[i]==3 || this.matriza[i]==5){
				this.matriza[i] = this.matriza[i]/ this.matriza[i];
			}
			if(this.matrizb[i]==2 || this.matrizb[i]==3 || this.matrizb[i]==5){
				this.matrizb[i] = this.matrizb[i]/ this.matrizb[i];
			}
		}
	}
}
