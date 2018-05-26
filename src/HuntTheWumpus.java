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
		grid.setColor(playerPos[1],playerPos[0],Color.red);
		while(true){
			lastPos[0] = playerPos[0];
			lastPos[1] = playerPos[1];
			while(lastCode == code){

				code = grid.getCode();
				//program was going to fast. This is some slow down code
				int durp;
					for(int i=0;i<1000;i++)
				durp = 1;

			}
			System.out.println("Start");
			lastCode = code;
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
			//program was going to fast. This is some slow down code
			int durp;
			for(int i=0;i<1000;i++)
				durp = 1;



			if(lastPos[0]!=playerPos[0]||lastPos[1]!=playerPos[1]){
				System.out.println("Moved to ("+(1+playerPos[1])+", "+(5-playerPos[0])+")");
				grid.setColor(playerPos[0],playerPos[1],Color.red);
				grid.setColor(lastPos[0],lastPos[1],Color.pink);
			}
			for(int i=-1;i<2;i++){
				for(int j=-1;j<2;j++){
					temp[0]=playerPos[0]+i;
					temp[1]=playerPos[1]+j;
					System.out.println("Pass");
					if(samePos(temp,wumPos))
						System.out.println("You smell a wumpus");
				}
			}



		}



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

}
