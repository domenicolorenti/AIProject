# Ball Sort Game with ASP Solver

This project implements a graphical "Ball Sort Puzzle" game in Java, enhanced with an AI solver using Answer Set Programming (ASP) via the [DLV2](https://www.dlvsystem.com/) solver and the [embASP](https://www.mat.unical.it/aspcomp2019/embasp/) framework.

## Features

- **Interactive GUI**: Play the ball sort puzzle with a graphical interface.
- **AI Solver**: Automatically solve the puzzle using ASP logic and the DLV2 solver.
- **Customizable Levels**: Modify the initial state and rules via logic program files.

## How It Works

- The game consists of several tubes, each containing colored balls.
- The goal is to sort the balls so that each tube contains balls of only one color.
- The AI solver uses ASP to find the optimal sequence of moves to solve the puzzle, minimizing the number of moves.

## Project Structure

```
.
├── src/
│   ├── Main.java           # Main entry point, sets up GUI and solver
│   ├── Board.java          # Game board and GUI logic
│   ├── aspmodel/           # ASP model classes (Move, Pallina, Colore, etc.)
│   └── tube/               # Game logic for tubes and balls
├── lib/
│   ├── embASP.jar          # embASP Java library for ASP integration
│   ├── dlv2, dlv2.exe, ...# DLV2 solver binaries for different OSes
│   └── antlr-4.7-complete.jar # Dependency for embASP
├── encodings/
│   └── bubblesorting       # ASP logic program for the ball sort puzzle
├── bubblesorting.txt       # Example input for the ASP solver
└── ...
```

## Getting Started

### Prerequisites

- Java 8 or higher
- [DLV2](https://www.dlvsystem.com/) (included in `lib/`)
- [embASP](https://www.mat.unical.it/aspcomp2019/embasp/) (included in `lib/`)

### Running the Game

1. **Compile the Java sources** (from the project root):

   ```sh
   javac -cp "lib/embASP.jar:lib/antlr-4.7-complete.jar" -d bin src/**/*.java
   ```

2. **Run the game**:

   ```sh
   java -cp "bin:lib/embASP.jar:lib/antlr-4.7-complete.jar" Main
   ```

   - On Windows, use `;` instead of `:` as the classpath separator.

3. **Solver Configuration**:
   - The solver binary used is set in `Main.java`:
     - For Linux: `lib/dlv2`
     - For Windows: `lib/dlv2.exe`
     - For MacOS: `lib/dlv2-macOS-64bit.mac_5`
   - Uncomment the appropriate line for your OS.

### How to Play

- The GUI displays tubes and colored balls.
- You can interact with the game using your mouse.
- The AI solver can be triggered to show the optimal solution.

### Customization

- **Logic Program**: The ASP logic for the puzzle is in `encodings/bubblesorting`.
- **Initial State**: Modify the facts in the logic program or in the Java code to change the starting configuration.

## File Descriptions

- `src/Main.java`: Sets up the GUI and connects to the ASP solver.
- `src/Board.java`: Handles the game board, rendering, and user interaction.
- `src/aspmodel/`: Contains Java classes that map to ASP predicates (e.g., `Move`, `Pallina`).
- `src/tube/`: Contains the core logic for tubes and balls.
- `encodings/bubblesorting`: The ASP logic program that encodes the rules and constraints of the puzzle.
- `bubblesorting.txt`: Example of an ASP input file for the solver.

## Credits

- Uses [embASP](https://www.mat.unical.it/aspcomp2019/embasp/) for Java-ASP integration.
- Uses [DLV2](https://www.dlvsystem.com/) as the ASP solver.

## License

Specify your license here. 