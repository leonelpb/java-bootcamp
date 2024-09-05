export function manejarParametros() {
    const urlParams = new URLSearchParams(window.location.search);
    const tipo = urlParams.get('tipo');

    if (tipo) {
        document.querySelector(`input[name="tipo"][value="${tipo}"]`).checked = true;
    }
}

document.addEventListener('DOMContentLoaded', manejarParametros);