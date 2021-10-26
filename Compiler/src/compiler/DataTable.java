package compiler;

import java.util.ArrayList;

public class DataTable {
    ArrayList DT = new ArrayList() ;


    void D_Table(ArrayList D) {

        Object Type= D.get(0);
        Object Name= D.get(1);
        Object AM= D.get(2);

        DT.add(Type);
        DT.add(Name);
        DT.add(AM);
//        System.out.println(DT);

    }

    void D_Table(Object s) {
        System.out.println("\t \t \t \t \t Class "+s+" Body Table:");
        System.out.println("\t \t \t \t \t -----------------------");
        System.out.println(" \t\t      **************************************************************");
        System.out.println( " \t\t      Type" +  " \t\t     Name  " + " \t\t    Access Modifiers");
        System.out.println(" \t\t      **************************************************************");
        for (int i = 0; i < DT.size(); i++) {

            System.out.print(" \t\t      " + DT.get(i)   );
            if ((i + 1) % 3 == 0) {
                System.out.println("");
            }
        }
        System.out.println(" \t\t      **************************************************************");
        System.out.println("");
        System.out.println("");

    }
}
