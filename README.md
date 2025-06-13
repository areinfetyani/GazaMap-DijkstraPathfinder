# GazaMap-DijkstraPathfinder

An interactive JavaFX application that visualizes the **shortest path** between cities in Gaza using **Dijkstra’s algorithm**. Designed as a course project for COM336 (Design and Analysis of Algorithms), the application allows users to select source and destination cities and view the optimal path drawn directly on a map.

---

## 📚 Project Description

This project simulates GPS-like routing by using Dijkstra’s algorithm to find the cheapest or shortest route between two cities. The map includes **50+ cities**, and each connection reflects Euclidean distances calculated from real-world coordinates (latitude and longitude).

Users can interact with the map:
- Select source/destination using the mouse or dropdown menus
- View the selected path dynamically drawn on the map
- Switch cities and re-run pathfinding without restarting the app

---

## ⚙️ Technologies Used

- **JavaFX** — for building the dynamic UI
- **Scene Builder** — for designing the interface visually
- **Java** — for backend logic and shortest path computation
- **Custom MinHeap & Graph Structures** — for optimized Dijkstra implementation

---

## 🖼️ Screenshots

### 🟢 Welcome Page

![image](https://github.com/user-attachments/assets/9af1519a-7b23-4b29-80ca-b3bf17e3b7e7)


---

### 🗺️ Interactive Map & Route Visualization

![image](https://github.com/user-attachments/assets/81a419bf-2eae-4f94-9490-024e5ba8fe2c)

![image](https://github.com/user-attachments/assets/cb1e45e7-8a0e-416f-93d6-2e96abf634f7)

---

## 🧠 How It Works

- Input files define:
  - Cities (with names, latitude, longitude)
  - Road connections between cities
- Cities are rendered as location markers (📍) on the map
- When a user selects two cities:
  - Dijkstra’s algorithm calculates the cheapest path
  - The map redraws the selected path as a bold red line
  - Distance and passed cities are shown in the UI

---

## 📁 File Structure

- `Main.java` – launches the JavaFX application
- `Graph.java` – handles graph construction and shortest path logic
- `Vertex.java` / `Edge.java` – represent cities and roads
- `MapRenderer.java` – converts lat/lon into on-screen projection
- `MinHeap.java` – custom implementation for Dijkstra optimization
- `*.fxml` – UI layout designed via Scene Builder
- `USA.txt` – file with cities (name, lat, lon)
- `Test.txt` – file with edge connections

---

## 🛠️ How to Run

1. Open the project in an IDE like IntelliJ or Eclipse with JavaFX support
2. Make sure JavaFX libraries are properly configured
3. Run `Main.java`
