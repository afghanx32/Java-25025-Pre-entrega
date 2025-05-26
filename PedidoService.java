package com.talentotech.service;

import com.talentotech.model.Pedido;
import com.talentotech.model.Producto;
import com.talentotech.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {
    private final ProductoService productoService;

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void crearPedido(Scanner scanner) {
        List<Producto> disponibles = productoService.getProductos();
        if (disponibles.isEmpty()) {
            System.out.println("No hay productos disponibles.");
            return;
        }

        List<Producto> seleccionados = new ArrayList<>();
        int seleccion;

        do {
            System.out.println("\nProductos disponibles:");
            for (int i = 0; i < disponibles.size(); i++) {
                System.out.println((i + 1) + ". " + disponibles.get(i));
            }
            System.out.print("Seleccione el número del producto (0 para finalizar): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Número inválido. Intente nuevamente.");
                scanner.next();
            }
            seleccion = scanner.nextInt();
            scanner.nextLine();

            if (seleccion > 0 && seleccion <= disponibles.size()) {
                seleccionados.add(disponibles.get(seleccion - 1));
                System.out.println("Producto agregado al pedido.");
            } else if (seleccion != 0) {
                System.out.println("Selección inválida.");
            }
        } while (seleccion != 0);

        if (!seleccionados.isEmpty()) {
            Pedido nuevoPedido = new Pedido(seleccionados);
            JsonUtil.guardarPedido(nuevoPedido);
            System.out.println("Pedido creado con éxito:");
            System.out.println(nuevoPedido);
        } else {
            System.out.println("No se creó el pedido.");
        }
    }
}
