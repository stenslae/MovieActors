# Movie Actors Graph

## Project Overview
This program constructs a **graph of actors** where vertices represent actors and edges represent shared appearances in movies. Using this graph, users can explore connections between actors, compute shortest paths, and identify the minimum set of movies connecting them all.

This project includes:
- Building an undirected, unweighted **adjacency list graph** from a file
- Finding a **Minimum Spanning Tree (MST)** using **Kruskal’s Algorithm**
- Finding **shortest paths** using **Dijkstra’s Algorithm**
- Finding the **longest path in the MST** using **DFS/BFS**

Inspired by *The Oracle of Bacon*, this program demonstrates practical applications of graph theory in analyzing movie data.

---

## Features

### 🎬 Graph Creation
- Parses an input file (`Actor|Movie`) to build an **adjacency list**
- Each actor is a vertex; edges represent shared movie appearances
- Each edge includes the movie name

### 🌲 Minimum Spanning Tree (Menu Option 1)
- Uses **Kruskal’s Algorithm**
- Assigns uniform edge weights
- Displays edges in MST and the **list of unique movies** needed to connect all actors

### 🔀 Shortest Path Between Actors (Menu Option 2)
- Prompts user for two actor names
- Uses **Dijkstra’s Algorithm** to find the shortest path (minimum hops)
- Displays the number of hops and path between actors
- Gracefully handles invalid input (e.g., actor not found)

### 🧭 Longest Path in MST (Menu Option 3)
- Converts MST into adjacency list
- Uses **DFS/BFS** to find the longest path
- Ignores movies for this step—focuses purely on actor connections

---

## Acknowledgments
- Inspiration: This work was based on course materials provided by Reese Pearsall, CSCI 232: Data Structures and Algorithms & Data Structures and Algorithms, Montana State Univeristy - Bozeman.
- Libraries/Tools: All code is written in Java using standard libraries.
