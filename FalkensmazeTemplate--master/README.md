# TRABAJANDO CON FICHEROS EN JAVA

Java posee una serie de clases que permite interactuar con ficheros, en esta práctica se trabaja con estos tanto en formato binario, Json y XML, en especial estos dos últimos.
   
## Enunciado
Se ha desarrollado un editor de laberintos para el juego Falken's Mace de forma que sea sencillo crear y modificar niveles. Para terminar el proyecto queda poder guardar y cargar laberintos en los siguientes formatos:

   - Binario (serializado).
   - XML.
   - JSON.

![image](https://github.com/pass1enator/falkensmaze/blob/master/ejemplo.png?raw=true)

El modelo del juego consiste simplemente en un laberinto representado por la clase Maze, que posee los siguientes atributos:
```Java
    private Size size;
    private Block[][] blocks;
    private double time;
    private String sound;
```

**Todos estos campos se han de guardar y poder cargar en los ficheros** 

A su vez la clase Block unicamente tiene un String con el nombre del bloque que contiene (si contiene alguno).

En la clase Maze se tienen los métodos estáticos que permiten cargar y guardar los laberintos, siendo la tarea principal de la práctica trabajar con fichero que almacenen objetos binarios, formato JSON y XML:

```Java
    public static Maze load(File file) {
        
    }
    public static void save(Maze maze, File file) {
      
    }

```
## Tareas

 - Crear un método y usarlo en  save (Maze maze, File file) para guardar el laberinto en formato binario (**1 punto**)
 - Crear un método y usarlo en  save (Maze maze, File file) para guardar el laberinto en formato XML pudiendo hacer manualmente o con librerías (**1,25 puntos**)
 - Crear un método y usarlo en  save (Maze maze, File file) para guardar el laberinto en formato JSON pudiendo hacerlo de forma manual o usando librerías externas (**1,25 puntos**)
 - Crear un método y usarlo en  Maze load ( File file) para cargar el laberinto en formato binario (**1 punto**)
 - Crear un método y usarlo en  Maze load ( File file) para cargar el laberinto en formato XML pudiendo hacer manualmente o con librerías (**1,25 puntos**)
 - Crear un método y usarlo en  Maze load ( File file) para cargar el laberinto en formato JSON pudiendo hacerlo de forma manual o usando librerías externas (**1,25 puntos**)
 -  Comentar el código correctamente (**0,5 puntos**)
 -  Almacenar todos los datos del laberinto (**0,5 puntos**)
 -  Que los ficheros json y XML sean válidos (**1,5 puntos**)
 -  Filtar los ficheros con extensiones xml, json y bin(binario) (**0,5 puntos**)
