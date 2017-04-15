
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main (String []args){
		int y , x , generation;
		y= 10;
		x =10;
		generation = 1;
		int[][] grid = new int[y][x];
		
		game game_ob = new game();
		
		Scanner scan = new Scanner(System.in);
		
		grid = game_ob.restart(grid);
		
		while(true) {
			game_ob.print(grid);
			
			int n ;
			n = scan.nextInt();
			if(n == 1) { // next generation
				generation++;
				System.out.println("GENERATION " + generation);
				grid =  game_ob.generate(grid);
			}
			if(n == 0){
				System.out.println("CLOSING");
				break;
			}
			if(n == 2){
				grid = game_ob.restart(grid);
			}
		}
		scan.close();
	}
	
	
}
class game{
	private int[][] grid_temp = new int[0][0];
	

	public int[][] restart(int[][] grid) {
	//	Arrays.fill(grid, null);
		Random ran = new Random();
		for(int i = 0; i <  grid.length;i++) {
			for(int a = 0; a < grid[i].length;a++) {
				grid[i][a] = ran.nextInt(2);
			}
		}
		return grid;
		
	}
	public int[][] generate(int[][] grid) {
		grid_temp = grid;
		
		for(int i = 0; i< grid.length;i++){
			for(int a = 0; a < grid[i].length;a++){
				int score = 0;
				if(a+1!=grid[i].length && grid_temp[i][a+1] == 1)score++;
				if(a-1!= -1 && grid_temp[i][a-1] == 1)score++;
				if(i+1!=grid.length && grid_temp[i+1][a] == 1)score++;
				if(i-1!= -1 && grid_temp[i-1][a] == 1)score++;
				
				if(i-1!= -1 && a-1 != -1&& grid_temp[i-1][a-1] == 1)score++;
				if(i-1!= -1 && a+1!=grid[i].length && grid_temp[i-1][a+1] == 1)score++;
				if(i+1!= grid[i].length  && a+1!=grid[i].length && grid_temp[i+1][a+1] == 1)score++;
				if(i+1!= grid[i].length  && a-1 != -1 && grid_temp[i+1][a-1] == 1)score++;
			
				if(grid[i][a] == 0) {
					if(score == 3) grid[i][a] = 1;
				}
				
				if(grid[i][a] == 1) {
					if(score < 2) grid[i][a] = 0;
					if(score == 2 || score == 3) grid[i][a] = 1;
					if(score > 3) grid[i][a] = 0;
				}
				
			}
		}
		
		//update(grid);
		return grid;
	}
	public void update(int[][] grid) {
		grid = grid_temp;
	}
	public  void print(int[][] grid){
		for(int i = 0; i< grid.length;i++){
			for(int a = 0; a < grid[i].length;a++){
				System.out.print(grid[i][a] + " ");
			}
			System.out.println();
		}
	}
}








