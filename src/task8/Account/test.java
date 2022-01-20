package task8.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
    FileReader fileReader = new FileReader("src/task8/Account/ID.txt");
    BufferedReader bufferedReader=new BufferedReader(fileReader);
    long ID=(long) 0;
    String line=bufferedReader.readLine();
    FileWriter fileWritter = new FileWriter("src/task8/Account/ID.txt",false);
    if(line==null){
        //如果空文件
        ID=100001;
    }else{
        ID=Long.parseLong(line);
    }
    String nextID=Long.toString(ID+1);
    fileWritter.write(nextID);
    bufferedReader.close();
    fileWritter.close();
    System.out.println(ID);
      /*  long NextID=ID+1;
            FileOutputStream fos = new FileOutputStream("src/task8/Account/ID.txt") ;
			ObjectOutputStream oos = new ObjectOutputStream(fos) ;
			oos.writeObject(Long.toString(NextID));
            oos.close();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }finally{
        ois.close();
    }*/
        
    }
    
    
}
