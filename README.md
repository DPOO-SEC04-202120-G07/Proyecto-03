# DPOO-SEC04-202120-G07 - Proyecto 02

Juan Sebastián Ortega Romero | js.ortegar1@uniandes.edu.co 

Camilo Andrés Morillo Cervantes | c.morillo@uniandes.edu.co

Especificaciones e instrucciones para ejecutar la aplicación: 

-Existen dos formas de iniciar la aplicación, una para iniciar el SISTEMA INVENTARIO y la otra para iniciar el SISTEMA POS, sin embargo ambas están contenidas por el mismo paquete global. Si desea iniciar INVENTARIO debe iniciar la aplicación desde la  global.sistemas.inventario.consola.InterfazSI y si desea iniciar POS debe inicar la aplicación desde global.sistemas.pos.consola.InterfazPOS.

-Por defecto el inventario del supermercado está vacio, por lo que se proponen los siguientes pasos para realizar todas las pruebas necesarias:
  1) En el SISTEMA INVENTARIO cargue la base de datos (Opción 1)
  2) Diríjase al archivo Proyecto-01\SupermarketHandler\lotesNuevos\lotesNuevos.csv (o cree uno en la misma carpeta) donde podrá encontrar la información de los lotes de prueba que se desean añadir. Sientase libre de añadir lotes al csv siguiendo la misma estructura. (Nota: Cuando guarde el archivo NO deje una linea en blanco al final)
  3) En el SISTEMA INVENTARIO cargue los lotes nuevos (Opción 2), ingresando el nombre del archivo de lotes (En este caso lotesNuevos) 
  4) Realice las pruebas con la información cargada.
  5) Guarde la información en la base de datos para mantener la persistencia (Opcion 6)
  6) Con la información inicial ya guardada en la base de datos, puede salir del SISTEMA INVENTARIO y realizar las pruebas que desee en el SISTEMA POS siguiendo una estructura similar a los pasos anteriores (Cargar-Consultar-Guardar). 

#En todo el supermercado solo existe un objeto fijo que debe ser definido manualmente en los CSV: las unidades de almacenamiento (puesto que estan tienen posiciones físicas e inamovibles y su cantidad no tiende a cambiar). Por default se ha incluido una unidad de almacenamiento de prueba por unidad especializada de la siguiente forma: U-1 (Góndola), U-2 (Frescos), U-3 (Congelador) y U-4 (Refrigerador).

#Note que la base de datos se almacena en la carpeta 'data'

#Note que los lotes nuevos aun no cargados se almacenan en la carpeta 'lotesNuevos'
