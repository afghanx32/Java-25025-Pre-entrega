package com.talentotech.util;

import com.talentotech.model.Producto;
import com.talentotech.model.Pedido;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static final String PRODUCTOS_FILE = "src/main/resources/productos.json";
    private static final String PEDIDOS_FILE = "src/main/resources/pedidos.json";

    public static List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(PRODUCTOS_FILE)) {
            JSONArray array = (JSONArray) parser.parse(reader);
            for (Object obj : array) {
                JSONObject p = (JSONObject) obj;
                String nombre = (String) p.get("nombre");
                double precio = (double) p.get("precio");
                productos.add(new Producto(nombre, precio));
            }
        } catch (Exception e) {
            System.out.println("No se encontraron productos guardados previamente.");
        }
        return productos;
    }

    public static void guardarProductos(List<Producto> productos) {
        JSONArray array = new JSONArray();
        for (Producto p : productos) {
            JSONObject obj = new JSONObject();
            obj.put("nombre", p.getNombre());
            obj.put("precio", p.getPrecio());
            array.add(obj);
        }
        try (Writer writer = new FileWriter(PRODUCTOS_FILE)) {
            writer.write(array.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarPedido(Pedido pedido) {
        JSONArray array = new JSONArray();
        for (Producto p : pedido.getProductos()) {
            JSONObject obj = new JSONObject();
            obj.put("nombre", p.getNombre());
            obj.put("precio", p.getPrecio());
            array.add(obj);
        }
        try (Writer writer = new FileWriter(PEDIDOS_FILE, true)) {
            writer.write(array.toJSONString());
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
