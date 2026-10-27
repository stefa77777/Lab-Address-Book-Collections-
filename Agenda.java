package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Agenda {
    private Map<String, Contacto> contactos;
    private Set<String> grupos;
    private Map<String, Set<String>> contactosPorGrupo;
    private List<Contacto> historial;

    public Agenda() {
        this.contactos = new TreeMap<>();
        this.grupos = new TreeSet<>();
        this.contactosPorGrupo = new TreeMap<>();
        this.historial = new ArrayList<>();
    }

    public boolean agregarContacto(Contacto c) {
        if (contactos.containsKey(c.getNombre())) {
            System.out.println("Error: Ya existe un contacto con el nombre '" + c.getNombre() + "'");
            return false;
        }
        contactos.put(c.getNombre(), c);
        historial.add(c);
        System.out.println("Contacto agregado: " + c.getNombre());
        return true;
    }

    public Contacto buscarContacto(String nombre) {
        return contactos.get(nombre); // Retorna el contacto o null si no existe
    }

    public boolean eliminarContacto(String nombre) {
        if (!contactos.containsKey(nombre)) {
            System.out.println("Contacto no encontrado: " + nombre);
            return false;
        }

        contactos.remove(nombre);

        historial.removeIf(c -> c.getNombre().equals(nombre));
        for (Set<String> nombresGrupo : contactosPorGrupo.values()) {
            nombresGrupo.remove(nombre);
        }

        System.out.println("Contacto eliminado: " + nombre);
        return true;
    }
    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }
        System.out.println("LISTA DE CONTACTOS (orden alfabetico)");
        for (Contacto c : contactos.values()) {
            System.out.println(c);
        }
    }

    public void listarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("El historial está vacío.");
            return;
        }
        System.out.println("HISTORIAL (orden de insercion)");
        for (Contacto c : historial) {
            System.out.println(c);
        }
    }

    public boolean agregarGrupo(String nombreGrupo) {
        if (grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' ya existe");
            return false;
        }
        grupos.add(nombreGrupo);
        contactosPorGrupo.put(nombreGrupo, new TreeSet<>());
        System.out.println("Grupo '" + nombreGrupo + "' creado");
        return true;
    }

    public boolean agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
            return false;
        }
        if (!contactos.containsKey(nombreContacto)) {
            System.out.println("Error: El contacto '" + nombreContacto + "' no existe");
            return false;
        }

        Set<String> miembros = contactosPorGrupo.get(nombreGrupo);
        if (miembros.contains(nombreContacto)) {
            System.out.println("El contacto ya pertenece a este grupo.");
            return false;
        }

        miembros.add(nombreContacto);
        System.out.println("Contacto agregado al grupo '" + nombreGrupo + "'");
        return true;
    }

    public void listarGrupos() {
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos creados.");
            return;
        }
        System.out.println("LISTA DE GRUPOS:");
        for (String g : grupos) {
            System.out.println("- " + g);
        }
    }

    public void mostrarContactosPorGrupo(String nombreGrupo) {
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
            return;
        }

        Set<String> miembros = contactosPorGrupo.get(nombreGrupo);
        if (miembros == null || miembros.isEmpty()) {
            System.out.println("El grupo '" + nombreGrupo + "' no tiene contactos");
            return;
        }

        System.out.println("Contactos en '" + nombreGrupo + "':");
        for (String nombreContacto : miembros) {
            Contacto c = contactos.get(nombreContacto);
            if (c != null) {
                System.out.println(c);
            }
        }
    }
}
