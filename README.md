# TicTacToe

A simple TicTacToe game implemented in Java using Spring Boot. This project demonstrates object-oriented design, strategy patterns, Builder design pattern for constructing complex objects, and basic bot logic for playing TicTacToe.

## Features

- Play TicTacToe on a customizable board size
- Supports human vs human and human vs bot gameplay
- Multiple bot difficulty strategies (Easy, etc.)
- Pluggable winning strategies (row, column, diagonal)
- Clean, modular codebase using Java and Spring Boot

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- IntelliJ IDEA (recommended)

### Installation

1. Clone the repository: git clone https://github.com/RvtjSngh/TicTacToe.git cd TicTacToe
2. Build the project: mvn clean install
3. 3. Run the application: mvn spring-boot:run
  

## Project Structure

- `models/` - Core game models (Board, Cell, Move, etc.)
- `strategies/` - Winning and bot playing strategies
- `controllers/` - REST API endpoints (if applicable)
- `services/` - Game logic and orchestration

## How to Play

- Start the application.
- Follow the prompts or use the REST API (if available) to play the game.
- Choose board size, player types, and start playing!

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License.

## Author

- [RvtjSngh](https://github.com/RvtjSngh)
