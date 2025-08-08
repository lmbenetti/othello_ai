# Othello AI 🎯

This is a Java-based implementation of the classic board game **Othello**. It supports both **human and AI players**. 
This project was developed as part of the course Introducction to Artificial Intelligence from the IT University of Copenhagen. The Java code that runs the game was provided by the course manager.

The task was to create an implementation of the interface IOthelloAI using a MiniMax Algorithm with Alpha-Beta pruning, applying algorithmic logic, and designing clear, maintainable object-oriented code.

We implemented the IOthelloAI interface with the class MiniMaxABPruning. We used a Tuple class in our implementation.


## 🧠 AI Features

- **Minimax Algorithm**: The AI evaluates possible future game states to select the best move.
- **Alpha-Beta Pruning**: We used alpha-beta pruning to improve the response time.
- **Custom Evaluation Function**: Evaluates board states based on strategic heuristics.
- **Difficulty Tuning**: Depth limit can be adjusted to control difficulty. Higher depth increase the reponse time.

---

## 🧱 Project Structure

| Class | Description |
|-------|-------------|
| `Othello` | Entry point of the application. Initializes the game and handles user interaction. |
| `OthelloGUI` | GUI to show the Othello game and to listen for input for the user/human player |
| `Position` | Represents a coordinate on the game board using column and row indices. |
| `Game State` | Represent the state of a game.  It is defined by a 2-dimensional board and whose turn it is. |
| `IOthelloAI` (interface) | General interface to implement an AI that plays Othello. |
| `DumAI` | Implementation of `IOthelloAI` that picks the first possible move. |
| `MiniMaxABPruning` | Implementation of `IOthelloAI` using Minimax search with depth-limited recursion. |
| `Tuple` | Assisting clase of MiniMaxABPruning Class. It carries a utility and a movement.|

---

## 🕹️ How to Run

1. **Compile:**
   ```bash
   javac *.java
2. **Run:**
```bash
    java Othello <Player1> <Player2> [board_size]
    <Player1> — Either human (for a human-controlled player) or the name of a class implementing IOthelloAI.
    <Player2> — Must be the name of a class implementing IOthelloAI.
    [board_size] — (Optional) Even integer ≥ 4, defaults to 8.