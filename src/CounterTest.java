/**
 * August Ryan Brenner
 * abrenn10@my.smccd.edu
 * CIS 256
 * CounterTest.java
 * Project 1
 * August 22nd, 2013
 */

import java.util.*;

public class CounterTest {
    public static void main(String [] args){
        // instantiate scanner object
        Scanner input = new Scanner(System.in);
        // instantiate Counter array list
        ArrayList<Counter> counterArrayList = new ArrayList();
        //instantiate user selection for the main UI loop
        int userSelection;

        // do while loop to make test selections
        do{

            // promt user for inputs for actions testing counter class
            System.out.println("Please select from one of the following menu options:\n1)Input integer\n" +
                    "2)Display counters\n3)Test equality\n4)Adjust Counters\n5)Copy Counter\n6)Add counters\n7)Exit");
            userSelection = input.nextInt();

            // switch for handling selections testing all parts of the counter class
            switch(userSelection){
                // Case 1 prompts user for integer counts each digit, and adds counter to array list
                case 1:
                    System.out.println("Please enter an integer greater than 0 and below 2147483648:");
                    int integerInput = input.nextInt();
                    Counter newCounter = new Counter(10);

                    boolean flag = true;
                    while(flag == true){
                        int digit = integerInput % 10;
                        newCounter.addOne(digit);
                        if(integerInput > 10){
                            integerInput = (integerInput - digit)/10;
                        }else{
                            flag = false;
                        }
                    }
                    System.out.println(newCounter.toString());
                    counterArrayList.add(newCounter);
                    break;
                // Case 2 displays each counter stored in the array list
                case 2:
                    String display = "";
                    int i = 0;
                    for(Counter counter: counterArrayList){
                        display += String.format("%d) %s\n", i, counter.toString());
                        i++;
                    }
                    System.out.println(display);
                    break;
                // Case 3 tests the equals method
                case 3:
                    System.out.println("Enter the integers corresponding to the first counter");
                    int firstCounter = input.nextInt();
                    System.out.println("Enter the integers corresponding to the second counter");
                    int secondCounter = input.nextInt();

                    String output;
                    if(counterArrayList.get(firstCounter).equals(counterArrayList.get(secondCounter))) {
                        output = "The two counters are equal";
                    } else {
                        output = "The two counters are not equal";
                    }
                    System.out.println(output);
                    break;
                // Case 4 allows tests adding, subtracting, and add all
                case 4:
                    System.out.println("Enter the integers corresponding to the counter");
                    int counter = input.nextInt();
                    int selection = 0;
                    while(selection != 4){
                        System.out.println("Select adjustment:\n1) Subtract\n2) Add\n3) Add all\n4) Exit");
                        selection = input.nextInt();
                        int counterPosition = 0;
                        if(selection == 1){
                            while(counterPosition != -1){
                                System.out.printf("%s\nSelect position to subtract from (-1 to exit)\n",
                                        counterArrayList.get(counter).toString());
                                counterPosition = input.nextInt();
                                if(counterPosition != -1){
                                    counterArrayList.get(counter).loseOne(counterPosition);
                                }
                            }
                        }else if(selection == 2){
                            while(counterPosition != -1){
                                System.out.printf("%s\nSelect position to subtract from (-1 to exit)\n",
                                        counterArrayList.get(counter).toString());
                                counterPosition = input.nextInt();
                                if(counterPosition != -1){
                                    counterArrayList.get(counter).addOne(counterPosition);
                                }
                            }
                        }else if(selection == 3){
                            Counter.addAll(counterArrayList.get(counter));
                            System.out.printf("%s\n",
                                    counterArrayList.get(counter).toString());
                        }
                    }

                    break;
                // Case 5 copies a Counter object
                case 5:
                    System.out.println("Enter the integers corresponding to the counter to copy");
                    int copyCounter = input.nextInt();
                    counterArrayList.add(counterArrayList.get(copyCounter).copy());
                    break;
                // Case 6 sums two counter objects
                case 6:
                    System.out.println("Enter the integers corresponding to the first counter");
                    int firstSumCounter = input.nextInt();
                    System.out.println("Enter the integers corresponding to the second counter");
                    int secondSumCounter = input.nextInt();
                    counterArrayList.add(Counter.sum(counterArrayList.get(firstSumCounter),
                            counterArrayList.get(secondSumCounter)));
                    break;
            }

        } while(userSelection != 7);

    }
}