package Game;
import java.util.Random;

public class Board {
	
	//Variáveis "private" para manter as interações entre os códigos sem afetar a matriz criada
	private int[][] map;
	private int lines;
	private int columns;
	
	//Método construtor da matriz mapa para o jogo
	public Board(int[][] map) {
		
		this.lines = map.length;
		this.columns = map[0].length;
		this.map = new int[lines][columns];
		
		//Matriz copiada novamente para evitar que ela seja modificada sem ser pelos métodos
		for (int i = 0; i<lines; i++) {
			for (int j = 0; j<columns; j++) {
				
				this.map[i][j] = map[i][j];
			}
		}
	}
	
	//Método para exibir o mapa
	public void show_map() {
		
		int k = 0;
		int z = 0;
		
		for (int[] line : map) {
			for (int i : line) {
				if (k<3) {
					if (i==0) {
						
						System.out.print("║       ");	
					}
					else if (i>0 && i<10) {
						
						System.out.print("║   "+i+"   ");
					}
					else if (i>10 && i<100) {
						
						System.out.print("║   "+i+"  ");
					}
					else if (i>100 && i<1000) {
						
						System.out.print("║   "+i+" ");
					}
					else {

						System.out.print("║   "+i);
					}
					k++;
				}
				else {
					if (i==0) {
					
					System.out.println("║       ║");	
				}
				else if (i>0 && i<10) {
					
					System.out.println("║   "+i+"   ║");
				}
				else if (i>10 && i<100) {
					
					System.out.println("║   "+i+"  ║");
				}
				else if (i>100 && i<1000) {
					
					System.out.println("║   "+i+" ║");
				}
				else {

					System.out.println("║   "+i+"║");
				}
					k = 0;
				}
			}

			z++;
			
			if (z==4) {
				
				System.out.println("╠═══════╩═══════╩═══════╩═══════╣");
			}
			else {
				
				System.out.println("╠═══════╬═══════╬═══════╬═══════╣");		
			}
		}
	}
	
	//Método para introdução de elemento aleatório na matriz map
	public void set_num() {
		
			Random rd = new Random();
			boolean end = false;
			
			//Percorre a procura de espaços vazios
			for (int i=0; i<lines;i++) {
				for (int j=0;j<columns;j++) {
					if (map[i][j] == 0) {
						end = true;
						break;
					}
				}
				//Não faz nada se estiver cheia
				if (end) {
					break;
				}
			}
			if (!end) {
				return;
			}
			
			int l,c;
			
			do {
				
				l = rd.nextInt(lines);
				c = rd.nextInt(columns);
			}while (map[l][c] != 0);
			
			map[l][c] = (rd.nextDouble()<=0.9) ? 2:4;
	}
	
	//Método para troca de posições da matirz para a direita
	public int pos_numR() {
		
		int sum = 0;
		
	    for (int i = 0; i < lines; i++) {
	    	for (int j = columns - 2; j >= 0; j--) {
	        	 if (map[i][j] != 0) {
	            	
	                int k = j;
	                
	                while (k + 1 < columns && map[i][k + 1] == 0) {
	                	
	                    map[i][k + 1] = map[i][k];
	                    map[i][k] = 0;
	                    k++;
	                }
	            }
	        }
	        for (int j = columns - 2; j >= 0; j--) {
	            if (map[i][j] != 0 && map[i][j] == map[i][j + 1]) {
	            	
	                map[i][j + 1] *= 2;
	                map[i][j] = 0;
	                sum += map[i][j+1];
	            }
	        }
	        
	        for (int j = columns - 2; j >= 0; j--) {
	            if (map[i][j] != 0) {
	            	
	                int k = j;
	                
	                while (k + 1 < columns && map[i][k + 1] == 0) {
	                	
	                    map[i][k + 1] = map[i][k];
	                    map[i][k] = 0;
	                    k++;
	                }
	            }
	        }
	    }
		return sum;
	}
	
