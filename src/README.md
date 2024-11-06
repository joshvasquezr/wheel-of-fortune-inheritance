# Wheel of Fortune and Mastermind Game Project

## Overview

This project involves designing and coding two games, **Wheel of Fortune** and **Mastermind**, using **inheritance**, **polymorphism**, **interfaces**, and **abstract classes**. The aim is to build a robust, reusable code structure that can accommodate both games and potentially others by maximizing code reuse and minimizing redundancy.

The project is divided into two parts:
1. **Part 1** - Refactor and enhance the Wheel of Fortune game using inheritance.
2. **Part 2** - Implement Mastermind and further refactor code to share similarities in a superclass called `GuessingGame`.

## Learning Objectives

- Apply inheritance, polymorphism, interfaces, and abstract classes to design flexible and reusable code.
- Use abstract classes and interfaces to define game behaviors.
- Understand and implement inheritance in creating different versions of a game.

---

## Part 1: Wheel of Fortune Refactor

### Game Classes and Interfaces

The **Wheel of Fortune** game has been refactored into several classes and interfaces:

- **GameRecord**: Stores the score and player ID for a single game play. Implements `Comparable` to allow sorting by score.
- **AllGamesRecord**: Maintains a record of multiple games and provides methods for analyzing scores, including:
    - `add(GameRecord)`: Adds a game record.
    - `average()`: Returns the average score across all games.
    - `average(playerId)`: Returns the average score for a particular player.
    - `highGameList(n)`: Returns a list of the top `n` scores.
    - `highGameList(playerId, n)`: Returns the top `n` scores for a specific player.

- **Game** (abstract class): Defines the structure of any game with methods:
    - `playAll()`: Plays a set of games, returning an `AllGamesRecord`.
    - `play()`: Abstract method for a single game play.
    - `playNext()`: Determines if the next game should be played.

- **WheelOfFortune** (abstract class, extends `Game`): Adds specific functionality for the Wheel of Fortune game, such as:
    - `getGuess(String previousGuesses)`: Abstract method for obtaining a guess, to be implemented by subclasses.
    - `randomPhrase()`, `generateHiddenPhrase()`, and `processGuess()` methods for handling game-specific logic.

- **WheelOfFortuneUserGame** (extends `WheelOfFortune`): Allows a user to play Wheel of Fortune. Overrides `getGuess` to take user input via `Scanner`.

- **WheelOfFortuneAIGame** (extends `WheelOfFortune`): Allows an AI to play Wheel of Fortune. Can handle multiple AI players and play through all phrases.

### Player Interface and AI Players

- **WheelOfFortunePlayer** (interface): Defines methods for AI players, including:
    - `nextGuess()`: Returns the next character guess.
    - `playerId()`: Returns the player's ID.
    - `reset()`: Resets the player for a new game.

- **Concrete Implementations of AI Players**:
    - **DumbAIPlayer**: Guesses randomly.
    - **NormalAIPlayer**: Guesses without repeating previous guesses.
    - **SmartAIPlayer**: Guesses based on common letter frequency in English.

### Running Wheel of Fortune Games

Each game variant (user or AI) has a `main` method:
- **WheelOfFortuneUserGame**: Runs a set of games for a single user.
- **WheelOfFortuneAIGame**: Runs a set of games for multiple AI players, allowing them to play each phrase in turn.

---

## Part 2: Mastermind and GuessingGame Superclass

In Part 2, **Mastermind** is introduced, and similarities between Wheel of Fortune and Mastermind are codified in a superclass named `GuessingGame`. The `GuessingGame` class encapsulates shared behaviors and logic to maximize code reuse.

### Mastermind

Mastermind is a game where the player tries to guess a secret code of colors. This code includes:
- `GameRecord` and `AllGamesRecord`: Utilized for storing and analyzing game data.
- `GuessingGame`: A superclass for shared functionality between Wheel of Fortune and Mastermind.
- **Mastermind**: A user-based version of the game that uses color codes as the secret phrase, with logic tailored to Mastermind's unique rules.

### Alternative Games

If approved, other games with similarities to Wheel of Fortune may be substituted for Mastermind, provided they follow similar guessing mechanics.

---

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/yourrepository.git