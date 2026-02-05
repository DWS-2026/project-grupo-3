## 👥 Miembros del Equipo
| Nombre y Apellidos | Correo URJC | Usuario GitHub |
|:--- |:--- |:--- |
| Nerea Blázquez | n.blazquez.2024@alumnos.urjc.es  | Nereablz |
| Inés Sebastián | i.sebastian.2024@alumnos.urjc.es | iness-1810 |
|  Sara García   | s.garcial.2024@alumnos.urjc.es   | SGL23 |
| Camila Montero | ci.montero.2022@alumnos.urjc.es | cmont20 |

---

## 🎭 **Preparación: Definición del Proyecto**

### **Descripción del Tema**
Vamos a crear una aplicación sobre plantas y sus cuidados de manera que cualquier usuario pueda acceder a esta información, además los usuarios registrados podrán añadir nuevas plantas, editar su publicación y poder ver sus pedidos realizados. En esta aplicación habrá una tienda en la que podrán comprar productos para las plantas y un carrito de compra.
### **Entidades**
Indicar las entidades principales que gestionará la aplicación y las relaciones entre ellas:

1. **Entidad 1**: Usuario
2. **Entidad 2**: Producto
3. **Entidad 3**: Pedido (carrito de la compra)
4. **Entidad 4**: Plantas

**Relaciones entre entidades:**
- Usuario - Pedido: Un usuario puede realizar múltiples pedidos y cada pedido pertenece a un único usuario (1:N)
- Pedido - Producto: Un pedido puede contener varios productos y un producto puede aparecer en muchos pedidos distintos (N:M)
- Usuario - Planta: Un usuario puede registrar múltiples plantas y una planta pertenece a un usuario (N:1)
- Planta - Producto: Una planta puede necesitar o usar varios productos y un mismo producto puede ser útil para distintas plantas (N:M)

### **Permisos de los Usuarios**
Describir los permisos de cada tipo de usuario e indicar de qué entidades es dueño:

* **Usuario Anónimo**: 
  - Permisos:
      - Visualizar el catálogo de plantas disponible para un usuario anónimo
      - Visualizar el catálogo de productos
      - Realizar pedidos de productos
      - Registrarse
      - Loguearse
  - Es dueño de su pedido

* **Usuario Registrado**: 
  - Permisos:
      - Gestionar su perfil
      - Ver y gestionar sus propias plantas
      - Realizar pedidos de productos
      - Ver el historial de sus pedidos
  - Es dueño de:
      - Su perfil de usuario
      - Sus plantas
      - Sus propios pedidos

* **Administrador**: 
  - Permisos:
      - Gestión completa del catálogo de productos
      - Gestión de usuarios
      - Visualización y gestión de todos los pedidos
  - Es dueño de:
      - Los productos
      - Todos los pedidos
      - Todos los usuarios

### **Imágenes**
Indicar qué entidades tendrán asociadas una o varias imágenes:

- **[Entidad con imágenes 1]**: Usuario - Una imagen de avatar por usuario
- **[Entidad con imágenes 2]**: Planta - Una imagen o varias imágenes por planta
- **[Entidad con imágenes 3]**: Producto - Una imagen por producto

---

## 🛠 **Práctica 1: Maquetación de páginas con HTML y CSS**

