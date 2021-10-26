package compiler;
public class Token 
{
    public String classPart;
    public String valuePart;
    public int lineNo;
    
    public Token(String cp,String vp, int ln)
    {
        this.classPart=cp;
        this.valuePart=vp;
        this.lineNo=ln;
    }
    
            
    @Override
    public String toString()
    {
//        char a='=';
//        return this.classPart+a+ this.valuePart+a+this.lineNo;
        return this.classPart;
    }
    public String toString1() {
//        char a = '|';
//        return this.classPart  +a+  this.valuePart  +a+  this.lineNo;
        return this.valuePart;

    }
    
}
