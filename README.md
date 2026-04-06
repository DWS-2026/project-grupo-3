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
5. **Entidad 5**: Comentarios

**Relaciones entre entidades:**
- Usuario - Pedido: Un usuario puede realizar múltiples pedidos y cada pedido pertenece a un único usuario (1:N)
- Pedido - Producto: Un pedido puede contener varios productos y un producto puede aparecer en muchos pedidos distintos (N:M)
- Usuario - Planta: Un usuario puede registrar múltiples plantas y una planta pertenece a un usuario (N:1)
- Usuario - Comentarios: un usuario puede escribir múltiples comentarios y un comentario pertenece a un único usuario(1:N)

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
      - Ver y gestionar sus propias plantas (cada usuario solo puede ver sus propias plantas)
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
📹 **[Enlace al vídeo en YouTube](https://youtu.be/bpeHyIqIuOU)**
> Vídeo mostrando las principales funcionalidades de la aplicación web.

### **Diagrama de Navegación**
Diagrama que muestra cómo se navega entre las diferentes páginas de la aplicación:

![Diagrama de Navegación](imagesGithub/navigation-diagram.jpg)

> Hay páginas que accedes dependiendo del nivel de autorización que tenga el usuario. Por ejemplo, mis pedidos, solo se accede si eres un usuario registrado, al igual que las subpáginas para editar las plantas y subir una nueva planta, además de la posibilidad de visualizar tu perfil y poder modificarlo. Las pantallas del carrito, de pago, y el muestrario de las plantas, son accesibles por todos los usuarios excepto el administrador (admin). El admin es el único que puede acceder al panel del admin y como consecuencia a la gestión de pedidos, usuarios y productos, teniendo esta última una opción para agregar nuevos productos y otra para editarlos. Las únicas pantallas que puede navegar todos los usuarios son la de registro (o cambio de usuario) y la de productos.

### **Capturas de Pantalla y Descripción de Páginas**

#### **1. Página Principal / Home**
![Página Principal](imagesGithub/screens/common/homePage.png)

> Página de inicio que muestra el nombre de la aplicación Green Care y ofrece accesos directos al catálogo de plantas, al catálogo de productos y a las opciones de inicio de sesión y registro. Incluye una barra de navegación únicamente con la opción de ver su pedido (por ser un usuario sin registrarse) y un pie de página, permitiendo al usuario navegar entre las principales secciones de la aplicación

#### **2. Página de inicio de sesión**
![Página de inicio de sesión](imagesGithub/screens/common/sign-in.png)

> Página que permite al usuario introducir su correo electrónico y contraseña para iniciar sesión o registrarse en la aplicación. Incluye un formulario con campos de email, contraseña y una opción de recordarme. Tras enviar los datos, el usuario es redirigido a la página principal con su sesión iniciada. La pantalla incluye barra de navegación y pie de página para acceder al resto de secciones de la aplicación.

#### **3. Catálogo de plantas - Usuario No Registrado**
![Página del catálogo de plantas](imagesGithub/screens/noRegisteredUser/catalogPlants.png)

> Página que muestra un catálogo de plantas de ejemplo, donde se presentan distintas plantitas con su imagen, nombre, descripción, frecuencia de riego y valoración mediante estrellas. En este modo, el usuario no autenticado solo puede visualizar las plantas disponibles, sin opciones de edición, creación, eliminación ni subida de imágenes. La página incluye barra de navegación y pie de página para acceder al resto de secciones de la aplicación

#### **4. Catálogo de productos**
![Página del catálogo de productos](imagesGithub/screens/common/catalogProducts.png)

> Página que muestra un catálogo de productos para el cuidado de las plantas, donde cada producto incluye su imagen, nombre, descripción y precio. Desde esta pantalla, tanto los usuarios registrados como los no registrados pueden añadir productos al carrito de compra mediante el botón correspondiente. La página incluye barra de navegación y pie de página, permitiendo acceder al resto de secciones de la aplicación.

#### **5. Carrito de la compra**
![Página del carrito de la compra](imagesGithub/screens/common/shoppingCart.png)

> Página que muestra los productos añadidos al carrito, indicando para cada uno su nombre, precio y cantidad. Desde esta pantalla, el usuario puede incrementar o disminuir unidades, eliminar productos y visualizar el precio total de la compra. Incluye un botón para proceder al pago, esta página es accesible tanto para usuarios autenticados como no autenticados y presenta barra de navegación y pie de página para acceder al resto de la aplicación.

#### **6. Pago**
![Página del pago](imagesGithub/screens/common/payment.png)

> Página que permite al usuario revisar los productos del carrito junto con su cantidad y precio total, y completar un formulario de facturación con sus datos personales y de envío. Incluye la selección del método de pago y los campos necesarios para realizar el pago. Desde esta pantalla, el usuario puede volver al carrito o finalizar la compra, tanto si está registrado como si no lo está.

#### **7. Página de inicio para usuario registrado**
![Página de inicio para usuario registrado](imagesGithub/screens/registeredUser/homePage.png)

> Página de inicio para usuarios autenticados. Muestra el nombre de la aplicación y botones de acceso directo al catálogo de plantas y al catálogo de productos. Las opciones de inicio de sesión y registro se ocultan, mientras que los accesos a funciones de usuario como “Mis pedidos” y perfil están visibles en la barra de navegación. Incluye barra de navegación y pie de página con elementos adaptados según el estado de login y rol del usuario

#### **8. Catálogo de plantas - Usuario Registrado**
![Página del catálogo de plantas para usuario registrado](imagesGithub/screens/registeredUser/catalogPlants.png)

> Página que muestra el jardín personal del usuario, donde se listan sus plantas registradas en forma de tarjetas con imágenes, nombre, descripción, frecuencia de riego y valoración. Desde cada tarjeta, el usuario puede marcar plantas como favoritas, eliminarlas, subir nuevas fotos o editar su información. Además, se incluye una tarjeta para añadir nuevas plantas al catálogo personal. La pantalla dispone de barra de navegación y pie de página para acceder al resto de funcionalidades de la aplicación.

#### **9. Editar planta**
![Página de edición para una planta](imagesGithub/screens/registeredUser/editPlant.png)

> Esta página permite al usuario actualizar sus datos asociados a sus plantas. Incluye la posibilidad de cambiar la foto de la planta, modificar el nombre de la planta, añadir información adicional relevante (como cuidados, días de riego, tipo de abono) y actualizar la descripción. Los cambios se pueden guardar con el botón Guardar cambios o cancelar la edición para volver al catálogo de plantas. Mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **10. Pedidos del usuario**
![Página del pedido del usuario](imagesGithub/screens/registeredUser/myOrders.png)

> Pantalla que permite al usuario consultar el historial de sus compras. Muestra una tabla con el número de pedido, fecha, productos comprados, total y estado del pedido (procesando, entregado, etc.). Incluye un botón para volver al perfil del usuario y mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **11. Perfil del usuario**
![Página del perfil del usuario](imagesGithub/screens/registeredUser/profile.png)

> Pantalla que muestra la información personal del usuario, incluyendo nombre, foto de perfil y descripción. Permite editar el perfil, añadir nuevas plantas al catálogo personal y acceder a la gestión de pedidos. Mantiene la barra de navegación y pie de página con las opciones visibles según el estado de login.

#### **12. Editar perfil del usuario**
![Página de editar perfil del usuario](imagesGithub/screens/registeredUser/editProfile.png)

> Pantalla que permite al usuario actualizar su información personal, incluyendo nombre, foto de perfil y descripción. Dispone de campos editables y botones para guardar los cambios o cancelar y volver al perfil. Mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **13. Configurar usuario**
![Página de editar perfil del usuario](imagesGithub/screens/registeredUser/configuration.png)

> Pantalla que permite al usuario cambiar su contraseña y su email. Dispone de campos editables y botones para guardar los cambios o cancelar y volver al perfil. Mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **14. Registrar nueva planta**
![Página de registro de una nueva planta](imagesGithub/screens/registeredUser/newPlant.png)

> Pantalla que permite al usuario registrar una nueva planta en su jardín personal. Incluye campos para nombre, cuidados específicos, descripción y subida de imagen de la planta. Dispone de botones para guardar la nueva planta o cancelar y volver al catálogo. Mantiene la barra de navegación y pie de página con opciones visibles según el estado de login.

#### **15. Página de inicio para administrador**
![Página de inicio para administrador](imagesGithub/screens/admin/homePage.png)

> Página de inicio para usuarios con permisos de administrador. Muestra las opciones principales del sistema, pero oculta elementos que no son necesarios para el admin, como carrito, catálogo de plantas y perfil de usuario. Incluye la barra de navegación y pie de página con las opciones visibles según el estado de login y privilegios de administrador por eso se agrega la opción de admin panel.

#### **16. Catálogo de productos para administrador**
![Página de catálogo de productos para administrador](imagesGithub/screens/admin/productCatalog.png)

> Página que muestra todos los productos disponibles con su imagen, nombre, descripción y precio. Para administradores, se ocultan los botones de “Añadir al carrito”, dejando visible únicamente la información de los productos. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **17. Panel de Administrador**
![Página del panel de administrador](imagesGithub/screens/admin/adminPanel.png)

> Pantalla principal del administrador donde se accede a las distintas áreas de gestión: usuarios, productos y pedidos. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **18. Gestión de usuarios**
![Página de la gestión de usuarios](imagesGithub/screens/admin/usersManagement.png)

> Pantalla que permite al administrador visualizar y gestionar todos los usuarios registrados en el sistema. Incluye una tabla con la información básica de cada usuario como el nombre, ID y link al perfil y opciones para seleccionar y eliminar usuarios. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **19. Gestión de productos**
![Página de la gestión de productos](imagesGithub/screens/admin/productsManagement.png)

> Pantalla que permite al administrador visualizar, añadir, editar o eliminar productos del catálogo de la tienda. Incluye una tabla con información básica de cada producto (nombre, precio e imagen) y checkboxes para selección múltiple. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **20. Registrar nuevo producto**
![Página del registro de un nuevo producto](imagesGithub/screens/admin/newProduct.png)

> Página que contiene un formulario que permite al administrador registrar un nuevo producto en la tienda. Incluye campos para el nombre, descripción, precio e imagen del producto. Dispone de botones para guardar los cambios o cancelar y volver al catálogo de productos. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **21. Editar producto**
![Página de edición de un producto](imagesGithub/screens/admin/editProduct.png)

> Página que contiene un formulario que permite al administrador modificar los datos de un producto existente en la tienda. Se pueden actualizar el nombre, el precio y, de manera opcional, la imagen del producto. Incluye botones para guardar los cambios o cancelar y volver a la pantalla de gestión de productos. Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

#### **22. Gestión de pedidos**
![Página de edición de un producto](imagesGithub/screens/admin/managementOrders.png)

> Página del administrador donde se visualizan todos los pedidos realizados por los usuarios. Muestra el nombre del usuario, los productos pedidos (con cantidad) y permite actualizar el estado del pedido mediante un selector desplegable (En proceso, Enviado, Entregado). Incluye barra de navegación y pie de página con opciones ajustadas según los permisos de administrador.

### **Participación de Miembros en la Práctica 1**

#### **Alumno 1 - Inés Sebastián Santamaría**

Al principio me encargaba de la página de inicio sesión junto con toda la lógica de detrás. Según fue evolucionando el proyecto y con ello las necesidades del mismo, se fueron asignando más responsabilidades, como el catálogo para observar las plantas del usuario o la página para poder gestionar a los usuarios. También cree el código de detrás del header y el footer global, de manera que se imprimeran con solo poner el id. Por último también me encargé del header en si, los estilos, los botones ... Además del código necesario para ir ocultando los paneles en la navegación según que usuario se registraba y la página donde estaba. 

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Aquí ya conseguí que el header fuera global y se incluyera en otros documentos con solo poner el id header-global](https://github.com/DWS-2026/project-grupo-3/commit/05ecf4aa845443b312b485d6a896608196f2eb68) | [Header_global](https://github.com/DWS-2026/project-grupo-3/blob/main/html/header.html)   |
|2| [En este momento fijé el estilo de la página para iniciar sesión y lo moví a un archivo externo css, quedando más bonito el código y asentando las bases para futuras modificaciones](https://github.com/DWS-2026/project-grupo-3/commit/ce1fca407e3d33c27ffca1ef3e32027bc1b9d70d) | [Sign-in](https://github.com/DWS-2026/project-grupo-3/blob/main/html/sign-in.html)   |
|3| [En este commit se creó el catálogo de plantitas, asentando la base del html y permitiendo posteriormente que editara el html para configurarse según el usuario que visitaba la página](https://github.com/DWS-2026/project-grupo-3/commit/2de46f354d1bf9a72fe7657294c9107965d617cd) | [Catálogo_de_plantas](https://github.com/DWS-2026/project-grupo-3/blob/main/html/catalogoPlantas.html)  |
|4| [Con este commit tenemos una pantalla de gestión de usuarios](https://github.com/DWS-2026/project-grupo-3/commit/0b816628f24c9a4e17b5f475b3aca776a2ca9d2b) | [Gestion_de_usuarios](https://github.com/DWS-2026/project-grupo-3/blob/main/html/gestionUsuarios.html)   |
|5| [También una parte muy importante de mi aportación fue unificar los estilos para que todo quedara uniforme y no se repitiera código innecesario, sobretodo esto se puede ver en el css para los forms](https://github.com/DWS-2026/project-grupo-3/commit/db93e8647590dab7c3bf32db9800379a25d6f1ea)  | [Forms_general](https://github.com/DWS-2026/project-grupo-3/blob/main/assets/css/forms-general.css)  |

---

#### **Alumno 2 - Sara García Lopo**

Me he encargado de la página de perfil del usuario, la sección de edición del perfil y la configuración del perfil, encargándome de la maquetación, los estilos visuales y la organización del contenido. Además, he aplicado principios de diseño responsive para asegurar una correcta visualización en distintos dispositivos, como estilos para mejorar la usabilidad y la experiencia del usuario.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Añadir las funcionalidades de editar y publicar](https://github.com/DWS-2026/project-grupo-3/commit/c62529b165b5b33cd927112e6a1821cae8e3f2cb#diff-283e184f66f273bc8dbb436c152027dbcc3b0842891ff43fb9d91054c43e2424)  | [editProfile.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/editProfile.html)   |
|2| [Añadir funciones a la pagina del perfil para poder ver pedidos y añadir planta](https://github.com/DWS-2026/project-grupo-3/commit/5d54176af6329c7b8a6d5c1501db76f991178370#diff-604ee7395973716cdf9c4cacf305fb0dca839dc52697b7920e631a71a4352b50)  | [user.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/user.html)   |
|3| [Eliminar el botón de Publicar para cambiarlo por configuración](https://github.com/DWS-2026/project-grupo-3/commit/4ad7b789bb3bc6a6e724476bb13abc7a61b9e62e)  | [user.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/user.html)   |
|4| [Cambiar botón para cambiar la foto de perfil](https://github.com/DWS-2026/project-grupo-3/commit/067d5faca35221e4f803ca88667b90ee632e3506)  | [configuration.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/configuration.html)   |
|5| [Añadí una sección para poder cambiar el correo y la contraseña](https://github.com/DWS-2026/project-grupo-3/commit/14b3284416dd1efeac7b1e0380f0738b361e359c)  | [configuration.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/configuration.html)   |

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

#### **Alumno 4 - Nerea Blázquez Zayas**

He desarrollado la página principal de la aplicación, encargándome de su maquetación y estilos para definir la primera vista del usuario. También ayude a implementar el footer global, asegurando su correcta integración y reutilización en las distintas páginas. Además, participé en el desarrollo del panel de administración, creando el formulario para registrar y editar productos, así como la pantalla de gestión de pedidos, restringiendo su acceso exclusivamente a usuarios con rol de administrador.

| Nº |                                                                                                   Commits                                                                                                    |                                                 Files                                                 |
|:--:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------:|
| 1  |                     [Implementación final de la página principal y su respectivo CSS](https://github.com/DWS-2026/dws-2026-project-base/commit/4e905e8f29ee5266713b5a57073688044ffe30de)                     |          [index.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/index.html)          |
| 2  | [Implementación del footer anterior al actual, el cual le hemos quitado información, y su respectivo CSS](https://github.com/DWS-2026/dws-2026-project-base/commit/0c3fb0de3e17858ab1048f739a3917109b3827a8) |                                     [footer.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/footer.html)                                      |
| 3  |                  [Implementación para crear un nuevo producto desde el panel del admin](https://github.com/DWS-2026/dws-2026-project-base/commit/06ac44fe82576026ba751a4e032698e03dd7419b)                   |  [nuevoProducto.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/nuevoProducto.html)  |
| 4  |            [Implementación de una pantalla de gestión de los pedidos desde el panel del admin](https://github.com/DWS-2026/dws-2026-project-base/commit/c3d2dc62218fa0c09c54b37e5b904641e3018761)            | [gestionPedidos.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/gestionPedidos.html) |
| 5  |           [Implementación de una pantalla para la edición del producto desde el panel de admin](https://github.com/DWS-2026/dws-2026-project-base/commit/b480c4ff2a57e429c2772d90e20c5632b40d08f3)           |    [eidtProduct.html](https://github.com/DWS-2026/project-grupo-3/blob/main/html/editProduct.html)    |

---

## 🛠 **Práctica 2: Web con HTML generado en servidor**

### **Vídeo de Demostración**
📹 **[Enlace al vídeo en YouTube](https://www.youtube.com/watch?v=vq54yXXmeug)**
> Vídeo mostrando las principales funcionalidades de la aplicación web.

### **Navegación y Capturas de Pantalla**

#### **Diagrama de Navegación**

![Diagrama de Navegación-1](imagesGithub/ScreenDiagram-1.png)
![Diagrama de Navegación-2](imagesGithub/ScreenDiagram-2.png)

> En este nuevo diagrama de navegación se han cambiado de dos pantallas de configuración y edición del usuario a una sola, hemos añadido un quizz de plantas y hemos remodelado toda la parte de las pantallas de las plantas. 

##### **Vistas Comunes**

#### **1. Página Principal / Home**
![Página Principal](imagesGithub/screens/common/homePageNew.png)

> Página de inicio actualizada con un diseño más limpio y moderno. Ofrece accesos directos al catálogo de plantas, productos y opciones de sesión, con una barra de navegación adaptada.

#### **2. Quizz de Plantas**
![Quizz de plantas](imagesGithub/screens/common/quizzPlants.png)

> Funcionalidad interactiva que permite a los usuarios realizar un cuestionario para recibir recomendaciones personalizadas sobre qué plantas se adaptan mejor a sus necesidades.

#### **3. Resultado del Quizz**
![Resultado del Quizz](imagesGithub/screens/common/resultQuizz.png)

> Pantalla que muestra el resultado personalizado del cuestionario, indicando qué planta es la ideal para el usuario basándose en sus respuestas.

#### **4. Catálogo de Productos (Soft Delete)**
![Catálogo de productos no disponible](imagesGithub/screens/common/catalogProductsNoAvailable.png)

> Vista del catálogo donde se puede observar la implementación del "borrado lógico" (soft delete). Los productos marcados como no disponibles muestran una etiqueta de "No disponible".

#### **5. Error de Acceso Denegado (403)**
![Acceso Denegado](imagesGithub/screens/common/accessDenied.png)

> Página de error personalizada que se muestra cuando un usuario intenta acceder a una sección para la que no tiene permisos (ej. un usuario registrado intentando entrar al panel de admin).

#### **6. Error de Servidor (500)**
![Error del Servidor](imagesGithub/screens/common/error.png)

> Página de error genérica para manejar excepciones inesperadas del servidor, manteniendo la estética de la aplicación y proporcionando un camino de vuelta a la página principal.

##### **Vistas de Usuario No Registrado**

#### **7. Página de Registro de Usuario**
![Página de registro](imagesGithub/screens/noRegisteredUser/register.png)

> Formulario para que nuevos usuarios creen una cuenta, solicitando nombre, correo electrónico y contraseña.

#### **8. Catálogo de Plantas (Anónimo)**
![Catálogo de plantas](imagesGithub/screens/noRegisteredUser/catalogPlantsNew.png)

> Permite a los visitantes ver las plantas disponibles en el sistema y sus valoraciones sin necesidad de estar autenticados.

#### **9. Tablón de Reviews (Anónimo)**
![Tablón de Reviews](imagesGithub/screens/noRegisteredUser/reviews.png)

> Muestra las opiniones y valoraciones de otros usuarios. Los visitantes pueden leerlas pero no participar hasta que inicien sesión.

##### **Vistas de Usuario Registrado**

#### **10. Página de Inicio (Registrado)**
![Página de inicio registrado](imagesGithub/screens/registeredUser/homePageNew.png)

> Vista personalizada para el usuario autenticado, con acceso rápido a su colección de plantas, sus pedidos y el catálogo de productos.

#### **11. Catálogo de Plantas (Registrado)**
![Catálogo de plantas registrado](imagesGithub/screens/registeredUser/CatalogPlantsNew.png)

> Espacio donde el usuario puede gestionar su colección, valorar plantas de otros usuarios y añadir nuevas plantas a su perfil. Incluye paginación para facilitar la navegación.

#### **12. Catálogo de Productos (Registrado)**
![Catálogo de productos registrado](imagesGithub/screens/registeredUser/CatalogProductsNew.png)

> Tienda online donde los usuarios pueden explorar productos y añadirlos al carrito. También incluye sistema de paginación.

#### **13. Catálogo de Productos (Soft Delete parra usuario registrado)**
![Catálogo de productos no disponible](imagesGithub/screens/registeredUser/catalogProductsNoAvailable.png)

> Vista del catálogo donde se puede observar la implementación del "borrado lógico" (soft delete). Los productos marcados como no disponibles muestran una etiqueta de "No disponible" y se elimina el botón de "Añadir al carrito".


#### **14. Tablón de Reviews (Registrado)**
![Tablón de Reviews](imagesGithub/screens/registeredUser/reviews.png)

> Muestra las opiniones y valoraciones de otros usuarios, permitiendo a los usuarios registrados no solo leerlas, también participar dejando sus propias valoraciones y comentarios y se pueden ver las valoraciones de cada producto o planta en su detalle.


#### **15. Detalle de Reviews de Producto**
![Detalle de reviews](imagesGithub/screens/registeredUser/showProductReview.png)

> Vista detallada de un producto específico, permitiendo a los usuarios registrados ver el producto del que se hizo una valoración.

#### **16. Carrito de la Compra Actualizado**
![Carrito de la compra](imagesGithub/screens/registeredUser/shoppingCartNew.png)

> Resumen de productos seleccionados, permitiendo modificar cantidades o eliminar artículos antes de proceder al pago.

#### **17. Pedido Realizado con Éxito**
![Pedido Éxito](imagesGithub/screens/registeredUser/orderSuccess.png)

> Pantalla de confirmación tras realizar una compra satisfactoriamente, con enlaces para seguir comprando o ver el historial de pedidos.

#### **18. Mis Pedidos**
![Pedidos del usuario](imagesGithub/screens/registeredUser/myOrdersNew.png)

> Historial detallado de todas las compras realizadas por el usuario, con el estado actual de cada pedido.

#### **19. Perfil de Usuario Actualizado**
![Perfil de usuario](imagesGithub/screens/registeredUser/profileNew.png)

> Vista mejorada del perfil que muestra la información del usuario y su actividad reciente en la plataforma.

#### **20. Configuración de Cuenta**
![Configuración de cuenta](imagesGithub/screens/registeredUser/configurationNew.png)

> Interfaz para actualizar datos sensibles como el correo electrónico o la contraseña de forma segura.

##### **Vistas de Administrador**

#### **21. Panel de Control / Inicio Admin**
![Página de inicio administrador](imagesGithub/screens/admin/homePageNew.png)

> Menú principal para el administrador con acceso a las herramientas de gestión global del sistema.

#### **22. Gestión de Usuarios**
![Gestión de usuarios](imagesGithub/screens/admin/userManagementNew.png)

> Herramienta para que el administrador pueda supervisar, editar o eliminar cuentas de usuario.

#### **23. Gestión de Productos**
![Gestión de productos](imagesGithub/screens/admin/productsManagementNew.png)

> Interfaz administrativa para el mantenimiento del catálogo de productos, incluyendo creación, edición y borrado lógico.

#### **24. Gestión de Pedidos Global**
![Gestión de pedidos](imagesGithub/screens/admin/managementsOrdersNew.png)

> Panel que permite al administrador ver y actualizar el estado de todos los pedidos realizados en la plataforma.

### **Instrucciones de Ejecución**

#### **Requisitos Previos**
- **Java**: versión 21 o superior
- **Maven**: versión 3.8 o superior
- **MySQL**: versión 8.0 o superior
- **Git**: para clonar el repositorio

#### **Pasos para ejecutar la aplicación**

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/DWS-2026/project-grupo-3
   cd project-grupo-3
   ```

2. **Compilar el proyecto**
   ```
   mvn clean install
   ```
3. **Ejecutar la aplicación**
   ```
   mvn spring-boot:run
   ```
4. **Accedeer a la aplicación**

#### **Credenciales de prueba**
- **Usuario Admin**: usuario: `Admin`, contraseña: `admin`
- **Usuario Registrado**: usuario: `Pepe`, contraseña: `pepe`

### **Diagrama de Entidades de Base de Datos**

Diagrama mostrando las entidades, sus campos y relaciones:

![Diagrama Entidad-Relación](imagesGithub/databaseDiagram.png)

> ["El diagrama muestra las 4 entidades principales: Usuario, Producto, Pedido, Plantas y Comentarios, con sus respectivos atributos y relaciones 1:N y N:M, además se pueden observar otras entidades como por ejemplo PlantType que realmente son tablas intermedias para poder comunicar plantas y comentarios o para hacer más fácil su implementación como en el ejmplo de orderItems que posibilita añadir un producto con una cantidad dentro de un pedido."]

### **Diagrama de Clases y Templates**

Diagrama de clases de la aplicación con diferenciación por colores o secciones:

![Diagrama de Clases](imagesGithub/classesDiagram.png)

> El diagrama representa la arquitectura MVC de la aplicación mediante un código de colores: **Púrpura** para las Vistas (Templates), **Verde** para los Controladores, **Rojo** para los Servicios, **Azul** para los Repositorios y **Blanco** para las Entidades de Dominio. 
> 
> Cabe destacar que **CartItem** no es una entidad persistente, sino un objeto de sesión para el manejo temporal del carrito. Por otro lado, **Order** y **OrderItems** son entidades fundamentales: mientras que **Order** guarda la información general del pedido, **OrderItems** actúa como el nexo que detalla los productos y cantidades asociadas a cada compra.

### **Participación de Miembros en la Práctica 2**

#### **Alumno 1 - Inés Sebastián Santamaría**

Para esta segunda entrega mi primera tarea fue conseguir pasar de un modelo estático a uno dinámico, moviendo carpetas , conectando la base de datos y montando la arquitectura necesaria para el proceso. Después me encargé del desarrollo de la entidad planta (con todo lo que ello implicaba, además de modificar los html para ajustarse mejor a nuestro modelo), pero con ello me di cuenta de que hacía falta una entidad imagen y también la desarrollé al completo. Después diversifiqué mi desarrollo en la gestión de roles y el control de accesos, permitiendo y asignando los atributos necearios a cada usuario, además de configurar una página de acceso denegado en caso necesario. Tras esto último, caímos en que necesitábamos una página de error y ayudé a su desarrollo consiguiendo que capturara correctamente los errores y mostrando la información mínima al usuario. 

| Nº    |                                                                                      Commits                                                                                       |                                                                                                                                            Files                                                                                                                                             |
|:------------: |:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|1|                            [Construir la entidad imagen](https://github.com/DWS-2026/project-grupo-3/commit/ac6629c4)                            |                                                                                           [Image](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/model/Image.java)                                                                                            |
|2|                 [Conseguir conectar el proyecto con la base de datos](https://github.com/DWS-2026/project-grupo-3/commit/891ae5a7106975b46c0d23c39060f5ab1d4df902)                 |                                                                                   [Aplication Properties](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/resources/application.properties)                                                                                   |
|3|                           [Construir la entidad plantitas](https://github.com/DWS-2026/project-grupo-3/commit/9898c3c582cc5ad740872e61ac9e438ab2dd82f0)                            |                                                                                       [Plant](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/model/Plant.java)                                                                                        |
|4| [Me encargé de configurar los roles y de controlar los accesos a las url, junto con los botones relacionado con ello](https://github.com/DWS-2026/project-grupo-3/commit/f2dd74d6) | [WebSecurityConfig](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/security/WebSecurityConfig.java) [SessionAttributes](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/modelAttributes/SessionAttributes.java) |
|5|      [Modificar la edición de las plantas para que se ajuste a la base de datos](https://github.com/DWS-2026/project-grupo-3/commit/15d6777d1724646f3bdedf8f09b7128598d2082e)      |                                                                                    [Edit Plant](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/resources/templates/Plants/editPlant.html)                                                                                    |

---

#### **Alumno 2 - Sara García Lopo**

En esta parte de la práctica, me he encargado del desarrollo del módulo de usuario, centrado en la gestión de la interacción entre el usuario y el sistema. Esta parte incluye la lógica necesaria para el registro de usuarios, el inicio de sesión y la gestión de sus credenciales.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Añadí la función para poder eliminar al usuario](https://github.com/DWS-2026/project-grupo-3/commit/cfd4565764a19595aeeab58434b0b6338e3ceebe)  | [User Controller](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/controller/UserController.java)   |
|2| [Función para encontrar a los usuarios](https://github.com/DWS-2026/project-grupo-3/commit/a22cdf9698199d902cc03d19310642f9e880911e)  | [User Service](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/service/UserService.java)   |
|3| [Añadí la función para que no se puedan repetir los correos, usuarios o contraseñas de los distintosn usuarios](https://github.com/DWS-2026/project-grupo-3/commit/e398a613169fb185a9a02ad2232b34a49bc4839a)  | [User Controller](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/controller/UserController.java)   |
|4| [Puse los permisos que van a tener tanto el usuario como el admin](https://github.com/DWS-2026/project-grupo-3/commit/e0cfc22773d86479c18d3d4d2ea62bf114c14266)  | [User COntroller](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/controller/UserController.java)   |
|5| [Modificar el espacio de la foto de perfil para que el usuario pueda poner su propia foto](https://github.com/DWS-2026/project-grupo-3/commit/801f20bfe35b5eb64f3fd06dc1d580ca64a74c36)  | [User Controller](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/controller/UserController.java)   |

---

#### **Alumno 3 - Camila Montero Huerto**

En esta parte de la práctica he implementado la gestión completa de productos (creación, edición y control por parte del administrador), así como el sistema de pedidos mediante las entidades Order y OrderItem, que permiten representar pedidos compuestos por varios productos con sus cantidade. También me encargué del carrito de la compra usando la entidad Cartitem para gestionar los productos seleccionados por el usuario. Además, realicé la transición de páginas estáticas a controladores para dotar de funcionalidad a la aplicación y desarrollé una página de éxito tras completar un pedido correctamente. 

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Cambio de las rutas a los HTMLs por la ruta de los controladores](https://github.com/DWS-2026/project-grupo-3/commit/72a0bc1f2b523813e687d8fefa59b8f141718d35)  | [Editar Producto](https://github.com/DWS-2026/project-grupo-3/blob/72a0bc1f2b523813e687d8fefa59b8f141718d35/src/main/resources/templates/Productos/editProduct.html)   |
|2| [Entidad Producto con controlador, servicio y repositorio y funcionando la parte del administrador](https://github.com/DWS-2026/project-grupo-3/commit/ebedb6898d1a212df8585e5ccca3c6ab6bbb0985)  | [Producto](https://github.com/DWS-2026/project-grupo-3/blob/ebedb6898d1a212df8585e5ccca3c6ab6bbb0985/src/main/java/es/codeurjc/board/model/Product.java)   |
|3| [Entidad Order y OrderItems junto a la lógica del carrito de compra usando la clase CartItem con funcionalidad completa](https://github.com/DWS-2026/project-grupo-3/commit/49281423149bfde54685f3122f8184c9f511da75)  | [Pedido](https://github.com/DWS-2026/project-grupo-3/blob/49281423149bfde54685f3122f8184c9f511da75/src/main/java/es/codeurjc/board/model/Order.java)   |
|4| [Se cambia la eliminación de un producto por parte del Admin por poner a un producto en estado no disponible hasta que el admin lo cambie  ](https://github.com/DWS-2026/project-grupo-3/commit/1f00642a69b1147d6e224198be89ec5fef43577b)  | [Funciones deleteById y reactivateById](https://github.com/DWS-2026/project-grupo-3/blob/1f00642a69b1147d6e224198be89ec5fef43577b/src/main/java/es/codeurjc/board/service/ProductService.java)   |
|5| [Página de éxito después de realizar un pedido correctamente](https://github.com/DWS-2026/project-grupo-3/commit/1f00642a69b1147d6e224198be89ec5fef43577b)  | [exito](https://github.com/DWS-2026/project-grupo-3/blob/1f00642a69b1147d6e224198be89ec5fef43577b/src/main/resources/templates/Orders/orderSuccess.html)   |


---

#### **Alumno 4 - Nerea Blázquez Zayas**

Para esta parte de la prácica me he encargado de realizar todo lo relacionado con las reseñas en la base de datos además de sus respectivos cambios en los html. A parte de estos cambios también me he encargado de realizar los cambios en la seguridad implimentando el CSRF y el HTTPS para la pagina web. Por otro lado he realizado el html de la pagina de registro, la cual nos faltaba en la practica anterior y me he encargado de pasar todos los comentarios a inglés.

| Nº    |                                                                                   Commits                                                                                   |                                                                          Files                                                                           |
|:------------: |:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------:|
|1|                     [Construui la entidad de las reseñas](https://github.com/DWS-2026/project-grupo-3/commit/af61f7ade87ef824acdccaadfb2548ddba1538b6)                      |                    [Reviews](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/model/Review.java)                    |
|2|                           [Implemente el CSRF](https://github.com/DWS-2026/dws-2026-project-base/commit/3dd5003fd547f194ec66786ea85c2cd56fc4a717)                           | [CSRFHandlerConfiguration](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/java/es/codeurjc/board/security/CSRFHandlerConfiguration.java) |
|3|                          [Implemente el HTTPS](https://github.com/DWS-2026/dws-2026-project-base/commit/7807f070b92ff7f8f4bd022e5e855473ab09a10f)                           |                          [keystore.jks](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/resources/keystore.jks)                           |
|4| [Edite el foro con las reseñas para que se adaptasen a la base de datos](https://github.com/DWS-2026/dws-2026-project-base/commit/f9d1acb8e2fb563a57f629002dd587115799e379) |                      [forum](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/resources/templates/Reviews/forum.html)                      |
|5|                                                                 [Implemente la pagina de registro](https://github.com/DWS-2026/dws-2026-project-base/commit/a2176cd616cd61a4ca337c4d5d4166df41f1632f)                                                                  |                                                                [register ](https://github.com/DWS-2026/project-grupo-3/blob/main/src/main/resources/templates/register.html)                                                                |

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
