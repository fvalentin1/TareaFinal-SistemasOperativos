public class Repetidos implements Runnable{
	int matriza[];
	int matrizb[];
	int coordenadas[];
    	int fila;

	public Repetidos(int matriza[], int matrizb[], int coordenadas[],int fila){
		this.fila = fila;
		this.coordenadas = coordenadas;
		this.matriza = matriza;
		this.matrizb = matrizb;
	}

	@Override
	public synchronized void run(){
		for(int i = 0; i < matriza.length; i++){
			if(this.matriza[i]==this.matrizb[i]){
				this.coordenadas[i] = matriza[i];
			}
		}
	}
}
