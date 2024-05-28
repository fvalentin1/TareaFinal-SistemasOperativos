import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.math.*;

public class TareaSO{

	//Hilos separados en caso de agregar o quitar para alguna funcion en especifico
	public static int HILOS_UNOS = 10;
	public static int HILOS_PRIMOS = 10;
	public static int HILOS_DIVISION = 10;
	public static int HILOS_REPETIDOS = 20;
	public static int HILOS_MULTIPLICACION = 10;

	public static void main(String[] args) throws InterruptedException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese la dimension de las matrices");
		int dimension=sc.nextInt();
		int matriza[][]=new int [dimension][dimension];
		int matrizb[][]=new int [dimension][dimension];
		int matrizc[][]= new int [dimension][dimension];
		int unos_a[][]= new int [dimension][dimension];
		int unos_b[][]= new int [dimension][dimension];
		int coordenadas[][]= new int [dimension][dimension];
		int primosa[][]= new int [dimension][dimension];
		int primosb[][]= new int [dimension][dimension];
		int opcion;
		System.out.println("\n");
        
		//Se generan e imprimen las matrices aleatorias
		System.out.println("MATRIZ A");
		for(int x=0; x < matriza.length; x++){
 			System.out.print("[");
 			for(int y=0; y < matriza[x].length; y++){
				matriza[x][y]=(int)(Math.random()*9)+1;
				System.out.print(matriza[x][y]);
				if(y!=matriza[x].length-1){
					System.out.print("\t");
				}
			}
			System.out.println("]");
		}
			
		System.out.println("\n");
		System.out.println("MATRIZ B");
		for(int x=0; x < matrizb.length; x++){
			System.out.print("[");
			for(int y=0; y < matrizb[x].length; y++){
				matrizb[x][y]=(int)(Math.random()*9)+1;
				System.out.print(matrizb[x][y]);
				if(y!=matrizb[x].length-1){
				System.out.print("\t");
				}
			}
			System.out.println("]");
		}

		System.out.println("\n");
		for(int i=0; i<dimension; i++){
			for (int j = 0; j < dimension; j++) {
				unos_a[i][j] = 0;
				unos_b[i][j] = 0;
				coordenadas[i][j]=0;
				primosa[i][j]=0;
				primosb[i][j]=0;
			}
		}
		System.out.println("\n");

		do{
			//Pool de hilos de tamaño 10
			ExecutorService executor_busqueda_unos = Executors.newFixedThreadPool(HILOS_UNOS);
			for(int i = 0; i < dimension; i++){
				Busqueda busqueda_unos = new Busqueda(matriza[i], matrizb[i],unos_a[i], unos_b[i], i);
				executor_busqueda_unos.execute(busqueda_unos);
			}
			executor_busqueda_unos.shutdown();

			//Pool de hilos de tamaño 10
			ExecutorService executor_primos = Executors.newFixedThreadPool(HILOS_PRIMOS);
			for(int i = 0; i < dimension; i++){
				Primos primos = new Primos(matriza[i], matrizb[i],primosa[i], primosb[i], i);
				executor_primos.execute(primos);
			}
			executor_primos.shutdown();

			//Pool de hilos de tamaño 20
			ExecutorService executor_repetidos = Executors.newFixedThreadPool(HILOS_REPETIDOS);
			for(int i = 0; i < dimension; i++){
				Repetidos repetidos = new Repetidos(matriza[i], matrizb[i], coordenadas[i], i);
				executor_repetidos.execute(repetidos);
			}
			executor_repetidos.shutdown();

			//Pool de hilos de tamaño 10
			ExecutorService executor_multi = Executors.newFixedThreadPool(HILOS_MULTIPLICACION);
			for(int i = 0; i < dimension; i++){
				Multiplicacion multi = new Multiplicacion(matriza[i], matrizb[i],matrizc[i], i);
				executor_multi.execute(multi);
			}
			executor_multi.shutdown();

			System.out.println("---------------------------------------------------------------");
			System.out.println(" .:: Trabajo con matrices ::. ");
			System.out.println("1. Buscar todos los unos en ambas matrices");
			System.out.println("2. Buscar los primeros 3 numeros primos en ambas matrices (2-3-5)");
			System.out.println("3. Buscar los repetidos en ambas matrices");
			System.out.println("4. Multiplicar coordenada a coordenada ambas matrices");
			System.out.println("5. Salir");
			opcion = sc.nextInt();

			switch(opcion){

				case 1:
					System.out.println("Funcionalidad 1\n");
					int sumamatriza=0;
					int sumamatrizb=0;
					for(int i =0; i<dimension; i++){
						for(int j = 0; j < dimension; j++){
							sumamatriza=sumamatriza+unos_a[i][j];
							sumamatrizb=sumamatrizb+unos_b[i][j];
						}
					}
					System.out.println("La Matriz A tiene: "+sumamatriza+ " unos.");
					System.out.println("La Matriz B tiene: "+sumamatrizb+ " unos.");
					System.out.println("");

					System.out.println("Matriz A");
					for(int i = 0; i < dimension; i++){
						for(int j = 0; j < dimension; j++){
							System.out.print("["+ matriza[i][j] + "]");
						}
						System.out.println("");   
					}

					System.out.println("");

					System.out.println("Matriz B");
					for(int i = 0; i < dimension; i++){
						for(int j = 0; j < dimension; j++){
							System.out.print("["+ matrizb[i][j] + "]");
						}
						System.out.println("");   
					}
                 
					System.out.println("");
					break;

				case 2:
					System.out.println("Funcionalidad 2\n");
					for(int i=0; i<dimension; i++){
						for(int j = 0; j < dimension; j++){
							if(primosa[i][j]!=0){
								System.out.println(" Se encuentra un "+ primosa[i][j] + " en las coordenadas ["+i+"]["+j+"] de la matriz A, se divide por "+ primosa[i][j] + "  y así obtener finalmente que la coordenada  ["+i+"]["+j+"] sea un 1");
							}
							if(primosb[i][j]!=0){
								System.out.println(" Se encuentra un "+ primosb[i][j] + " en las coordenadas ["+i+"]["+j+"] de la matriz  B, se divide por "+ primosb[i][j]+ "  y así obtener finalmente que la coordenada  ["+i+"]["+j+"] sea un 1");
							}  
						}
					}

					//Pool de hilos tamaño 10 cuando se necesite la division
					ExecutorService executor_division = Executors.newFixedThreadPool(HILOS_DIVISION);
					for (int i = 0; i < dimension; i++) {
						Division division = new Division(matriza[i], matrizb[i], i);
						executor_division.execute(division);
					}
					executor_primos.shutdown();
					System.out.println("");
					break;

				case 3:
					System.out.println("Funcionalidad 3\n");
					for(int i=0; i<dimension; i++){
						for(int j = 0; j < dimension; j++){
							if(coordenadas[i][j]!=0){
								System.out.println("Matriz a = Matriz b = "+coordenadas[i][j]+" en las coordenadas ["+i+"]["+j+"]");
							}
						}
					}
					System.out.println(" ");
					break;

				case 4:
					System.out.println("Funcionalidad 4\n");
					System.out.println("Matriz A por Matriz B");
					for(int i=0; i<dimension; i++){
						for(int j = 0; j < dimension; j++){
							System.out.print("["+ matrizc[i][j]+ "]");
						}
						System.out.println("");
					}
					System.out.println(" ");
					break;
				case 5: System.exit(0);
				}
		}while(opcion!=5);
	}
}
