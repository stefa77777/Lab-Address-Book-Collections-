package org.example;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion = 0;

        do {
            System.out.println("\n=== AGENDA DE CONTACTOS ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Listar contactos (orden alfabetico)");
            System.out.println("5. Listar historial (orden de insercion)");
            System.out.println("6. Agregar grupo");
            System.out.println("7. Agregar contacto a grupo");
            System.out.println("8. Listar grupos");
            System.out.println("9. Mostrar contactos por grupo");
            System.out.println("10. Salir");
            System.out.print("Elige una opcion: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Opcion invalida, intenta de nuevo");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("--- AGREGAR CONTACTO ---");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Telefono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Contacto nuevo = new Contacto(nombre, telefono, email);
                    agenda.agregarContacto(nuevo);
                    break;

                case 2:
                    System.out.println("--- BUSCAR CONTACTO ---");
                    System.out.print("Nombre a buscar: ");
                    String nombreBusqueda = scanner.nextLine();
                    Contacto encontrado = agenda.buscarContacto(nombreBusqueda);
                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado);
                    } else {
                        System.out.println("Contacto no encontrado: " + nombreBusqueda);
                    }
                    break;

                case 3:
                    System.out.println("--- ELIMINAR CONTACTO ---");
                    System.out.print("Nombre a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    agenda.eliminarContacto(nombreEliminar);
                    break;

                case 4:
                    System.out.println();
                    agenda.listarContactos();
                    break;

                case 5:
                    System.out.println();
                    agenda.listarHistorial();
                    break;

                case 6:
                    System.out.println("--- AGREGAR GRUPO ---");
                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = scanner.nextLine();
                    agenda.agregarGrupo(nombreGrupo);
                    break;

                case 7:
                    System.out.println("--- AGREGAR CONTACTO A GRUPO ---");
                    System.out.print("Nombre del contacto: ");
                    String cGrupo = scanner.nextLine();
                    System.out.print("Nombre del grupo: ");
                    String gContacto = scanner.nextLine();
                    agenda.agregarContactoAGrupo(cGrupo, gContacto);
                    break;

                case 8:
                    System.out.println();
                    agenda.listarGrupos();
                    break;

                case 9:
                    System.out.println("--- MOSTRAR CONTACTOS POR GRUPO ---");
                    System.out.print("Nombre del grupo: ");
                    String grupoMostrar = scanner.nextLine();
                    agenda.mostrarContactosPorGrupo(grupoMostrar);
                    break;

                case 10:
                    System.out.println("Saliendo de la agenda...");
                    break;

                default:
                    System.out.println("Opcion invalida, intenta de nuevo");
                    break;
            }

        } while (opcion != 10);

        scanner.close();
    }
}