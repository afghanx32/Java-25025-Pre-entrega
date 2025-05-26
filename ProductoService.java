package com.talentotech.service;

import com.talentotech.model.Producto;
import com.talentotech.util.JsonUtil;

import java.util.List;
import java.util.Scanner;

public class ProductoService {
    private final List<Producto> productos;

    public ProductoService() {
        this.productos = JsonUtil.cargarProductos();
    }

    public void agregarProducto(Scanner scanner) {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese precio del producto: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Precio inválido. Intente nuevamente.");
            scanner.next();
        }
        double precio = scanner.nextDouble();
        scanner.nextLine();

        if (precio <= 0) {
            System.out.println("El precio debe ser mayor que cero.");
            return;
        }

        Producto nuevoProducto = new Producto(nombre, precio);
        productos.add(nuevoProducto);
        JsonUtil.guardarProductos(productos);
        System.out.println("Producto agregado correctamente.");
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
