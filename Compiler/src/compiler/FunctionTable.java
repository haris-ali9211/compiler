package compiler;

import java.util.ArrayList;

public class FunctionTable {
    ArrayList F_Table = new ArrayList();

    void F_Table(ArrayList F) {

        Object Name= F.get(0);
        Object Type= F.get(1);
        Object Scope= F.get(2);

        F_Table.add(Name);
        F_Table.add(Type);
        F_Table.add(Scope);
//        System.out.println(F_Table);
    }

    void F_Table() {
        System.out.println("\t \t \t \t \t Function Table:");
        System.out.println("\t \t \t \t \t ---------------");
        System.out.println("\t\t     *****************************************************");
        System.out.println( " \t\t      Type" +  " \t\t     Name  " + " \t\t    Scope");
        System.out.println("\t\t     *****************************************************");
        for (int i = 0; i < F_Table.size(); i++) {

            System.out.print(" \t\t      " + F_Table.get(i)   );
            if ((i + 1) % 3 == 0) {
                System.out.println("");
            }
        }
        System.out.println("\t\t     *****************************************************");
        System.out.println("");
        System.out.println("");

    }
}
