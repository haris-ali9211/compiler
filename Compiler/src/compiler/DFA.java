package compiler;

import java.util.regex.*;

public class DFA 
{
    private Matcher m;
    private int noofSt;
    private String[] repStr;
    private int[][] TT;
    int IS;
    private int[] FS;
    
    public DFA(int NS,String[] IC,int[][] TT,int IS,int[] FS)
    {
        this.noofSt = NS;
        this.repStr = IC;
        this.TT = TT;
        this.IS = IS;
        this.FS = FS;
    }
    
    public boolean validate(String input)
    {
        boolean validcheck = false;
        String inp[] = input.split("");
        int CS = this.IS;
        for(int i = 0; i<input.length();i++)
        {
            CS = transition(CS,inp[i]);
        }
        for(int i = 0 ;i<FS.length;i++)
        {
            if(CS == FS[i])
            {
                validcheck = true;
                break;
            }
            else
            {
                validcheck =  false;
            }
        }
        return validcheck;
    }
    
    int transition(int St,String str)
    {
        int ind = -1;
        for(int i = 0; i < this.repStr.length;i++)
        {
            Pattern p = Pattern.compile(this.repStr[i]);
            this.m = p.matcher(str);
            if(this.m.matches())
            {
                ind = i;
                break;
            }
        }
        if(ind != -1)
        {
            return this.TT[St][ind];
        }
        else
        {
            return this.noofSt;
        }
    }
    boolean onFinal(int state)
    {
        for (int i = 0; i < this.FS.length; i++)
        {
            if(FS[i]==state)
            {
                return true;
            }
        }
        return false;
    }
}
