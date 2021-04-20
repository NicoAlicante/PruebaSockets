import java.net.*;
import java.io.*;

public class ClienteUDP {

  // Los argumentos proporcionan el mensaje y el nombre del servidor
  public static void main(String args[]) {
	  
	  args=new String[2];
	  args[0]="Esto es una pruebecita de UDP";
	  args[1]="localhost";

    try {
      DatagramSocket socketUDP = new DatagramSocket();
      byte[] mensaje = args[0].getBytes();
      InetAddress hostServidor = InetAddress.getByName(args[1]);
      int puertoServidor = 6789;

      // Construimos un datagrama para enviar el mensaje al servidor
      DatagramPacket peticion =
        new DatagramPacket(mensaje, args[0].length(), hostServidor,
                           puertoServidor);

      System.out.println("Enviando mensaje ");
      // Enviamos el datagrama
      socketUDP.send(peticion);
      socketUDP.send(peticion);
      socketUDP.send(peticion);
      System.out.println("Enviado mensaje!");
      
      System.out.println("Puerto usado: "+socketUDP.getPort());
      
      System.out.println("Puerto usado: "+socketUDP.getLocalPort());
    

      // Construimos el DatagramPacket que contendrá la respuesta
      byte[] bufer = new byte[1000];
      DatagramPacket respuesta =
        new DatagramPacket(bufer, bufer.length);
      

      System.out.println("Esperando respuesta");
      socketUDP.receive(respuesta);

      // Enviamos la respuesta del servidor a la salida estandar
      System.out.println("Respuesta: " + new String(respuesta.getData()));

      // Cerramos el socket
      socketUDP.close();

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
}