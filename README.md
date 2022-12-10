# Desafio_Android
 Desafío para practica en SoyMomo
 
Se decidió optar por un listado que recopilara toda la info de criptomonedas recibida por la API, 
la cual posteriormente pasara por una funcion de filtro que las ordenara y dejara al top 10.
Al implementarlo de esta manera, podemos extender en futuras versiones esta aplicación para mayor manejo
en los datos iniciales, a la vez que conservamos nuestro filtro inicial. 
En otro ambito, se dejo una barra superior para establecer el valor de la notificacion (Desafio extra),
el cual puede ser implementado en un futuro.

Features:
-Incorporación de API de coinmarketcap, desplegando las diez crypto con mayor valor actual.
Por implementar:
-Notificación para BTC segun valor definido por usuario
Implementable:
-Mayor variedad de filtros, selección de cantidad de monedas a desplegar, comparar unas con otras.
-Añadir porcentaje de cambio respecto a un tiempo definido por el usuario.
-Mayor funcionalidad en notificacione, permitiendo por ej notificar al usuario cuando una moneda sobrepase
un porcentaje de cambio, o cuando una nueva moneda aparezca en el top 10.


Bugs:
-Al no poseer manipulación de usuario aún, no esta propenso a fallas imprevistas.
-Un bug posible sería la modificación del formato de la API utilizada
-En la misma línea, la aplicación es fragil a cambios en la informacion recibida.
