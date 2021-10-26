package compiler;

import java.io.IOException;
import java.util.ArrayList;

public class TokenGenerator 
{
    private ArrayList<Token> tokenList;
    private String[] tokens;
    private Filing f;
    private String path="tokens";
    
    public TokenGenerator(String[] tokens) throws IOException
    {
        this.tokens=tokens;
        tokenList=new ArrayList();
        f=new Filing(path);
    }
    public void add(Token t)
    {
        tokenList.add(t);
    }
    private void tokenIdentifier() throws IOException
    {
        String temp;
        for(int i=0;i<this.tokens.length;i++)
        {
            temp=this.tokens[i];
            this.add(new Token(this.identifyClassPart(temp),this.identifyValuePart(temp),this.identifyLineNo(temp)));
        }
        f.write(this.toString());
    }
    private String identifyClassPart(String temp)
    {
        return "";
    } 
    private String identifyValuePart(String temp)
    {
        
        return "";
    } 
    private int identifyLineNo(String temp)
    {
        return 0;
    } 
    @Override
    public String toString()
    {
        String temp="";
        for(int i=0;i<this.tokenList.size();i++)
        {
            temp=temp+","+this.tokenList.get(i).toString();
        }
        return temp;
    }
}