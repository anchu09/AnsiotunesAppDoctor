package BITalino;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;


import db.pojos.Patient;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BitalinoDemo {

    public static Frame[] frame;
    public static void RunBita(Patient pcurrent) {
    	String s1="";
    	String s2="";
    	
    	
        BITalino bitalino = null;
        try {
            bitalino = new BITalino();
            // Code to find Devices
            //Only works on some OS
            Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);

            //You need TO CHANGE THE MAC ADDRESS
            //You should have the MAC ADDRESS in a sticker in the Bitalino
            String macAddress = "98:D3:31:FD:3B:92";
            
            //Sampling rate, should be 10, 100 or 1000
            int SamplingRate = 100;
            bitalino.open(macAddress, SamplingRate);

            // Start acquisition on analog channels A2 and A6
            // For example, If you want A1, A3 and A4 you should use {0,2,3}
            int[] channelsToAcquire = {1,2};
            bitalino.start(channelsToAcquire);

            //Read in total 10000000 times
            for (int j = 0; j < 100; j++) {

                //Each time read a block of 10 samples 
                int block_size=10;
                frame = bitalino.read(block_size);

               // System.out.println("size block: " + frame.length);
                
                s1+="size block: " + frame.length+"\n";

                //Print the samples
                for (int i = 0; i < frame.length; i++) {
                    
                	s2+=(j * block_size + i) + " seq: " + frame[i].seq + " "
                            + frame[i].analog[0] + " "
                            + frame[i].analog[1] + " " + "\n";
                         
                	
                	/*System.out.println((j * block_size + i) + " seq: " + frame[i].seq + " "
                            + frame[i].analog[0] + " "
                            + frame[i].analog[1] + " "
                    //  + frame[i].analog[2] + " "
                    //  + frame[i].analog[3] + " "
                    //  + frame[i].analog[4] + " "
                    //  + frame[i].analog[5]
                    );*/

                }
                s1=s1+s2;
                
            }

            //System.out.println(s1);
            
            //crear un archivo //ojo archivos con el mismo nombre se sobreescriben
            
            File f= new File("../Ansiotunes/reports/"+pcurrent.getName()+"_"+pcurrent.getID()+"_"+LocalDate.now()+".txt");
            
            
            try {
    			f.createNewFile();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
            
            //imprimir s1  en el archivo
            PrintWriter p= new PrintWriter(f);
            p.println(s1);
            p.close();
            
            
            //guardar, cerrar el archivo ?????
            
            
            //stop acquisition
            bitalino.stop();
        } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (BITalinoException ex) {
                Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
public static void main(String[] args) {
    	
    	String s1="";
    	String s2="";
    	
    	
        BITalino bitalino = null;
        try {
            bitalino = new BITalino();
            // Code to find Devices
            //Only works on some OS
            Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);

            //You need TO CHANGE THE MAC ADDRESS
            //You should have the MAC ADDRESS in a sticker in the Bitalino
            String macAddress = "98:D3:41:FD:4E:E8";
            
            //Sampling rate, should be 10, 100 or 1000
            int SamplingRate = 100;
            bitalino.open(macAddress, SamplingRate);

            // Start acquisition on analog channels A2 and A6
            // For example, If you want A1, A3 and A4 you should use {0,2,3}
            int[] channelsToAcquire = {1,2};
            bitalino.start(channelsToAcquire);

            //Read in total 180 times
            
            //f=100, block=100. T=0.01. cada bloque es 1s. tomar muestras durante 180s, j=180. j son los segundos. 
            for (int j = 0; j < 180; j++) {

                //Each time read a block of 100 samples 
                int block_size=100;
                frame = bitalino.read(block_size);

               // System.out.println("size block: " + frame.length);
                
                //s1+="size block: " + frame.length+"\n";
                

                //Print the samples
                for (int i = 0; i < frame.length; i++) {
                    
                	s2+=(j * block_size + i) + " seq: " + frame[i].seq + " "
                            + frame[i].analog[0] + " "
                            + frame[i].analog[1] + " " + "\n";
                         
                    
                	
                	/*System.out.println((j * block_size + i) + " seq: " + frame[i].seq + " "
                            + frame[i].analog[0] + " "
                            + frame[i].analog[1] + " "
                    //  + frame[i].analog[2] + " "
                    //  + frame[i].analog[3] + " "
                    //  + frame[i].analog[4] + " "
                    //  + frame[i].analog[5]
                    );*/

                }
                s1=s2;
                
                
            }

            System.out.println(s1);
            
            //crear un archivo //ojo archivos con el mismo nombre se sobreescriben
            
            File f= new File(LocalDate.now()+".txt");
            
            
            try {
    			f.createNewFile();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
            
            //imprimir s1  en el archivo
            PrintWriter p= new PrintWriter(f);
            p.println(s1);
            p.close();
            
            
            //guardar, cerrar el archivo ?????
            
            
            //stop acquisition
            bitalino.stop();
        } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (BITalinoException ex) {
                Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}