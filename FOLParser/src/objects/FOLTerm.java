package objects;

public class FOLTerm
{
    public FOLFunction func;
    public boolean notInverted;
    
    public FOLTerm(FOLFunction func, boolean doNotInvert)
    {
        this.func=func;
        this.notInverted=doNotInvert;
    }
       
}
