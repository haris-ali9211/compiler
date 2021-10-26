package compiler;

import java.io.*;
import java.util.ArrayList;

public class Filing
{
    private String path;
    private FileReader fr;
    private BufferedReader br;
    private BufferedWriter bw;
    private FileWriter fw;
    private String message;
    private ArrayList<String> codelines;


    public Filing(String fileName,String FolderName) throws IOException
    {
        this.codelines = new ArrayList<>();
        this.path="files\\"+FolderName;
        try
        {
            File f=new File(this.path);
            if(!f.exists())
                if(!f.mkdirs())
                    System.out.println("file was not created");
            this.path=this.path+"\\"+fileName+".txt";
            f=new File(this.path);
            f.createNewFile();
        }
        catch(Exception e)
        {
            System.out.println("file not created");
        }
        this.read();
    }
    public Filing(String name) throws IOException
    {
        this.codelines = new ArrayList<>();
        this.path="files\\"+name;
        try
        {
            File f=new File(this.path);
            if(!f.exists())
                if(!f.mkdirs())
                    System.out.println("file was not created");
            this.path=this.path+"\\"+name+".txt";
            f=new File(this.path);
            f.createNewFile();
        }
        catch(Exception e)
        {
            System.out.println("file not created");
        }
        this.read();
    }
    private void clear() throws IOException
    {
        this.write("");
    }
    public void write(String toWrite) throws IOException
    {
        openWriter();
        bw.write(toWrite);
        this.closeWriter();
    }
    private void openReader() throws FileNotFoundException
    {
        fr = new FileReader(path);
        br = new BufferedReader(fr);
    }
    private void openWriter() throws IOException
    {
        fw = new FileWriter(path);
        bw = new BufferedWriter(fw);
    }
    public void read() throws FileNotFoundException, IOException
    {
        this.openReader();
//        message="";
        String temp="";
        while((temp = br.readLine()) != null)
        {
            this.codelines.add(temp);
//            message=message+temp;
        }
        this.closeReader();
//        return message;
    }
    private void closeWriter() throws IOException
    {
        this.bw.close();
        this.fw.close();
    }
    private void closeReader() throws IOException
    {
        this.br.close();
        this.fr.close();
    }

    public ArrayList<String> getCodelines()
    {
        return codelines;
    }

}
