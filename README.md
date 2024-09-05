# Proyecto de Cotizador de Seguros

Este proyecto es una aplicación web simple que permite a los usuarios cotizar seguros para el hogar y el comercio. Además, incluye una integración con la API de [Random User](https://randomuser.me/) para generar un perfil de usuario aleatorio en una tarjeta. La aplicación consta de una página principal con cards que redirigen a un cotizador específico, y una página de cotización donde se puede calcular el costo del seguro.

## Funcionalidades

### 1. Página Principal con Cards Interactivas

En la página principal, se presentan dos secciones: **Seguros del Hogar** y **Seguros del Comercio**. Cada sección contiene tres cards clickeables que representan diferentes tipos de seguros. Cuando un usuario hace clic en una de estas cards, es redirigido a la página del cotizador con el tipo de seguro correspondiente preseleccionado.

- **Redirección**: Al hacer clic en una card, se redirige al usuario a `cotizador.html`. Dependiendo de la card seleccionada, el cotizador muestra automáticamente el tipo de seguro (hogar o comercio) relacionado con la sección desde donde se hizo clic.

### 2. Cotizador de Seguros

La página `cotizador.html` permite a los usuarios ingresar datos específicos como tipo de seguro, ubicación, valor de la propiedad y si desean cobertura adicional. Luego, el cotizador calcula el costo estimado de la póliza y lo muestra en la pantalla.

#### Cómo funciona el cálculo:

- **Tipo de Seguro**: Puede ser 'hogar' o 'comercio'. Esto afecta la base del costo (500 para hogar y 1000 para comercio).
- **Ubicación**: Puede ser 'urbano' o 'rural', lo que influye en una tasa que ajusta el costo.
- **Valor de la Propiedad**: El valor del inmueble o comercio para el cual se quiere obtener la póliza.
- **Cobertura Adicional**: Una opción que, si se selecciona, aumenta el costo de la póliza.

```javascript
function calcularPoliza(tipo, ubicacion, valor, cobertura) {
    let base = tipo === 'hogar' ? 500 : 1000;
    let tasaUbicacion = ubicacion === 'urbano' ? 1.2 : 1.1;
    let tasaCobertura = cobertura ? 1.5 : 1.0;

    let costo = base * tasaUbicacion * tasaCobertura * (valor / 10000);
    
    console.log(`El costo estimado de la póliza es: $${costo.toFixed(2)}`);
    return costo.toFixed(2);
}
```
### 3. Página o Pestaña API

La página o pestaña "API" está diseñada para mostrar un perfil de usuario aleatorio utilizando la API de [Random User](https://randomuser.me/). Al cargar la página, se muestra un usuario aleatorio, incluyendo su nombre, foto, correo electrónico y ubicación.

#### Botón Generador de Usuario

En la página "API", hay un botón que permite generar un nuevo usuario aleatorio cada vez que se hace clic en él. Este botón está ubicado en el centro de la pantalla, con un diseño sticky, manteniéndose siempre visible mientras el usuario navega.

```javascript
function fetchRandomUser() {
    fetch('https://randomuser.me/api/')
        .then(response => response.json())
        .then(data => {
            const user = data.results[0];

            // Actualiza la tarjeta con la información del usuario
            document.getElementById('user-name').textContent = `${user.name.first} ${user.name.last}`;
            document.getElementById('user-photo').src = user.picture.large;
            document.getElementById('user-email').textContent = `Email: ${user.email}`;
            document.getElementById('user-location').textContent = `Location: ${user.location.city}, ${user.location.country}`;
        })
        .catch(error => {
            console.error('Error fetching the user data:', error);
        });
}

document.addEventListener('DOMContentLoaded', function() {
    // Fetch user on page load
    fetchRandomUser();

    // Add event listener to the button
    document.getElementById('new-user-button').addEventListener('click', function() {
        fetchRandomUser();
    });
});
```
