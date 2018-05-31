import java.awt.*;
import java.util.Scanner;
import java.util.Random;


public class HuntTheWumpus{
	public static void main(String[] args){
		int code = 0, lastCode = 0;
		int[] playerPos = randPos();
		int[] lastPos = new int[2];
		int[] wumPos=randPos(), batPos=randPos(), pitPos=randPos(), arrPos=randPos(), temp = new int[2];
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
			}

			if(samePos(playerPos, batPos))
				playerPos = randPos();

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
		grid.setColor(playerPos[0],playerPos[1],Color.black);



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

}
