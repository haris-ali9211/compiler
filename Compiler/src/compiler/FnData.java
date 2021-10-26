package compiler;

public class FnData 
{
    private String name;
    private String type;
    private int scope;
    private boolean isAssigned;
    
    public FnData(String name, String type, int scope, boolean isAssigned) 
    {
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.isAssigned = isAssigned;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getScope() {
        return scope;
    }

    public boolean isIsAssigned() {
        return isAssigned;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }    
}
