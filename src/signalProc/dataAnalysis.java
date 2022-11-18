package signalProc;

import java.util.ArrayList;
import java.util.List;

public class dataAnalysis {
	
	//no se usará varianza, solo media. 
/*
	public static double[] variance(List<Integer> data, double[] mean, int segundos) {

		
		 

		double n = segundos 

		int size = data.size();

		int var = 0;
		int number = 0;
		int j = 0;

		double[] varLista = new double[(int) Math.ceil(size / n)];

		for (int i = 0; i < size; i++) {
			if (i>60 ||i<80)System.out.println("data: "+data.get(i)+"mean: "+mean[j]); 

			var += Math.pow(data.get(i) - mean[j], 2);
			number++;
			if (number % n == 0 || number == size) {
				varLista[j] = var / n;
				var = 0;
				j++;

			}

		}

		return varLista;

	}*/

	public static double[] mean(ArrayList<Integer> data) {

		double n = 30 / 0.01; //cuantas muestras coger en 30s. 
		int size = data.size();
		int media = 0;
		int number = 0;
		int j = 0;
		double[] mediaLista = new double[(int) Math.ceil(size / n)];
		for (int i = 0; i < size; i++) {

			media += data.get(i);
			number++;
			if (number % n == 0 || number == size) {
				mediaLista[j] = media / n;
				j++;
				media = 0;

			}

		}

		return mediaLista;

	}

	/*
	 * 

	 */
	public static int score(ArrayList<Integer> eda) {
		double[]mean=mean(eda);

		int size = mean.length;

		int noSoundj = (int) Math.ceil(90 / 30);
		int Soundj = size - noSoundj;
		double minNoSound;
		double minSound;
		int score = 0;
		double percent = 0;

		double[] meanNoSound = new double[noSoundj];
		double[] meanSound = new double[Soundj];

		for (int p = 0, i = 0; i < size; i++) {
			if (i < noSoundj) {
				meanNoSound[i] = mean[i];

			} else {

				meanSound[p] = mean[i];
				p++;
			}

		}

		minNoSound = getMin(meanNoSound);
		minSound = getMin(meanSound);
		System.out.println("nosound: "+minNoSound+"sound: "+minSound);
		percent = 100-(minSound * 100) / minNoSound; // if minSound is higher, this variable will not be used.´
		System.out.println("percent: "+percent);

		if (minSound > minNoSound) // Not Effective
			score = 1;
		else if (percent >= 0 && percent <= 1) // Barely effective
			score = 2;
		else if (percent > 1 && percent <= 3) // Little effective but there are significant changes.
			score = 3;
		else if (percent > 3 && percent <= 5) // Effective
			score = 4;
		else if (percent > 5) // Very effective
			score = 5;

		return score;
	}

	public static double getMin(double arr[]) {
		int n = arr.length;
		
		double res = arr[0];
		for (int i = 1; i < n; i++)
			res = Math.min(res, arr[i]);

		return res;

	}

}