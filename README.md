# Memory Scramble Game

A card-matching memory game built with Java Swing. Flip face-down cards to find matching pairs before time runs out.

## Features

- Configurable board size (rows × columns, must be even total)
- Configurable countdown timer
- Visual feedback for matches, mismatches, and game state

## Team Info
   - Mostafa Ahmed Moahmed                 11422025491155
   - Ahmed Wael Farouk                     11422025430235
   - Reem Ehab Moussa                      11422025486779

## Requirements

- Java JDK 8 or higher

## How to Build

From the project root directory, compile all source files into the `out/` directory:

```bash
javac src/*.java -d out/
```

## How to Run

After building, run the game:

```bash
java -cp out MemoryScrambleGame
```

## How to Play

1. **Configure the game**: Set the number of rows, columns, and the timeout (in seconds) in the setup dialog. The total number of cells (rows × columns) must be even.
2. **Start playing**: Click on cards to flip them face-up. Select two cards per turn.
3. **Find matches**: If the two selected cards show the same shape, they stay face-up. If not, they flip back after a short delay.
4. **Beat the clock**: Match all pairs before the countdown timer reaches zero.
5. **Win or lose**: Match all pairs to win, or run out of time for a game over.
6. **Replay**: After winning or losing, you'll be prompted to play again. Choose "Yes" to start a new game with fresh settings, or "No" to exit.
