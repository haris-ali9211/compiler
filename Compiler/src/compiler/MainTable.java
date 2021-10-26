package compiler;

import java.util.ArrayList;

public class MainTable {
    ArrayList Table = new ArrayList();

    void Table(ArrayList A) {
        Object AccesModifiers = A.get(0);
        Object Category = A.get(1);
        Object Type = A.get(2);
        Object Name = A.get(3);
        Object Parent = A.get(4);




        Table.add(AccesModifiers);
        Table.add(Category);
        Table.add(Type);
        Table.add(Name);
        Table.add(Parent);
        //Table.add(Link);


    }

    void Table() {
        System.out.println("\t \t \t \t \t  Main Table:");
        System.out.println("\t \t \t \t \t  -----------");
        System.out.println("**************************************************************************************");
        System.out.println("Access Modifier " + "   Category " +  "           Type " + "           Name " + "         Parent " + "     Link");
        System.out.println("**************************************************************************************");
        for (int i = 0; i < Table.size(); i++) {

            System.out.print("  " + Table.get(i) + "           ");
            if ((i + 1) % 5 == 0) {
                System.out.println("");
            }
        }
        System.out.println("**************************************************************************************");
        System.out.println("");
        System.out.println("");

    }
}
