document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('servicioModal');
    if (!modal) {
        return;
    }
    const header = document.getElementById('servicioModalHeader');
    const titulo = document.getElementById('servicioModalTitulo');
    const id = document.getElementById('servicioId');
    const tipo = document.getElementById('servicioTipo');
    const nombre = document.getElementById('servicioNombre');
    const precio = document.getElementById('servicioPrecio');
    const duracion = document.getElementById('servicioDuracion');

    modal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        if (!button) {
            return;
        }
        const modo = button.getAttribute('data-modo');
        titulo.textContent = button.getAttribute('data-titulo') || '';
        header.classList.remove('bg-info', 'bg-warning', 'text-white');
        if (modo === 'editar') {
            id.value = button.getAttribute('data-id') || '';
            tipo.value = button.getAttribute('data-tipo') || '';
            nombre.value = button.getAttribute('data-nombre') || '';
            precio.value = button.getAttribute('data-precio') || '';
            duracion.value = button.getAttribute('data-duracion') || '';
            header.classList.add('bg-warning');
        } else {
            id.value = '';
            tipo.selectedIndex = 0;
            nombre.value = '';
            precio.value = '';
            duracion.value = '';
            header.classList.add('bg-info', 'text-white');
        }
    });
});
