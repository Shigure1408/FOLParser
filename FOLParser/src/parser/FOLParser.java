package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import objects.FOLElement;
import objects.FOLFunction;
import objects.FOLObject;
import objects.FOLVariable;
import exceptions.ForbiddenKeywordException;
import exceptions.InvalidOverwriteException;

public class FOLParser
{
    private String name = "[a-zA-Z0-9_]+";

    private String number = "[0-9]+";

    private String function = name + "\\(.*\\)"; // TODO: Fix expression

    private String term = "(nie )?" + function;

    @SuppressWarnings("unused")
    private String formula = term + "( lub " + term + ")*";

    public void ParseFile(String fileLocation, ArrayList<FOLObject> objectList, ArrayList<FOLVariable> variableList, ArrayList<FOLFunction> functionList) // DEFINITION
    // of
    // variables,
    // objects
    // and
    // functions
    {
        try
        {

            String line = null;

            FileReader freader = new FileReader(fileLocation);
            BufferedReader reader = new BufferedReader(freader);
            while ((line = reader.readLine()) != null) // read & match line
            {
                ParseSingleLine(line, objectList, variableList, functionList);
            }
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void ParseSingleLine(String singleString, ArrayList<FOLObject> objectList, ArrayList<FOLVariable> variableList, ArrayList<FOLFunction> functionList) throws ForbiddenKeywordException, InvalidOverwriteException
    {
        String[] split = singleString.split(" ");
        if (singleString.matches("TELL object " + name))
        {
            if (split[2].equals("lub") || split[2].equals("nie"))
            {
                throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\"");
            }
            AddOrOverwrite(new FOLObject(split[2]), objectList, variableList, functionList);
        }
        else if (singleString.matches("TELL variable " + name))
        {
            if (split[2].equals("lub") || split[2].equals("nie"))
            {
                throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\"");
            }
            AddOrOverwrite(new FOLVariable(split[2]), objectList, variableList, functionList);
        }
        else if (singleString.matches("TELL function " + name + " " + number))
        {
            if (split[2].equals("lub") || split[2].equals("nie"))
            {
                throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\"");
            }
            if (Integer.parseInt(split[3]) == 0)
            {
                throw new IllegalArgumentException("Illegal number value \"" + split[3] + "\"");
            }
            AddOrOverwrite(new FOLFunction(split[2], Integer.parseInt(split[3])), objectList, variableList, functionList);
        }
        else if (singleString.matches("TELL formula "))
        {

        }
        else
        // If line matches none of the expected inputs
        {

        }
    }

    public void AddOrOverwrite(FOLElement element, ArrayList<FOLObject> objectList, ArrayList<FOLVariable> variableList, ArrayList<FOLFunction> functionList) throws InvalidOverwriteException
    {
        for (int i = 0; i < objectList.size(); i++) // CHECK objectList for
                                                    // duplicate name
        {
            if (objectList.get(i).name.equals(element.name))
            {
                if (element instanceof FOLObject)
                {
                    objectList.set(i, (FOLObject) element); // duplicate of same
                                                            // type gets
                                                            // replaced
                    return;
                }
                else
                {
                    throw new InvalidOverwriteException("Name \"" + element.name + "\" for Object already in use by Variable/Function");
                }
            }
        }

        for (int i = 0; i < variableList.size(); i++) // CHECK variableList for
                                                      // duplicate name
        {
            if (variableList.get(i).name.equals(element.name))
            {
                if (element instanceof FOLVariable)
                {
                    variableList.set(i, (FOLVariable) element); // duplicate of
                                                                // same type
                                                                // gets replaced
                    return;
                }
                else
                {
                    throw new InvalidOverwriteException("Name \"" + element.name + "\" for Variable already in use by Object/Function");
                }
            }
        }

        for (int i = 0; i < functionList.size(); i++) // CHECK functionList for
                                                      // duplicate name
        {
            if (functionList.get(i).name.equals(element.name))
            {
                if (element instanceof FOLFunction)
                {
                    if (((FOLFunction) element).number != functionList.get(i).number)
                    {
                        throw new InvalidOverwriteException("Function Overwrite \"" + element.name + "\" needs to have the same number of parameters");
                    }

                    functionList.set(i, (FOLFunction) element); // duplicate of
                                                                // same type
                                                                // gets replaced
                    return;
                }
                else
                {
                    throw new InvalidOverwriteException("Name \"" + element.name + "\" for Function already in use by Variable/Object");
                }
            }
        }

        if (element instanceof FOLObject)
        {
            objectList.add((FOLObject) element);
            return;
        }
        else if (element instanceof FOLVariable)
        {
            variableList.add((FOLVariable) element);
            return;
        }
        else if (element instanceof FOLFunction)
        {
            functionList.add((FOLFunction) element);
            return;
        }

    }

}