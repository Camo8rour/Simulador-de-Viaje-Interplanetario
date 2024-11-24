import java.util.Random;
import java.util.Scanner;

public class App {

    // Método con los planetas y sus distancias.
    static Object[] listPlanets() {
        String[] planets = { "Venus", "Marte", "Mercurio", "Júpiter", "Saturno", "Urano", "Neptuno" };
        double[] distance = { 42.4, 78.3, 91.7, 628.9, 1284.4, 2721.4, 4345.4 };

        return new Object[] { planets, distance };
    }

    // Método con las naves espaciales y sus velocidades.
    static Object[] listSpaceships() {
        String[] spaceShips = { "Transbordador espacial", "Sonda espacial", "Nave de propulsión nuclear",
                "Sonda solar de velocidad ultra alta", "Nave de propulsión de antimateria",
                "Nave con motor de curvatura" };
        double[] speed = { 28000, 61000, 100000, 600000, 10000000, 1080000000 };

        return new Object[] { spaceShips, speed };
    }

    public static void main(String[] args) throws Exception {
        var request = new Scanner(System.in);

        showMenu(request);

        request.close();
    }

    // Método del menú principal para elegir planeta.
    private static void showMenu(Scanner reqShow) {
        byte option;

        // Obtiene los planetas con sus respectivas distancias.
        Object[] planetCore = listPlanets();
        String[] planets = (String[]) planetCore[0];
        double[] distance = (double[]) planetCore[1];

        do {
            System.out.printf("%n-----------------------------------------------------%n");
            System.out.println("\t \t MENÚ DE OPCIONES");
            System.out.println("-----------------------------------------------------");
            System.out.println("Ingrese el número del planeta al que desea viajar.");

            // Muestra los planetas con sus distancias.
            for (int i = 0; i < planets.length; i++) {
                System.out.printf("%d. %s a %.1f millones de km.%n", i + 1, planets[i], distance[i]);
            }

            System.out.println("0. Salir del programa.");
            System.out.print("-> ");
            option = reqShow.nextByte();
            reqShow.nextLine();

            if (option > 0 && option <= planets.length) {
                // Muestra la distancia y luego llama al siguiente método para elegir la nave.
                System.out.printf("La distancia a %s es de %.1f millones de km.", planets[option - 1],
                        distance[option - 1]);
                pressEnter(reqShow);

                chooseShip(reqShow, distance[option - 1]);
            } else if (option != 0) {
                System.err.println("Ingrese una opción válida");
                pressEnter(reqShow);
            }

        } while (option != 0);

        System.out.println("Gracias por usar el simulador de viaje interplanetario, see you later.");
    }

    // Método para elegir la nave espacial.
    private static void chooseShip(Scanner reqShip, double planetDistance) {
        byte option;

        // Obtiene las naves espaciales y sus velocidades.
        Object[] spaceShipsCore = listSpaceships();
        String[] spaceShips = (String[]) spaceShipsCore[0];
        double[] speed = (double[]) spaceShipsCore[1];

        System.out.println("Ingrese el número de la nave espacial con la que desea viajar.");

        for (int i = 0; i < spaceShips.length; i++) {
            System.out.printf("%d. %s con una velocidad de %.0f km/h.%n", i + 1, spaceShips[i], speed[i]);
        }
        System.out.print("-> ");
        option = reqShip.nextByte();

        reqShip.nextLine();

        if (option > 0 && option <= spaceShips.length) {
            // Muestra la nave que seleccionó y luego va al método de cálculo.
            System.out.printf("%s viaja a %.0f km/h...", spaceShips[option - 1], speed[option - 1]);
            pressEnter(reqShip);
            calculateSpeed(reqShip, planetDistance, speed[option - 1]);
        } else {
            System.err.println("Opción inválida");
            pressEnter(reqShip);
        }
    }

    // Método para calcular el tiempo del viaje.
    private static void calculateSpeed(Scanner reqCalculate, double planetDistance, double shipSpeed) {

        double timeHours = (planetDistance * 1_000_000) / shipSpeed;

        System.out.printf("La duración del viaje es de %.2f horas.%n", timeHours);
        pressEnter(reqCalculate);

        progresBar(reqCalculate, planetDistance, shipSpeed, timeHours);
    }

