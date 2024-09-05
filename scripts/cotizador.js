function calcularPoliza(tipo, ubicacion, valor, cobertura) {
    let base = tipo === 'hogar' ? 500 : 1000;
    let tasaUbicacion = ubicacion === 'urbano' ? 1.2 : 1.1;
    let tasaCobertura = cobertura ? 1.5 : 1.0;

    let costo = base * tasaUbicacion * tasaCobertura * (valor / 10000);

    console.log(`El costo estimado de la póliza es: $${costo.toFixed(2)}`);
    return costo.toFixed(2);
}

function obtenerTipoDeURL() {
    const params = new URLSearchParams(window.location.search);
    return params.get('tipo') || 'hogar'; // Valor predeterminado es 'hogar'
}

// Iniciar el formulario con el tipo correcto
document.addEventListener('DOMContentLoaded', function() {
    const tipo = obtenerTipoDeURL();
    document.getElementById('tipo').value = tipo;

    const form = document.getElementById('cotizador-form');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const ubicacion = form.elements['ubicacion'].value;
        const valor = parseFloat(form.elements['valor'].value);
        const cobertura = form.elements['cobertura'].checked;

        const costo = calcularPoliza(tipo, ubicacion, valor, cobertura);
        document.getElementById('resultado').textContent = `El costo estimado de la póliza es: $${costo}`;
    });
});