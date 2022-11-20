package db.GUI;

import java.io.IOException;
import java.util.ArrayList;

public class probando {

	public static void main(String[] args) {
		/*
		 * //String s1="../AnsiotunesAppDoctor/reports/s_s_5447_2022-10-09.txt"; String
		 * s1="../AnsiotunesAppDoctor/reports/s_s_5447_2022-11-11.txt"; String
		 * s2="..\\AnsiotunesAppDoctor\\reports\\s_s_18555_2022-10-09.txt"; String
		 * s3="..\\AnsiotunesAppDoctor\\reports\\t_445_2022-11-11.txt"; String
		 * s4="..\\AnsiotunesAppDoctor\\reports\\t_445_2022-10-31.txt"; String
		 * s5="../AnsiotunesAppDoctor/reports/s_s_18555_2022-10-09.txt";
		 * 
		 * ArrayList<Integer>d1=null;
		 * 
		 * try { d1=signalProc.readData.readEda(s5); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * System.out.println(d1.size());
		 * d1=ConsultDataController.leerfichero(s1,"eda");
		 * System.out.println(d1.size());
		 */

		// String s1="../AnsiotunesAppDoctor/reports/s_s_18555_2022-10-09.txt";

		String s1 = "..\\AnsiotunesAppDoctor\\reports\\h_h_37418_2022-10-09.txt";

		ArrayList<Integer> d2 = ConsultDataController.leerfichero(s1, "eda");
		System.out.println(d2.size());

		String s2 = "..\\AnsiotunesAppDoctor\\reports\\s_s_18555_2022-10-09.txt";
		ArrayList<Integer> d1 = null;

		try {
			d1 = signalProc.readData.readEda(s1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(d1.size());
		ArrayList<Integer> d3 = ConsultDataController.leerfichero(s1, "eda");
		System.out.println(d3.size());
	}
}
