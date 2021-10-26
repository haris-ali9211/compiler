package compiler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BreakAndClassify 
{
    private char [] code;
    private int i;
    private ArrayList<Token> TokenArray;
    private ArrayList<String> CodeLines;
    private Identification syn;
    private int lineNo;
    private DFACheck dc;
    private boolean isText;
    private boolean isLetter;
    private boolean isNum;
    private boolean isID;
    
    
    
    public BreakAndClassify(ArrayList<String> codes)
    {
        this.dc = new DFACheck();
        this.isText = false;
        this.isLetter = false;
        this.isNum = false;
        this.isID = false;
        CodeLines = codes;
        TokenArray=new ArrayList();
        i=0;
        syn=new Identification();
        lineNo=0;
    }
    
    
    //<<<<<<========================== Word Breaking Function =================================>>>>>>>
    ArrayList<Token> wordBreaker()
    {
        char c;
        String temp = "";
        for (int ln = 0; ln < CodeLines.size(); ln++) 
        {
            this.lineNo = ln + 1;
            this.code = CodeLines.get(ln).toCharArray();
            for (i = 0; i < code.length; i++) 
            {
                c = code[i];
                if (!isText && !isLetter && !isNum) 
                {
                    if(syn.isPunctuator(""+code[i]))
                    {
                        TokenArray.add(new Token(syn.checkPunctuator(""+code[i]),code[i]+"",lineNo));
                        continue;
                    }
                    else if(syn.isOperator(""+code[i])||code[i]=='&'||code[i]=='|')
                    {
                        if(i<code.length-1)
                        {
                            if(syn.isOperator(""+code[i+1])||code[i]=='&'||code[i]=='|')
                            {
                                if((code[i]=='+')||(code[i]=='-')||(code[i]=='=')||code[i]=='&'||code[i]=='|')
                                {
                                    if(code[i]==code[i+1])
                                    {
                                        TokenArray.add(new Token(syn.checkOperator(""+code[i]+code[i+1]),""+code[i]+code[i+1],lineNo));
                                        i++;
                                        continue;
                                    }
                                }
                                if((code[i]=='+')||(code[i]=='-')||code[i]=='<'||code[i]=='>'||code[i]=='!'||code[i]=='*'||code[i]=='/'||code[i]=='%')
                                {
                                    if(code[i+1] == '=')
                                    {
                                        TokenArray.add(new Token(syn.checkOperator(""+code[i]+code[i+1]),""+code[i]+code[i+1],lineNo));
                                        i++;
                                        continue;
                                    }
                                    if(code[i]!=code[i+1] || code[i+1] != '=' )
                                    {
                                        TokenArray.add(new Token(syn.checkOperator(code[i]+""),code[i]+"",lineNo));
                                        continue;
                                    }
                                }
                                else if(code[i]=='+'||code[i]=='-'||code[i]=='/'||code[i]=='*'||code[i]=='%'||code[i]=='!'||code[i]=='=')
                                {
                                    TokenArray.add(new Token(syn.checkOperator(code[i]+""),code[i]+"",lineNo));
                                    continue;
                                }
                            }
                            else if(!syn.isOperator(""+code[i+1]))
                            {
                                TokenArray.add(new Token(syn.checkOperator(code[i]+""),code[i]+"",lineNo));
                                continue;
                            }
                        }
                        else
                        {
                            TokenArray.add(new Token(syn.checkOperator(code[i]+""),code[i]+"",lineNo));
                            continue;
                        }

                    }
                }
                //======================= Single Line Comment ========================
                if (code[i] == '#') 
                {
                    if(code[i+1] != '#')
                        break;
                    else if(code[i+1] == '#')
                    {
                        i=i+2;
                        while(i<code.length)
                        {
                            if(code[i]=='#' && code[i+1]=='#' && i<code.length)
                            {
                                i=i+2;
                                break;
                            }
                            i++;
                        }
                    }
                }
                //==================== Numeric (whole | real) constant =====================
                if(i<code.length && this.isDigit(code[i])) 
                {
                    if(this.checkIfReal(i))
                    {
                        continue;
                    }
                    else if (this.checkIfWhole(i)) 
                    {
                        continue;
                    }
                }
                //==================== Text constant =====================
                if(i<code.length && code[i]=='\"')
                {
                    if(checkIfText(i))
                    {
                        continue;
                    }
                }
                //===================== Letter constant =========================
                if(i<code.length && code[i]=='\'')
                {
                    if(checkIfLetter(i))
                    {
                        continue;
                    }
                }
                //========================= Keyword ==========================

                if(i<code.length && this.isAlphabet(code[i]))
                {
                    if(this.isIdentifier(i))
                    {
                        continue;
                    }
                }

                else if(i<code.length && code[i]== ' ')
                {
                    continue;
                }
                else
                {
                    if (i<code.length) 
                    {
//                        TokenArray.add(new Token("INVALID",""+code[i],lineNo));
                        this.saveToken(""+code[i], lineNo);
                        continue;
                    }
                    else
                    {
                        break;
                    }

                }


            }
        }
//        System.out.println(this.TokenArray.toString());
        return TokenArray;
    }
    
    //<<<<<<========================== Type Detecting Functions =================================>>>>>>>
    
    //============================================================================
    boolean checkIfWhole(int i)
    {
        String s="";
        while(this.isDigit(code[i]))
        {
            if(i<code.length)
                s=s+code[i];            
            i++;
            if(i==code.length)
                break;
        }
        i--;
        String[] whole = {"[+-]","[0-9]"};
        int TTwh[][] = {{1,2},{3,2},{3,2},{3,3}};
        int NSwh = 3;
        int ISwh = 0;
        int FSwh[] = {2};         
        DFA WHOLE = new DFA(NSwh,whole,TTwh,ISwh,FSwh);
        if(WHOLE.validate(s))
        {
            this.i=i;
            this.saveToken(s, lineNo);
            return true;
        }
        return false;
    }
    
    //===========================================================================================
    boolean checkIfReal(int i)
    {
        String s="";
        int dotCount = 0;
        int l = this.lineNo;
        while(i <this.code.length && (this.isDigit(code[i]) || code[i] == '.' || code[i] == 'e' || code[i] == 'E'))
        {
            if (dotCount < 1 && code[i] == '.') 
            {
                if (this.isDigit(code[i+1]))
                {
                    s=s+code[i];
                    dotCount++; 
                }
                else
                {
                    break;
                }
            }
            else if(i<code.length)
            {
                s=s+code[i];            
            }
            else if(i==code.length)
            {
                break;
            }
            else if (dotCount > 1) 
            {
                break;
            }
            i++;
        }
        i--;
        
        String[] real = {"[+-]","[0-9]","[eE]","[.]"};
        int TTr[][] = {{1,2,7,3},{7,2,7,3},{7,2,7,3},{7,3,4,7},{5,6,7,7},{7,6,7,7},{7,6,7,7},{7,7,7,7}};
        int NSr = 7;
        int ISr = 0;
        int FSr[] = {3,6};
        DFA REAL = new DFA(NSr,real,TTr,ISr,FSr);
        
        if(REAL.validate(s))
        {
            this.i=i;
            this.saveToken(s, lineNo);
            return true;
        }
        return false;
    }
    
    //=============================================================================
    boolean checkIfText(int n)
    {
        String temp="";
        char cc,c1,c11;
        while(i <this.code.length)
        {
            cc = code[n];
            if(n<code.length-1)
            {
                if(code[n]==10 || code[n]==13)
                {
                    break;
                }
                if(code[n]=='\"' && !isText)
                {
                    temp=temp+code[n];
                    this.isText = true;
                }
                else if(code[n]=='\\')
                {
                    c1 = code[n];
                    if(this.isEscape(code[n+1])||code[n+1]=='\''||code[n+1]=='\"')
                    {
                        c11 = code[n+1];
                        temp=temp+code[n] + code[n+1];
                        n++;
                        isText=true;
                    }
                    else
                    {
                        c11 = code[n+1];
                        temp=temp+code[n];
                        isText=false;
                        break;
                    }
                }
                else if(code[n]=='\"' && isText)
                {
                    temp=temp+code[n];
                    break;
                }
                else if (isText) 
                {
                    temp=temp+code[n];
                }
            }
            
            n++;
            if(n==code.length)
            {
                break;
            }
        }
        String[] text = {"\\\"" , "[A-Z0-9+=_!@#?$%^&*()<> ~`,./;:-]|[c-eg-mo-qsuwxyz]" ,"[abfnrtv]" , "\\\\" , "\\\'"};
        int TTtx[][] = {{1,5,5,5,5},{3,2,2,4,5},{3,2,2,4,5},{5,5,5,5,5},{2,5,2,2,2},{5,5,5,5,5}};
        int NStx = 5;
        int IStx = 0;
        int FStx[] = {3}; 
        DFA TEXT = new DFA(NStx,text,TTtx,IStx,FStx);
        isText=false;
        if(TEXT.validate(temp))
        {
            this.i=n;
            this.saveToken(temp, lineNo);
            return true;
        }
        this.i=n;
        this.saveToken(temp, lineNo);
        return false;
    }
    
    //==============================================================================================
    boolean checkIfLetter(int n)
    {
        String temp="";
        char cc,c1,c11;
        while(i <this.code.length )
        {
            cc = code[n];
            if(n<code.length-1)
            {
//                if(code[n]==10 || code[n]==13)
//                {
//                    break;
//                }
                if(code[n]=='\'' && !isLetter)
                {
                    temp=temp+code[n];
                    this.isLetter = true;
                }
                else if(code[n]=='\\')
                {
                    c1 = code[n];
                    if(this.isEscape(code[n+1])||code[n+1]=='\''||code[n+1]=='\"')
                    {
                        c11 = code[n+1];
                        temp=temp+code[n] + code[n+1];
                        n++;
                        isLetter=true;
                    }
                    else
                    {
                        c11 = code[n+1];
                        temp=temp+code[n];
                        isLetter=false;
                        break;
                    }
                }
                else if(code[n]=='\'' && isLetter)
                {
                    temp=temp+code[n];
                    break;
                }
                else if (isLetter) 
                {
                    temp=temp+code[n];
                }
            }
            
            n++;
            if(n==code.length)
            {
                break;
            }
        }
        
        String[] letter = {"\\\'" , "[A-Z0-9+=_!@#?$%^&*()<> ~`,./;:-]|[c-eg-mo-qsuwxyz]" ,"[abfnrtv]" , "\\\\" , "\\\""};
        int TTlt[][] = {{1,5,5,5,5},{5,2,2,4,5},{3,5,5,5,5},{5,5,5,5,5},{2,5,2,2,2},{5,5,5,5,5}};
        int NSlt = 5;
        int ISlt = 0;
        int FSlt[] = {3};        
        DFA LETTER = new DFA(NSlt,letter,TTlt,ISlt,FSlt);
        isLetter=false;
        if(LETTER.validate(temp))
        {
            this.i=n;
            this.saveToken(temp, lineNo);
            return true;
        }
        this.i=n;
        this.saveToken(temp, lineNo);
        return false;
    }
    
    //============================================================================
    boolean isIdentifier(int i)
    {       
        String s="";
        while(i <this.code.length && (this.isAlphabet(code[i]) || this.isDigit(code[i]) || code[i] == '_' )&& (!syn.isPunctuator(code[i]+"")))
        {
            s=s+code[i];
            i++;
        }
        i--;
        String[] identifier = {"_","[A-Za-z]","[0-9]"};
        int TTid[][] = {{1,2,3},{1,2,2},{2,2,2},{3,3,3}};
        int NSid = 3;
        int ISid = 0;
        int FSid[] = {2}; 
        DFA ID = new DFA(NSid,identifier,TTid,ISid,FSid);
        if(ID.validate(s))
        {
            this.i=i;
            this.saveToken(s, lineNo);
            return true;
        }
        return false;
    }
    
    
    //<<<<<<========================== Utility Functions =================================>>>>>>>
    
    private boolean isEscape(char ch)
    {
        char esc[] = new char[]{'a','b','f','n','r','t','v','\\'};//,'\'','\"'};
        for (int i = 0; i < esc.length; i++) 
        {
            if (ch == esc[i]) 
            {
                return true;
            }
        }
        return false;
    }
    
    private boolean isDigit(char ch)
    {
        char digit[] = new char[]{'0','1','2','3','4','5','6','7','8','9'};
        for (int i = 0; i < digit.length; i++) 
        {
            if (ch == digit[i]) 
            {
                return true;
            }
        }
        return false;
    }
    
    private boolean isSeparator(char ch)
    {
        char sep[] = new char[]{' ','\'','\"',10,'\n',';'};
        for (int i = 0; i < sep.length; i++) 
        {
            if (ch == sep[i]) 
            {
                return true;
            }   
        }
        if (this.isOperator(ch)) 
        {
            return true;
        }
        else if (this.syn.isPunctuator(""+ch)) 
        {
            return true;
        }
        return false;
    }
    
    private boolean isOperator(char op)
    {
        if (this.syn.isOperator(""+op)) 
        {
            return true;
        }
        return false;
    }
    
    private boolean isAlphabet(char ch)
    {
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(""+ch);
        if (m.matches()) 
        {
            return true;
        }
        return false;
    }
    
    private void saveToken(String temp,int LN)
    {
        ArrayList<String> ExcepTypes = new ArrayList<>(); 
        ExcepTypes.add("Exception");
        ExcepTypes.add("ArithmeticException");
        ExcepTypes.add("ArrayIndexOutOfBoundException");
        ExcepTypes.add("ClassNotFoundException");
        ExcepTypes.add("IOException");
        ExcepTypes.add("InterruptedException");
        ExcepTypes.add("NoSuchFieldException");
        ExcepTypes.add("NoSuchMethodException");
        ExcepTypes.add("NullPointerException");
        ExcepTypes.add("NumberFormatException");
        ExcepTypes.add("RuntimeException");
        ExcepTypes.add("TextIndexOutOfBoundsException");
        
        if (this.dc.checkLETTER(temp)) 
        {
            this.TokenArray.add(new Token("LETTER_CONST",temp,LN));
        }
        else if (this.dc.checkTEXT(temp)) 
        {
            this.TokenArray.add(new Token("TEXT_CONST",temp,LN));
        }
        else if (this.dc.checkREAL(temp) && !temp.equals(".")) 
        {
            this.TokenArray.add(new Token("REAL_CONST",temp,LN));
        }
        else if (this.dc.checkWHOLE(temp)) 
        {
            this.TokenArray.add(new Token("WHOLE_CONST",temp,LN));
        }
        else if (this.dc.checkID(temp)) 
        {
            if (this.syn.isKeyword(temp)) 
            {
                this.TokenArray.add(new Token(this.syn.checkKeyword(temp),temp,LN));
            }
            else if (ExcepTypes.contains(temp)) 
            {
                this.TokenArray.add(new Token("EXCEPTION",temp,LN));
            }
            else
            {
                this.TokenArray.add(new Token("IDENTIFIER",temp,LN));
            }
        }
        else if (this.syn.isOperator(temp)) 
        {
            this.TokenArray.add(new Token(this.syn.checkOperator(temp),temp,LN));
        }
        else if (this.syn.isPunctuator(temp)) 
        {
            this.TokenArray.add(new Token(this.syn.checkPunctuator(temp),temp,LN));
        }
        else if (!(temp.equals("\t") || temp.equals("\n") || temp.equals("\\s+"))) 
        {
            this.TokenArray.add(new Token("INVALID",temp,LN));
        }

    }
}