### **Vídeo de Demostración**
📹 **[Enlace al vídeo en YouTube](https://www.youtube.com/watch?v=x91MPoITQ3I)**
> Vídeo mostrando las principales funcionalidades de la aplicación web.

### **Diagrama de Navegación**
Diagrama que muestra cómo se navega entre las diferentes páginas de la aplicación:

![Diagrama de Navegación](images/navigation-diagram.jpg)

> Hay páginas que accedes dependiendo del nivel de autorización que tenga el usuario. Por ejemplo, mis pedidos, solo se accede si eres un usuario registrado, al igual que las subpáginas para editar las plantas y subir una nueva planta, además de la posibilidad de visualizar tu perfil y poder modificarlo. Las pantallas del carrito, de pago, y el muestrario de las plantas, son accesibles por todos los usuarios excepto el administrador (admin). El admin es el único que puede acceder al panel del admin y como consecuencia a la gestión de pedidos, usuarios y productos, teniendo esta última una opción para agregar nuevos productos y otra para editarlos. Las únicas pantallas que puede navegar todos los usuarios son la de registro (o cambio de usuario) y la de productos.

### **Capturas de Pantalla y Descripción de Páginas**

#### **1. Página Principal / Home**
![Página Principal](images/pantallas/comunes/home-page.png)

> Página de inicio que muestra el nombre de la aplicación Green Care y ofrece accesos directos al catálogo de plantas, al catálogo de productos y a las opciones de inicio de sesión y registro. Incluye una barra de navegación únicamente con la opción de ver su pedido (por ser un usuario sin registrarse) y un pie de página, permitiendo al usuario navegar entre las principales secciones de la aplicación

#### **2. Página de inicio de sesión**
![Página de inicio de sesión](images/pantallas/comunes/sign-in.png)

> Página que permite al usuario introducir su correo electrónico y contraseña para iniciar sesión o registrarse en la aplicación. Incluye un formulario con campos de email, contraseña y una opción de recordarme. Tras enviar los datos, el usuario es redirigido a la página principal con su sesión iniciada. La pantalla incluye barra de navegación y pie de página para acceder al resto de secciones de la aplicación.

#### **3. Catálogo de plantas - Usuario No Registrado**
![Página del catálogo de plantas](images/pantallas/usuarioNoRegistrado/catalogoPlantas.png)

> Página que muestra un catálogo de plantas de ejemplo, donde se presentan distintas plantitas con su imagen, nombre, descripción, frecuencia de riego y valoración mediante estrellas. En este modo, el usuario no autenticado solo puede visualizar las plantas disponibles, sin opciones de edición, creación, eliminación ni subida de imágenes. La página incluye barra de navegación y pie de página para acceder al resto de secciones de la aplicación

#### **4. Catálogo de productos**
![Página del catálogo de productos](images/pantallas/comunes/catalogoProductos.png)

> Página que muestra un catálogo de productos para el cuidado de las plantas, donde cada producto incluye su imagen, nombre, descripción y precio. Desde esta pantalla, tanto los usuarios registrados como los no registrados pueden añadir productos al carrito de compra mediante el botón correspondiente. La página incluye barra de navegación y pie de página, permitiendo acceder al resto de secciones de la aplicación.

#### **5. Carrito de la compra**
![Página del carrito de la compra](images/pantallas/comunes/carritoCompra.png)

> Página que muestra los productos añadidos al carrito, indicando para cada uno su nombre, precio y cantidad. Desde esta pantalla, el usuario puede incrementar o disminuir unidades, eliminar productos y visualizar el precio total de la compra. Incluye un botón para proceder al pago, esta página es accesible tanto para usuarios autenticados como no autenticados y presenta barra de navegación y pie de página para acceder al resto de la aplicación.

#### **6. Pago**
![Página del pago](images/pantallas/comunes/pago.png)

> Página que permite al usuario revisar los productos del carrito junto con su cantidad y precio total, y completar un formulario de facturación con sus datos personales y de envío. Incluye la selección del método de pago y los campos necesarios para realizar el pago. Desde esta pantalla, el usuario puede volver al carrito o finalizar la compra, tanto si está registrado como si no lo está.

#### **7. Página de inicio para usuario registrado**
![Página de inicio para usuario registrado](images/pantallas/usuarioRegistrado/home-page.png)

> Página de inicio para usuarios autenticados. Muestra el nombre de la aplicación y botones de acceso directo al catálogo de plantas y al catálogo de productos. Las opciones de inicio de sesión y registro se ocultan, mientras que los accesos a funciones de usuario como “Mis pedidos” y perfil están visibles en la barra de navegación. Incluye barra de navegación y pie de página con elementos adaptados según el estado de login y rol del usuario

#### **8. Catálogo de plantas - Usuario Registrado**
![Página del catálogo de plantas para usuario registrado](images/pantallas/usuarioRegistrado/catalogoPlantas.png)

> Página que muestra el jardín personal del usuario, donde se listan sus plantas registradas en forma de tarjetas con imágenes, nombre, descripción, frecuencia de riego y valoración. Desde cada tarjeta, el usuario puede marcar plantas como favoritas, eliminarlas, subir nuevas fotos o editar su información. Además, se incluye una tarjeta para añadir nuevas plantas al catálogo personal. La pantalla dispone de barra de navegación y pie de página para acceder al resto de funcionalidades de la aplicación.

#### **9. Editar planta**
![Página de edición para una planta](images/pantallas/usuarioRegistrado/editarPlanta.png)

> Esta página permite al usuario actualizar sus datos asociados a sus plantas. Incluye la posibilidad de cambiar la foto de la planta, modificar el nombre de la planta, añadir información adicional relevante (como cuidados, días de riego, tipo de abono) y actualizar la descripción. Los cambios se pueden guardar con el botón Guardar cambios o cancelar la edición para volver al catálogo de plantas. Mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **10. Pedidos del usuario**
![Página del pedido del usuario](images/pantallas/usuarioRegistrado/mispedidos.png)

> Pantalla que permite al usuario consultar el historial de sus compras. Muestra una tabla con el número de pedido, fecha, productos comprados, total y estado del pedido (procesando, entregado, etc.). Incluye un botón para volver al perfil del usuario y mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **11. Perfil del usuario**
![Página del perfil del usuario](images/pantallas/usuarioRegistrado/perfil.png)

> Pantalla que muestra la información personal del usuario, incluyendo nombre, foto de perfil y descripción. Permite editar el perfil, añadir nuevas plantas al catálogo personal y acceder a la gestión de pedidos. Mantiene la barra de navegación y pie de página con las opciones visibles según el estado de login.

#### **12. Editar perfil del usuario**
![Página de editar perfil del usuario](images/pantallas/usuarioRegistrado/editarPerfil.png)

> Pantalla que permite al usuario actualizar su información personal, incluyendo nombre, foto de perfil y descripción. Dispone de campos editables y botones para guardar los cambios o cancelar y volver al perfil. Mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **13. Configurar usuario**
![Página de editar perfil del usuario](images/pantallas/usuarioRegistrado/configuracion.png)

> Pantalla que permite al usuario cambiar su contraseña y su email. Dispone de campos editables y botones para guardar los cambios o cancelar y volver al perfil. Mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **14. Registrar nueva planta**
![Página de registro de una nueva planta](images/pantallas/usuarioRegistrado/nuevaPlanta.png)

> Pantalla que permite al usuario registrar una nueva planta en su jardín personal. Incluye campos para nombre, cuidados específicos, descripción y subida de imagen de la planta. Dispone de botones para guardar la nueva planta o cancelar y volver al catálogo. Mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **15. Página de inicio para administrador**
![Página de inicio para administrador](images/pantallas/admin/home-page.png)

> Página de inicio para usuarios con permisos de administrador. Muestra las opciones principales del sistema, pero oculta elementos que no son necesarios para el admin, como carrito, catálogo de plantas y perfil de usuario. Incluye la barra de navegación y pie de página con las opciones visibles según el estado de login y privilegios de administrador por eso se agrega la opción de admin panel.

#### **16. Catálogo de productos para administrador**
![Página de catálogo de productos para administrador](images/pantallas/admin/catalogoProductos.png)

> Página que muestra todos los productos disponibles con su imagen, nombre, descripción y precio. Para administradores, se ocultan los botones de “Añadir al carrito”, dejando visible únicamente la información de los productos. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **17. Panel de Administrador**
![Página del panel de administrador](images/pantallas/admin/panelAdmin.png)

> Pantalla principal del administrador donde se accede a las distintas áreas de gestión: usuarios, productos y pedidos. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **18. Gestión de usuarios**
![Página de la gestión de usuarios](images/pantallas/admin/gestiosUsuarios.png)

> Pantalla que permite al administrador visualizar y gestionar todos los usuarios registrados en el sistema. Incluye una tabla con la información básica de cada usuario como el nombre, ID y link al perfil y opciones para seleccionar y eliminar usuarios. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **19. Gestión de productos**
![Página de la gestión de productos](images/pantallas/admin/gestionProductos.png)

> Pantalla que permite al administrador visualizar, añadir, editar o eliminar productos del catálogo de la tienda. Incluye una tabla con información básica de cada producto (nombre, precio e imagen) y checkboxes para selección múltiple. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **20. Registrar nuevo producto**
![Página del registro de un nuevo producto](images/pantallas/admin/nuevoProducto.png)

> Página que contiene un formulario que permite al administrador registrar un nuevo producto en la tienda. Incluye campos para el nombre, descripción, precio e imagen del producto. Dispone de botones para guardar los cambios o cancelar y volver al catálogo de productos. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **21. Editar producto**
![Página de edición de un producto](images/pantallas/admin/editarProducto.png)

> Página que contiene un formulario que permite al administrador modificar los datos de un producto existente en la tienda. Se pueden actualizar el nombre, el precio y, de manera opcional, la imagen del producto. Incluye botones para guardar los cambios o cancelar y volver a la pantalla de gestión de productos. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **22. Gestión de pedidos**
![Página de edición de un producto](images/pantallas/admin/gestionPedidos.png)

> Página del administrador donde se visualizan todos los pedidos realizados por los usuarios. Muestra el nombre del usuario, los productos pedidos (con cantidad) y permite actualizar el estado del pedido mediante un selector desplegable (En proceso, Enviado, Entregado). Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

### **Participación de Miembros en la Práctica 1**

#### **Alumno 1 - Inés Sebastián Santamaría**

Al principio me encargaba de la página de inicio sesión junto con toda la lógica de detrás. Según fue evolucionando el proyecto y con ello las necesidades del mismo, se fueron asignando más responsabilidades, como el catálogo para observar las plantas del usuario o la página para poder gestionar a los usuarios. También cree el código de detrás del header y el footer global, de manera que se imprimeran con solo poner el id. Por último también me encargé del header en si, los estilos, los botones ... Además del código necesario para ir ocultando los paneles en la navegación según que usuario se registraba y la página donde estaba. 

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Este fue el último retoque a la página de inicio sesión, en este commit tuve que añadir padding para separar el texto del header que estaba chocando](https://github.com/DWS-2026/project-grupo-3/commit/a93020a60b6a75cd74271b369600781044bd1839)  | [Sign-in](https://github.com/DWS-2026/project-grupo-3/blob/main/html/sign-in.html)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Aquí terminé con el catálogo de plantas, añadí un sistema para puntuar las plantas y unifiqué estilos y clases](https://github.com/DWS-2026/project-grupo-3/commit/db93e8647590dab7c3bf32db9800379a25d6f1ea)  | [Catálogo_de_plantas](https://github.com/DWS-2026/project-grupo-3/blob/main/html/catalogoPlantas.html)  |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 2 - [Sara García Lopo]**

[Me he encargado de la página de perfil del usuario y la sección de edición del perfil, encargándome de la maquetación, los estilos visuales y la organización del contenido. Además, he aplicado principios de diseño responsive para asegurar una correcta visualización en distintos dispositivos, como estilos para mejorar la usabilidad y la experiencia del usuario.]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Añadir las funcionalidades de editar y publicar](https://github.com/DWS-2026/project-grupo-3/commit/c62529b165b5b33cd927112e6a1821cae8e3f2cb#diff-283e184f66f273bc8dbb436c152027dbcc3b0842891ff43fb9d91054c43e2424)  | [Edición Perfil](https://github.com/DWS-2026/project-grupo-3/blob/main/html/editProfile.html)   |
|2| [Añadir funciones a la pagina del perfil para poder ver pedidos y añadir planta](https://github.com/DWS-2026/project-grupo-3/commit/5d54176af6329c7b8a6d5c1501db76f991178370#diff-604ee7395973716cdf9c4cacf305fb0dca839dc52697b7920e631a71a4352b50)  | [Archivo2](https://github.com/DWS-2026/project-grupo-3/blob/main/html/user.html)   |
|3| [Eliminar el botón de Publicar para cambiarlo por configuración](https://github.com/DWS-2026/project-grupo-3/commit/4ad7b789bb3bc6a6e724476bb13abc7a61b9e62e)  | [Archivo3](https://github.com/DWS-2026/project-grupo-3/blob/main/html/user.html)   |
|4| [Cambiar botón para cambiar la foto de perfil](https://github.com/DWS-2026/project-grupo-3/commit/067d5faca35221e4f803ca88667b90ee632e3506)  | [Archivo4](https://github.com/DWS-2026/project-grupo-3/blob/main/html/configuration.html)   |
|5| [Descripción commit 5](https://github.com/DWS-2026/project-grupo-3/commit/14b3284416dd1efeac7b1e0380f0738b361e359c)  | [Archivo5](https://github.com/DWS-2026/project-grupo-3/blob/main/html/configuration.html)   |

---

#### **Alumno 3 - Camila Montero Huerto**

Me he encargado de desarrollar la parte relacionada con la tienda: desde el catálogo de productos hasta el proceso de compra, pasando por el carrito y la página de pago. Además, también implementé la sección de gestión de productos para administrador, la página para añadir una planta y la página donde se puede ver los pedidos del usuario y este pueda gestionarlos. 

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Implementación del catálogo de productos con su respectiva hoja de estilos](https://github.com/DWS-2026/project-grupo-3/commit/da40203b2d72491a667250e82b66ac17ec9cb4f6)  | [catalogoProductos.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/catalogoProductos.html)   |
|2| [Implementación del carrito de la compra con su respectiva hoja de estilos](https://github.com/DWS-2026/project-grupo-3/commit/da40203b2d72491a667250e82b66ac17ec9cb4f6)  | [carroCompra.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/carroCompra.html)   |
|3| [Implementación de la página de pago con su respectiva hoja de estilos](https://github.com/DWS-2026/project-grupo-3/commit/69bd7bce3618cee67dd563b2da63de0b2a25a705)  | [pago.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/pago.html)   |
|4| [Implementación de la página de gestión de productos](https://github.com/DWS-2026/project-grupo-3/commit/3198054889ff51182e990e812d877f9bebcefce3)  | [gestionProductos.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/gestionProductos.html)   |
|5| [Implementación de la página para añadir una planta](https://github.com/DWS-2026/project-grupo-3/commit/c676e9ee2f9e92e582d2e16e75d33385a35cecf2)  | [nuevaPlanta.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/nuevaPlanta.html)   |
|6| [Implementación de la página para que el usuario gestione sus pedidos](https://github.com/DWS-2026/project-grupo-3/commit/6206690e759cb8101ac2d8e2fc2307090f5ae313) | [pedidosUsuario.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/pedidosUsuario.html) |

---

#### **Alumno 4 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

## 🛠 **Práctica 2: Web con HTML generado en servidor**

### **Vídeo de Demostración**
📹 **[Enlace al vídeo en YouTube](https://www.youtube.com/watch?v=x91MPoITQ3I)**
> Vídeo mostrando las principales funcionalidades de la aplicación web.

### **Navegación y Capturas de Pantalla**

#### **Diagrama de Navegación**

Solo si ha cambiado.

#### **Capturas de Pantalla Actualizadas**

Solo si han cambiado.

### **Instrucciones de Ejecución**

#### **Requisitos Previos**
- **Java**: versión 21 o superior
- **Maven**: versión 3.8 o superior
- **MySQL**: versión 8.0 o superior
- **Git**: para clonar el repositorio

#### **Pasos para ejecutar la aplicación**

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/[usuario]/[nombre-repositorio].git
   cd [nombre-repositorio]
   ```

2. **AQUÍ INDICAR LO SIGUIENTES PASOS**

#### **Credenciales de prueba**
- **Usuario Admin**: usuario: `admin`, contraseña: `admin`
- **Usuario Registrado**: usuario: `user`, contraseña: `user`

### **Diagrama de Entidades de Base de Datos**

Diagrama mostrando las entidades, sus campos y relaciones:

![Diagrama Entidad-Relación](images/database-diagram.png)

> [Descripción opcional: Ej: "El diagrama muestra las 4 entidades principales: Usuario, Producto, Pedido y Categoría, con sus respectivos atributos y relaciones 1:N y N:M."]

### **Diagrama de Clases y Templates**

Diagrama de clases de la aplicación con diferenciación por colores o secciones:

![Diagrama de Clases](images/classes-diagram.png)

> [Descripción opcional del diagrama y relaciones principales]

### **Participación de Miembros en la Práctica 2**

#### **Alumno 1 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 2 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 3 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 4 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

## 🛠 **Práctica 3: Incorporación de una API REST a la aplicación web, análisis de vulnerabilidades y contramedidas**

### **Vídeo de Demostración**
📹 **[Enlace al vídeo en YouTube](https://www.youtube.com/watch?v=x91MPoITQ3I)**
> Vídeo mostrando las principales funcionalidades de la aplicación web.

### **Documentación de la API REST**

#### **Especificación OpenAPI**
📄 **[Especificación OpenAPI (YAML)](/api-docs/api-docs.yaml)**

#### **Documentación HTML**
📖 **[Documentación API REST (HTML)](https://raw.githack.com/[usuario]/[repositorio]/main/api-docs/api-docs.html)**

> La documentación de la API REST se encuentra en la carpeta `/api-docs` del repositorio. Se ha generado automáticamente con SpringDoc a partir de las anotaciones en el código Java.

### **Diagrama de Clases y Templates Actualizado**

Diagrama actualizado incluyendo los @RestController y su relación con los @Service compartidos:

![Diagrama de Clases Actualizado](images/complete-classes-diagram.png)

#### **Credenciales de Usuarios de Ejemplo**

| Rol | Usuario | Contraseña |
|:---|:---|:---|
| Administrador | admin | admin123 |
| Usuario Registrado | user1 | user123 |
| Usuario Registrado | user2 | user123 |

### **Participación de Miembros en la Práctica 3**

#### **Alumno 1 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 2 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 3 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 4 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |
