//Código obtenido de http://www.it.uc3m.es/celeste/docencia/cr/2003/PracticaSocketsTCP/

import java.net.*;
import java.io.*;


public class ServidorTCP 
{
	  public static void main(String[] args) throws IOException 
	  {

	    ServerSocket socketServidor = null;
	    try {
	      socketServidor = new ServerSocket(4444);
	    } catch (IOException e) {
	      System.out.println("No puede escuchar en el puerto: " + 4444);
	      System.exit(-1);
	    }

	    Socket socketCliente = null;
	    BufferedReader entradaSocket = null;
	    PrintWriter salidaSocket = null;

	    int ConexionesdeClientes=0;
	    do {
		    System.out.println("Escuchando: " + socketServidor);
		    
		    
		    
		    socketCliente=socketServidor.accept();
		    ConexionesdeClientes++;
		    try {
	
		      System.out.println("Conexión aceptada: "+ socketCliente);
		      entradaSocket = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	
		      salidaSocket = new PrintWriter(new BufferedWriter(new 
			  OutputStreamWriter(socketCliente.getOutputStream())),true);
		      
		      String mensaje;
		      do{  
		    	  
		        mensaje = entradaSocket.readLine();//Se lee del cliente
		        
		        
				System.out.println("Recibido: "+ mensaje);
				boolean permitido = false;
				String []contraseñas = {"123","Laclave","Passw"};
				for(int i = 0; i < contraseñas.length; i ++)
				{
					if(mensaje.equals(contraseñas[i]) )
					{
						permitido = true;
					}
				}
				if(permitido == true)
				{	
					salidaSocket.println("ok");//Se escribe en el cliente
				}else
					salidaSocket.println("Clave incorrecta");//Se escribe en el cliente
			
		      }while(!mensaje.equals("Adios"));
	
		    } catch (IOException e) {
		      System.out.println("IOException: " + e.getMessage());
		    }  
		    salidaSocket.close();
		    entradaSocket.close();
		    socketCliente.close();
	    
	    }while(ConexionesdeClientes<5);
	    
	    socketServidor.close();
	  }
	
}
