package compiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DFACheck 
{
                  //Identifier DFA
    private String[] identifier = {"_","[A-Za-z]","[0-9]"};
    private int TTid[][] = {{1,2,3},{1,2,2},{2,2,2},{3,3,3}};
    private int NSid = 3;
    private int ISid = 0;
    private int FSid[] = {2}; 
    private DFA ID = new DFA(NSid,identifier,TTid,ISid,FSid);

                 //Integer DFA
    private String[] whole = {"[+-]","[0-9]"};
    private int TTwh[][] = {{1,2},{3,2},{3,2},{3,3}};
    private int NSwh = 3;
    private int ISwh = 0;
    private int FSwh[] = {2};         
    private DFA WHOLE = new DFA(NSwh,whole,TTwh,ISwh,FSwh);


                //Floating-Point DFA
    private String[] real = {"[+-]","[0-9]","[eE]","[.]"};
    private int TTr[][] = {{1,2,7,3},{7,2,7,3},{7,2,7,3},{7,3,4,7},{5,6,7,7},{7,6,7,7},{7,6,7,7},{7,7,7,7}};
    private int NSr = 7;
    private int ISr = 0;
    private int FSr[] = {3,6};
    private DFA REAL = new DFA(NSr,real,TTr,ISr,FSr);

               //Character DFA
    private String[] letter = {"\\\'" , "[A-Z0-9+=_!@#?$%^&*()<> ~`,./;:-]|[c-eg-mo-qsuwxyz]" ,"[abfnrtv]" , "\\\\" , "\\\""};
    private int TTlt[][] = {{1,5,5,5,5},{5,2,2,4,5},{3,5,5,5,5},{5,5,5,5,5},{2,5,2,2,2},{5,5,5,5,5}};
    private int NSlt = 5;
    private int ISlt = 0;
    private int FSlt[] = {3};        
    private DFA LETTER = new DFA(NSlt,letter,TTlt,ISlt,FSlt);

               //String DFA
    private String[] text = {"\\\"" , "[A-Z0-9+=_!@#?$%^&*()<> ~`,./;:-]|[c-eg-mo-qsuwxyz]" ,"[abfnrtv]" , "\\\\" , "\\\'"};
    private int TTtx[][] = {{1,5,5,5,5},{3,2,2,4,5},{3,2,2,4,5},{5,5,5,5,5},{2,5,2,2,2},{5,5,5,5,5}};
    private int NStx = 5;
    private int IStx = 0;
    private int FStx[] = {3}; 
    private DFA TEXT = new DFA(NStx,text,TTtx,IStx,FStx);
        
               //String DFA
    private String[] flag = {"true","false"};
    private int TTf[][] = {{1,2},{3,3},{3,3},{3,3}};
    private int NSf = 3;
    private int ISf = 0;
    private int FSf[] = {1,2}; 
    private DFA FLAG = new DFA(NSf,text,TTf,ISf,FSf);
        
    
    
    
    /*=============Checking Datatype===============
    public boolean checkDATATYPE(String input)
    {
        Pattern p = Pattern.compile("int|float|char|String");
        Matcher m = p.matcher(input);        
        return m.matches();
    }*/
    
    /*=============Checking Identifier=============*/
    public boolean checkID(String input)
    {
        return this.ID.validate(input);
    }
    
    /*=============Checking Value type=============*/
    
    //Integer
    public boolean checkWHOLE(String input)
    {
        return this.WHOLE.validate(input);
    }
    
    //Float
    public boolean checkREAL(String input)
    {
        return this.REAL.validate(input);
    }
    
    //Character
    public boolean checkLETTER(String input)
    {
        return this.LETTER.validate(input);
    }
    
    //String
    public boolean checkTEXT(String input)
    {
        return this.TEXT.validate(input);
    }
    
    //Flag
    public boolean checkFLAG(String input)
    {
        return this.FLAG.validate(input);
    }
    
    
}
