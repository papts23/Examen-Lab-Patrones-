# Tienda Virtual - Patrones de Diseño

1. Descripción del Problema 

El objetivo es desarrollar el flujo de una tienda virtual funcional por consola. El sistema necesita calcular el total de un carrito de compras, aplicar descuentos dinámicos, procesar un pago con un servicio externo incompatible y disparar alertas automáticas al confirmar la compra.

2. Explicación de los Patrones Aplicados

* Strategy: Se usa para encapsular e intercambiar los algoritmos de descuento (NoDiscount, Percentage y FixedAmount`) por composición sin alterar el servicio principal.
* Adapter: Actúa como puente para conectar la interfaz propia PaymentProcessor con el servicio externo ExternalPayPalService`, adaptando sus métodos incompatibles de forma transparente.
* Observer: Permite desacoplar las notificaciones. Al confirmar la compra, el sistema avisa automáticamente a los observadores registrados (Email, Inventory y Admin).

3. Ejemplo de Salida en Consola

=== EJECUCIÓN CASO 1: EJEMPLO DE LA GUÍA ===

Compra confirmada por S/ 120.01 

Pago realizado con PayPal: PEN 120.006

Enviando correo al cliente... [Orden: S/ 120.01]

Actualizando inventario... [Orden: S/ 120.01]

Notificando al administrador... [Orden: S/ 120.01]


---------------------------------------------------

=== EJECUCIÓN CASO 2: COMPRA CON MÁS PRODUCTOS ===

Compra confirmada por S/ 265.00

Pago realizado con PayPal: PEN 265.0

Enviando correo al cliente... [Orden: S/ 265.00]

Actualizando inventario... [Orden: S/ 265.00]

Notificando al administrador... [Orden: S/ 265.00]

Process finished with exit code 0