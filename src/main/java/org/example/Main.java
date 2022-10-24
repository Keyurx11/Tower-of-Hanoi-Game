package org.example;

import java.util.*;

public class Main {
    private static Scanner stdin = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] towers = buildTowers(4);
        viewTowers(towers);
        int steps = 0;
        do {
            MakeValidMove(towers[0], towers[1]);
            //viewTowers(towers);

            MakeValidMove(towers[0], towers[2]);
            //viewTowers(towers);

            MakeValidMove(towers[1], towers[2]);
            //viewTowers(towers);

            steps += 3;
        } while (!PuzzleComplete(towers[2]));

        System.out.printf("Puzzle completed in %s steps!\n", steps);
        viewTowers(towers);
    }

    public static int[][] buildTowers(final int numberOfDiscs) {
        int[][] towers = new int[3][numberOfDiscs];

        for (int i = 0; i < numberOfDiscs; i++) {
            towers[0][i] = i + 1;
        }

        for (int i = 0; i < numberOfDiscs; i++) {
            towers[1][i] = -1;
            towers[2][i] = -1;
        }
        return towers;
    }

    public static void viewTowers(int[][] towers) {
        for (int i = 0; i < towers[0].length; i++) {
            for (int j = 0; j < towers.length; j++) {
                System.out.printf("%d   ", towers[j][i]);
            }
            System.out.printf("\n");
        }
        System.out.printf("--------------\n\n");
    }

    /**
     * Take a tower and find the first empty space, returning the index.
     * <p>
     * Takes a 1D array representing a tower, this function looks through the tower to find the first index which can
     * be written to. This returns -1 if the tower is full.
     *
     * @param tower the 1D array to find the first empty index of.
     * @return the first empty index. This is -1 if the tower is full.
     */
    public static int GetTopEmptyIndex(int[] tower) {
        for (int i = 0; i < tower.length; i++) {
            if (tower[i] != -1) {
                return i - 1;
            }
        }
        //If we reach this point, all tower values are -1. Return the bottom value.
        return tower.length - 1;
    }

    /**
     * Get the top value in a specified tower.
     * <p>
     * This function takes a tower and finds the top value which is not -1. This returns MAX INT when
     * the tower is empty.
     *
     * @param tower The tower to check.
     * @return the top value found.
     */
    public static int GetTopOfTowerValue(int[] tower) {
        int indexAtTopOfTower = GetTopEmptyIndex(tower) + 1;
        //Check if tower is empty
        if (indexAtTopOfTower >= tower.length) {
            return Integer.MAX_VALUE;
        }
        return tower[indexAtTopOfTower];
    }

    public static void MoveTopValue(int[] towerMoveTo, int[] towerMoveFrom) {
        //Find value at the top of the tower moving from
        int moveFromValue = GetTopOfTowerValue(towerMoveFrom);
        if (moveFromValue == Integer.MAX_VALUE) {
            return;
        }
        //Set value of tower moving to to the value from tower moving from
        towerMoveTo[GetTopEmptyIndex(towerMoveTo)] = moveFromValue;
        //Set value of tower moving from to -1
        towerMoveFrom[GetTopEmptyIndex(towerMoveFrom) + 1] = -1;
    }

    public static void MakeValidMove(int[] tower1, int[] tower2) {
        if (GetTopOfTowerValue(tower1) < GetTopOfTowerValue(tower2)) {
            MoveTopValue(tower2, tower1);
        } else {
            MoveTopValue(tower1, tower2);
        }
    }

    public static boolean PuzzleComplete(int[] tower) {
        for (int i = 0; i < tower.length; i++) {
            if (tower[i] != i + 1) {
                return false;
            }
        }
        return true;
    }
}