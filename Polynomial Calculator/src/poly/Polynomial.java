package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
    
    /**
     * Reads a polynomial from an input stream (file or keyboard). The storage format
     * of the polynomial is:
     * <pre>
     *     <coeff> <degree>
     *     <coeff> <degree>
     *     ...
     *     <coeff> <degree>
     * </pre>
     * with the guarantee that degrees will be in descending order. For example:
     * <pre>
     *      4 5
     *     -2 3
     *      2 1
     *      3 0
     * </pre>
     * which represents the polynomial:
     * <pre>
     *      4*x^5 - 2*x^3 + 2*x + 3 
     * </pre>
     * 
     * @param sc Scanner from which a polynomial is to be read
     * @throws IOException If there is any input error in reading the polynomial
     * @return The polynomial linked list (front node) constructed from coefficients and
     *         degrees read from scanner
     */
    public static Node read(Scanner sc) 
    throws IOException {
        Node poly = null;
        while (sc.hasNextLine()) {
            Scanner scLine = new Scanner(sc.nextLine());
            poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
            scLine.close();
        }
        return poly;
    }
    
    /**
     * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
     * The returned polynomial MUST have all new nodes. In other words, none of the nodes
     * of the input polynomials can be in the result.
     * 
     * @param poly1 First input polynomial (front of polynomial linked list)
     * @param poly2 Second input polynomial (front of polynomial linked list
     * @return A new polynomial which is the sum of the input polynomials - the returned node
     *         is the front of the result polynomial
     */
    public static Node add(Node poly1, Node poly2) {
        Node addedList = null; //start of ll to add to
        Node tail = null;
            
            if (poly1 == null && poly2 == null) { //meaning nothing in either list 
                return null;
                }
            if (poly1 == null) { //if there is nothing in poly1 list then just return poly 2 list 
                return poly2;
                }
            if (poly2 == null) { // if there is nothing in poly 2 list then return poly 1
                return poly1;
                }
    Node ptr = poly1;
    Node ptr2 = poly2;
     while (ptr != null && ptr2 != null) { //while both LL are not equal to null //set another in
         Node adding = null;
            //if (poly1.term.coeff + poly2.term.coeff != 0) {
             if (ptr.term.degree < ptr2.term.degree) { //if the degree of the first list node is less than the second
                   adding = new Node (ptr.term.coeff, ptr.term.degree, null); //add the lesser term because next deg can be equal
                ptr = ptr.next; //increment poly1 to the next node to compare    
           }
           
             else if (ptr.term.degree > ptr2.term.degree) {//if the degree of the first is greater than the second
                adding = new Node (ptr2.term.coeff, ptr2.term.degree, null);//adds the term from the first LL that was greater to the new LL
                ptr2 = ptr2.next; //increment poly1 to the next node to compare    
           }    
           
             else if (ptr.term.degree == ptr2.term.degree){//if the degrees of both are equal
                   adding = new Node (ptr.term.coeff + ptr2.term.coeff, ptr.term.degree, null);
                   ptr = ptr.next;//increment poly1 to the next node to compare
                   ptr2 = ptr2.next;//increment poly2 to the next node to compare    
           }
                 
             if (addedList == null) { // so if its the first in LL only 1 time
                    tail = adding; //set tail to the new node bc that is the back
                    addedList = adding; //set the new node to added list 
                } else {
                        tail.next = adding; // this links newito the ll
                        tail = adding; // this points tail to newi
                    
                }
           
        
                 
     while (ptr == null && ptr2 != null) {//if its at the end of the 1 list it should keep adding the terms of the 2 list so long as 1 is not null
         if (addedList == null) { // so if its the first in LL only 1 time
             tail = ptr2; //set tail to the new node bc that is the back
             addedList = ptr2; //set the new node to added list 
         } else {
             tail.next = ptr2; // this links newi to the ll
             tail = ptr2; // this points tail to newi

         }
         ptr2 = ptr2.next;
     }
     while (ptr2 == null && ptr != null) {
         if (addedList == null) { // so if its the first in LL only 1 time
             tail = ptr; //set tail to the new node bc that is the back
             addedList = ptr; //set the new node to added list 
         } else {
             tail.next = ptr; // this links newi to the ll
             tail = ptr; // this points tail to newi
         }
         ptr = ptr.next;
     }
    }
     Node headN = null;  
     Node tailN = null; 
     for (Node ptr3 = addedList; ptr3 != null; ptr3 = ptr3.next) {
         if (ptr3.term.coeff != 0) {
             if (headN == null) {
                 tailN = ptr3; 
                 headN = ptr3; 
             } else {
                tailN.next = ptr3; 
                tailN = ptr3; 
             }
         }
     }
     return headN;
    }

    
    /**
     * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
     * The returned polynomial MUST have all new nodes. In other words, none of the nodes
     * of the input polynomials can be in the result.
     * 
     * @param poly1 First input polynomial (front of polynomial linked list)
     * @param poly2 Second input polynomial (front of polynomial linked list)
     * @return A new polynomial which is the product of the input polynomials - the returned node
     *         is the front of the result polynomial
     */
    public static Node multiply(Node poly1, Node poly2) { //what if multiply by null
//Multiply work
        Node startMultiply = null; //start with an empty LL to add to 
        Node tail = null;
        if (poly1 == null || poly2 == null) { //meaning nothing in either list 
            return null;
        }
        
        for(Node ptr1 = poly1; ptr1 != null; ptr1 = ptr1.next) { // traverses through poly1
            for (Node ptr2 = poly2; ptr2 != null; ptr2= ptr2.next) { //traverses through poly2
                float coe = ptr1.term.coeff * ptr2.term.coeff; //multiplies the coeffs (3x^3 * 3x^4) = 9 as coeff
                int deg = ptr1.term.degree + ptr2.term.degree;//adds the degrees (3x^3 * 3x^4) = 7 as deg
                Node multiNewnode= new Node (coe, deg, null);
                
                int didFind = 0; // this is a flag so if zero that means u did not find a matching degree so you have to add to the end 
                Node ptr3 = startMultiply;
                if (ptr3 == null) {
                    startMultiply = multiNewnode; 
                    tail = multiNewnode; 
                } 
                
                if (ptr3 != null) {
                    while (ptr3 != null) {
                        if (ptr3.term.degree == multiNewnode.term.degree) {
                            float combinedCoeff= ptr3.term.coeff + multiNewnode.term.coeff; //if same degree then u add the coeffs
                            ptr3.term.coeff = combinedCoeff; //takes the coeff tha we solved for and sets it to the current ptr where it is - updates
                            didFind = 1; 
                        }
                        ptr3 = ptr3.next; 
                    }
                    if (didFind == 0) { // we didnt find the node we wanted
                        tail.next = multiNewnode; 
                        tail = multiNewnode; 
                    }
            }
        }
    }
        if (startMultiply == null) {
            return startMultiply;
        }
//// Zero Cases ////
        Node headN = null;  
        Node tailN = null; 
        for (Node ptr3 = startMultiply; ptr3 != null; ptr3 = ptr3.next) {
            if (ptr3.term.coeff != 0) {
                if (headN == null) {
                    tailN = ptr3; 
                    headN = ptr3; 
                } else {
                    tailN.next = ptr3; 
                    tailN = ptr3; 
                }
            }
        }
//// Sort ////
        for (Node ptr = headN; ptr != null; ptr = ptr.next) {
            for (Node ptr2 = headN; ptr2 != null; ptr2 = ptr2.next) {
                if (ptr.term.degree < ptr2.term.degree) { //if the degree of the first list node is less than the second
                    Term temp = ptr.term; //rearangment of the terms not nodes
                    Term temp2 = ptr2.term; 
                    ptr.term = temp2; //sets the term of the first ptr to temp2 so since it is less then we put the greater term in temp
                    ptr2.term= temp; //sets the term of firstptr to the temp since it was less 
                } //this sorts into assending order
            }
        }
        return headN;
    }    
    
    
        
    /**
     * Evaluates a polynomial at a given value.
     * 
     * @param poly Polynomial (front of linked list) to be evaluated
     * @param x Value at which evaluation is to be done
     * @return Value of polynomial p at x
     */
    public static float evaluate(Node poly, float x) {
        Node ptr = poly;
        double deg = 0;
        double coe = 0;
        float eval = 0;
        while (ptr != null) {
             deg = Math.pow(x, ptr.term.degree);
             coe = deg * ptr.term.coeff;
             eval += coe;
             ptr=ptr.next;
        }
            

        return eval;
    }
    
    /**
     * Returns string representation of a polynomial
     * 
     * @param poly Polynomial (front of linked list)
     * @return String representation, in descending order of degrees
     */
    public static String toString(Node poly) {
        if (poly == null) {
            return "0";
        } 
        
        String retval = poly.term.toString();
        for (Node current = poly.next ; current != null ;
        current = current.next) {
            retval = current.term.toString() + " + " + retval;
        }
        return retval;
    }    
}
