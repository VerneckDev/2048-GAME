# 2048-GAME

A terminal-based implementation of the classic **2048 puzzle game**, written in Java. The board is rendered using Unicode box-drawing characters, and the game is controlled entirely from the keyboard.
 
---
 
## Project Structure
 
```
VJGame/
├── module-info.java       # Java module declaration (module: VJGame)
└── Game/
    ├── Board.java         # Game board logic
    └── Main.java          # Entry point, menus, and game loop
```
 
---
 
## How to Compile & Run
 
**Requirements:** Java 9+ (uses the module system)
 
```bash
# Compile
javac -d out --module-source-path src -m VJGame
 
# Run
java -p out -m VJGame/Game.Main
```
 
Or from an IDE such as Eclipse, simply run `Main.java` as a Java Application.
 
---
 
## Gameplay
 
### Objective
Combine tiles by sliding them across a 4×4 grid. When two tiles with the same number collide, they merge into one with their sum. Reach the **2048** tile to win!
 
### Controls
 
| Key | Action  |
|-----|---------|
| `i` | Move Up |
| `m` | Move Down |
| `j` | Move Left |
| `k` | Move Right |
| `r` | Restart |
| `x` | Exit |
 
> Controls are **case-insensitive**.
 
### Rules
- Each move slides **all tiles** in the chosen direction.
- Tiles with the **same value** that collide **merge once per move**.
- After every valid move, a new tile (**2** or **4**) spawns randomly in an empty cell (90% chance of 2, 10% chance of 4).
- The game ends in **defeat** if the board is full and no merges are possible.
- The game ends in **victory** when a **2048** tile is reached.
---
 
## Code Overview
 
### `Board.java`
 
Encapsulates the game state and all board logic.
 
| Method | Description |
|--------|-------------|
| `Board(int[][] map)` | Constructor — deep-copies the provided grid |
| `show_map()` | Prints the board to the console using Unicode characters |
| `set_num()` | Spawns a new random tile (2 or 4) in an empty cell |
| `pos_numR()` | Slides and merges tiles to the **right**; returns score gained |
| `pos_numL()` | Slides and merges tiles to the **left**; returns score gained |
| `pos_numU()` | Slides and merges tiles **upward**; returns score gained |
| `pos_numD()` | Slides and merges tiles **downward**; returns score gained |
| `win()` | Returns `1` if a 2048 tile exists on the board |
| `lose()` | Returns `1` if no moves remain (board full, no merges possible) |
| `reset()` | Clears the board (sets all cells to 0) |
| `clone()` | Returns a deep copy of the current board |
| `getMap()` | Returns the raw 2D array (used for move validation) |
 
Each directional method follows a **3-pass pattern**:
1. Slide all non-zero tiles toward the target edge.
2. Merge adjacent equal tiles.
3. Slide again to close gaps left by merges.
### `Main.java`
 
Handles the game loop, user input, and menus.
 
- **`main()`** — top-level loop managing three states: main menu, active gameplay, and end-game (win/lose).
- **`menu1()`** — renders the start screen (`[O] Begin`, `[X] Exit`).
- **`menu2()`** — renders the in-game HUD, including the current score and available controls.
Move validation is done by comparing the board state before and after a move using `Arrays.deepEquals()`. If the board didn't change, no new tile is spawned.
 
---
 
## Scoring
 
Points are awarded whenever two tiles merge. The score increases by the **value of the resulting merged tile** (e.g. merging two 64s adds 128 points). The running total is displayed in the in-game menu.
 
---
 
## Known Limitations / Possible Improvements
 
- No persistent high score storage between sessions.
- The board size is fixed at 4×4.
- No undo functionality.
- The `show_map()` formatting breaks for values ≥ 10000 (5+ digits).
- Consider adding arrow key support via a library like `JLine` for a better UX.

---

## Authors

**João Pedro Verneck** — [@VerneckDev](https://github.com/VerneckDev)
**João Vitor Andrade** — [@Jonhi7139 ](https://github.com/Jonhi7139 )