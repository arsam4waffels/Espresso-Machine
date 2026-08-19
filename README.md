# Espresso Machine

A small Java project built for fun, experimentation, and learning.

This project is **not intended to be a real-world espresso machine simulator LMAO**.
It is mainly a playground for practicing Java concepts such as:

- Object-Oriented Programming (OOP)
- Encapsulation
- Composition
- Enums
- Records
- Builder Pattern
- State Management
- Custom Exceptions
- Validation
- Method Chaining

##  About the Project

The idea is to simulate a simple espresso machine.

The machine can:

- Turn on and off
- Manage different machine states
- Attach and detach a portafilter
- Configure the number of group heads
- Configure the water tank capacity
- Brew different types of coffee
- Calculate beverage output based on coffee weight
- Consume water during brewing
- Throw exceptions when an invalid operation is attempted


##  How Is a Coffee Brewed?

The brewing process is based on two main values:

1. The amount of coffee placed in the portafilter
2. The brew ratio of the selected coffee type

The basic formula is:

```text
Beverage Output = Coffee Weight × Brew Ratio
```

For example:

```text
Lungo
Coffee weight = 20g
Brew ratio = 1:3

20 × 3 = 60g beverage output
```

For simplicity, the project approximately treats:

```text
1g beverage ≈ 1ml water
```

Therefore:

```text
20g coffee
×
3 brew ratio
=
60g beverage
≈
60ml water consumption
```

### Example

A 15g Ristretto with a 1:1 ratio:

```text
15g × 1 = 15g beverage
≈ 15ml water
```

A 20g Lungo with a 1:3 ratio:

```text
20g × 3 = 60g beverage
≈ 60ml water
```

This is a simplified model for the purpose of the project.

---

## Object Creation

The Espresso Machine is created using the **Builder Pattern**.

Instead of passing many parameters directly to a constructor:

```java
new EspressoMachine(...);
```

the machine can be configured using:

```java
EspressoMachine machine =
        new EspressoMachine.Builder()
                .groupHeadCount(2)
                .waterTankCapacity(2.5)
                .build();
```

The Builder is responsible for collecting the initial configuration and creating the machine.

This makes the object creation process more readable and easier to extend.

---

##  What Should Each Object Be Responsible For?

The project tries to keep each object responsible for its own domain.

### EspressoMachine

The `EspressoMachine` is responsible for controlling the machine itself.

It manages:

* Machine state
* Brewing
* Portafilter attachment
* Group heads
* Interaction with the water tank
* Validation of machine operations

It should coordinate the other objects rather than storing every piece of information itself.

---

### MachineConfiguration

`MachineConfiguration` stores the configuration of the machine.

For example:

```text
Group head count
Water tank capacity
```

The configuration is immutable after the machine is created.

---

### WaterTank

`WaterTank` is responsible for water management.

It knows:

* Maximum capacity
* Current water level
* How much water can be consumed

For example:

```java
waterTank.consume(60);
```

The machine should not manually modify the tank's internal water level.

---

### Portafilter

`Portafilter` represents the physical portafilter and contains information about the coffee placed inside it.

For example:

```java
new Portafilter(
        CoffeeType.LUNGO,
        20
);
```

This means:

```text
Coffee type: LUNGO
Coffee weight: 20g
```

The user determines how much coffee is placed in the portafilter.

---

### CoffeeType

`CoffeeType` is an enum representing different coffee types.

Each coffee type defines its own brewing characteristics, such as its brew ratio.

For example:

```text
RISTRETTO → 1:1
ESPRESSO  → 1:2
LUNGO     → 1:3
```

The coffee type is responsible for calculating its expected beverage output based on the amount of coffee.

```java
double output =
        coffeeType.calculateOutput(coffeeWeight);
```

---

### MachineState

`MachineState` represents the current state of the machine.

For example:

```text
OFF
ON
BREWING
```

This allows the machine to prevent invalid operations.

For example:

```text
OFF
 ↓
turnOn()
 ↓
ON
 ↓
brewCoffee()
 ↓
BREWING
 ↓
ON
```

The machine should not be turned off while it is brewing.

---

## Exceptions

The project uses custom exceptions to represent invalid machine operations.

Examples include:

```text
AlreadyOnException
AlreadyOffException
MachineIsOffException
MachineIsBrewingException
PortafilterNotInPlaceException
NotEnoughWaterException
```

For example, trying to brew while the machine is off should result in:

```java
MachineIsOffException
```

Trying to brew without a portafilter should result in:

```java
PortafilterNotInPlaceException
```

Trying to brew without enough water should result in:

```java
NotEnoughWaterException
```

---

## Brewing Flow

A simplified brewing flow looks like this:

```text
Create Espresso Machine
        ↓
Configure Machine
        ↓
Turn Machine ON
        ↓
Create Portafilter
        ↓
Attach Portafilter
        ↓
Select Coffee Type
        ↓
Set Coffee Weight
        ↓
Calculate Beverage Output
        ↓
Calculate Water Consumption
        ↓
Check Water Tank
        ↓
Consume Water
        ↓
Brew Coffee
        ↓
Machine returns to ON
```

---

## Technologies

* Java
* Object-Oriented Programming
* Java Records
* Enums
* Builder Pattern
* Custom Exceptions
* IntelliJ IDEA
* Git & GitHub

---

## 📌 Disclaimer

So... this project is made **for fun, experimentation, and learning Java**.

It is not a professional coffee machine simulation (i mean... duh?) and its calculations are intentionally simplified.

All that means don't randomly go and start a Espresso Machine Construction factory  base on this code lol.

Also, I would like to thank the café where I worked for a time; they taught me the principles and rules of coffee extraction. I am truly grateful.
