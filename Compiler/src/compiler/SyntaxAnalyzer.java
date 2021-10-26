package compiler;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class SyntaxAnalyzer extends Main {

    SemanticCheck sen = new SemanticCheck();
    static int codeFileIndex = 0;

    MainTable t = new MainTable();
    FunctionTable f = new FunctionTable();
    DataTable d = new DataTable();

    ArrayList MainTableArr = new ArrayList();
    ArrayList FuncTable = new ArrayList();
    ArrayList DataTable = new ArrayList();

    int scope = 0;
    boolean bool;
    Object s;
    String param="";
    String type="";

//    int scope = 0;
//
//    private static ArrayList scopelist = new ArrayList();
//
//    public static ArrayList getList()
//    {
//     return scopelist;
//    }

    boolean S()
    {
        if (class_dec())
        {
            if (S())
            {
                return true;
            }
        }
        else if (interface_st())
        {
            if (S())
            {
                return true;
            }
        }
        else if (codeToken.get(codeFileIndex).equals("$"))
        {
            System.out.println("NO SYNTAX ERROR");
            System.out.println("");
//            sen.Table();
//            sen.FunctionTable();

            t.Table();
            System.out.println("");
            f.F_Table();
            return true;
        }

        System.out.println("SYNTAX ERROR");
        return false;
    }

    boolean class_dec()
    {
//        MainTableArr.clear();
//        if (AM())
//        {
//            if (Abs())
//            {
//                if (codeToken.get(codeFileIndex).equals("CLASS"))
//                {
//                    MainTableArr.add(valuePart.get(codeFileIndex));
//                    codeFileIndex++;
//                    if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//                    {
//                        if (sen.SampleTable.contains(valuePart.get(codeFileIndex)))
//                        {
//                            System.out.println(valuePart.get(codeFileIndex) + " class already exist");
//                            MainTableArr.clear();
//                        }
//                        else
//                        {
//                            MainTableArr.add(valuePart.get(codeFileIndex));
//                        }
//                        codeFileIndex++;
//                        if (extend() || implement())
//                        {
//                            if (!MainTableArr.isEmpty())
//                            {
//                                sen.Table(MainTableArr);
//                            }
//                            if (codeToken.get(codeFileIndex).equals("C_B_O"))
//                            {
////                                scope++;
////                                scopelist.add(scope);
//                                codeFileIndex++;
//                                if (class_body())
//                                {
//                                    if (codeToken.get(codeFileIndex).equals("C_B_C"))
//                                    {
//                                        codeFileIndex++;
//                                        return true;
//                                    }
//                                    return true;
//                                }
//                            }
//                        }
//                    }
//                }
//            }l
//        }
//        return false;

//        System.out.println("class called");
        MainTableArr.clear();


        if (AM())
        {
            if (Abs())
            {
                if (codeToken.get(codeFileIndex).equals("CLASS"))
                {
                    MainTableArr.add(valuePart.get(codeFileIndex));
                    codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                    {
                        if (t.Table.contains(valuePart.get(codeFileIndex)))
                        {
                            System.out.println(valuePart.get(codeFileIndex) + " is already declared");
                            MainTableArr.clear();
                        }
                        else
                        {
                            MainTableArr.add(valuePart.get(codeFileIndex));
                            s=valuePart.get(codeFileIndex);
                        }
                        codeFileIndex++;
                        if (extend() || implement())
                        {
                            if (!MainTableArr.isEmpty())
                            {
                                //  System.out.println("main table" + MainTable);
                                t.Table(MainTableArr);
                                //  System.out.println("table" + t.Table);
                            }

                            if (codeToken.get(codeFileIndex).equals("C_B_O"))
                            {
                                scope++;
                                codeFileIndex++;
                                if (class_body())
                                {
                                    if (codeToken.get(codeFileIndex).equals("C_B_C"))
                                    {
                                        d.D_Table(s);
                                        d.DT.clear();
                                        codeFileIndex++;
                                        return true;
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean AM()  //check access modifier
    {
        if (codeToken.get(codeFileIndex).equals("AM"))
        {
            MainTableArr.add(valuePart.get(codeFileIndex));
            codeFileIndex++;
            return true;
        }

        else if (codeToken.get(codeFileIndex).equals("ABSTR") || codeToken.get(codeFileIndex).equals("CLASS"))
        {
            MainTableArr.add("null");
            return true;
        }
        return false;
    }

    boolean Abs() // ye check karna hai
    {
        if (codeToken.get(codeFileIndex).equals("ABSTR"))
        {
            MainTableArr.add(valuePart.get(codeFileIndex));
            codeFileIndex++;
            return true;
        }

        else if (codeToken.get(codeFileIndex).equals("CLASS"))
        {
            MainTableArr.add("General");
            return true;
        }
        return false;
    }

    boolean extend()
    {
//        if (codeToken.get(codeFileIndex).equals("INH"))
//        {
//            codeFileIndex++;
//            {
//                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//                {
//                    if (!MainTableArr.isEmpty())
//                    {
//                        if (sen.SampleTable.contains(valuePart.get(codeFileIndex)))
//                        {
//                            MainTableArr.add(valuePart.get(codeFileIndex));
//
//                        }
//                        else
//                        {
//                            System.out.println(valuePart.get(codeFileIndex) + " does not exist");
//                            MainTableArr.clear();
//                        }
//                    }
//                    codeFileIndex++;
//                    return true;
//                }
//            }
//        }
//        else if (codeToken.get(codeFileIndex).equals("C_B_O"))
//        {
////            scope++;
////            scopelist.add(scope);
//            if (!MainTableArr.isEmpty())
//            {
//                MainTableArr.add("null");
//            }
//            return true;
//        }
//        return false;

        if (codeToken.get(codeFileIndex).equals("INH"))
        {
            codeFileIndex++;
            {
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    if (!MainTableArr.isEmpty())
                    {
                        if (t.Table.contains(valuePart.get(codeFileIndex)))
                        {
                            MainTableArr.add(valuePart.get(codeFileIndex));

                        }
                        else
                        {
                            System.out.println(valuePart.get(codeFileIndex) + " is not declared");
                            MainTableArr.clear();
                        }
                    }
                    codeFileIndex++;
                    return true;
                }
            }
        }
        else if (codeToken.get(codeFileIndex).equals("C_B_O"))
        {
            if (!MainTableArr.isEmpty())
            {
                MainTableArr.add("null");
            }
            return true;
        }
        return false;
    }

    boolean class_body() //check class body
    {
        if(codeToken.get(codeFileIndex).equals("C_B_C"))
        {
            return true;
        }
        else if (main_method())
        {
            return true;
        }

        else if (body())
        {
            return true;
        }
        return false;
    }

    boolean main_method() // ye check karna hai
    {
        if (codeToken.get(codeFileIndex).equals("MAIN"))
        {
            codeFileIndex++;

            if (body())
            {
                return true;
            }
        }
        return false;
    }

    boolean body() //check body
    {
        if (codeToken.get(codeFileIndex).equals("C_B_O"))
        {
//            scope++;
//            scopelist.add(scope);
            codeFileIndex++;
            if (MST())
            {
                if (codeToken.get(codeFileIndex).equals("C_B_C"))
                {
                    codeFileIndex++;
                    return true;
                }
            }

            else
            {
                return true;
            }
        }

        else if (MST())
        {
            return true;
        }

        return false;
    }

    boolean MST() {
        if (SST())
        {
            if (MST())
            {
                return true;
            }

            else
            {
                return true;
            }
        }
        return false;
    }

    boolean SST()
    {
//        if (codeToken.get(codeFileIndex).equals("DATATYPES"))
//        {
////            scopelist.add(valuePart.get(codeFileIndex));
//            codeFileIndex++;
//            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//            {
////                scopelist.add(valuePart.get(codeFileIndex));
////                scopelist.add(scope);
//                codeFileIndex++;
//                if (dec())
//                {
//                    return true;
//                }
//                else if (Array())
//                {
//                    return true;
//                }
//
//            }
//        }
        if(bool)
        {
            if (codeToken.get(codeFileIndex).equals("DATATYPES"))
            {
                FuncTable.add(valuePart.get(codeFileIndex));
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    if(f.F_Table.contains(valuePart.get(codeFileIndex)))
                    {
                        int c=f.F_Table.indexOf(valuePart.get(codeFileIndex));
                        if(f.F_Table.get(c+1).equals(scope))
                        {
                            System.out.println(valuePart.get(codeFileIndex)+" is already declared");
                            FuncTable.clear();
                            codeFileIndex++;
                            if (dec())
                            {
                                return true;
                            }
                            else if (Array())
                            {
                                return true;
                            }
                        }
                        else
                        {
                            FuncTable.add(valuePart.get(codeFileIndex));
                            FuncTable.add(scope);
                            codeFileIndex++;

                            if (dec())
                            {
                                return true;
                            }
                            else if (Array())
                            {
                                return true;
                            }
                        }
                    }

                    else{
                        FuncTable.add(valuePart.get(codeFileIndex));
                        FuncTable.add(scope);
                        codeFileIndex++;

                        if (dec())
                        {
                            return true;
                        }
                        else if (Array())
                        {
                            return true;
                        }
                    }
                }
            }
        }

        else if (codeToken.get(codeFileIndex).equals("DATATYPES"))
        {
            DataTable.add(valuePart.get(codeFileIndex));
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                if(d.DT.contains(valuePart.get(codeFileIndex)))
                {
                    System.out.println(valuePart.get(codeFileIndex)+" is already declared");
                    DataTable.clear();
                    codeFileIndex++;
                    if (dec())
                    {
                        return true;
                    }
                    else if (Array())
                    {
                        return true;
                    }
                }

                else
                {
                    DataTable.add(valuePart.get(codeFileIndex));
                    DataTable.add("null");
                    codeFileIndex++;

                    if (dec())
                    {
                        return true;
                    }
                    else if (Array())
                    {
                        return true;
                    }
                }
            }
        }

        else if (print())
        {
            return true;
        }
        else if (input())
        {
            return true;
        }
        else if (switch_st())
        {
            return true;
        }
        else if (func_dec())
        {
            return true;
        }
        else if (func_call())
        {
            return true;
        }
        else if (for_st())
        {
            return true;
        }
        else if (if_else())
        {
            return true;
        }
        else if (ArrayL())
        {
            return true;
        }
        else if (while_st())
        {
            return true;
        }
        else if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
        {
            codeFileIndex++;
            if (COM())
            {
                return true;
            }
            else if (X())
            {
                if (codeToken.get(codeFileIndex).equals("S_C"))
                {
                    codeFileIndex++;
                    return true;
                }
            }
        }
        else if (inc_dec_st())
        {
            return true;
        }
        else if (codeToken.get(codeFileIndex).equals("LOOP_CONTROL"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("S_C"))
            {
                codeFileIndex++;
                return true;
            }
        }
        else if (codeToken.get(codeFileIndex).equals("LOOP_CONTROL"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("S_C"))
            {
                codeFileIndex++;
                return true;
            }
        }
        else if (codeToken.get(codeFileIndex).equals("THIS"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("DOT"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("S_C"))
                    {
                        codeFileIndex++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean dec() //check deceleration
    {
        if (init())
        {
            if (list())
            {
                return true;
            }
        }
        return false;
    }

    boolean init() //check initialization
    {
        if (codeToken.get(codeFileIndex).equals("ASSIGN"))
        {
            codeFileIndex++;
            if (OE())
            {
                return true;
            }
        }
        else if (codeToken.get(codeFileIndex).equals("S_C"))
        {
            return true;
        }
        return false;
    }

    boolean OE() //OR expression
    {
        if (AE())
        {
            if (OE_dash())
            {
                return true;
            }
            else
            {
                return true;
            }
        }

        return false;
    }

    boolean AE() //AND expression
    {
        if (RE())
        {
            if (AE_dash())
            {
                return true;
            }
            else
            {
                return true;
            }
        }

        return false;
    }

    boolean RE() //Relation expression
    {
        if (E())
        {
            if (RE_dash())
            {
                return true;
            }
            else {
                return true;
            }
        }
        return false;
    }

    boolean E()
    {
        if (T())
        {
            if (E_dash())
            {
                return true;
            }
            else
            {
                return true;
            }
        }
        return false;
    }

    boolean T()
    {
        if (F())
        {
            if (T_dash())
            {
                return true;
            }
            else
            {
                return true;
            }
        }

        return false;
    }

    boolean F()
    {
        if (TH())
        {
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                if (F_dash())
                {
                    return true;
                }
            }
        }
        else if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
        {
            codeFileIndex++;
            if (F_dash())
            {
                return true;
            }
            else
            {
                return true;
            }
        }
        else if (codeToken.get(codeFileIndex).equals("WHOLE_CONST") || codeToken.get(codeFileIndex).equals("REAL_CONST") || codeToken.get(codeFileIndex).equals("TEXT_CONST") || codeToken.get(codeFileIndex).equals("LETTER_CONST"))
        {
            codeFileIndex++;
            return true;

        }
        else if (codeToken.get(codeFileIndex).equals("R_B_O"))
        {
            codeFileIndex++;
            if (OE())
            {
                if (codeToken.get(codeFileIndex).equals("R_B_C"))
                {
                    codeFileIndex++;
                    return true;
                }
            }
        }
        else if (inc_dec())
        {
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                return true;
            }
        }
        else if (codeToken.get(codeFileIndex).equals("LOG_O"))
        {
            codeFileIndex++;
            if (F())
            {
                return true;
            }
        }
        return false;
    }

    boolean list()
    {
//        if (codeToken.get(codeFileIndex).equals("S_C"))
//        {
//            codeFileIndex++;
//            return true;
//        }
//        else if (codeToken.get(codeFileIndex).equals("COMMA"))
//        {
//            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//            {
//                codeFileIndex++;
//                if (init())
//                {
//                    if (list())
//                    {
//                        return true;
//                    }
//                }
//            }
//
//        }
//        return false;

        if(bool) {
            if (codeToken.get(codeFileIndex).equals("S_C"))
            {
                if (!FuncTable.isEmpty()) {
                    f.F_Table(FuncTable);
                    FuncTable.clear();

                }
                codeFileIndex++;

                return true;
            }
            else if (codeToken.get(codeFileIndex).equals("COMMA"))
            {
                FuncTable.add(valuePart.get(codeFileIndex-2));
                codeFileIndex++;

                if (codeToken.get(codeFileIndex).equals("IDENTIFIER")) {
                    FuncTable.add(valuePart.get(codeFileIndex));
                    FuncTable.add(scope);
                    codeFileIndex++;
                    if (init()) {
                        if (list()) {
//                        FuncTable.clear();
                            return true;
                        }
                    }
                }

            }
        }

        else if(!bool)
        {
            if (codeToken.get(codeFileIndex).equals("S_C"))
            {
                if(!DataTable.isEmpty())
                {
                    d.D_Table(DataTable);
                    DataTable.clear();
                }
                codeFileIndex++;

                return true;
            }
            else if (codeToken.get(codeFileIndex).equals("COMMA"))
            {
                DataTable.add(valuePart.get(codeFileIndex-2));
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    DataTable.add(valuePart.get(codeFileIndex));
                    DataTable.add("null");
                    codeFileIndex++;
                    if (init())
                    {
                        if (list())
                        {
//                        FuncTable.clear();
                            return true;
                        }
                    }
                }

            }
        }

        return false;
    }

    boolean OE_dash()
    {
        if (codeToken.get(codeFileIndex).equals("LOG_O"))
        {
            codeFileIndex++;
            if (AE())
            {
                if (OE_dash())
                {
                    return true;
                }
                else
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean AE_dash() {
        if (codeToken.get(codeFileIndex).equals("LOG_O"))
        {
            codeFileIndex++;
            if (RE())
            {
                if (AE_dash())
                {
                    return true;
                }
                else
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean RE_dash()
    {
        if (codeToken.get(codeFileIndex).equals("RO"))
        {
            codeFileIndex++;
            if (E()) {
                if (RE_dash())
                {
                    return true;
                }
                else
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean E_dash()
    {
        if (codeToken.get(codeFileIndex).equals("PM") || codeToken.get(codeFileIndex).equals("MDM"))
        {
            codeFileIndex++;
            if (T())
            {
                if (E_dash())
                {
                    return true;
                }
                else
                {
                    return true;
                }
            }

        }
        return false;
    }

    boolean Array()
    {
//        System.out.println("array called");

            if (codeToken.get(codeFileIndex).equals("S_B_O"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("S_B_C"))
                {
                    codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("S_B_O"))
                    {
                        if (two_d_array())
                        {
                            return true;
                        }
                    }
                    else if (arrlist())
                    {
                        return true;
                    }
                }
            }
        return false;
    }

    boolean two_d_array()
    {
//        System.out.println("two d called" + codeToken.get(codeFileIndex));

        if (codeToken.get(codeFileIndex).equals("S_B_O"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("S_B_C"))
            {
                codeFileIndex++;
                if (B1())
                {
//                    System.out.println("b1 true");
                    return true;
                }
            }
        }

        return false;
    }

    boolean B1() {
        if (codeToken.get(codeFileIndex).equals("S_C"))
        {
            codeFileIndex++;
            return true;
        }
        else if (codeToken.get(codeFileIndex).equals("ASSIGN"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("C_B_O"))
            {
//                scope++;
//                scopelist.add(scope);
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("C_B_O"))
                {
//                    scope++;
//                    scopelist.add(scope);
                    codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("WHOLE_CONST") || codeToken.get(codeFileIndex).equals("REAL_CONST") || codeToken.get(codeFileIndex).equals("TEXT_CONST") || codeToken.get(codeFileIndex).equals("LETTER_CONST"))
                    {
                        codeFileIndex++;
                        if (B2())
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    boolean B2()
    {
        if (codeToken.get(codeFileIndex).equals("COMMA"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("WHOLE_CONST") || codeToken.get(codeFileIndex).equals("REAL_CONST") || codeToken.get(codeFileIndex).equals("TEXT_CONST") || codeToken.get(codeFileIndex).equals("LETTER_CONST"))
            {
                codeFileIndex++;
                if (B2())
                {
                    return true;
                }
            }
        }
        else if (codeToken.get(codeFileIndex).equals("C_B_C"))
        {
            codeFileIndex++;
            if (B3())
            {
                return true;
            } else if (codeToken.get(codeFileIndex).equals("C_B_C"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("S_C"))
                {
                    codeFileIndex++;
                    return true;
                }
            }
        }

        return false;
    }

    boolean B3()
    {
        if (codeToken.get(codeFileIndex).equals("COMMA"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("C_B_O"))
            {
//                scope++;
//                scopelist.add(scope);
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("WHOLE_CONST") || codeToken.get(codeFileIndex).equals("REAL_CONST") || codeToken.get(codeFileIndex).equals("TEXT_CONST") || codeToken.get(codeFileIndex).equals("LETTER_CONST"))
                    codeFileIndex++;
                {
                    if (B2())
                    {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    boolean arrlist()
    {
        if (codeToken.get(codeFileIndex).equals("S_C"))
        {
            codeFileIndex++;
            return true;
        }
        else if (codeToken.get(codeFileIndex).equals("ASSIGN"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("C_B_O"))
            {
//                scope++;
//                scopelist.add(scope);
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("WHOLE_CONST") || codeToken.get(codeFileIndex).equals("REAL_CONST") || codeToken.get(codeFileIndex).equals("TEXT_CONST") || codeToken.get(codeFileIndex).equals("LETTER_CONST"))
                {
                    codeFileIndex++;
                    if (arr2list()) {
                        if (codeToken.get(codeFileIndex).equals("C_B_C"))
                        {
                            codeFileIndex++;
                            if (codeToken.get(codeFileIndex).equals("S_C"))
                            {
                                codeFileIndex++;
                                return true;
                            }
                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean arr2list()
    {
        if (codeToken.get(codeFileIndex).equals("COMMA"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("WHOLE_CONST") || codeToken.get(codeFileIndex).equals("REAL_CONST") || codeToken.get(codeFileIndex).equals("TEXT_CONST") || codeToken.get(codeFileIndex).equals("LETTER_CONST"))
                codeFileIndex++;
            {
                if (arr2list())
                {
                    return true;
                }
                else if (codeToken.get(codeFileIndex).equals("C_B_C"))
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean print()
    {
        if (codeToken.get(codeFileIndex).equals("OUTPUT"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("R_B_O"))
            {
                codeFileIndex++;
                if (pl_list())
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean pl_list()
    {
        if (codeToken.get(codeFileIndex).equals("TEXT_CONST"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("R_B_C"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("S_C"))
                {
                    codeFileIndex++;
                    return true;
                }
            }
            else if (codeToken.get(codeFileIndex).equals("COMMA"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    codeFileIndex++;
                    if (pl_list())
                    {
                        return true;
                    }
                    else if (codeToken.get(codeFileIndex).equals("R_B_C"))
                    {
                        codeFileIndex++;
                        if (codeToken.get(codeFileIndex).equals("S_C"))
                        {
                            codeFileIndex++;
                            return true;
                        }
                    }
                }
            }

        }

        else if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("R_B_C"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("S_C"))
                {
                    codeFileIndex++;
                    return true;
                }
            }
            else if (codeToken.get(codeFileIndex).equals("COMMA"))
            {
                codeFileIndex++;
                if (pl_list())
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean input() {
        if (codeToken.get(codeFileIndex).equals("INPUT"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("R_B_O"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER") || codeToken.get(codeFileIndex).equals("TEXT_CONST") )
                {
                    codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("R_B_C"))
                    {
                        codeFileIndex++;
                        if (codeToken.get(codeFileIndex).equals("S_C"))
                        {
                            codeFileIndex++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

//    boolean switch_st()
//    {
//        if (codeToken.get(codeFileIndex).equals("SWITCH"))
//        {
//            codeFileIndex++;
//            if (codeToken.get(codeFileIndex).equals("R_B_O"))
//            {
//                codeFileIndex++;
//                if(codeToken.get(codeFileIndex).equals("DATATYPES"))
//                {
//                    codeFileIndex++;
//                    if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//                    {
//                        codeFileIndex++;
//                        if (dec())
//                        {
//                            if (codeToken.get(codeFileIndex).equals("R_B_C"))
//                            {
//                                codeFileIndex++;
//                                if (codeToken.get(codeFileIndex).equals("C_B_O"))
//                                {
//                                    codeFileIndex++;
//                                    if (case_())
//                                    {
//                                        if (codeToken.get(codeFileIndex).equals("C_B_C"))
//                                        {
//                                            codeFileIndex++;
//                                            return true;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                else if(codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//                {
//                    codeFileIndex++;
//                    if (dec())
//                    {
//                        if (codeToken.get(codeFileIndex).equals("R_B_C"))
//                        {
//                            codeFileIndex++;
//                            if (codeToken.get(codeFileIndex).equals("C_B_O"))
//                            {
//                                codeFileIndex++;
//                                if (case_())
//                                {
//                                    if (codeToken.get(codeFileIndex).equals("C_B_C"))
//                                    {
//                                        codeFileIndex++;
//                                        return true;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                else if (codeToken.get(codeFileIndex).equals("R_B_C"))
//                {
//                    codeFileIndex++;
//                    if (codeToken.get(codeFileIndex).equals("C_B_O"))
//                    {
//                        codeFileIndex++;
//                        if (case_())
//                        {
//                            if (codeToken.get(codeFileIndex).equals("C_B_C"))
//                            {
//                                codeFileIndex++;
//                                return true;
//                            }
//                        }
//                    }
//                }
//                else if (OE())
//                {
//                    if (codeToken.get(codeFileIndex).equals("R_B_C"))
//                    {
//                        codeFileIndex++;
//                        if (codeToken.get(codeFileIndex).equals("C_B_O"))
//                        {
//                            codeFileIndex++;
//                            if (case_())
//                            {
//                                if (codeToken.get(codeFileIndex).equals("C_B_C"))
//                                {
//                                    codeFileIndex++;
//                                    return true;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }

    boolean switch_st()
    {
        if (codeToken.get(codeFileIndex).equals("SWITCH"))
        {
            codeFileIndex++;
            if (OE()) {

                if (codeToken.get(codeFileIndex).equals("C_B_O"))
                {
                    scope++;
//                    scopelist.add(scope);
                    codeFileIndex++;

                    if (case_()) {

                        if (codeToken.get(codeFileIndex).equals("C_B_C"))
                        {
                            codeFileIndex++;
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    boolean case_()
    {
        if (codeToken.get(codeFileIndex).equals("case"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("WHOLE_CONST") || codeToken.get(codeFileIndex).equals("REAL_CONST") || codeToken.get(codeFileIndex).equals("TEXT_CONST") || codeToken.get(codeFileIndex).equals("LETTER_CONST"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("S_C"))
                {
                    codeFileIndex++;
                    if (body())
                    {
//                        System.out.println(codeToken.get(codeFileIndex));
                        if (case_())
                        {
                            return true;
                        }
                        else if (default_st())
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean default_st()
    {
        if (codeToken.get(codeFileIndex).equals("DEFAULT"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("S_C"))
            {
                codeFileIndex++;
                if (body())
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean func_dec()
    {
//        if (AM())
//        {
//            if (return_type())
//            {
//                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//                {
//                    codeFileIndex++;
//                    if (codeToken.get(codeFileIndex).equals("R_B_O"))
//                    {
//                        codeFileIndex++;
//                        if (Pl3())
//                        {
//                            if (codeToken.get(codeFileIndex).equals("R_B_C"))
//                            {
//                                codeFileIndex++;
//                                if (codeToken.get(codeFileIndex).equals("C_B_O"))
//                                {
////                                    scope++;
////                                    scopelist.add(scope);
//                                    codeFileIndex++;
//                                    if (func_body())
//                                    {
//                                        return true;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return false;

        if (codeToken.get(codeFileIndex).equals("AM"))
        {
//            System.out.println(valuePart.get(codeFileIndex));
//            DataTable.add(0, " ");
//            DataTable.add(1, " ");
            DataTable.add(valuePart.get(codeFileIndex));
            codeFileIndex++;
            if (return_type())
            {
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    DataTable.add(valuePart.get(codeFileIndex));
                    codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("R_B_O"))
                    {
                        scope++;
                        codeFileIndex++;
                        if (Pl3())
                        {
                            if (codeToken.get(codeFileIndex).equals("R_B_C"))
                            {
                                codeFileIndex++;
                                param=param+" --> "+type;
                                DataTable.add(param);
                                d.D_Table(DataTable);
                                DataTable.clear();
                                param="";

                                if (codeToken.get(codeFileIndex).equals("C_B_O"))
                                {
                                    bool=true;
                                    codeFileIndex++;
                                    if (func_body())
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean return_type() {
        if (codeToken.get(codeFileIndex).equals("DATATYPES"))
        {
            type=valuePart.get(codeFileIndex).toString();
            codeFileIndex++;
            return true;
        }
        return false;
    }

    boolean func_body()
    {
//        if (MST()) {
//            if (ret_line())
//            {
//                if (codeToken.get(codeFileIndex).equals("C_B_O"))
//                {
////                    scope++;
////                    scopelist.add(scope);
//                    codeFileIndex++;
//                    return true;
//                }
//            }
//            else if (codeToken.get(codeFileIndex).equals("C_B_C"))
//            {
//                codeFileIndex++;
//                return true;
//            }
//        }
//
//

        if (MST())
        {
            if (ret_line())
            {
                if (codeToken.get(codeFileIndex).equals("C_B_O"))
                {
                    bool=false;
                    codeFileIndex++;
                    return true;
                }
            }
            else if (codeToken.get(codeFileIndex).equals("C_B_C"))
            {
                bool=false;
                codeFileIndex++;
                return true;
            }
        }
        return false;
    }

    boolean ret_line() {
        if (codeToken.get(codeFileIndex).equals("RET"))
        {
            codeFileIndex++;
            if (ret()) {
                if (codeToken.get(codeFileIndex).equals("S_C"))
                {
                    codeFileIndex++;
                    return true;
                }
            }
        }
        return false;
    }

    boolean ret()
    {
        if (OE())
        {
            return true;
        }
        else if (codeToken.get(codeFileIndex).equals("R_B_C"))
        {
            return true;
        }
        return false;
    }

    boolean for_st()
    {
        if (codeToken.get(codeFileIndex).equals("LOOP"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("R_B_O"))
            {
                codeFileIndex++;
                if (c1())
                {
                    if (c2())
                    {
                        if (codeToken.get(codeFileIndex).equals("S_C"))
                        {
                            codeFileIndex++;
                            if (c3())
                            {
                                if (codeToken.get(codeFileIndex).equals("R_B_C"))
                                {
                                    codeFileIndex++;
                                    if (body())
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean c1()
    {
        if (codeToken.get(codeFileIndex).equals("DATATYPES"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                if (dec())
                {
                    return true;
                }
            }
        }
        else if(codeToken.get(codeFileIndex).equals("IDENTIFIER"))
        {
            codeFileIndex++;
            if (dec())
            {
                return true;
            }
        }
        else if (assign_st())
        {
            return true;
        }
        else if (codeToken.get(codeFileIndex).equals("S_C"))
        {
            codeFileIndex++;
            return true;
        }
        return false;
    }

    boolean c2()
    {
        if(codeToken.get(codeFileIndex).equals("IDENTIFIER"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("RO"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("DOT"))
                    {
                        codeFileIndex++;
                        if (codeToken.get(codeFileIndex).equals("LENGTH"))
                        {
                            codeFileIndex++;
                            return true;
                        }
                    }
                }
            }
        }
        else if (OE())
        {
            return true;
        }
        else if (codeToken.get(codeFileIndex).equals("S_C"))
        {
            return true;
        }
        return false;
    }

//    boolean c3()
//    {
//        if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//        {
//            codeFileIndex++;
//            if (X())
//            {
//                if (c4())
//                {
//                    return true;
//                }
//            }
//            else if (codeToken.get(codeFileIndex).equals("INC_DEC") || codeToken.get(codeFileIndex).equals("RO"))
//            {
//                if (c4()) {
//                    return true;
//                }
//            }
//
//        }
//        else if (inc_dec())
//        {
//            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//            {
//                codeFileIndex++;
//                if (X())
//                {
//                    return true;
//                }
//            }
//        }
//        else if (codeToken.get(codeFileIndex).equals("R_B_O"))
//        {
//            return true;
//        }
//        return false;
//    }
//
//    boolean c4() {
//        if (assign_opr())
//        {
//            if (OE())
//            {
//                return true;
//            }
//        }
//        else if (inc_dec())
//        {
//            return true;
//        }
//        return false;
//    }


    boolean c3()
    {
        if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
        {
            codeFileIndex++;
//            System.out.println(codeToken.get(codeFileIndex));
            if (codeToken.get(codeFileIndex).equals("AO") || codeToken.get(codeFileIndex).equals("INC_DEC"))
            {
                if (c4())
                {
                    return true;
                }
            }
            else if (X())
            {
                if (c4())
                {
                    return true;
                }
            }
        }
        else if (inc_dec())
        {
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                if (X())
                {
                    return true;
                }
            }
        }
        else if (codeToken.get(codeFileIndex).equals("R_B_C"))
        {
            return true;
        }
        return false;
    }

    boolean c4()
    {
        if (assign_opr())
        {
            if (OE())
            {
                return true;
            }
        }
        else if (inc_dec())
        {
            return true;
        }
        return false;
    }

    boolean assign_st()
    {
        if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
        {
            codeFileIndex++;
            if (X()) {
                if (assign_opr())
                {
                    if (OE()) {
                        if (codeToken.get(codeFileIndex).equals("S_C"))
                        {
                            codeFileIndex++;
                            return true;
                        }
                    }
                }

            }

        }
        return false;
    }

    boolean X()
    {
        if (codeToken.get(codeFileIndex).equals("DOT"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                if (X())
                {
                    return true;
                }
                else if (D())
                {
                    return true;
                }
            }
            else if (func_call())
            {
                if (X2())
                {
                    return true;
                }
            }

        }
        else if (codeToken.get(codeFileIndex).equals("R_B_O"))
        {
            codeFileIndex++;
            if (index())
            {
                if (codeToken.get(codeFileIndex).equals("R_B_C")) {
                    codeFileIndex++;
                    if (X3())
                    {
                        return true;
                    } else if (D())
                    {
//                        System.out.println("d is true" + codeToken.get(codeFileIndex));
                        return true;
                    }
                }
            }

        }
        else if (D())
        {
            return true;
        }
        return false;
    }

    boolean X2()
    {
        if (codeToken.get(codeFileIndex).equals("DOT"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                if (X())
                {
                    return true;
                }
            }
        }
        else if (codeToken.get(codeFileIndex).equals("S_C"))
        {
            codeFileIndex++;
            return true;
        }
        return false;
    }

    boolean X3()
    {
        if (codeToken.get(codeFileIndex).equals("DOT"))
        {

            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                if (X())
                {
                    return true;
                }

            }
        }
        return false;
    }

    boolean assign_opr()
    {
        if (codeToken.get(codeFileIndex).equals("ASSIGN") || codeToken.get(codeFileIndex).equals("RO"))
        {
            codeFileIndex++;
            return true;
        }
        return false;
    }

    boolean inc_dec_st()
    {
        if (codeToken.get(codeFileIndex).equals("INC_DEC"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                if (X())
                {
                    if (codeToken.get(codeFileIndex).equals("S_C"))
                    {
                        codeFileIndex++;
                    }
                }
            }
            else if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                if (X())
                {
                    if (inc_dec())
                    {
                        if (codeToken.get(codeFileIndex).equals("S_C"))
                        {
                            codeFileIndex++;
                            return true;
                        }
                    }
                }

            }

        }
        return false;
    }

    boolean inc_dec()
    {
        if (codeToken.get(codeFileIndex).equals("INC_DEC"))
        {
            codeFileIndex++;
            return true;
        }
        return false;
    }

    boolean D()
    {
        if (assign_opr())
        {
            if (OE())
            {
                if (codeToken.get(codeFileIndex).equals("S_C"))
                {
                    codeFileIndex++;
                    return true;
                }
            }
        }
        else if (inc_dec())
        {
            if (codeToken.get(codeFileIndex).equals("S_C"))
            {
                codeFileIndex++;
                return true;
            }
        }
        else if (codeToken.get(codeFileIndex).equals("S_C"))
        {
            codeFileIndex++;
            return true;
        }
        return false;
    }

    boolean func_call()
    {
        if (codeToken.get(codeFileIndex).equals("FUNCTION"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("R_B_O"))
                { codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("R_B_C"))
                    {
                        codeFileIndex++;
                        return true;
                    }
                    else if (fn_list())
                    {
                        return true;
                    }
                }
        }
        return false;
    }

    boolean fn_list()
    {
        if (codeToken.get(codeFileIndex).equals("WHOLE_CONST") || codeToken.get(codeFileIndex).equals("REAL_CONST") || codeToken.get(codeFileIndex).equals("TEXT_CONST") || codeToken.get(codeFileIndex).equals("LETTER_CONST"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("R_B_C"))
            {
                codeFileIndex++;
                return true;
            }
            else if (codeToken.get(codeFileIndex).equals("COMMA"))
            {
                codeFileIndex++;
                if (fn_list())
                {
                    return true;
                }
            }

        }
        return false;
    }

    boolean index()
    {
        if (codeToken.get(codeFileIndex).equals("WHOLE_CONST"))
        {
            codeFileIndex++;
            return true;
        }
        return false;
    }

    boolean f_list()
    {
//        if (codeToken.get(codeFileIndex).equals("COMMA"))
//        {
//            codeFileIndex++;
//            if (codeToken.get(codeFileIndex).equals("DATATYPES"))
//            {
////                scopelist.add(valuePart.get(codeFileIndex));
//                codeFileIndex++;
//                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//                {
////                    scopelist.add(valuePart.get(codeFileIndex));
////                    scopelist.add(scope+1);
//                    codeFileIndex++;
//                    if (f_list())
//                    {
//                        return true;
//                    }
//                    else if (codeToken.get(codeFileIndex).equals("R_B_C"))
//                    {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;

        if (codeToken.get(codeFileIndex).equals("COMMA"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("DATATYPES"))
            {
                FuncTable.add(valuePart.get(codeFileIndex));
                param=param+","+valuePart.get(codeFileIndex).toString();
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    FuncTable.add(valuePart.get(codeFileIndex));
                    FuncTable.add(scope);
                    f.F_Table(FuncTable);
                    FuncTable.clear();
                    codeFileIndex++;

                    if (f_list())
                    {
                        return true;
                    }
                    else if (codeToken.get(codeFileIndex).equals("R_B_C"))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean T_dash()
    {
        if (codeToken.get(codeFileIndex).equals("MDM") || codeToken.get(codeFileIndex).equals("PM"))
        {
            codeFileIndex++;
            if (F()) {
                if (T_dash())
                {
                    return true;
                }
                else {
                    return true;
                }

            }

        }
        return false;
    }

    boolean TH()
    {
        if (codeToken.get(codeFileIndex).equals("THIS"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("DOT"))
            {
                codeFileIndex++;
                return true;
            }
        }
        return false;
    }

    boolean F_dash()
    {
        if (codeToken.get(codeFileIndex).equals("R_B_O")) {
            codeFileIndex++;
            if (Pl()) {
                if (codeToken.get(codeFileIndex).equals("R_B_C")) {
                    codeFileIndex++;
                    return true;
                }
            }
        }
        else if (inc_dec())
        {
            return true;
        }
        return false;
    }

    boolean Pl()
    {
        if (OE())
        {
            if (Pl2())
            {
                return true;
            }
            else if (codeToken.get(codeFileIndex).equals("R_B_C"))
            {
                return true;
            }
        }
        return false;
    }

    boolean Pl2()
    {
        if (codeToken.get(codeFileIndex).equals("COMMA"))
        {
            codeFileIndex++;
            if (OE())
            {
                if (Pl2())
                {
                    return true;
                }
                else if (codeToken.get(codeFileIndex).equals("R_B_C"))
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean Pl3()
    {
//        if (codeToken.get(codeFileIndex).equals("DATATYPES"))
//        {
////            scopelist.add(valuePart.get(codeFileIndex));
//            codeFileIndex++;
//            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//            {
////                scopelist.add(valuePart.get(codeFileIndex));
////                scopelist.add(scope+1);
//                codeFileIndex++;
//                if (codeToken.get(codeFileIndex).equals("COMMA"))
//                {
//                    if (f_list())
//                    {
//                        return true;
//                    }
//                }
//                else if (codeToken.get(codeFileIndex).equals("R_B_C"))
//                {
//                    return true;
//                }
//            }
//        }
//        return false;

        if (codeToken.get(codeFileIndex).equals("DATATYPES"))
        {
            FuncTable.add(valuePart.get(codeFileIndex));
            param = valuePart.get(codeFileIndex).toString();
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                FuncTable.add(valuePart.get(codeFileIndex));
                FuncTable.add(scope);
                f.F_Table(FuncTable);
                FuncTable.clear();
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("COMMA"))
                {
                    if (f_list())
                    {
                        return true;
                    }
                }
                else if (codeToken.get(codeFileIndex).equals("Large bracket CL"))
                {
                    return true;
                }

            }
        }
        else if(codeToken.get(codeFileIndex).equals("R_B_C"))
        {
            return true;
        }
        return false;
    }


    boolean if_else() {

        if (codeToken.get(codeFileIndex).equals("IF"))
        {
            codeFileIndex++;
            if (OE())
            {
                if (body())
                {
                    if (o_else())
                    {
                        return true;
                    }
                    else if (codeToken.get(codeFileIndex).equals("C_B_C"))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean o_else()
    {
        if (codeToken.get(codeFileIndex).equals("ELSE"))
        {
            codeFileIndex++;
            if (body())
            {
                return true;
            }
        }
        return false;
    }

    boolean ArrayL()
    {
        if (codeToken.get(codeFileIndex).equals("ARRAYLIST"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("R_B_O"))
            {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("DATATYPES"))
                {
                    codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("R_B_C"))
                    {
                        codeFileIndex++;
                        if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                        {
                            codeFileIndex++;
                            if (codeToken.get(codeFileIndex).equals("ASSIGN"))
                            {
                                codeFileIndex++;
                                if (codeToken.get(codeFileIndex).equals("NEW"))
                                {
                                    codeFileIndex++;
                                    if (codeToken.get(codeFileIndex).equals("ARRAYLIST"))
                                    {
                                        codeFileIndex++;
                                        if (codeToken.get(codeFileIndex).equals("C_B_O"))
                                        {
                                            codeFileIndex++;
                                            if (codeToken.get(codeFileIndex).equals("C_B_C"))
                                            {
                                                codeFileIndex++;
                                                if (codeToken.get(codeFileIndex).equals("S_C"))
                                                {
                                                    codeFileIndex++;
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean while_st()
    {
        if (codeToken.get(codeFileIndex).equals("WHILE"))
        {
            codeFileIndex++;
            if (OE())
            {
//                System.out.println(codeToken.get(codeFileIndex));
                if (body())
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean COM()
    {
        if (obj_dec_list())
        {
            return true;
        }
        else if (X())
        {
            return true;
        }
        return false;
    }

    boolean obj_dec_list()
    {
        if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
        {
            codeFileIndex++;
            if (list1())
            {
                return true;
            }
        }
        else if (codeToken.get(codeFileIndex).equals("ASSIGN"))
        {
            codeFileIndex++;
            if (list2())
            {
                if (codeToken.get(codeFileIndex).equals("S_C"))
                {
                    codeFileIndex++;
                    return true;
                }
            }
        }
        return false;
    }

    boolean list1()
    {
        if (codeToken.get(codeFileIndex).equals("S_C"))
        {
            codeFileIndex++;
            return true;
        }
        else if (codeToken.get(codeFileIndex).equals("ASSIGN"))
        {
            codeFileIndex++;
            if (list2())
            {
                if (codeToken.get(codeFileIndex).equals("S_C"))
                {
                    codeFileIndex++;
                    return true;
                }

            }
        }
        else if (codeToken.get(codeFileIndex).equals("COMMA"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                codeFileIndex++;
                if (list1())
                {
                    return true;
                }
            }
        }
        return false;
    }

    boolean list2()
    {
        if (codeToken.get(codeFileIndex).equals("NEW"))
        {
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER")) {
                codeFileIndex++;
                if (codeToken.get(codeFileIndex).equals("R_B_O"))
                {
                    codeFileIndex++;

                    if (codeToken.get(codeFileIndex).equals("R_B_C"))
                    {
                        codeFileIndex++;
                        return true;

                    }
                }
            }
        }
        else if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
        {
            codeFileIndex++;
        }
        return false;
    }


    boolean interface_st()
    {
//        MainTableArr.add("null");
//        MainTableArr.add("null");
//        if (codeToken.get(codeFileIndex).equals("INTERFACE"))
//        {
//            MainTableArr.add(valuePart.get(codeFileIndex));
//            codeFileIndex++;
//            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//            {
//                if (sen.SampleTable.contains(valuePart.get(codeFileIndex)))
//                {
//                    System.out.println(valuePart.get(codeFileIndex) + " already exist");
//                    MainTableArr.clear();
//                }
//                else
//                {
//                    MainTableArr.add(valuePart.get(codeFileIndex));
//                }
//                codeFileIndex++;
//                if (implement()) {
//                    if (!MainTableArr.isEmpty())
//                    {
//                        //  System.out.println("main table" + MainTable);
//                        sen.Table(MainTableArr);
//                        //  System.out.println("table" + t.Table);
//
//                    }
//                    if (codeToken.get(codeFileIndex).equals("C_B_O"))
//                    {
//                        codeFileIndex++;
//                        if (interface_function())
//                        {
//                            if (codeToken.get(codeFileIndex).equals("C_B_C"))
//                            {
//                                codeFileIndex++;
//                                return true;
//                            }
//
//                        }
//
//                    }
//
//                }
//            }
//        }
//        return false;

        MainTableArr.add("null");
        MainTableArr.add("null");
        if (codeToken.get(codeFileIndex).equals("INTERFACE"))
        {
            MainTableArr.add(valuePart.get(codeFileIndex));
            codeFileIndex++;
            if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
            {
                if (t.Table.contains(valuePart.get(codeFileIndex)))
                {
                    System.out.println(valuePart.get(codeFileIndex) + " is already declared");
                    MainTableArr.clear();
                }
                else
                {
                    MainTableArr.add(valuePart.get(codeFileIndex));
                }
                codeFileIndex++;
                if (implement())
                {
                    if (!MainTableArr.isEmpty())
                    {
                        //  System.out.println("main table" + MainTable);
                        t.Table(MainTableArr);
                        //  System.out.println("table" + t.Table);

                    }
                    if (codeToken.get(codeFileIndex).equals("C_B_O"))
                    {
                        codeFileIndex++;
                        if (interface_function())
                        {
                            if (codeToken.get(codeFileIndex).equals("C_B_C"))
                            {
                                codeFileIndex++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean interface_function()
    {
        if (AM()) {
            if (return_type())
            {
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    codeFileIndex++;
                    if (codeToken.get(codeFileIndex).equals("R_B_O"))
                    {
                        codeFileIndex++;
                        if (codeToken.get(codeFileIndex).equals("R_B_C"))
                        {
                            codeFileIndex++;
                            if (codeToken.get(codeFileIndex).equals("S_C"))
                            {
                                codeFileIndex++;
                                if (interface_function())
                                {
                                    return true;
                                }

                            }
                        }
                    }
                }
            }
        }
        else if (codeToken.get(codeFileIndex).equals("C_B_C"))
        {
            return true;
        }
        return false;
    }

    boolean implement() {
//        if (codeToken.get(codeFileIndex).equals("IMPLEMENTS"))
//        {
//            codeFileIndex++;
//            {
//                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
//                {
//                    if (!MainTableArr.isEmpty())
//                    {
//                        if (sen.SampleTable.contains(valuePart.get(codeFileIndex)))
//                        {
//                            int a=sen.SampleTable.indexOf(valuePart.get(codeFileIndex));
//                            if(sen.SampleTable.get(a-1).equals("INTERFACE"))
//                            {
//                                MainTableArr.add(valuePart.get(codeFileIndex));
//                            }
//                            else
//                            {
//                                System.out.println(valuePart.get(codeFileIndex)+" is not INTERFACE");
//                                MainTableArr.clear();
//                            }
//
//                        } else {
//                            System.out.println(valuePart.get(codeFileIndex) + " already exist");
//                            MainTableArr.clear();
//                        }
//                    }
//                    codeFileIndex++;
//                    return true;
//                }
//            }
//        }
//        else if (codeToken.get(codeFileIndex).equals("C_B_O"))
//        {
//            if (!MainTableArr.isEmpty())
//            {
//                MainTableArr.add("null");
//            }
//            return true;
//        }
//        return false;

        if (codeToken.get(codeFileIndex).equals("IMPLEMENTS"))
        {
            codeFileIndex++;
            {
                if (codeToken.get(codeFileIndex).equals("IDENTIFIER"))
                {
                    if (!MainTableArr.isEmpty())
                    {
                        if (t.Table.contains(valuePart.get(codeFileIndex)))
                        {
                            int a=t.Table.indexOf(valuePart.get(codeFileIndex));
                            if(t.Table.get(a-1).equals("interface"))
                            {
                                MainTableArr.add(valuePart.get(codeFileIndex));
                            }
                            else
                            {
                                System.out.println(valuePart.get(codeFileIndex)+" is not an interface");
                                MainTableArr.clear();
                            }
                        }
                        else
                        {
                            System.out.println(valuePart.get(codeFileIndex) + " is not declared");
                            MainTableArr.clear();
                        }
                    }
                    codeFileIndex++;
                    return true;
                }
            }
        }
        else if (codeToken.get(codeFileIndex).equals("C_B_O"))
        {
            if (!MainTableArr.isEmpty())
            {
                MainTableArr.add("null");
            }
            return true;
        }
        return false;
    }
}