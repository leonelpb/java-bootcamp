function abrirCotizador(event) {
    const tipo = event.currentTarget.dataset.tipo;
    window.location.href = `pages/cotizador.html?tipo=${tipo}`;
}
document.querySelectorAll('.card').forEach(card => {
    card.addEventListener('click', abrirCotizador);
});