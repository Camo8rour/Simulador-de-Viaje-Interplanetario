# 🚀 Simulador de Viaje Interplanetario 🌌

Este programa es un simulador interactivo donde el usuario puede elegir un planeta y una nave espacial para realizar un viaje espacial, gestionando recursos y enfrentando eventos aleatorios durante el trayecto. 👨‍🚀

---

## ✨ Estructura del código

El código está estructurado en varios métodos que realizan diferentes tareas:

1. **Listar los planetas, sus distancias y descripciones.** 🪐
2. **Listar las naves espaciales, sus velocidades y capacidad de pasajeros.** 🚀
3. **Menú principal con sistema de selección secuencial.** 📝
4. **Cálculo del tiempo de viaje.** ⏳
5. **Simulación interactiva del viaje con gestión de recursos.** 🎮
6. **Sistema de eventos aleatorios durante el viaje.** 🎲
7. **Función para esperar que el usuario presione ENTER.** ⌨️

---

## 🛸 Métodos principales

### 1. **Método `listPlanets()` - Planetas, distancias y descripciones 🌍**

```java
static Object[] listPlanets() {
    String[] planets = { "Venus", "Marte", "Mercurio", "Júpiter", "Saturno", "Urano", "Neptuno" };
    double[] distance = { 42.4, 78.3, 91.7, 628.9, 1284.4, 2721.4, 4345.4 };
    String[] description = {
        "es similar en tamaño a la Tierra, pero con una atmósfera densa y temperaturas extremas.",
        "el planeta rojo tiene un clima frío y valles secos, con signos de agua en el pasado.",
        // ... más descripciones ...
    };
    return new Object[] { planets, distance, description };
}
```

### Explicación:
- Este método ahora devuelve un arreglo de tres elementos:
    - Un arreglo de planetas: Nombres de los planetas del sistema solar
    - Un arreglo de distancias: Distancia en millones de kilómetros desde la Tierra
    - Un arreglo de descripciones: Información detallada sobre cada planeta
> 🔑 **Importante:** La estructura `Object[]` permite agrupar diferentes tipos de datos en un solo retorno.

### 2. **Método `listSpaceships()` - Naves espaciales, velocidades y capacidad 🚀**

```java
static Object[] listSpaceships() {
    String[] spaceShips = { "Transbordador espacial", "Sonda espacial", "Nave de propulsión nuclear",
            "Sonda solar de velocidad ultra alta", "Nave de propulsión de antimateria",
            "Nave con motor de curvatura" };
    double[] speed = { 28000, 61000, 100000, 600000, 10000000, 1080000000 };
    int[] passengers = { 15, 7, 9, 6, 10, 13 };
    return new Object[] { spaceShips, speed, passengers };
}
```

### Explicación:
- Ahora incluye tres tipos de datos:
    - Nombres de las naves espaciales
    - Velocidades en km/h
    - Capacidad de pasajeros de cada nave
> 🚀 La capacidad de pasajeros es una nueva característica que afecta la planificación del viaje.

### 3. **Método `showMenu()` - Sistema de selección secuencial 📋**

```java
private static void showMenu(Scanner reqShow) {
    byte optionM;
    double selectedPlanetDistance = -1;
    double selectedShipSpeed = -1;
    
    do {
        System.out.println("1. Seleccionar un planeta de destino.");
        System.out.println("2. Seleccionar una nave espacial.");
        System.out.println("3. Iniciar la simulación del viaje.");
        System.out.println("0. Salir del programa.");
        // ... lógica de selección ...
    } while (optionM != 0);
}
```

### Explicación:
- **Nuevo sistema secuencial:** El usuario debe:
    1. Primero seleccionar un planeta
    2. Luego elegir una nave espacial
    3. Finalmente iniciar la simulación
- **Validación mejorada:** Verifica que se hayan seleccionado tanto el planeta como la nave antes de iniciar la simulación

### 4. **Método `calculateSpeed()` - Cálculo del tiempo de viaje ⏳**

```java
private static double calculateSpeed(Scanner reqCalculate, double planetDistance, double shipSpeed) {
    double timeHours = (planetDistance * 1_000_000) / shipSpeed;
    double timeDays = timeHours / 24;
    System.out.printf("La duración del viaje es de %.3f días equivalentes a %.3f horas.%n", timeDays, timeHours);
    return timeHours;
}
```

### Explicación:
- **Cálculos mejorados:**
    - Convierte la distancia a kilómetros
    - Calcula el tiempo en horas y días
    - Retorna el tiempo para la simulación
> 🔑 El tiempo calculado se utiliza en la simulación del viaje.

### 5. **Método `travelSimulation()` - Simulación interactiva del viaje 🎮**

```java
private static void travelSimulation(Scanner reqCalculate, double planetDistance, double shipSpeed, double timeHours) {
    double combustible = 100.0;  // Combustible inicial
    double oxigeno = 100.0;      // Oxígeno inicial
    
    // Sistema de progreso y recursos
    int totalSteps = 50;
    // ... lógica de simulación ...
}
```

### Explicación:
- **Características principales:**
    - Barra de progreso visual
    - Sistema de gestión de recursos (combustible y oxígeno)
    - Tiempo restante actualizado en tiempo real
    - Eventos aleatorios durante el viaje
> 🎮 La simulación es interactiva y requiere que el usuario tome decisiones durante el viaje.

### 6. **Método `triggerRandomEvent()` - Eventos aleatorios 🎲**

```java
private static void triggerRandomEvent(Scanner reqCalculate, Random random, double combustible, 
        double oxigeno, double combustiblePorPaso, double oxigenoPorPaso) {
    // Eventos positivos y negativos
    // Sistema de reparación
    // Gestión de recursos
}
```

### Explicación:
- **Sistema de eventos:**
    - Eventos positivos y negativos aleatorios
    - Decisiones del usuario para reparar problemas
    - Impacto en los recursos disponibles
> 🎲 Cada evento tiene una probabilidad del 10% de ocurrir en cada paso de la simulación.

### 7. **Método `pressEnter()` - Control de flujo ⌨️**

```java
private static void pressEnter(Scanner pressRequest) {
    System.out.printf("%nOprima ENTER para continuar.%n");
    System.out.print("-> ");
    pressRequest.nextLine();
}
```

### Explicación:
- Controla el ritmo de la simulación
- Permite al usuario leer la información antes de continuar
- Mejora la experiencia de usuario

## 🎮 Características especiales

### Sistema de Recursos 📊
- **Combustible:** Necesario para el viaje
- **Oxígeno:** Vital para la tripulación
- Ambos recursos disminuyen durante el viaje
- Se pueden recuperar mediante eventos positivos o reparaciones

### Eventos Aleatorios 🎲
- **Eventos negativos:**
    - Fugas de oxígeno
    - Consumo acelerado de combustible
    - Problemas de sistema
- **Eventos positivos:**
    - Fuentes adicionales de oxígeno
    - Optimización de consumo
    - Suministros extra

### Barra de Progreso 📈
- Visualización en tiempo real del avance
- Muestra porcentaje completado
- Indica niveles de recursos
- Actualiza tiempo restante

***🎮 En constante actualización...***