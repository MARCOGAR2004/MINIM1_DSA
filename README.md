He tenido problemas con la función de find Punto donde busco una petición mediante su longitud y su latitud, nunca la encontraba, y en el service hay bastantes funciones donde me sale que los datos que le introduzco esta en un formato incorrecto.
Me funciona:
-Añadir un usuari.
-Listarusuarios alfabeticamente.
-Consultar la informacion de un usuario.
Las demas demas partes están en desarrollo

Versión final:
En principio ya funciona todo. 
Lo que me fallaba en la función find Punto era que en el constructor de peticiones tenia la longitud canviada con la latitud, y a raíz de eso ya me empezaron a fallar el resto de funciones donde necesitaba verificar que el punto existia, por lo que el resto de funciones que no me funcionaban que puse que estan en desarrollo estaban bien ya, pero me fallaban en el find Punto
También he añadido que informe en el swagger cuando no encuentra un punto o un usuario.
Y por ultimo he arreglado los constructores para que no me saliera el error 500 en el swagger.
      
