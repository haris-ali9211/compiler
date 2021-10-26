package compiler;

import java.util.ArrayList;

public class Class 
{
    private String name;
    private String am;
    private boolean isGlobal;
    private boolean isAbstract;
    private String inherit;
    private ArrayList<Entity> EntityTable;

    public Class(String name, String am, boolean isGlobal, boolean isAbstract, String inherit) 
    {
        this.name = name;
        this.am = am;
        this.isGlobal = isGlobal;
        this.isAbstract = isAbstract;
        this.inherit = inherit;
        this.EntityTable = new ArrayList<Entity>();
    }
    
    public void insertEntity(String name, String type, String am, boolean isAssigned, boolean isConstant, boolean isGlobal, boolean isAbstract)
    {
        this.EntityTable.add(new Entity(name,type,am,isAssigned,isConstant,isGlobal,isAbstract));
    }
    
    public boolean entityLookUp(String Name, String Type)
    {
        for (int i = 0; i < this.EntityTable.size(); i++) 
        {
            if (this.EntityTable.get(i).getName().equals(Name) && this.EntityTable.get(i).getType().equals(Type)) 
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean entityLookUp(String Name)
    {
        for (int i = 0; i < this.EntityTable.size(); i++) 
        {
            if (this.EntityTable.get(i).getName().equals(Name)) 
            {
                return true;
            }
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public void setIsGlobal(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public void setIsAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public void setInherit(String inherit) {
        this.inherit = inherit;
    }

    public void setEntityTable(ArrayList<Entity> EntityTable) {
        this.EntityTable = EntityTable;
    }

    public String getName() {
        return name;
    }

    public String getAm() {
        return am;
    }

    public boolean getGlobal() {
        return isGlobal;
    }

    public boolean isIsAbstract() {
        return isAbstract;
    }

    public String getInherit() {
        return inherit;
    }

    public ArrayList<Entity> getEntityTable() {
        return EntityTable;
    }
    
    
    
}
