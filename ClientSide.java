import java.io.*;
import java.net.*;

public class ClientSide
{
    Socket socket;
    DataInputStream dataIn;
    DataOutputStream dataOut;
    public ClientSide()
    {
        try
        {
            String servername = "localhost";
            socket = new Socket(servername, 6);
            System.out.println(socket);
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
            ClientChat();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void ClientChat() throws IOException
    {
            BufferedReader bReader= new BufferedReader(new InputStreamReader(System.in));
            String string;
            do
            {
               string = bReader.readLine();
               dataOut.writeUTF(string);
               dataOut.flush();
               System.out.println("Server Message:" + dataIn.readUTF());
            }
            while(!string.equals("stop"));
    }
    public static void main(String as[])
    {
        new ClientSide(); 
    }
}

// testing some things, this partial code is from: https://stackoverflow.com/questions/28308584/connecting-two-computers-for-client-server-communication-in-java
// setup port forwarding on port 6, get an online server, get the ip of said server, input ip into code.