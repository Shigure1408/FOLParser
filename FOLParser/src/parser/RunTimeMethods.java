package parser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import objects.*;

public class RunTimeMethods
{
    public LinkedList<FOLObject> objects;

    public LinkedList<FOLVariable> variables;

    public LinkedList<FOLFunction> functions;

    FOLParser parser = new FOLParser();

    public void initialFileInput()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Input first file name/location:");
        Path path = Paths.get(scan.nextLine());
        while (Files.notExists(path))
        {
            System.out.println("Cannot find file, try again");
            path = Paths.get(scan.nextLine());
        }

        parser.ParseFirstFile(scan.nextLine(), objects, variables, functions);
        
        scan.close();
    }

    public void waitForInput()
    {
        Scanner scan = new Scanner(System.in);
        while (true)
        {
            System.out.println("Awaiting Input:");
            System.out.println(scan.nextLine());
            if (true)
            {
                break;
            }
        }

        scan.close();
    }
}
