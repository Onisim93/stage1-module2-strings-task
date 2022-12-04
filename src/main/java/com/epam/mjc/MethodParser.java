package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

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
        int indexFirstBracket = signatureString.indexOf("(");
        int indexLastBracket = signatureString.indexOf(")");
        String[] parseSignatureString = signatureString.substring(0, indexFirstBracket).split(" ");
        int counter = 0;
        String accessModifier = parseSignatureString.length > 2 ? parseSignatureString[counter++] : null;
        String returnType = parseSignatureString[counter++];
        String methodName = parseSignatureString[counter];

        List<MethodSignature.Argument> argumentList = new ArrayList<>();

        if (indexFirstBracket != indexLastBracket-1) {
            String[] parseArguments = signatureString.substring(indexFirstBracket+1, indexLastBracket).split(",");
            for (String pair : parseArguments) {
                String[] keyValue = pair.trim().split(" ");
                MethodSignature.Argument argument = new MethodSignature.Argument(keyValue[0], keyValue[1]);
                argumentList.add(argument);
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, argumentList);
        if (accessModifier != null) {
            methodSignature.setAccessModifier(accessModifier);
        }

        methodSignature.setReturnType(returnType);


        return methodSignature;
    }
}
