/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_ex1_int;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.x509.IPAddressName;

/**
 *
 * @author LeHoa
 */
public class Client {
    
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            //gui ma sinh vien + code
            byte[] dataseding = ";B16DCCN151;100".getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(dataseding, dataseding.length, InetAddress.getByName("localhost"), 1107);
            datagramSocket.send(datagramPacket);
            System.out.println("Gui thanh cong");
            
            //Nhan chui ip + 2 so a,b
            byte[] data = new byte[1024];
            datagramPacket = new DatagramPacket(data, data.length);
            datagramSocket.receive(datagramPacket);
            System.out.println("nhan thanh cong");
            
            //Xu ly chuoi nhan duoc
            String str = new String(datagramPacket.getData()).trim();
            System.out.println(str);
            
            String str1[] = str.split(";");
            String str2[] = str1[1].split(",");
            System.out.println(str2[0]);
            
            int a = Integer.parseInt(str2[0]);
            int b = Integer.parseInt(str2[1]);
            System.out.println(a);
            
            int uc = UCLN(a, b);
            int bc =  BCNN(a, b);
            int t =  a+b;
            int tich =a*b;
            
            String ans = ""+str1[0]+";"+uc+","+bc+","+t+","+tich;
            System.out.println(ans);
            
            //gui tra kq
            byte[] datasen = ans.getBytes();
            datagramPacket = new DatagramPacket(datasen, datasen.length, InetAddress.getByName("localhost"), 1107);
            datagramSocket.send(datagramPacket);
            System.out.println("gui thanh cong");

            
            
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int UCLN(int a, int b){
        while(a!=b){
            if(a>b){
                a=a-b;
            }
            else{
                b=b-a;
            }
            
        }
        return a;
    }
    
    public static int BCNN(int a, int b){
        return (a*b)/UCLN(a,b);
    }
    
    
}
