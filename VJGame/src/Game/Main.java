package Game;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Main {
	static int p = 0;
	
	public static void main(String[] args) {
		
		String op = "";
		Scanner sc = new Scanner(System.in);
		int[][] pos1 = new int[4][4];
		int[][] pos2 = new int[4][4];
		Board b1 = new Board(pos1);
		Board b2 = new Board(pos2);
		
		boolean on = true;
		boolean ply = true;
		boolean m1 = true;
		boolean m2 = true;
		boolean chk = true;
		
		while (on) {
			
			menu1();
			
			if (sc.hasNextInt()) {
				
				System.out.println("Select a valid option");
				sc.nextLine();
				continue;				
			}
			else {
				
				m1 = false;
				op = sc.nextLine().toUpperCase();
			}
			
			
			while (!m1) {
				
				switch (op) {
                	case "X":
                		
                		m1 = true;
                		on = false;
                		continue;
                	case "O":
                		
                		b1.set_num();
                		b1.set_num();
                		b2 = b1.clone();
                		
                		m1 = true;
                		ply = false;
                		op = "";
                		p = 0;
                		continue;
                	default:
                		
                		System.out.println("Select a valid option.");
                		m1 = true;
                		continue;
            		}
            }
			
			 while (!ply) {
				
				String border = "╔" + "═══════╦".repeat(3) + "═══════╗";
				System.out.println(border);
				 
				b1.show_map();
				
				int lose = b1.lose();
				int win = b1.win();
				
				if (lose==1 && win!=1) {
					
					System.out.println("║           You lost!           ║");
					chk = false;
				}
				else if (win==1) {
					
					System.out.println("║           You win!          ║");
					chk = false;
				}				
				
				menu2();
				
				while (!chk) {
					
					if (sc.hasNextInt()) {
						
						System.out.println("Do you want to EXIT or RESTART?");
						sc.nextLine();
						continue;				
					}
					else {
						
						op = sc.nextLine().toUpperCase();
					}
					
					switch (op) {
					case "X":

	            		System.out.println("X");
	            		on = false;
	            		m1 = true;
	            		m2 = true;
	            		ply = true;
	            		chk = true;
	            		continue;
					case "R":

	            		System.out.println("R");
	            		chk = true;
	            		b1.reset();
	            		b2.reset();
	            		
	            		b1.set_num();
	            		b1.set_num();
	            		b2 = b1.clone();
	    				System.out.println(border);
	            		
	            		b1.show_map();
	            		p = 0;
	            		menu2();
	            		continue;
	            	default:

						System.out.println("Do you want to EXIT or RESTART?");
						continue;
					}
				}
				
				if (ply) {
					
					break;
				}
				
				if (sc.hasNextInt()) {
					
					System.out.println("Select a valid option");
					continue;
				}
				else {
					
					op = sc.nextLine().toUpperCase();
					m2 = false;
				}
				
				while (!m2) {
					
					b2 = b1.clone();
					
					if (op.equals("M")) {
						
	            		int d = b1.pos_numD();
	            		p = p + d;
	            		
	            		if (Arrays.deepEquals(b1.getMap(), b2.getMap())) {
	            			break;
	            		}
	            		else {
		            		b1.set_num();
		            		break;
	            		}
					}
					else if (op.equals("I")) {

	            		int u = b1.pos_numU();
	            		p = p + u;
	            		
	            		if (Arrays.deepEquals(b1.getMap(), b2.getMap())) {
	            			break;
	            		}
	            		else {
		            		b1.set_num();
		            		break;
	            		}
					}
					else if (op.equals("K")) {
						
	            		int r = b1.pos_numR();
	            		p = p + r;
	            		
	            		if (Arrays.deepEquals(b1.getMap(), b2.getMap())) {
	            			break;
	            		}
	            		else {
		            		b1.set_num();
		            		break;
	            		}
					}
					else if (op.equals("J")) {

	            		int l = b1.pos_numL();
	            		p = p + l;
	            		
	            		if (Arrays.deepEquals(b1.getMap(), b2.getMap())) {
	            			break;
	            		}
	            		else {
		            		b1.set_num();
		            		break;
	            		}
					}
					else if (op.equals("X")) {

	            		m2 = true;
	            		ply = true;
	            		on = false;
	            		break;
					}
					else if (op.equals("R")) {

	            		chk = true;
	            		b1.reset();
	            		
	            		b1.set_num();
	            		b1.set_num();
	            		b2 = b1.clone();
	            		p = 0;
	            		break;
					}
					else {

	            		System.out.println("Select a valid option.");
                		m2 = true;
                		break;	
					}
				}
			}
				
		}
	}	

	public static void menu1() {
		
		int width = 33;
	    
	    String[] lines = {
	        "  2048 GAME  ",
	        "",
	        " [O] BEGIN   ",
	        " [X] EXIT    ",
	        "",
	    };
	    
	    String border = "╔" + "═".repeat(width - 2) + "╗";
	    String bottomBorder = "╚" + "═".repeat(width - 2) + "╝";
	    
	    System.out.println(border);
	    for (String line : lines) {
	        int padding = (width - 2 - line.length()) / 2;
	        String formattedLine = "║" + " ".repeat(Math.max(0, padding)) + line + " ".repeat(Math.max(0, width - 2 - padding - line.length())) + "║";
	        System.out.println(formattedLine);
	    }
	    System.out.println(bottomBorder);
	}
	
	public static void menu2() {
		
	    int width = 33;
	    
	    String[] lines = {
	        "  2048 GAME  ",
	        "",
	        "Score:     "+p,
	        "",
	        " [m] DOWN   ",
	        " [i] UP     ",
	        " [k] RIGHT  ",
	        " [j] LEFT   ",
	        "",
	        " [X] EXIT   ",
	        " [R] RESTART",
	        "",
	    };
	    
	    String bBorder = "╚" + "═".repeat(width - 2) + "╝";
	    
	    for (String line : lines) {
	    	
	    	int padd = (width - 2 - line.length()) / 2;
	    		
		        String fLine = "║" + " ".repeat(Math.max(0, padd)) + line + " ".repeat(Math.max(0, width - 2 - padd - line.length())) + "║";
		        System.out.println(fLine);	    		
	    }
	    System.out.println(bBorder);
	}
}