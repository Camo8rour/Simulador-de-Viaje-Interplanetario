import java.util.Scanner;

public class App {

    //Método con los planetas y sus distancias.
    static Object[] listPlanets() {
        String[] planets = { "Júpiter", "Marte", "Mercurio", "Neptuno", "Saturno", "Urano", "Venus" };
        double[] distance = { 628.9, 78.3, 91.7, 4345.4, 1284.4, 2721.4, 42.4 };

        return new Object[] { planets, distance };
    }

    //Método con llas naves espaciales y sus velocidades.
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
    
    //Método del menú principal para elegir planeta.
    private static void showMenu(Scanner reqShow) {
        byte option;
        
        //Obtiene los planesas con sus respectivas distancias.
        Object[] planetCore = listPlanets();
        String[] planets = (String[]) planetCore[0];
        double[] distance = (double[]) planetCore[1];

        do {
            System.out.printf("%n-----------------------------------------------------%n");
            System.out.println("\t \t MENÚ DE OPCIONES");
            System.out.println("-----------------------------------------------------");
            System.out.println("Ingrese el número del planeta al que desea viajar.");

            //Muestra los planetas con sus distancias.
            for (int i = 0; i < planets.length; i++) {
                System.out.printf("%d. %s a %.1f millones de km.%n", i + 1, planets[i], distance[i]);
            }

            System.out.println("0. Salir del programa.");
            System.out.print("-> ");
            option = reqShow.nextByte();
            reqShow.nextLine();

            if (option > 0 && option <= planets.length) {
                //Muestra la distancia y luego llama al siguiente método para elegir la nave.
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

    //Método para elegir la nave espacial.
    private static void chooseShip(Scanner reqShip, double planetDistance) {
        byte option;

        //Obtiene las naves espaciales y sus velociadaes.
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
            //Muestra la nave que selecciono y luego va al método de calculo.
            System.out.printf("%s viajando a %.0f km/h...", spaceShips[option - 1], speed[option - 1]);
            pressEnter(reqShip);
            calculateSpeed(reqShip, planetDistance, speed[option - 1]);
        } else {
            System.err.println("Opcion invalida");
            pressEnter(reqShip);
        }
    }

    //Método para calcular el tiempo del viaje.
    private static void calculateSpeed(Scanner reqCalculate, double planetDistance, double shipSpeed) {
        
        double timeHours = (planetDistance * 1_000_000) / shipSpeed;

        double timeDays = timeHours / 24;
        double timeYears = timeDays / 365;
        double timeMinutos = timeHours * 60;

        System.out.printf("La duracion del viaje es de %.2f horas (%.2f minutos, %.2f días, %.2f años).%n", timeHours, timeMinutos, timeDays, timeYears);
        pressEnter(reqCalculate);
    
        double combustible = 100.0;  // 100% de combustible al inicio
        double oxigeno = 100.0;  // 100% de oxígeno al inicio
        
    
        // avance progreso
    System.out.println("viajando...");

    int totalSteps = 100;  // progreso 1 a 100
    long sleepTimePerStep = 500;  // Tiempo milisegundos si quieres lo puedes agrandar o minimizar

    double combustiblePorPaso = 100.0 / totalSteps;  // Disminuye 1% por cada paso
    double oxigenoPorPaso = 100.0 / totalSteps;  // Disminuye 1% por cada paso

    String nave = "[:>";  // la nave xd
    
    for (int i = 1; i <= totalSteps; i++) {
        try {
            
            Thread.sleep(sleepTimePerStep); 
        } catch (InterruptedException e) {
            System.err.println("Error en el hilo de ejecución: " + e.getMessage());
            return;

        }

         // quitar recursos
        combustible -= combustiblePorPaso;
        oxigeno -= oxigenoPorPaso;

        // barra de progreso
        double percentage = (i * 100.0) / totalSteps;
        String barra = "=".repeat(i) + nave + " ".repeat(totalSteps - i);

        System.out.printf("\r[%s] %.2f%% | Combustible: %.2f%% | Oxígeno: %.2f%%", barra, percentage, combustible, oxigeno);
    }

    System.out.println("\nViaje completado!");
    pressEnter(reqCalculate);
}


    //Método para esperar a que el usuario presione ENTER para seguir.
    private static void pressEnter(Scanner pressRequest) {
        System.out.printf("%nOprima ENTER para continuar.%n");
        pressRequest.nextLine();
    }
}