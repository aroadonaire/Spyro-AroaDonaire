# Spyro The Dragon

## Introducción
Esta aplicación permite ver información sobre los personajes, mundos y coleccionables del juego Spyro. Incluye una guía de usuario y Easter Eggs

## Características Principales
* **Guía Interactiva:** Consta de un sistema de bocadillos y sonidos que explica la app al usuario nuevo. Si ya la has visto, no volverá a salir.
* **Enciclopedia:** Listados detallados de los personajes, mundos y coleccionables con RecyclerView y CardView.
* **Easter Eggs:**
    * **Video Secreto:** Pulsa 3 veces rápido en cualquier mundo para desbloquear un vídeo.
    * **Magia de Ripto:** Mantén pulsado sobre Ripto en la lista de personajes para ver un efecto creado con Canvas.

## Tecnologías Utilizadas
* **Lenguaje:** Kotlin.
* **Navegación:** NavGraph, para organizar las pantallas de la app.
* **Animaciones:** ValueAnimator y Canvas.
* **Multimedia:** sonidos y VideoView para vídeos.
* **Persistencia:** SharedPreferences para que si has visto la guía, no la vuelvas a ver.

## Instrucciones de Uso
1. Clonar el repositorio: https://github.com/aroadonaire/Spyro-AroaDonaire.git
2. Abrir con **Android Studio**.
3. Sincronizar Gradle y ejecutar en un emulador.

## Conclusiones del Desarrollador
Los mayores problemas que ha tenido este trabajo fueron sobre todo los easter eggs, para que el video apareciera a los 3 click y volvieramos a la pantalla y el crear el efecto mágico de la varita de Ripto. Tambien la guía interactiva pero aquí, sobre todo, el uso del SharedPreferences para que no se volviera a abrir.
