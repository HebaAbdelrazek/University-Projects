import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	public static void GenGrid(String grid) throws IOException {
		String[] gridTemp = grid.split(";");
		
		String[] rowsAndColumns = gridTemp[0].split(",");
		String rows = rowsAndColumns[0];
		String columns = rowsAndColumns[1];
		
		if(Integer.parseInt(rows) > 5 || Integer.parseInt(columns) > 5) {
			System.out.println("The maximum number of rows or columns allowed is 5");
		}
		else {
			String[] ironMan = gridTemp[1].split(",");
			String ironManX = ironMan[0];
			String ironManY = ironMan[1];
					
			String[] thanos = gridTemp[2].split(",");
			String thanosX = thanos[0];
			String thanosY = thanos[1];
			
			String[] stones = gridTemp[3].split(",");
			String stone1X = stones[0];
			String stone1Y = stones[1];
			String stone2X = stones[2];
			String stone2Y = stones[3];
			String stone3X = stones[4];
			String stone3Y = stones[5];
			String stone4X = stones[6];
			String stone4Y = stones[7];
			
			int count = 0;
			boolean exists = true;
			while(exists) {
				count++;
				File file = new File("KB" + count + ".pl");
				exists = file.exists();
			}
			
			BufferedWriter bf = new BufferedWriter(new FileWriter("KB" + count + ".pl"));
			
			bf.write("rows(" + rows + ")" + ".");
			bf.newLine();
			bf.write("columns(" + columns + ")" + ".");
			bf.newLine();
			
			bf.write("ironManInitial(" + ironManX + "," + ironManY + ").");
			bf.newLine();
			
			bf.write("thanos(" + thanosX + "," + thanosY + ")" + ".");
			bf.newLine();
			
			bf.write("stone(1," + stone1X + "," + stone1Y + ")" + ".");
			bf.newLine();
			
			bf.write("stone(2," + stone2X + "," + stone2Y + ")" + ".");
			bf.newLine();
			
			bf.write("stone(3," + stone3X + "," + stone3Y + ")" + ".");
			bf.newLine();
			
			bf.write("stone(4," + stone4X + "," + stone4Y + ")" + ".");
			bf.newLine();
			
			bf.close();
		}
		
		
				
	}
	
	public static void main(String[]args) throws IOException {
		Main main = new Main();
		
		main.GenGrid("5,5;1,2;3,4;1,1,2,1,2,2,3,3");
		main.GenGrid("3,3;0,0;2,2;0,1,1,0,1,2,2,1");
	}

}
