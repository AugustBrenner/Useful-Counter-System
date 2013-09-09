/**
 * August Ryan Brenner
 * abrenn10@my.smccd.edu
 * CIS 256
 * Counter.java
 * Project 1
 * August 22nd, 2013
 */


import javax.swing.*;
import java.util.*;


public class Counter {
    private int counter[];

    //Effect:  initializes 1 counter to 0
    //Precondition: None
    //Postcondition:  counter 1 is ready for use
    public Counter() {
        counter = new int[1];
        counter[0] = 0;
    }

    //Effect:  initializes max counters to 0
    //Precondition: max is a positive value
    //Postcondition: max counters  are ready for use
    public Counter(int max) {
        if(max > 0) {
            counter = new int[max];
            for(int i = 0; i < max; i++) {
                counter[0] = 0;
            }
        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Number of counters must be positive and real.", "Input error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //Effect: throws an exception for invalid inputs
    //Precondition: none
    //Postcondition: throws exception
    public static void invalidInputException(String outputMessage) {
        InputMismatchException invalidInputException = new InputMismatchException(outputMessage);
        throw invalidInputException;
    }

    //Effect: generates dialog error box for invalid inputs
    //Precondition: none
    //Postcondition: generates dialog error box
    public static void errorDialogBox(String outputMessage) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, outputMessage, "Input error",
                JOptionPane.ERROR_MESSAGE);

    }

    /*public int[] integerArraySort(int[] unsortedArray) {
        int length = unsortedArray.length;
        if (length <= 1) {
            return unsortedArray;
        } else {
            int[] low;
            int[] medium;
            int[] high;
            int pivot = unsortedArray[0];

            for(int i=0; i < length; i++){
                if(unsortedArray[i] < pivot){
                    low.
                }
        }
    }
    // should I use array lists for my low, medium, high, or should I count the number of each and set up arrays?
    */

    //Effect: mutator method; increases counter num by 1
    //Precondition: 0 <= num <=  the number of counters-1
    //Postcondition: counter ‘num’ has increased in number by 1
    public void addOne(int num) {
        if(num >= 0 && num < counter.length) {
            counter[num]++;
        }else{
            errorDialogBox(String.format("Counter must be within 0 and %d.", counter.length - 1));
        }
    }

    //Effect: mutator method; decreases counter num by 1
    //Precondition: 0 <= num <=  the number of counters-1
    //Postcondition: counter ‘num’ has decreased in number by 1
    public void loseOne(int num) {
        if(num >= 0 && num < counter.length) {
            counter[num]--;
        }else{
            errorDialogBox(String.format("Counter must be within 0 and %d.", counter.length - 1));
        }
    }

    //Effect:  access method ; provides value of counter num
    //Precondition: 0 <= num <=  the number of counters-1
    //Postcondition: No change to Counter object
    //Returns: returns value of counter num
    public int getCount(int num) {
        int result = 0;
        try {
            if(num >= 0 && num < counter.length) {
                result = counter[num];
            }else{
                invalidInputException(String.format("Counter must be within 0 and %d.", counter.length - 1));
            }
        } catch(InputMismatchException e) {
            result = -1;
        }
        return result;
    }

    //Effect:  access method ; provides number of counters
    //Precondition: None
    //Postcondition: No change to Counter object
    //Returns: returns number of Counters in this object
    public int getMax() {
        return counter.length;
    }


    //Effect: updates all counters in parameter aCounter
    //Precondition: aCounter is a non-null counter object
    //Postcondition:  all Counters in aCounter have been increased by 1
    public static void addAll (Counter aCounter) {
        if(aCounter != null){
            for(int i = 0; i < aCounter.getMax(); i++) {
                aCounter.addOne(i);
            }
        }else{
            errorDialogBox("Null counter.");
        }
    }

    //Effect: determines the validity of a counter number
    //Precondition: None
    //Precondition: No change to Counter object
    //Returns: true if num is a valid counter number, false otherwise
    public boolean validCount (int num) {
        if(num >= 0 &  num < counter.length){
            return true;
        } else {
            return false;
        }
    }

    //Effect: determines the counter with the largest value
    //Precondition: None
    //Postcondition: No change to Counter object
    //Returns:  number of counter with largest value, in case of tie, returns the lowest numbered counter
    public int largest() {
        int largest = counter[0];
        int length = counter.length;
        for(int i = 1; i < length; i++) {
            if(counter[i] > largest) {
                largest = counter[i];
            }
        }
        return largest;
    }

    //Effect: determines equality of two Counter objects
    //Precondition: obj is non-null object
    //Postcondition:  Counter object is unchanged
    //Returns:  true if all Counters of implied object are equal to all counters in obj, false otherwise
    //Overrides:  Object class equals
    @Override
    public boolean equals (Object obj) {
        boolean flag = true;
        if (obj instanceof Counter){
            if(getMax() == ((Counter) obj).getMax()){
                int length = getMax();
                int i = 0;
                while(i<length && flag == true){
                    if(getCount(i) != ((Counter) obj).getCount(i)){
                        flag = false;
                    }
                    i++;
                }
            } else flag = false;
        } else flag = false;
        return flag;
    }

    //Effect: creates a string representation of this Counter object
    //Precondition:   None
    //Postcondition:   the Counter object is unchanged
    //Returns:  a string representation of the Counter object
    //Overrides: Object class toString
    @Override
    public String toString() {
        String output = "";
        int length = counter.length;
        for(int i = 0; i < length; i++){
            output += String.format("%d  ", counter[i]);
        }
        return output;
    }

    //Effect: a copy of the implied object is created
    //Precondition:   None
    //Postcondition:  Counter object is unchanged
    //Returns:  a new Counter object with the same counter values as the implied object
    public Counter copy() {
        int length = getMax();
        Counter newCounter = new Counter(length);
        for(int i = 0; i < length; i++) {
            int count = counter[i];
            for (int j = 0; j < count; j++){
                newCounter.addOne(i);
            }
        }
        return newCounter;
    }

    //Effect:  creates a Counter which is the sum of two Counter objects obj1 and obj2
    //Precondition:  None
    //Postcondition:  two parameter Counter objects are unchanged
    //Returns:  a new Counter object
    public static  Counter sum(Counter obj1,  Counter obj2) {
        int obj1Length = obj1.getMax();
        int obj2Length = obj2.getMax();
        int largerLength;

        if(obj1Length > obj2Length){
            largerLength = obj1Length;
        } else{
            largerLength = obj2Length;
        }

        Counter newCounter = new Counter(largerLength);
        for(int i = 0; i < obj1Length; i++) {
            int count = obj1.getCount(i);
            if (count < 0){
                for (int j = 0; j > count; j--){
                    newCounter.loseOne(i);
                }
            }else {
                for (int j = 0; j < count; j++){
                    newCounter.addOne(i);
                }
            }
        }
        for(int i = 0; i < obj2Length; i++) {
            int count = obj2.getCount(i);
            if (count < 0){
                for (int j = 0; j > count; j--){
                    newCounter.loseOne(i);
                }
            }else {
                for (int j = 0; j < count; j++){
                    newCounter.addOne(i);
                }
            }
        }
        return newCounter;
    }

}
