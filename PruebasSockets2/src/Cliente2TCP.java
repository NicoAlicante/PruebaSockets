import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente2TCP 
{
	public static void main(String[] args)  throws IOException 
	{
	    Socket socketCliente = null;
	    BufferedReader entradaSocket = null;
	    PrintWriter salidaSocket = null;


	    try {
	      socketCliente = new Socket("localhost", 4444);

	      System.out.println("Cliente conectado: "+socketCliente);
	      
	      entradaSocket = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	      salidaSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
	
	    } catch (IOException e) 
	    {
		System.err.println("No puede establer canales de E/S para la conexión");
	        System.exit(-1);
	    }
	    Scanner teclado = new Scanner(System.in);

	    String lineaServidor, lineaTeclado;

	    try 
	    {
	      do 
	      {
	    	  System.out.print("Introduce el mensaje a enviar: ");
	    	  
	    	  lineaTeclado = teclado.nextLine();	        

		        salidaSocket.println(lineaTeclado);
		        lineaServidor = entradaSocket.readLine();
		        System.out.println("Respuesta servidor: '" + lineaServidor+"'");
	      }while (!lineaTeclado.equals("Adios"));
	    } catch (IOException e) {
	    	System.out.println("IOException: " + e.getMessage());
	    }


	    salidaSocket.close();
	    entradaSocket.close();
	    teclado.close();
	    socketCliente.close();
	    
	    System.out.println("Finalizo");
	}

}
