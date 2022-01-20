package task10.CS;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserClient {
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private ObjectOutputStream objectout;
    private ObjectInputStream objectin;
public UserClient() throws UnknownHostException, IOException{
    socket=new Socket("127.0.0.1",9000);//�ػ���ַ
    in=socket.getInputStream();//������
    out=socket.getOutputStream();//�����
    objectout=new ObjectOutputStream(out);
    objectin=new ObjectInputStream(in);
}
public void SendMessage(To to) throws IOException{
    objectout.writeObject(to);
    objectout.reset();
    objectout.flush();
}
public To ReceiveMessage() throws ClassNotFoundException, IOException{
    To rTo = null ;
    Object obj = objectin.readObject();//���ܷ��������͵�to��Ϣ
    if(obj == null)
        rTo = null ;
    else
        rTo= (To)obj ;
    return rTo;
}

public void close() {
    try {
        if (out != null)
            out.close();
        if (in != null)
            in.close();
        if (socket != null)
            socket.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}