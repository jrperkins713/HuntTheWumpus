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
		while(samePos(playerPos,wumPos)||samePos(playerPos, batPos)||samePos(playerPos, pitPos))
			playerPos = randPos();
		while(samePos(arrPos, pitPos)||samePos(arrPos,batPos))
			arrPos = randPos();

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
					else wumPos = wumpusMove(wumPos);
					arrows--;
					break;
				case 38:
					if(shoot(playerPos, wumPos, "up"))
						won = true;
					else wumPos = wumpusMove(wumPos);
					arrows--;
					break;
				case 39:
					if(shoot(playerPos, wumPos, "right"))
						won = true;
					else wumPos = wumpusMove(wumPos);
					arrows--;
					break;
				case 40:
					if(shoot(playerPos, wumPos, "down"))
						won = true;
					else wumPos = wumpusMove(wumPos);
					arrows--;
					break;

			}
			if(won) break;
			if(arrows==0&&arrPos!=null){
				System.out.println("You ran out of arrows. Your poor Wumpus hunting skills cause you to die of embarrassment");
				break;
			}
			if(samePos(playerPos, batPos)){
				System.out.println("WOOOSH");
				playerPos = randPos();
			}
			if(arrPos!=null && samePos(playerPos, arrPos)){
				System.out.println("You found the arrow of a previous hunter");
				arrows++;
				arrPos = null;
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
			System.out.println("You have "+arrows+" arrows");



			grid.setColor(lastPos[0],lastPos[1],Color.pink);
			grid.setColor(playerPos[0],playerPos[1],Color.red);
			while(samePos(wumPos,batPos))
				wumPos = wumpusMove(wumPos);
			code = 0;
			System.out.println();
		}
		if(won){
			grid.setColor(playerPos[0],playerPos[1],new Color(255,215,0));
			System.out.println("You killed the wumpus! You will forever more be known for your legendary Wumpus hunting skills");
		}
		else{
			grid.setColor(playerPos[0],playerPos[1], Color.black);
			System.out.println("You have met with a terrible fate, haven't you?");
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

	public static boolean hasLost(int[] p, int[] w, int[] pit){
		if(samePos(p, w)){
			System.out.println("Oh no! You have been eaten by the Wumpus");
			return true;
		}
		else if(samePos(p, pit)){
			System.out.println("You fell into a bottomless pit");
			return true;
		}
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

	public static int[] wumpusMove(int[] pos){
		Random rand = new Random();
		int[] newPos = {-1,-1};
		while(newPos[0]<0||newPos[0]>4||newPos[1]<0||newPos[1]>4){
			newPos[0] = pos[0];
			newPos[1] = pos[1];
			double chance = rand.nextDouble();
			if(chance<.2){
				newPos[0] = pos[0]+1;
			}
			else if(chance<.4){
				newPos[0] = pos[0]-1;
			}
			else if(chance<.6){
				newPos[1] = pos[1]+1;
			}
			else if(chance<.8){
				newPos[1] = pos[1]-1;
			}
			//else, wumpus stays in square
		}
		return newPos;
	}




}