    // Método para ver el progreso, la cantidad de combustible, oxígeno y tiempo estimado.
    private static void progresBar(Scanner reqCalculate, double planetDistance, double shipSpeed, double timehours) {
        double combustible = 100.0; // 100% de combustible al inicio.
        double oxigeno = 100.0; // 100% de oxígeno al inicio.

        // Avance progreso
        System.out.println("\t Viajando...");

        int totalSteps = 50; // Progreso 1 a 100.
        long sleepTimePerStep = 300; // Tiempo milisegundos si quieres lo puedes agrandar o minimizar.

        // Puede cambiarlos para validar cuando faltan recursos
        double combustiblePorPaso = 50.0 / totalSteps; // Disminuye 0,5% por cada 1% de avance de la nave.
        double oxigenoPorPaso = 50.0 / totalSteps; // Disminuye 0,5% por cada 1% de avance de la nave.

        String nave = "[>"; // La nave xd.

        // Generar un objeto Random para los eventos aleatorios.
        Random random = new Random();

        for (int i = 1; i <= totalSteps; i++) {
            try {
                Thread.sleep(sleepTimePerStep);
            } catch (InterruptedException e) {
                System.err.println("Error en el hilo de ejecución: " + e.getMessage());
                return;
            }

            // Quitar recursos.
            combustible -= combustiblePorPaso;
            oxigeno -= oxigenoPorPaso;

            // Calcular distancia recorrida y restante.
            double distancetraveled = (planetDistance * 1_000_000) * (i * 1.0 / totalSteps); // Convertir a km.
            double remainingdistance = (planetDistance * 1_000_000) - distancetraveled; // Convertir a km.

            // Calcular el tiempo restante en horas.
            double remainingtime = remainingdistance / shipSpeed;

            // Convertir el tiempo restante a horas y minutos.
            int remainingHours = (int) remainingtime;
            int remainingMinutes = (int) ((remainingtime - remainingHours) * 60);

            // Barra de progreso
            double percentage = (i * 100.0) / totalSteps;
            String barra = "=".repeat(i) + nave + " ".repeat(totalSteps - i);

            System.out.printf(
                    "\r[%s] %.2f%% | Combustible: %.2f%% | Oxígeno: %.2f%% | Tiempo Restante: %02d:%02d horas",
                    barra, percentage, combustible, oxigeno, remainingHours, remainingMinutes);

            // Generar eventos aleatorios 
            if (random.nextInt(100) < 10) { // 10% de probabilidad de que ocurra un evento en cada paso.
                triggerRandomEvent(reqCalculate, random, combustible, oxigeno);
            }

            // (game over)
            if (combustible <= 0 || oxigeno <= 0) {
                System.err.println("\n¡Game over! Los recursos se han agotado.");
                pressEnter(reqCalculate);
                return; // Fin del juego si los recursos se acaban.
            }
        }

        // Recursos al final del viaje.
        System.out.println("\n");
        if (combustible >= 50.0 && oxigeno >= 50.0) {
            System.out.println("Viaje completado con éxito. Tienes los recursos necesarios para un viaje de regreso.");
        } else {
            System.out.println("Viaje completado con éxito, pero los recursos son insuficientes para regresar.");
            if (combustible < 50.0) {
                System.err.printf("Combustible bajo solo queda: %.2f%% restante.%n", combustible);
            }
            if (oxigeno < 50.0) {
                System.err.printf("Oxígeno bajo solo queda: %.2f%% restante.%n", oxigeno);
            }
        }

        pressEnter(reqCalculate);
    }

    
    private static void triggerRandomEvent(Scanner reqCalculate, Random random, double combustible, double oxigeno) {
        System.out.println("\n¡Un evento ha ocurrido durante el viaje!");

        // evento negativo
        if (random.nextBoolean()) {
            int eventLoss = random.nextInt(3); // 3 posibles eventos de pérdida pueden cambiarse las frases
            switch (eventLoss) {
                case 0:
                    System.out.println("¡Oh no! Una fuga de oxígeno ha ocurrido.");
                    oxigeno -= 10;
                    break;
                case 1:
                    System.out.println("¡Cuidado! El combustible está siendo consumido más rápido.");
                    combustible -= 15;
                    break;
                case 2:
                    System.out.println("¡Problema en el sistema! Se ha perdido algo de oxígeno.");
                    oxigeno -= 5;
                    break;
            }
        } else {
            // Evento positivo
            int eventGain = random.nextInt(3); // 3 posibles eventos de ganancia pueden cambairse las frases
            switch (eventGain) {
                case 0:
                    System.out.println("Has encontrado una fuente de oxígeno adicional.");
                    oxigeno += 10;
                    break;
                case 1:
                    System.out.println("¡Excelente! El sistema ha optimizado el consumo de combustible.");
                    combustible += 15;
                    break;
                case 2:
                    System.out.println("¡Suerte! Has encontrado un suministro de oxígeno adicional.");
                    oxigeno += 5;
                    break;
            }
        }

        // Asegurarse de que los recursos no superen el 100% ni bajen del 0%
        oxigeno = Math.min(100, Math.max(0, oxigeno));
        combustible = Math.min(100, Math.max(0, combustible));

        // Preguntar al usuario si desea actuar
        System.out.print("¿Deseas repararlo? (Si/No): ");
        String respuesta = reqCalculate.nextLine().toLowerCase();

        if (respuesta.equals("si")) {
            // Acción positiva restaura recursos
            oxigeno = Math.min(100, oxigeno + 10);
            combustible = Math.min(100, combustible + 10);
            System.out.println("Has decidido reparar el problema, los recursos han aumentado.");
        } else {
            // Respuesta incorrecta 
            System.out.println("Has decidido no tomar acción. Los recursos disminuyen debido al daño no reparado.");
            oxigeno -= 5;
            combustible -= 5;
        }

        // Imprimir los recursos actuales
        System.out.printf("Recursos actuales - Combustible: %.2f%% | Oxígeno: %.2f%%\n", combustible, oxigeno);
        pressEnter(reqCalculate);
    }

    // Método para esperar a que el usuario presione ENTER para continuar.
    private static void pressEnter(Scanner pressRequest) {
        System.out.printf("%nOprima ENTER para continuar.%n");
        pressRequest.nextLine();
    }
}
