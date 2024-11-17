# 🚀 Simulador de Viaje Interplanetario 🌌

Este programa es un simulador donde el usuario puede elegir un planeta y una nave espacial para calcular el tiempo de viaje desde la Tierra.👨‍🚀

---

## ✨ Estructura del código

El código está estructurado en varios métodos que realizan diferentes tareas:

1. **Listar los planetas y sus distancias.** 🪐
2. **Listar las naves espaciales y sus velocidades.** 🚀
3. **Menú principal para elegir planeta y nave.** 📝
4. **Cálculo del tiempo de viaje.** ⏳
5. **Función para esperar que el usuario presione ENTER.** ⌨️

---

## 🛸 Métodos principales

### 1. **Método `listPlanets()` - Planetas y distancias 🌍**

```java
static Object[] listPlanets() {
    String[] planets = { "Júpiter", "Marte", "Mercurio", "Neptuno", "Saturno", "Urano", "Venus" };
    double[] distance = { 628.9, 78.3, 91.7, 4345.4, 1284.4, 2721.4, 42.4 };

    return new Object[] { planets, distance };
}
```

### Explicación:

- Este método devuelve un arreglo de objetos `Object[]` que contiene dos elementos:
    - Un arreglo de planetas: Nombres de los planetas del sistema solar.
    - Un arreglo de distancias: Distancia en millones de kilómetros de la Tierra a cada planeta.
> 🔑 **Importante:** La razón de devolver un `Object[]` es que estamos devolviendo dos tipos de datos diferentes: un arreglo de cadenas `(String[])` y un arreglo de números decimales `(double[])`. La estructura `Object[]` permite agruparlos juntos.

### **2. Método `listSpaceships()` - Naves espaciales y velocidades 🚀**

```java
static Object[] listSpaceships() {
    String[] spaceShips = { "Transbordador espacial", "Sonda espacial", "Nave de propulsión nuclear", 
                            "Sonda solar de velocidad ultra alta", "Nave de propulsión de antimateria", 
                            "Nave con motor de curvatura" };
    double[] speed = { 28000, 61000, 100000, 600000, 10000000, 1080000000 };

    return new Object[] { spaceShips, speed };
}
```
### Explicación:
- Similar al método anterior, este devuelve un arreglo de objetos que contiene:
    - Un arreglo de naves espaciales: Nombres de diferentes tipos de naves.
    - Un arreglo de velocidades: Velocidades de estas naves en km/h.
> 🚀 Cada nave tiene una velocidad asociada que se usará para calcular el tiempo de viaje.

### **3. Método `showMenu()` - Menú de selección del planeta 📋**

```java
private static void showMenu(Scanner reqShow) {
    // Obtiene los planetas y sus respectivas distancias.
    Object[] planetCore = listPlanets();
    String[] planets = (String[]) planetCore[0];
    double[] distance = (double[]) planetCore[1];

    do {
        // Muestra los planetas y sus distancias.
        for (int i = 0; i < planets.length; i++) {
            System.out.printf("%d. %s a %.1f millones de km.%n", i + 1, planets[i], distance[i]);
        }
        // Opciones para el usuario.
        System.out.println("0. Salir del programa.");
        System.out.print("-> ");
        option = reqShow.nextByte();
        reqShow.nextLine();

        // Lógica para elegir el planeta.
        if (option > 0 && option <= planets.length) {
            System.out.printf("La distancia a %s es de %.1f millones de km.", planets[option - 1], distance[option - 1]);
            pressEnter(reqShow);
            chooseShip(reqShow, distance[option - 1]);
        } else if (option != 0) {
            System.err.println("Ingrese una opción válida");
            pressEnter(reqShow);
        }
    } while (option != 0);
}
```

### Explicación:

- **Mostrar Planetas:** Este método muestra una lista numerada de los planetas junto con su distancia desde la Tierra. El usuario puede elegir un planeta ingresando el número correspondiente.
- **Selección de Planeta:** Si el usuario elige un planeta válido (entre 1 y el número de planetas), se muestra la distancia y se pasa al siguiente paso: elegir una nave espacial.
>🔑 **Concepto importante:** El método utiliza un ciclo `do-while` para permitir que el usuario elija varias veces hasta que seleccione la opción 0 para salir.

### **4. Método `chooseShip()` - Selección de nave espacial 🚀**

```java
private static void chooseShip(Scanner reqShip, double planetDistance) {
    // Obtiene las naves espaciales y sus velocidades
    Object[] spaceShipsCore = listSpaceships();
    String[] spaceShips = (String[]) spaceShipsCore[0];
    double[] speed = (double[]) spaceShipsCore[1];

    // Muestra las opciones de naves espaciales
    for (int i = 0; i < spaceShips.length; i++) {
        System.out.printf("%d. %s con una velocidad de %.0f km/h.%n", i + 1, spaceShips[i], speed[i]);
    }

    // Lógica de selección de nave
    System.out.print("-> ");
    option = reqShip.nextByte();
    reqShip.nextLine();

    if (option > 0 && option <= spaceShips.length) {
        System.out.printf("%s viajando a %.0f km/h...", spaceShips[option - 1], speed[option - 1]);
        pressEnter(reqShip);
        calculateSpeed(reqShip, planetDistance, speed[option - 1]);
    } else {
        System.err.println("Opción inválida");
        pressEnter(reqShip);
    }
}
```

### Explicación:

- **Mostrar Naves:** Similar al menú de planetas, este método muestra las naves espaciales disponibles junto con sus velocidades.
- **Selección de Nave:** El usuario elige una nave ingresando el número correspondiente. Una vez seleccionada la nave, se pasa al cálculo del tiempo de viaje.

### **5. Método `calculateSpeed()` - Cálculo del tiempo de viaje ⏳**

```java
private static void calculateSpeed(Scanner reqCalculate, double planetDistance, double shipSpeed) {
    double timeHours = (planetDistance * 1_000_000) / shipSpeed;
    double timeDays = timeHours / 24;
    double timeYears = timeDays / 365;
    double timeMinutos = timeHours * 60;

    System.out.printf("La duración del viaje es de %.2f horas (%.2f minutos, %.2f días, %.2f años).%n", timeHours, timeMinutos, timeDays, timeYears);
    pressEnter(reqCalculate);
}
```
### Explicación:

- **Cálculo de Tiempo:** Este método calcula el tiempo que tardaría en llegar a un planeta dado:
    - Convierte la distancia del planeta (en millones de kilómetros) a kilómetros.
    - Usa la fórmula de tiempo: `tiempo = distancia / velocidad.`
    - Calcula el tiempo en horas, minutos, días y años.
>🔑 **Importante:** Este cálculo no tiene en cuenta factores como la aceleración o desaceleración, lo que lo hace una simplificación, pero es útil para obtener una estimación rápida.

### **6. Método `pressEnter() `- Esperar la acción del usuario ⌨️**

```java
private static void pressEnter(Scanner pressRequest) {
    System.out.printf("%nOprima ENTER para continuar.%n");
    pressRequest.nextLine();
}
```

### Explicación:

- Este método espera que el usuario presione la tecla **ENTER** para continuar con la ejecución del programa. Es útil para que el usuario pueda leer los resultados antes de que se muestre la siguiente pantalla.

***🎮 En constante actualización...***