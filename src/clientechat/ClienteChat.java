
package clientechat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteChat {

    final static int PORT = 40080;
    final static String HOST = "192.168.103.93";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket sk = new Socket(HOST,PORT);
            
            enviarMensajesAlServidor(sk);
        } catch (IOException ex) {
            Logger.getLogger(ClienteChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void enviarMensajesAlServidor(Socket sk){
        
        OutputStream os = null;
        InputStream is = null;
        try {
            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            Scanner sc = new Scanner(System.in);
            String linea;
            while(true){
                System.out.println("Escribe algo: ");
                 linea = sc.nextLine();
                bw.write(linea);
                bw.newLine();
                bw.flush();
                
                linea = br.readLine();
                System.out.println("El servidor dice: " + linea);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteChat.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(os != null) try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(ClienteChat.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        
    }
    
}
