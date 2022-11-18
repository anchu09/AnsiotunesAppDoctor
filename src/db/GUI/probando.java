package db.GUI;

import java.io.IOException;
import java.util.ArrayList;

public class probando {

	
	public static void main(String[] args) {
		
		String s1="../Ansiotunes/reports/s_s_18555_2022-10-09.txt";
		
		ArrayList<Integer>d1=null;
		
		try {
			d1=signalProc.readData.readEda(s1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(d1.size());
		d1=ConsultDataController.leerfichero(s1,"eda");
		System.out.println(d1.size());
	}
}
