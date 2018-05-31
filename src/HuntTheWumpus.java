import java.awt.*;
import java.util.Scanner;
import java.util.Random;


public class HuntTheWumpus{
	public static void main(String[] args){
		int code = 0, lastCode = 0, arrows=5;
		boolean won = false;
		int[] playerPos = randPos();
		int[] lastPos = new int[2];
		int[] wumPos=randPos(), batPos=randPos(), pitPos=randPos(), arrPos=randPos(), temp = new int[2];
		while(samePos(playerPos,wumPos)||samePos(playerPos, batPos))
			playerPos = randPos();

		Scanner vReader = new Scanner(System.in);
		Grid grid = new Grid();
		grid.setColor(playerPos[0],playerPos[1],Color.red);
		while(!hasLost(playerPos, wumPos, pitPos)){

			lastPos[0] = playerPos[0];
			lastPos[1] = playerPos[1];
			while(code == 0){
				code = grid.getCode();
				try {
					Thread.sleep(100);
				}
				catch(Exception e) {

				}
			}
			switch(code){
				case 87:
					if(playerPos[0]!=0)
						playerPos[0]--;
					break;
				case 65:
					if(playerPos[1]!=0)
						playerPos[1]--;
					break;
				case 83:
					if(playerPos[0]!=4)
						playerPos[0]++;
					break;
				case 68:
					if(playerPos[1]!=4)
						playerPos[1]++;
					break;
				case 37:
					if(shoot(playerPos, wumPos, "left"))
						won = true;
					arrows--;
					break;
				case 38:
					if(shoot(playerPos, wumPos, "up"))
						won = true;
					arrows--;
					break;
				case 39:
					if(shoot(playerPos, wumPos, "right"))
						won = true;
					arrows--;
					break;
				case 40:
					if(shoot(playerPos, wumPos, "down"))
						won = true;
					arrows--;
					break;

			}
			if(won){
				System.out.println("You won!");
				break;
			}

			if(samePos(playerPos, batPos)){
				System.out.println("WOOOSH");
				playerPos = randPos();
			}

			if(lastPos[0]!=playerPos[0]||lastPos[1]!=playerPos[1]){
				System.out.println("Moved to ("+(1+playerPos[1])+", "+(5-playerPos[0])+")");
			}
			for(int i=-1;i<2;i++){
				for(int j=-1;j<2;j++){
					temp[0]=playerPos[0]+i;
					temp[1]=playerPos[1]+j;
					if(samePos(temp,wumPos))
						System.out.println("You smell a wumpus");
					if(samePos(temp,pitPos))
						System.out.println("You feel a breeze");
					if(samePos(temp,batPos))
						System.out.println("You hear flapping");
				}
			}



			grid.setColor(lastPos[0],lastPos[1],Color.pink);
			grid.setColor(playerPos[0],playerPos[1],Color.red);
			code = 0;
			System.out.println();
		}
		if(won)
			grid.setColor(playerPos[0],playerPos[1],new Color(255,215,0));
		else
			grid.setColor(playerPos[0],playerPos[1], Color.black);



	}
	public static boolean samePos(int[] play, int[] thing){
		if(play[0] ==thing[0]&&play[1]==thing[1])
			return true;
		else return false;
	}

	public static int[] randPos(){
		Random rand = new Random();
		int[] temp = {rand.nextInt(5),rand.nextInt(5)};
		return temp;
	}

	public static boolean hasLost(int[] p, int[] w, int[] pit){
		if(samePos(p, w))
			return true;
		if(samePos(p, pit))
			return true;
		return false;
	}

	public static boolean shoot(int[] p, int[] w, String dir){
		int[] shootPos = {p[0],p[1]};
		switch(dir){
			case "left":
				shootPos[1]--;
				break;
			case "right":
				shootPos[1]++;
				break;
			case "up":
				shootPos[0]--;
				break;
			case "down":
				shootPos[0]++;
				break;
		}
		if(samePos(shootPos,w))
			return true;
		return false;
	}



}
