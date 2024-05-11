import java. util. *;
public class Autoassociator {
	private int weights[][];
	private int trainingCapacity;
	
	public Autoassociator(CourseArray courses) {
		int courses_number = courses.length();
		weights = new int[courses_number][courses_number];
		trainingCapacity = (int) (0.14 * weights.length);
	}
	
	public int getTrainingCapacity() {
		return trainingCapacity;
	
	public void training(int pattern[]) {
		if(pattern.length == weights.length && trainingCapacity > 0){
			int p;
			for(int i = 0; i < pattern.length; i++)
				for(int j = i+1; j< pattern.length; j++){
					p = pattern[i] * pattern[j];
					weights[i][j] += p;
					weights[j][i]+= p;
				}
			trainingCapacity--;
		}
	}
	
	public int unitUpdate(int neurons[]) {
		Random random = new Random();
		int ind = random.nextInt(neurons.length);
		unitUpdate(neurons,ind);
		return ind;
	}
	
	public void unitUpdate(int neurons[], int ind) {
		int sum = 0;
		for(int i = 0; i < neurons.length; i++) {
			sum += weights[ind][i] * neurons[i];
		}
		neurons[ind] = (sum >= 0) ? 1:-1;
	}
	
	public void chainUpdate(int neurons[], int steps) {
		for(;steps>0;steps--)
			unitUpdate(neurons);
	}
	
	public void fullUpdate(int neurons[]) {
		boolean bool = false;
		while(!bool){
			int[] duplicate = new int[neurons.length];
			System.arraycopy(neurons,0, duplicate, 0, neurons.length);
			unitUpdate(neurons);
			bool = java.util.Arrays.equals(duplicate,neurons);
		}
	}
}
