Assignment 5: Strategy Pattern - Airport Operations

1. Class Responsibility Table (SRP)

| Class | Responsibility |
| :--- | :--- |
| `Flight` | Stores operational parameters including flight number, weight, status flags. |
| `Gate` | Stores gate attributes including identification, availability, runway distance, jet bridge type. |
| `GateAllocationStrategy` | Declares execution contract for algorithm implementations determining suitable gates. |
| `FirstAvailableGateStrategy` | Finds the first unoccupied gate within the provided gates list. |
| `TurnaroundTimeOptimizationStrategy` | Selects closest available gate to runway for minimizing taxiing time. |
| `LargeAircraftMatchStrategy` | Matches wide-body aircraft with gates equipped with double jet bridges. |
| `GateManagerContext` | Delegates gate selection tasks to currently assigned strategy object. |
| `GateAllocationSelector` | Chooses appropriate gate allocation strategy dynamically using predefined domain rules. |
| `LandingFeeStrategy` | Declares execution contract for calculating total landing fees for flights. |
| `WeightBasedFeeStrategy` | Computes landing fees linearly based on aircraft maximum takeoff weight. |
| `PeakHourFeeStrategy` | Computes landing fees by applying surcharges during peak operational hours. |
| `EcoNoiseFeeStrategy` | Computes landing fees considering environmental conditions retrieved from external services. |
| `WeatherService` | Declares contract for accessing live external meteorological data. |
| `SimulatedWeatherService` | Provides mock wind speed data for testing fee calculation routines. |
| `Main` | Demonstrates manual runtime strategy switching alongside automated selector decision logic. |


2. Pattern Justification: Why Strategy (Not State or Template Method)?

This project adopts the **Strategy Pattern** to handle interchangeable airport operations logic at runtime. Below is the rationale distinguishing Strategy from related behavioral design patterns:

### Why not State?
While both patterns share similar class diagrams, their intent differs fundamentally:
* **Strategy** algorithms are completely independent, stateless, and unaware of other concrete strategies. The context or client explicitly sets or selects which algorithm executes based on external conditions.
* **State** represents a finite state machine where states hold references to the context and trigger transitions to other states automatically. Gate assignment and landing fee algorithms do not transition into one another; they are distinct, interchangeable choices.

### Why not Template Method?
* **Template Method** relies on compile-time inheritance, defining an algorithm framework in an abstract base class while letting subclasses override specific steps.
* **Strategy** relies on object composition and interface implementation. This allows changing whole algorithms dynamically at runtime via `setStrategy()`, adhering strictly to the *Open/Closed Principle* and *Prefer Composition Over Inheritance*.

---

## 3. Git Refactoring Commits

<img width="580" height="46" alt="Screenshot 2026-10-21 at 21 19 09" src="https://github.com/user-attachments/assets/cec7dfd4-a748-4295-9a64-d4a0eb6a9764" />


* **Before Refactoring (Bad Code with `switch/if-else`):** `3ea1a1f`
* **After Refactoring (Strategy Pattern Applied):** `2d1a6c6`
