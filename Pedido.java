package com.talentotech.model;

import java.util.List;

public class Pedido {
    private List<Producto> productos;

    public Pedido(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    @Override
    public String toString() {
        return "Pedido con " + productos.size() + " productos | Total: $" + calcularTotal();
    }
}
