package parser;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        /*ArrayList<String> test = new ArrayList<String>();
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");
        
        for(String s : test)
        {
            if(s.equals("c"))
            {
                test.set(test.indexOf(s), "CCC");
            }
            System.out.println(s);
        }
        
        System.out.println();
        
        for(int i=0; i<test.size(); i++)
        {
            if(test.get(i).equals("d"))
            {
                test.set(i, "ASB");
            }
            System.out.println(test.get(i));
        }
        System.out.println();
        
        for(String s : test)
            System.out.println(s);*/
        
        RunTimeMethods run = new RunTimeMethods();
        run.initialFileInput();
    }
}
