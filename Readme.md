Pasos para deployar el proyecto

Este proyecto fue realizado en 3 componentes :

desarrolloFE -> es el front end
desarrolloBFF -> capa intermedia entre la comunicacion del front end con el back end
desarrolloBE -> es el back end
despliegue proyecto desarrolloFE: Primero, se debe descargar los 3 proyectos, luego en el caso del proyecto desarrolloFE se debe abrir en un editor de texto, en mi caso utilice Visual Studio Code, este editor tiene un terminal que permite acceder a la ruta del proyecto desarrolloFE, estando ahi se debe colocar el comando npm install que permite descargar todas las dependencias del proyecto y se generara una carpeta llamada node_modules, a continuación se debe colocar el comando ng serve para levantar el proyecto y colocar en el navegador localhost:4200

despliegue proyecto desarrolloBFF: Segundo, se debe importar el proyecto en la herramienta Spring boot como Gradle -> Existing Gradle Project. En caso que aparezca con errores el proyecto se debe ir al proyecto -> clic derecho y colocar Gradle -> Refresh Gradle Project Project -> Refresh , Project -> Clean. Luego para ejecutar el proyecto desarrolloBFF se debe ir al proyecto -> clic derecho y colocar Run As -> Spring boot App y aparecera en la consola la ejecución cuando este finalizado se mostrará que el proyecto se levantará en el puerto 8082

despliegue proyecto desarrolloBE: Segundo, se debe importar el proyecto en la herramienta Spring boot como Gradle -> Existing Gradle Project. En caso que aparezca con errores el proyecto se debe ir al proyecto -> clic derecho y colocar Gradle -> Refresh Gradle Project Project -> Refresh , Project -> Clean. Luego para ejecutar el proyecto desarrolloBE se debe ir al proyecto -> clic derecho y colocar Run As -> Spring boot App y aparecera en la consola la ejecución cuando este finalizado se mostrará que el proyecto se levantará en el puerto 8081

Para realizar las pruebas Tienen que estar los 3 proyectos levantados y colocar en el navegador localhost:4200 (ingresar nombre completo de la persona y la fecha de nacimiento) luego al hacer clic en el boton el servicio responderá con la felicitación en caso de estar de cumpleaños o no mostrará la felicitación en caso de haber estado o estará de cumpleaños.

Para realizar test se debe ir al proyecto -> clic derecho y colocar Coverage As -> JUnit Test

