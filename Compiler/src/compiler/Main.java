package compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static ArrayList codeToken = new ArrayList();
    public static ArrayList valuePart = new ArrayList();

    public static void main(String[] args) throws IOException
    {
        Filing f=new Filing("code","code");
        BreakAndClassify cl=new BreakAndClassify(f.getCodelines());
        ArrayList<Token> a=cl.wordBreaker();
        String s="";
        for (int i = 0; i < a.size(); i++)
        {
            s=s+a.get(i).toString()+"\r\n";
        }

        f=new Filing("tokens","tokens");
        f.write(s);
        String L="";
        for (int i = 0; i < a.size(); i++)
        {
            L=L+a.get(i).toString1()+"\r\n";
        }

        f=new Filing("Lexical Tokens","tokens");
        f.write(L);
        File file = new File(".\\files\\tokens\\tokens.txt");
        Scanner scan = new Scanner(file);
        while(scan.hasNext())
        {
            codeToken.add(scan.nextLine());
        }
        codeToken.add("$");
        File vallueToken = new File(".\\files\\tokens\\Lexical Tokens.txt");
        Scanner scan1 = new Scanner(vallueToken);
        while(scan1.hasNext())
        {
            valuePart.add(scan1.nextLine());
        }
        System.out.println(codeToken);
//        System.out.println(valuePart);
        SyntaxAnalyzer z = new SyntaxAnalyzer();
        z.S();
    }
}
