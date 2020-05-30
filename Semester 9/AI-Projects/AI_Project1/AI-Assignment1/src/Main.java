import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue; 
import java.util.Timer;

public class Main{
	
	public static String solve(String grid, String strategy, boolean visualize) {
		String solution = "";
		
		String[] gridTemp = grid.split(";");
		
		String[] warriors = gridTemp[4].split(",");
		
		String[] rowsAndColumns = gridTemp[0].split(",");
		int rows = Integer.parseInt(rowsAndColumns[0]);
		int columns = Integer.parseInt(rowsAndColumns[1]);
		
		if(rows < 5 || columns < 5 || warriors.length < 5) {
			System.out.println("The grid dimensions of the input grid must be at least 5x5 and the number of warriors must be at least 5");
		}
		else {
			
			EndGame problem = new EndGame(grid);
			
			AbstractSearch as = new AbstractSearch();
			
			Node sol = as.solve(problem, strategy);
			
			if(sol != null) {
				List<Node> path = sol.getPathFromRoot();

				for (int i = 0; i < path.size(); i++) {
					if (i == path.size() - 1)
						solution += path.get(i).getOperator() + ";";
					else
						solution += path.get(i).getOperator() + ",";
				}

				solution+= sol.pathCost + ";" + as.numberOfExpandedNodes;
				
				if(visualize) {
					String[] tmp = solution.split(";");
					String[] operations = tmp[0].split(",");
					
					String[][] gridV = new String[rows][columns];
					
					
					//adding iron man to the grid
					String[] ironMan = gridTemp[1].split(",");
					
					int[] currPosition = new int[2];
					currPosition[0] = Integer.parseInt(ironMan[0]); //add iron man x coordinate
					currPosition[1] = Integer.parseInt(ironMan[1]); //add iron man y coordinate
					
					//adding thanos to the grid
					String[] thanos = gridTemp[2].split(",");
					
					int[] thanosPosition = new int[2];
					thanosPosition[0] = Integer.parseInt(thanos[0]); //add thanos x coordinate
					thanosPosition[1] = Integer.parseInt(thanos[1]); //add thanos y coordinate
					
					gridV[thanosPosition[0]][thanosPosition[1]] = "T";
					
					
					//adding the stones to the grid
					String[] stones = gridTemp[3].split(",");
					
					for(int i = 0 ; i < stones.length-1 ; i+=2) {
						gridV[Integer.parseInt(stones[i])][Integer.parseInt(stones[i+1])] = "S";
					}	
					
					for(int i = 0 ; i < warriors.length-1 ; i+=2) {
						gridV[Integer.parseInt(warriors[i])][Integer.parseInt(warriors[i+1])] = "W";
					}
					
					for(int i = 0 ; i < gridV.length ; i++) {
						for(int j = 0 ; j < gridV[0].length ; j++) {
							if(gridV[i][j] == null)
								gridV[i][j] = "E";
						}
					}
					
					printGrid(gridV, currPosition);
					
					for(int i = 0 ; i < operations.length ; i++) {
						System.out.print("Current Cell: " + currPosition[0] + "," + currPosition[1] + " ");
						System.out.println("Operator: " + operations[i]);
						if(operations[i].equals("up"))
							currPosition[0] = currPosition[0]-1;
						else if(operations[i].equals("down"))
							currPosition[0] = currPosition[0]+1;
						else if(operations[i].equals("left")) 
							currPosition[1] = currPosition[1]-1;
						else if(operations[i].equals("right")) 
							currPosition[1] = currPosition[1]+1;
						else if(operations[i].equals("collect"))
							gridV[currPosition[0]][currPosition[1]] = "E";
						else if(operations[i].equals("kill")) {
							if(currPosition[0] > 0 && gridV[currPosition[0]-1][currPosition[1]] == "W")
								gridV[currPosition[0]-1][currPosition[1]] = "E";
							else if(currPosition[0] < rows-1 && gridV[currPosition[0]+1][currPosition[1]] == "W")
								gridV[currPosition[0]+1][currPosition[1]] = "E";
							else if(currPosition[1] > 0 && gridV[currPosition[0]][currPosition[1]-1] == "W")
								gridV[currPosition[0]][currPosition[1]-1] = "E";
							else if(currPosition[1] < columns-1 && gridV[currPosition[0]][currPosition[1]+1] == "W")
								gridV[currPosition[0]][currPosition[1]+1] = "E";
						}
						printGrid(gridV, currPosition);
					}
				}
				
			}
		}
		
		if(solution == "") {
			solution = "There is no solution";
		}
		
		return solution;
	}
	
	public static void printGrid(String[][] grid, int[] ironMan) {
		for(int i = 0 ; i < grid.length ; i++) {
			for(int j = 0 ; j < grid[0].length ; j++) {
				if(ironMan[0] == i && ironMan[1] == j)
					System.out.print("I ");
				else
					System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[]args) {
		Main main = new Main();
		
		String sol = main.solve("5,5;2,2;4,2;4,0,1,2,3,0,2,1,4,1,2,4;3,2,0,0,3,4,4,3,4,4", "GR2", true);
		
		System.out.println(sol);
		//love you dajdaj
	}

}
