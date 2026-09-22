# Tracker App
 
A Java desktop app for tracking wages and costs — plug in your numbers, get an instant answer.
 
## What it does
 
Tracker App has two calculators:
 
- **Wage Tracker** — is your monthly wage enough to cover your expenses? Enter your monthly wage, rent, groceries, and amenities, and it tells you how much you have left over (or how short you are).
- **Costs Tracker** — given your hourly wage, how many hours do you need to work to cover your expenses? Enter your hourly wage, rent, groceries, and amenities, and it tells you the hours needed.
Both trackers share the same underlying logic (`Tracker`, an abstract parent class), so results are calculated consistently and future tracker types can be added without rewriting existing code.
 
## Tech Stack
 
| Piece | What it is |
|---|---|
| Java | Language |
| Swing | GUI toolkit |
| FlatLaf | Modern styling for Swing |
| Maven | Dependency management + build |
 
## Getting Started
 
This is a standard Maven project — no manual dependency downloads needed, Maven fetches FlatLaf automatically.
 
### Prerequisites
 
- JDK 17 or higher
- Eclipse **or** VS Code (both work — see below)
### Running it in Eclipse
 
1. File → Import → Maven → Existing Maven Projects → select this folder
2. Once it finishes importing, right-click the project → Run As → Java Application
3. Select `TrackerApp` as the class to run
### Running it in VS Code
 
1. Install the "Extension Pack for Java" (bundles Maven support)
2. Open this folder in VS Code
3. Open `TrackerApp.java`, click the ▶ Run button above `main()`
## Project Structure
 
```
src/
├── main/java/com/group40/    → application source code
└── test/java/com/group40/    → unit tests
```
 
## Notes
 
- Session history is **not saved to disk** — it resets when the app closes. This is intentional.
- Only rent, groceries, and amenities count as expenses for now — no custom categories yet.