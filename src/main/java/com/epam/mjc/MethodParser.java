package com.epam.mjc;

import com.epam.mjc.MethodSignature.Argument;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        //throw new UnsupportedOperationException("You should implement this method.");
        String accessModifier = "";
        String returnType;
        String methodName;


        String[] str = signatureString.split("\\(");
        StringTokenizer st1 = new StringTokenizer(str[0], " ");

        if (st1.countTokens() == 3){
            accessModifier = st1.nextToken();
            returnType = st1.nextToken();
            methodName = st1.nextToken();
        } else {
            returnType = st1.nextToken();
            methodName = st1.nextToken();
        }

        str[1] = str[1].substring(0,str[1].length()-1);
        StringTokenizer st2 = new StringTokenizer(str[1], " ");

        List<Argument> args = new ArrayList<>();
        while (st2.hasMoreTokens()){
            String type = st2.nextToken();
            String name = st2.nextToken();
            if( name.endsWith(",") ) {
                name = name.substring(0,name.length()-1);
            }
            Argument arg = new Argument(type, name);
            args.add(arg);
        }
        if (args.size() > 0){
            MethodSignature ms = new MethodSignature(methodName, args);
            ms.setReturnType(returnType);
            ms.setAccessModifier(accessModifier.equals("") ? null : accessModifier);
            return ms;
        } else {
            MethodSignature ms = new MethodSignature(methodName);
            ms.setAccessModifier(accessModifier.equals("") ? null : accessModifier);
            ms.setReturnType(returnType);
            return ms;
        }

    }
}
