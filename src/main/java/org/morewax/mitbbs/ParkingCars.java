package org.morewax.mitbbs;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem
 * Parking Cars
 * Allegedly this is a question from G.
 * 比如这一排停车位初始状态：
 * CarA CarB CarC EmptySlot CarD CarE
 * 移动CarA会变成：
 * EmptySlot CarB CarC CarA CarD CarE
 * 现在给一个最终的排列，比如：
 * CarE CarA EmptySlot CarC CarB CarD.
 * Your function should print out the moving sequence.
 * Created by byuan on 2/16/16.
 */
public class ParkingCars {
    public void moveCars(char[] parkingLot, char[] newParkingLot) {
        //Map<Character, Integer> result = new HashMap<>();

        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < parkingLot.length; ++i) {
            m.put(parkingLot[i], i);
        }

        for (int i = 0; i < newParkingLot.length; ++i) {
            if (newParkingLot[i] == '-' || newParkingLot[i] == parkingLot[i]) {
                continue;
            }

            // get the empty slot position
            char currentCar = parkingLot[i];
            char newCar = newParkingLot[i];
            int emptySlot = m.get('-');
            int oldNewCarPosition = m.get(newCar);
            int oldCurrentCarPosition = m.get(currentCar);

            System.out.println("Step " + (i+1));
            if (currentCar == '-') {
                System.out.println("Current car is empty slot, no need to move");
            } else {
                System.out.println("Moving current car " + currentCar + " from " + oldCurrentCarPosition + " to " + emptySlot);
                parkingLot[emptySlot] = parkingLot[i];
            }
            System.out.println("Moving new car " + newCar + " from " + oldNewCarPosition + " to " + oldCurrentCarPosition);
            parkingLot[oldCurrentCarPosition] = parkingLot[oldNewCarPosition];
            System.out.println("Marking " + oldNewCarPosition + " be empty");
            parkingLot[oldNewCarPosition] = '-';

            m.put(newCar, oldCurrentCarPosition);
            m.put(currentCar, emptySlot);
            m.put('-', oldNewCarPosition);

            System.out.println("");
        }

        //return result;
    }

    public void print(char[] parkingLot) {
        System.out.print("[");

        for (int i = 0; i < parkingLot.length; ++i) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(parkingLot[i]);
        }

        System.out.println("]");
    }

    public static void main(String[] args) {
        final ParkingCars s = new ParkingCars();

        char[] p1 = new char[]{'A', 'B', 'C', '-', 'D', 'E'};
        char[] np1 = new char[]{'E', 'A', '-', 'C', 'B', 'D'};
        s.moveCars(p1, np1);
        s.print(p1);
    }
}
