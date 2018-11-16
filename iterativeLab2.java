//Ryan Namgung and Jinhoo Bong
import java.util.*;
import java.io.*;

public class iterativeLab2
{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		int numOfInts = input.nextInt();
		int[] arrayOfInts = new int[numOfInts + 1];

		int goalSum = input.nextInt();

		input.nextLine();

		int i = 0;
		while(input.hasNextInt()){
			int num = input.nextInt();
			arrayOfInts[i] = num;
			i++;
		}

		System.out.println(isSubsetSum(arrayOfInts, numOfInts, goalSum));
	}

	public static void print2DArray(int[][] subset) {
		for(int i = 0; i <= subset.length-1; i++) {
	    	for(int j = 0; j <= subset[i].length -1; j++) {
	    		System.out.print(subset[i][j]);
	    	}
    		System.out.println();
    	}
	}


	public static ArrayList<Integer> isSubsetSum(int[] listOfInts, int n, int goal){

		ArrayList<Integer> paths = new ArrayList<Integer>();
		int[][] subset = new int[n+1][goal+1];

	  
	    for (int i = 1; i <= goal; i++){
	      subset[0][i] = 0;
	     }

	    for (int i = 0; i <= n; i++){
	      subset[i][0] = 1;
	    }

	    System.out.println();
	  
	     for (int i = 1; i <= n; i++){
	       for (int j = 1; j <= goal; j++){

	         if(j < listOfInts[i-1]){
	         	subset[i][j] = subset[i-1][j];
	         }else if (j >= listOfInts[i-1]){
	         		if(subset[i-1][j] == 1){
	          	 		subset[i][j] = 1;

	          	 	}else if(subset[i - 1][j-listOfInts[i-1]] == 1){
	          	 		subset[i][j] = 1;
		       		}
	   	   	}
	 	  }
	 	}

	 	System.out.println("FINAL Full Array:");
	 	print2DArray(subset);

	 	System.out.println();

	 	// Trace the final full array for the path
	 	// n is number of elements (y axis)
	 	// goal is the sum (x axis)
	 	for(int i = n; i >= 1; i--){
	 		for(int j = goal; j >= 0; j--){
	 			if(subset[i][j] == 1) { //&& j != 0){
	 				// If we don't choose the element, 
	 				if(subset[i-1][j] == 1){
	 					//paths.add(listOfInts[i]);
	 					i--;
	 					j++; // Counteract the j--

	 				// If we do choose the element
	 				}else if(subset[i-1][j-listOfInts[i-1]] == 1){
	 					paths.add(listOfInts[i-1]);

	 					j = Math.max(0, j-listOfInts[i-1] + 1); // Add 1 to counteract the j--

	 					i--; // Decrement i after I use it for calculation

	 				}
	 			}
	 			if(i == 0)
	 				break;
	 		}
	 	}

	 	System.out.println(paths);

	 	int temp = 0;
	 	for(int i = 0; i < paths.size(); i++){
	 		temp += paths.get(i);
	 	}

	 	if(temp == goal){
	 		System.out.println("POSSIBLE");
	 		return paths;
		 }else{
		 	System.out.println("IMPOSSIBLE");
		 	return null;
		 }
	}
}
