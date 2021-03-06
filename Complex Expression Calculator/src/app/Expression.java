package app;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {

    
    public static String delims = " \t*+-/()[]";
            
    /**
     * Populates the vars list with simple variables, and arrays lists with arrays
     * in the expression. For every variable (simple or array), a SINGLE instance is created 
     * and stored, even if it appears more than once in the expression.
     * At this time, values for all variables and all array items are set to
     * zero - they will be loaded from a file in the loadVariableValues method.
     * 
     * @param expr The expression
     * @param vars The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
 
    //then do cases 
    public static void 
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        
        String arrholder = "";
        String name = "";
        for (int i = 0; i < expr.length(); i++) { //float.parse float
            char k = expr.charAt(i);
            String l = Character.toString(k);


            if (Character.isLetter(k) == true) {  //if the character at k is a letter //this should also handle ()
                // if there is a space after the current character
                name = name + l; 
                boolean sp = i+1 < expr.length();  //makes it so that u dont get out of bounds
                if (sp == true) {
                    if (expr.charAt(i+1) == ' ') { //if its at the end of the variable
                        //System.out.println("variable is: " + name); 
                        Variable v = new Variable (name);
                        vars.add(v); 
                        name = "";

                    } 
                    // a + A[b*b B[a]]
                    else if (expr.charAt(i+1) == '[') { //if its an array 
                        //System.out.println("array is: " + name); 
                        Array ar = new Array(name);
                        arrays.add(ar); 
                        name = "";
                    } // varsa + ...
                    else if (expr.charAt(i+1) != ' ' && Character.isLetter(expr.charAt(i+1))) {


                    } 
                    // w]
                    else if (expr.charAt(i+1) != ' ' && !Character.isLetter(expr.charAt(i+1))) { 
                        //System.out.println("variable is: " + name); 
                        Variable v = new Variable (name);
                        vars.add(v); 
                        name = "";

                    } 
                } else {
                    //System.out.println("var (i+1) is: " + name); 
                    Variable v = new Variable(name); 
                    vars.add(v); 
                    name = ""; 
                }

            }


        }       
        /** COMPLETE THIS METHOD **/
        /** DO NOT create new vars and arrays - they are already created before being sent in
         ** to this method - you just need to fill them in.
         **/
    }
    
    /**
     * Loads values for variables and arrays in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     * @param vars The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     */
    //
    public static void 
    loadVariableValues(Scanner sc, ArrayList<Variable> vars, ArrayList<Array> arrays) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String tok = st.nextToken();
            Variable var = new Variable(tok);
            Array arr = new Array(tok);
            int vari = vars.indexOf(var);
            int arri = arrays.indexOf(arr);
            if (vari == -1 && arri == -1) {
                continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                vars.get(vari).value = num;
            } else { // array symbol
                arr = arrays.get(arri);
                arr.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    arr.values[index] = val;              
                }
            }
        }
    }
    
    /**
     * Evaluates the expression.
     * 
     * @param vars The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */

    public static float 
    evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
        Stack<Float> vAndA = new Stack<Float>(); //stack that holds the numbers variables and arrays
        Stack<Character> operator = new Stack<Character>();//stack that holds the operators 
        String name = ""; //long vars name 
        String valuesInputed = ""; //holds all the values
        int namedata1 = 0;//vars
        int namedata2 = 0; 
        int [] ff = null;//arrays
        String kk = "";
        String arrholder = "";
        float sum = 0; 
        int numArray = 0;
        String arrayParent = ""; 
        float arrayParentEquals = 0; 
        
        for (int i = 0; i < expr.length(); i++) { //float.parse float
            char k = expr.charAt(i);
            String l = Character.toString(k);


            if (Character.isLetter(k) == true) {  //if the character at k is a letter //this should also handle ()
                // if there is a space after the current character
                name = name + l; 
                boolean sp = i+1 < expr.length();  //makes it so that u dont get out of bounds
                if (sp == true) {
                    if (expr.charAt(i+1) == ' ') { //if its at the end of the variable
                        //System.out.println("variable is: " + name); 
                        for (int j =0; j<vars.size(); j++) { //travers the vars to find its match
                            if (vars.get(j).name.equals(name)) { //if the index in the vars equals the character //was previous name in the .equals
                                namedata1 = vars.get(j).value; //gets the value of that index and sets it to namedata
                                valuesInputed= valuesInputed + Integer.toString(namedata1); //adds this to the string that we input all the values to
                                name = "";
                                //System.out.print(namedata1);
                            }
                        }
                        name = "";

                    } 
                    // a + A[b*b B[a]]
                    else if (expr.charAt(i+1) == '[') { //if its an array 
                        //System.out.println("array is: " + name); 
                        String arrString = ""; 
                        int arr = 0; 
                        //if (i + 2 <expr.length()) { //
                        numArray++; 
                        for (arr = i+2; arr < expr.length(); arr++) { //arrays
                            if (expr.charAt(arr) == '[') {
                                numArray++; 
                            }
                            else if (expr.charAt(arr) == ']'){
                                numArray--; 
                                if (numArray == 0) {
                                    arrayParent = expr.substring(i + 2, arr);
                                    arrayParentEquals = evaluate(arrayParent, vars, arrays);
                                    boolean bounds = arr+1 < expr.length(); 
                                    if (bounds) {
                                        if (expr.charAt(arr+1) == ')') {
                                            i = arr;
                                        } else {
                                            i = arr + 1; 
                                        } 
                                    } else {
                                        i = arr + 1; 
                                    }
                                    break; 
                                }
                                //arrString = arrString + expr.charAt(arr); 
                            }
                        }
                        
//                        // increment i
//                        i = arr + 1; 
//                        // now, have the string of the array contents, need to recurse
//                        float eval = evaluate(arrString, vars, arrays); 
                        
                        // now, get value of array and plug into valuesInputed
                        for (int j = 0; j < arrays.size(); j++) {
                            if (arrays.get(j).name.equals(name)) {
                                namedata1 = arrays.get(j).values[(int)arrayParentEquals]; 
                            }
                        }
                        valuesInputed = valuesInputed + Integer.toString(namedata1) + " "; 
                        name = "";
                    } // varsa + ...
                    else if (expr.charAt(i+1) != ' ' && Character.isLetter(expr.charAt(i+1))) { 
                        continue;  
                    } 
                    // w] -> variable
                    else if (expr.charAt(i+1) != ' ' && !Character.isLetter(expr.charAt(i+1))) { 
                        //System.out.println("variable is: " + name); 
                        for (int j =0; j<vars.size(); j++) { //travers the vars to find its match
                            if (vars.get(j).name.equals(name)) { //if the index in the vars equals the character //was previous name in the .equals
                                namedata1 = vars.get(j).value; //gets the value of that index and sets it to namedata
                                valuesInputed= valuesInputed + Integer.toString(namedata1); //adds this to the string that we input all the values to
                                name = "";
                                //System.out.print(namedata1);
                            }
                        }
                        name = "";

                    } 
                } else {
                    // variable
                    //System.out.println("var (i+1) is: " + name); 
                    for (int j =0; j<vars.size(); j++) { //travers the vars to find its match
                        if (vars.get(j).name.equals(name)) { //if the index in the vars equals the character //was previous name in the .equals
                            namedata1 = vars.get(j).value; //gets the value of that index and sets it to namedata
                            valuesInputed= valuesInputed + Integer.toString(namedata1); //adds this to the string that we input all the values to
                            name = "";
                            //System.out.print(namedata1);
                        }
                    }
                    name = ""; 
                }

            } else {
                valuesInputed = valuesInputed + l; 
            }


        }
        
        
        //System.out.println("valuesInputed = " + valuesInputed); 
    
        try {
            sum = Integer.parseInt(valuesInputed); //will catch the case if it is like 1 or 1000 or one number
            return sum; 
        } catch (Exception e) { 
        }


        String num = "";
        String gigi = "";
        char currPoint = ' ';
        String jsjs = "";
        int ho = 0;
        float hos = 0;
        int sizV = 0;
        int sizO = 0;
        String mulholder = "";
        float r = 0;
        float volar = 0;
        char whatISop = ' ';
        int whereIt = 0;
        String parent = "";
        float parentEquals = 0;
        String wholeThingtoadd = "";
        String rev = "";
        int numParenth = 0; 

        for (int s = 0; s < valuesInputed.length(); s++) { //goes through the string with the values that have been inputed
            currPoint = valuesInputed.charAt(s); //sets curr point to s
            jsjs = jsjs + currPoint; //makes the new string     

            if (currPoint != ' ' && currPoint == '(' ) {
                //whereIt = s; 
                for (int it = s; it < valuesInputed.length(); it++) {
                    currPoint = valuesInputed.charAt(it);
                    if (currPoint != ' ' && currPoint == '(' ) {
                        numParenth++; 
                    } else if (currPoint != ' ' && currPoint == ')') {
                        numParenth--; 
                        if (numParenth == 0) {
                            parent = valuesInputed.substring(s + 1, it);
                            parentEquals= evaluate(parent, vars, arrays);
                            vAndA.push(parentEquals);
                            s = it + 1; 
                            break; 
                        } 
                    }
                }

            }
            else if ( Character.isDigit(currPoint) == true) { //now if it is a digit
                num = num + currPoint;
                if (s + 1 < valuesInputed.length()) {
                    for (int j = s + 1; j < valuesInputed.length(); j++) {
                        if (Character.isDigit(valuesInputed.charAt(j))) {
                            num = num + valuesInputed.charAt(j);
                        }
                        else {
                            break;
                        }
                        s++;
                    }
                }
                ho = Integer.parseInt(num);
                hos = (float) ho; 
                if (operator.isEmpty() == false) {
                    whatISop = operator.peek();

                    if (whatISop == '*') {
                        operator.pop();
                        volar = vAndA.pop() * (hos);
                        vAndA.push(volar);

                    }
                    else if (whatISop == '/') {
                        operator.pop();
                        volar = vAndA.pop() / (hos);
                        vAndA.push(volar);

                    }
                    else {
                        vAndA.push(hos);//push bc it is add or sub
                    }
                }
                else {
                    vAndA.push(hos);
                }
                num = "";
                whatISop = ' ';
            }

            else if (currPoint != ' ' && (currPoint == '+' || currPoint == '-' || currPoint == '/' || currPoint == '*')) { //if its an operator than push it

                if (currPoint == '+' || currPoint == '-' ) { //push that op in bc it has a low precidence
                    operator.push(currPoint);
                }
                else if (currPoint == '/' || currPoint == '*') {
                    operator.push(currPoint);
                } 
            }
        }
        
        /* String 3 - 6 - 7
         * iteration 1: 
         * pop 7, pop - 
         * new = - 7 
         * 
         * new = - 6 + new
         */
        while (!vAndA.isEmpty()) { //makes the string
            if (operator.size() >= 1) {
                float t = vAndA.pop(); //pop top of vAndA
                char s = operator.pop(); //pop the mul and divide 
                wholeThingtoadd = Character.toString(s) + " " + Float.toString(t) + " " + wholeThingtoadd; 
            } else {
                float t = vAndA.pop(); //pop top of vAndA
                wholeThingtoadd = Float.toString(t) + " " + wholeThingtoadd; 
            }
        }
        
        
        // string beep
        StringTokenizer org = new StringTokenizer(wholeThingtoadd, " "); 
        String tok = "";  
        
        while (org.hasMoreTokens()) {
            tok = org.nextToken(); 
            if (tok.equals("*") || tok.equals("/")) {
                operator.push(tok.charAt(0));
            } else if (tok.equals("+") || tok.equals("-")) {
                operator.push(tok.charAt(0));
            } else {
                vAndA.push(Float.parseFloat(tok)); 
                if (!operator.isEmpty()) {
                    if (operator.peek() == '*' || operator.peek() == '/') {
                        //String tok2 = org.nextToken(); 
                        float c = vAndA.pop(); 
                        float a = vAndA.pop(); 
                        char b = operator.pop(); 
                        vAndA.push(dotheMATH (a, b, c));
                    }
                }
            }
        }
        
        // now add to string
        String beforeAddNSub = ""; 
        while (!vAndA.isEmpty()) { //makes the string
            if (operator.size() >= 1) {
                float t = vAndA.pop(); //pop top of vAndA
                char s = operator.pop(); //pop the mul and divide 
                beforeAddNSub = Character.toString(s) + " " + Float.toString(t) + " " + beforeAddNSub; 
            } else {
                float t = vAndA.pop(); //pop top of vAndA
                beforeAddNSub = Float.toString(t) + " " + beforeAddNSub; 
            }
        }
        
        //System.out.println(wholeThingtoadd);
        
        // Now, consilidate all the pluses and minuses and finish calculations 
        StringTokenizer organized = new StringTokenizer(beforeAddNSub, " "); 
        String token = ""; 
        while (organized.hasMoreTokens()) {
            token = organized.nextToken(); 
            
             if (token.equals("+") || token.equals("-")) { //orr mul...
                operator.push(token.charAt(0)); 
            } else {
                vAndA.push(Float.parseFloat(token)); 
            }
            // if vna size == 2, do the operation and push back 
            if (vAndA.size() == 2 && !operator.isEmpty()) {
                float a = vAndA.pop(); //pop top of vAndA
                char b = operator.pop(); //pop the mul and divide 
                float c = vAndA.pop(); //pop the new top of the vanda
                vAndA.push(dotheMATH (a, b, c));// need to change so it does comp at the end of that loop
            }
        }
        // get final float.. our answer!
        sum = vAndA.pop(); 
        //System.out.println(sum); 
        return sum;
    }

    /*
     * Helper-Method to do the calculations because it would make the code very long if I didnt have this here
     */
    public static float dotheMATH (float a, char b, float c) { // this helper method will do the math based off of what the poped values are 
        float theMathisDONE = 0;
        if (b == '+') {
            theMathisDONE = a + c;
        }
        else if (b == '-') {
            theMathisDONE = c - a;
        }
        else if (b == '*') {
            theMathisDONE = a * c;
        }
        else if (b == '/') {
            theMathisDONE = c / a;
        }
        return theMathisDONE;

    }
}
