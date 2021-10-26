package compiler;
import java.util.Hashtable;

public class Identification
{
    private Hashtable<String,String> keywords;
    private Hashtable<String,String> Punctuators;
    private Hashtable<String,String> Operators;

    public Identification()
    {
        this.keywords = new Hashtable<>();
        this.Punctuators = new Hashtable<>();
        this.Operators = new Hashtable<>();
        this.initilizeSyntaxData();
    }

    private void initilizeSyntaxData()
    {
        //<<<<<<<<<<<<<<<<<<<<<<<<<------KEYWORDS----->>>>>>>>>>>>>>>>>>>>>>>>>>>

        this.keywords.put("Text", "DATATYPES"); // will act as String
        this.keywords.put("Letter", "DATATYPES"); // will act as character
        this.keywords.put("Real", "DATATYPES"); // will act as float
        this.keywords.put("Whole", "DATATYPES"); // will act as integer
        this.keywords.put("Flag", "DATATYPES"); // will act as boolean

        this.keywords.put("if", "IF");

        this.keywords.put("else", "ELSE");

        this.keywords.put("vacant", "VACANT"); // will act as void

        this.keywords.put("loop", "LOOP"); // a general loop (for + while)
        this.keywords.put("till", "WHILE"); // a general loop (for + while)

        this.keywords.put("stop", "LOOP_CONTROL");
        this.keywords.put("resume", "LOOP_CONTROL");

        this.keywords.put("arraylist", "ARRAYLIST");
        this.keywords.put("array", "ARRAY");

        this.keywords.put("true", "FLAG_VALUE");
        this.keywords.put("false", "FLAG_VALUE");

        this.keywords.put("class", "CLASS");

        this.keywords.put("return", "RET");

        this.keywords.put("abstract", "ABSTR");
        this.keywords.put("implements", "IMPLEMENTS");
        this.keywords.put("interface", "INTERFACE");

        this.keywords.put("const", "CONSTANT"); // will act as final or constant

        this.keywords.put("try", "TRY");

        this.keywords.put("catch", "CATCH");

        this.keywords.put("finally", "FINALLY");

        this.keywords.put("throw", "THROW");

        this.keywords.put("extend", "INH");

        this.keywords.put("empty", "EMPTY");

        this.keywords.put("global", "GLOBAL");

        this.keywords.put("this", "THIS");
        this.keywords.put("new", "NEW");

        this.keywords.put("parent", "PARENT");

        this.keywords.put("public", "AM");
        this.keywords.put("private", "AM");
        this.keywords.put("protected", "AM");

        this.keywords.put("switch", "SWITCH");
        this.keywords.put("prob", "case");
        this.keywords.put("dlf", "DEFAULT");

        this.keywords.put("create", "CREATE");

        this.keywords.put("main", "MAIN");

        this.keywords.put("scan", "INPUT");

        this.keywords.put("show", "OUTPUT");

        this.keywords.put("fun", "FUNCTION");

        this.keywords.put("length", "LENGTH");


        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<------PUNCTUATORS----->>>>>>>>>>>>>>>>>>>>>>>>>>

        this.Punctuators.put(".", "DOT");

        this.Punctuators.put(",", "COMMA");

        this.Punctuators.put(";", "S_C");

        this.Punctuators.put("(", "R_B_O");

        this.Punctuators.put(")", "R_B_C");

        this.Punctuators.put("{", "C_B_O");

        this.Punctuators.put("}", "C_B_C");

        this.Punctuators.put("[", "S_B_O");

        this.Punctuators.put("]", "S_B_C");


        //<<<<<<<<<<<<<<<<<<<<<<<<<<<------OPERATORS----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        this.Operators.put("--", "INC_DEC");
        this.Operators.put("++", "INC_DEC");

        this.Operators.put("!", "NOT");

        this.Operators.put("-", "PM");
        this.Operators.put("+", "PM");

        this.Operators.put("*", "MDM");
        this.Operators.put("/", "MDM");
        this.Operators.put("%", "MDM");

        this.Operators.put("==", "RO");
        this.Operators.put("!=", "RO");
        this.Operators.put(">=", "RO");
        this.Operators.put("<=", "RO");
        this.Operators.put(">", "RO");
        this.Operators.put("<", "RO");

        this.Operators.put("&&", "LOG_O");

        this.Operators.put("||", "LOG_O");

        this.Operators.put("!", "LOG_O");

        this.Operators.put("+=", "AO");
        this.Operators.put("-=", "AO");
        this.Operators.put("*=", "AO");
        this.Operators.put("/=", "AO");
        this.Operators.put("%=", "AO");

        this.Operators.put("=", "ASSIGN");
    }

    public String checkKeyword(String word)
    {
        if (this.keywords.containsKey(word))
        {
            return this.keywords.get(word);
        }
        return "IDENTIFIER";
    }
    public String checkPunctuator(String word)
    {
        if (this.Punctuators.containsKey(word))
        {
            return this.Punctuators.get(word);
        }
        return "INVALID";
    }
    public String checkOperator(String word)
    {
        if (this.Operators.containsKey(word))
        {
            return this.Operators.get(word);
        }
        return "INVALID";
    }

    public boolean isKeyword(String word)
    {
        return this.keywords.containsKey(word);
    }
    public boolean isOperator(String word)
    {
        return this.Operators.containsKey(word);
    }
    public boolean isPunctuator(String word)
    {
        return this.Punctuators.containsKey(word);
    }
}
