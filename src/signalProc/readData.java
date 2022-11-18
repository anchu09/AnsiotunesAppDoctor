package signalProc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class readData {
	
	//extraer datos en listas para luego hacer analisis de varianza y media para el baremo
	// e identificar picos R en otra clase (not yet)
	
	/*
	 * La extracción de los datos en lista permite extraer los datos del fichero en columna ECG, columna EDA, todo ello
	 * por espacios. Hasta el ECG hay 3 espacios, hasta el eda hay 4 espacios. Si se cambia el formato del fichero que se
	 * haga respetando los espacios. 
	 */
	  public static List<Integer> readEcg(String path) throws IOException  {
	        File doc = new File(path);

	        FileInputStream fi=null;
			try {
				fi = new FileInputStream(doc);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
	        InputStreamReader isr = new InputStreamReader(fi);
	        BufferedReader br = new BufferedReader(isr);
	        String texto = "";

	        List<Integer> ecg = new LinkedList<>();

	        for (String line = br.readLine(); line != null && !line.equals("") && !line.equals("\n"); line = br.readLine()) {
	            ecg.add(singleDataEcg(line));
	        }
	        
	        br.close();
	        isr.close();
	        fi.close();

	        return ecg;
	    }
	    
	  public static ArrayList<Integer> readEda(String path) throws FileNotFoundException, IOException {
	        File doc = new File(path);

	        FileInputStream fi = new FileInputStream(doc);
	        InputStreamReader isr = new InputStreamReader(fi);
	        BufferedReader br = new BufferedReader(isr);
	        String texto = "";

	       ArrayList<Integer> eda = new ArrayList<>();
	        
	        for (String line = br.readLine(); line != null && !line.equals("") && !line.equals("\n"); line = br.readLine()) {
	        	
	        	int number=singleDataEda(line);
	        	
	            eda.add(number);
	        	
	        }
	        
	        br.close();
	        isr.close();
	        fi.close();
	        return eda;
	    }
	  
	  public static void contador(String line) {
		  
		  for(int i=0;i<line.length();i++) {
			  
			  System.out.println("i="+i+" y el string es:"+line.substring(i,i+1));
		  }
		  
	  }
	        
	  public static int singleDataEda(String string) {
	        int len = string.length(); //no cuenta el 0. hola=> length 4
	        
	        int space = 0;
	        String number = "";

	        int i = 0;

	        while (i < len && space < 4) {
	            if (space == 3) {

	                number += string.substring(i, i + 1);

	            }
	            i++;
	            if (string.substring(i, i + 1).equals(" ")) {

	                space++;
	                i++;
	            }
	        }
	        int num=0;
	        try {
	             num = Integer.parseInt(number);
	        } catch (NumberFormatException e) {
	            System.out.println("he fallado en String:" + string + "y el char es: " + number);
	        }
	        return num;

	    }

	  public static int singleDataEcg(String string) {
	        int len = string.length(); //no cuenta el 0. hola=> length 4

	        int space = 0;
	        String number = "";

	        int i = 0;

	        while (i < len && space < 4) {
	        	
	            if (space == 3) {


	                number += string.substring(i, i + 1);

	            }
	            i++;
	            if (string.substring(i, i + 1).equals(" ")) {

	                space++;
	                i++;
	            }
	        }
	        int num=0;
	        try {
	             num = Integer.parseInt(number);
	        } catch (NumberFormatException e) {
	            System.out.println("he fallado en String:" + string + "y el char es: " + number);
	        }
	        return num;

	    }
	  
	  public static void main(String[] args) throws FileNotFoundException, IOException {
		  
		  String t = "../Ansiotunes2910v2/reports/t_445_2022-10-31.txt"; 
		  	//2022-10-20 2min, 1 min no, 1min music
		  	//2022-10-21 3min, 1.5 no, 1.5 music.

	        List<Integer> lista = readEda(t);
	        

	        System.out.println(Arrays.toString(lista.toArray()));
	        
	       //List<Integer> lista2 = readEcg(t);

	       // System.out.println(Arrays.toString(lista2.toArray()));
	        
	        
	       //double[]media=dataAnalysis.mean(lista);  
	       //double[]varianza=dataAnalysis.variance(lista, media,30); variance is not going to be used. 
	       
	     /*  System.out.println(media.length);
	        
	        
	        for(int i=0;i<media.length;i++) {
	        	
	        	System.out.println(media[i]);
	        }
	        
	        System.out.println(dataAnalysis.score(media));
	        /*
	        System.out.println("separacion \n");
	        for(int i=0;i<varianza.length;i++) {
	        	if(i==60)System.out.println("MUSIC \n \n");
	        	System.out.println(varianza[i]);
	        }
	        
	        /*
	        File f = new File("C:\\CEU\\5º Curso\\Telemedicina\\hola.txt");
	        f.createNewFile();
	        PrintWriter p=new PrintWriter(f);
	        
	       for(int i=0;i<lista2.size();i++) {
	        	
	        	p.println(lista2.get(i));
	        }
	        
	        p.close();*/
	}
}