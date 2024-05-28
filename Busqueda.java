public class Busqueda implements Runnable{
	int matriza[];
	int matrizb[];
	int unos_a[];
   	int unos_b[];
	int fila;

	public Busqueda(int matriza[], int matrizb[], int unos_a[], int unos_b[],int fila){
		this.fila = fila;
		this.unos_a = unos_a;
		this.unos_b = unos_b;
		this.matriza = matriza;
		this.matrizb = matrizb;
	}

	@Override
	public synchronized void run(){
		for(int i = 0; i < matriza.length; i++){
			if(this.matriza[i]==1){
				this.unos_a[i] = 1;
			}
			if(this.matrizb[i]==1){
				this.unos_b[i] = 1;
			}
		}
	}
}
