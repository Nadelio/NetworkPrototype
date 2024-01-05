import java.io.*;
import java.net.*;

public class ServerSide
{
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInStream;
    DataOutputStream dataOutStream;
    public ServerSide()
    {
        try
        {
            System.out.println("Server Started");
            serverSocket = new ServerSocket(6);
            socket = serverSocket.accept();
            System.out.println(socket);
            System.out.println("CLIENT CONNECTED");
            dataInStream = new DataInputStream(socket.getInputStream());
            dataOutStream = new DataOutputStream(socket.getOutputStream());
            ServerChat();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main (String as[])
    {
        new ServerSide();
    }

    public void ServerChat() throws IOException
    {
        String string, string1;
        do
        {
            string = dataInStream.readUTF();
            System.out.println("Client Message:"+string);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            string1 = br.readLine();
            dataOutStream.writeUTF(string1);
            dataOutStream.flush();
        }
        while(!string1.equals("bye"));
    }
}

// testing some things, this partial code is from: https://stackoverflow.com/questions/28308584/connecting-two-computers-for-client-server-communication-in-java