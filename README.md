# DPOO-SEC04-202120-G07 - Proyecto 02

Juan Sebastián Ortega Romero | js.ortegar1@uniandes.edu.co 

Camilo Andrés Morillo Cervantes | c.morillo@uniandes.edu.co

Especificaciones e instrucciones para ejecutar la aplicación: 

-Ambos sistemas parten de la clase InterfazGrafica donde se encuentra el único método main de todo el aplicativo. A partir de este, el uso del programa y la diferenciación de los sistemas es bastante intuitivo. Sin embargo, es necesario aclarar que la persistencia es manejada de forma AUTOMÁTICA teniendo en cuenta dos factores. Primero, la CARGA se realiza en el momento en que se accede a los sistemas (tras pasar por cada Log-In específico) y segundo, el GUARDADO se realiza en el momento en que el usuario CIERRA LA VENTANA PRINCIPAL, tras esta acción saldrá un mensaje notificando que se han guardado los cambios realizados en dicha sesión. 

-Por defecto el inventario del supermercado CUENTA CON PRODUCTOS PRE-CARGADOS, por lo que se proponen los siguientes pasos para realizar todas las pruebas necesarias:

#Sistema Inventario
1) En el Sistema Inventario encontrará ciertos productos que ya han sido cargados en la sección derecha de la aplicación. Arriba de estos encontrará una barra de búsqueda que le permitira filtrar dichos productos con base en su ID ÚNICO (que puede empezar por P- en algunos casos). Sientase libre de interactuar con dichos productos y de consultar su desempeño, así mismo puede observar los lotes relacionados a cada uno. En el caso de las Zucaritas podrá observar que ese lote SE ENCUENTRA VENCIDO.

2) Por medio del botón "Eliminar Lotes Vencidos", seleccione la fecha actual y diríjase nuevamente a las Zucaritas. Ahora podrá observar que el Lote 01 ha sido eliminado, esto se debe a que ha sido marcado internamente como vencido. 

3) Con el botón "Agregar Unidad de Almacenamiento" podrá agregar nuevas unidades en dado caso de que se expanda el supermercado. Recuerde que existen 4 unidades de prueba por defecto [U-1 (Góndola), U-2 (Frescos), U-3 (Congelador) y U-4 (Refrigerador)].

4) Con el botón "Agregar Imagen" sientase libre de agregar la imagen faltante: la lechuga. Para esto digite el código de este producto (P-54) y seleccione la imagen correspondiente. Existe total libertad para cambiar o agregar nuevas imagenes al sistema SIEMPRE Y CUANDO ALMACENE LAS NUEVAS IMÁGENES EN LA CARPETA "imagenesProductos".

5) Han llegado nuevos lotes al supermercado, para agregarlos diríjase al botón "Agregar nuevos lotes", seleccione el archivo "lotesNuevos" y siga los pasos correspondientes (¡Recuerde utilizar únicamente unidades de almacenamiento ya creadas!). Una vez finalice el proceso, agregue las imágenes correspondientes.

6)Una vez termine de interactuar con el sistema, cierre la ventana principal para guardar los cambios.


#Sistema POS
7) Ahora que el supermercado tiene una gran variedad de productos, vuelva a abrir la aplicación pero ahora diríjase al Sistema POS.

8) Pruebe el botón "Registrar cliente" y regístrese a usted mismo como cliente. ¡Recuerde que su cédula será la llave con la que el cajero podrá relacionarlo con su frecuencia de compra!

9) Dirígase a "Iniciar compra activa" y antes de iniciar una compra con su cédula, pruebe la cédula de uno de nuestros clientes frecuentes: 1000612221. Al hacer esto,  podrá observar como el diagrama se ilumina para mostrar la frecuencia de compra de dicho cliente. Ahora sí, digite su cédula e inicie una nueva compra.

10)Escoja los productos que le apetezca. No se preocupe si selecciona productos no disponibles o selecciona una cantidad superior a la disponible, puesto que el sistema le avisará y no agregará dichos productos a la compra.

11) Una vez finalice su sesión de compras, seleccione el botón "Finalizar compra" donde se le mostrará la factura.

12) Cierre la ventana para almacenar la información.
