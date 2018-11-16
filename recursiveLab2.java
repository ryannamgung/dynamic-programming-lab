//Ryan Namgung and Jinhoo Bong
import java.util.*;
import java.io.*;

public class recursiveLab2
{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		int numOfInts = input.nextInt();
		ArrayList<Integer> arrayOfInts= new ArrayList<Integer>();
		int goalSum = input.nextInt();

		input.nextLine();

		while(input.hasNextInt()){
			int num = input.nextInt();
			arrayOfInts.add(num);
		}

		ArrayList<Integer>[][] subset = new ArrayList[numOfInts + 1][goalSum + 1];

		ArrayList<Integer> randomArrayList = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			randomArrayList.add((int)(Math.random()*10)+1);
		}

		for(int i = 0; i <= subset.length - 1; i++){
			for(int j = 0; j <= subset[i].length - 1; j++){
				subset[i][j] = randomArrayList;
			}
		}

		ArrayList<Integer> val = isSubsetSum(subset, arrayOfInts, numOfInts, goalSum, randomArrayList);

		int temp = 0;
		for(int i = 0; i < val.size(); i++){
			temp += val.get(i);
		}

		if(temp == goalSum){
			System.out.println("POSSIBLE");
			System.out.println(val);
		}else{
			System.out.println("IMPOSSIBLE");
			System.out.println("null");
		}
	}


	public static ArrayList<Integer> isSubsetSum(ArrayList<Integer>[][] numOfInts, ArrayList<Integer> arrayOfInts, 
		int n, int goal, ArrayList<Integer> randomArrayList){
		if(numOfInts[n][goal] != randomArrayList) {
			return numOfInts[n][goal];
		}

		if(goal == 0){
			ArrayList<Integer> myList = new ArrayList<Integer>();
			numOfInts[n][goal] = myList;
			return myList;
		}

		int rowShift = n - 1;
		if(rowShift < 0) {
			numOfInts[n][goal] = null;
			return null;
		}

		ArrayList<Integer> s1 = isSubsetSum(numOfInts, arrayOfInts, rowShift, goal, randomArrayList);
		if(s1 != null && s1 != randomArrayList){
			numOfInts[n][goal] = s1;
			return s1;
		}

		int colShift = goal - arrayOfInts.get(n-1);
		if(colShift < 0) {
			numOfInts[n][goal] = null;
			return null;
		}
		
		ArrayList<Integer> s2 = isSubsetSum(numOfInts, arrayOfInts, rowShift, colShift, randomArrayList);
		if(s2 != null && s2 != randomArrayList){
			s2.add(arrayOfInts.get(n-1));
			numOfInts[n][goal] = s2;

			return s2;
		}

		numOfInts[n][goal] = null;
		return null;
	}
}
