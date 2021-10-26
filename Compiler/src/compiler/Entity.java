package compiler;

public class Entity 
{
    private String name;
    private String type;
    private String am;
    private boolean isAssigned;
    private boolean isConstant;
    private boolean isGlobal;
    private boolean isAbstract;

    public Entity(String name, String type, String am, boolean isAssigned, boolean isConstant, boolean isGlobal, boolean isAbstract) {
        this.name = name;
        this.type = type;
        this.am = am;
        this.isAssigned = isAssigned;
        this.isConstant = isConstant;
        this.isGlobal = isGlobal;
        this.isAbstract = isAbstract;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public void setIsConstant(boolean isConstant) {
        this.isConstant = isConstant;
    }

    public void setIsGlobal(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public void setIsAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAm() {
        return am;
    }

    public boolean isIsAssigned() {
        return isAssigned;
    }

    public boolean isIsConstant() {
        return isConstant;
    }

    public boolean isIsGlobal() {
        return isGlobal;
    }

    public boolean isIsAbstract() {
        return isAbstract;
    }

    
}