	//Método para troca para a esquerda
	public int pos_numL() {
		
		int sum = 0;
			
		for (int i =0;i<lines;i++) {
			for (int j=1;j<=columns-1;j++) {
				if (map[i][j] != 0) {
						
					int k = j;
						
					while (k-1>=0 && map[i][k-1] == 0) {
							
						map[i][k-1] = map[i][k];
						map[i][k] = 0;
						k--;
					}
				}
			}
			for (int j=1;j<=columns-1;j++) {
				if (j-1>=0 && map[i][j-1] == map[i][j]){
					
					map[i][j-1] *= 2;
					map[i][j] = 0;
					
					sum += map[i][j-1];							
				}
			}
			for (int j=1;j<=columns-1;j++) {
				if (map[i][j] != 0) {
						
					int k = j;
						
					while (k-1>=0 && map[i][k-1] == 0) {
							
						map[i][k-1] = map[i][k];
						map[i][k] = 0;
						k--;
					}
				}
			}
		}
		return sum;
	}
		
		//Método para trocas para baixo
	public int pos_numD() {
		
		int sum = 0;
			
		for (int i=0;i<columns;i++) {
			for (int j=lines-2;j>=0;j--) {
				if (map[j][i] != 0) {
						
					int k = j;
						
					while (k+1<lines && map[k+1][i] == 0) {
							
						map[k+1][i] = map[k][i];
						map[k][i] = 0;
						k++;
					}
				}
			}
			for (int j=lines-2;j>=0;j--) {

				if (j+1<lines && map[j+1][i] == map[j][i]){
						
					map[j+1][i] *= 2;
					map[j][i] = 0;
					
					sum += map[j+1][i];							
				}
			}
			for (int j=lines-2;j>=0;j--) {
				if (map[j][i] != 0) {
						
					int k = j;
						
					while (k+1<lines && map[k+1][i] == 0) {
							
						map[k+1][i] = map[k][i];
						map[k][i] = 0;
						k++;
					}
				}
			}
		}
		return sum;
	}
		
	//Método para trocas para cima
	public int pos_numU() {
		
		int sum = 0;
					
		for (int i=0;i<columns;i++) {
			for (int j=1;j<=lines-1;j++) {
				if (map[j][i] != 0) {
								
					int k = j;
								
					while (k-1>=0 && map[k-1][i] == 0) {
									
						map[k-1][i] = map[k][i];
						map[k][i] = 0;
						k--;
					}
				}
			}
			for (int j=1;j<=lines-1;j++) {
				if (j-1>=0 && map[j-1][i] == map[j][i]){
								
					map[j-1][i] *= 2;
					map[j][i] = 0;
					
					sum += map[j-1][i];	
				}
			}
			for (int j=1;j<=lines-1;j++) {
				if (map[j][i] != 0) {
								
					int k = j;
								
					while (k-1>=0 && map[k-1][i] == 0) {
									
						map[k-1][i] = map[k][i];
						map[k][i] = 0;
						k--;
					}
				}
			}
		}
		return sum;
	}
	
	public void reset() {
		
		for (int i=0;i<lines;i++) {
			for (int j=0;j<columns;j++) {
				map[i][j] = 0;
			}
		}
	}
	
	public int win() {
		
		int w=0;
		
		for (int i=0;i<lines;i++) {
			for (int j=0;j<columns;j++) {
				if (map[i][j] == 2048) {
					
					w=1;
				}
			}
		}
		
		return w;
		
	}//Método derrota
	public int lose() {
	    for (int i = 0; i < lines; i++) {
	        for (int j = 0; j < columns; j++) {
	            if (map[i][j] == 0) {
	                return 0; // Ainda há espaço livre
	            }
	            if (j < columns - 1 && map[i][j] == map[i][j + 1]) {
	                return 0; // Movimento possível na horizontal
	            }
	            if (i < lines - 1 && map[i][j] == map[i + 1][j]) {
	                return 0; // Movimento possível na vertical
	            }
	        }
	    }
	    return 1; // Nenhum movimento possível
	}
	
	public Board clone() {
		
	    int[][] map2 = new int[lines][columns];
	    
	    for (int i=0; i<lines;++i) {
	    	
	    	map2[i] = map[i].clone();
	    }
	    
		return new Board(map2);
	}
	
	public int[][] getMap() {
	    return map;
	}
}