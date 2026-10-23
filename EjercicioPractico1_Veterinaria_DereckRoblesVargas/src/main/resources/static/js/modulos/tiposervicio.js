document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('tipoServicioModal');
    if (!modal) {
        return;
    }
    const header = document.getElementById('tipoServicioModalHeader');
    const titulo = document.getElementById('tipoServicioModalTitulo');
    const id = document.getElementById('tipoServicioId');
    const nombre = document.getElementById('tipoServicioNombre');
    const descripcion = document.getElementById('tipoServicioDescripcion');

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
            nombre.value = button.getAttribute('data-nombre') || '';
            descripcion.value = button.getAttribute('data-descripcion') || '';
            header.classList.add('bg-warning');
        } else {
            id.value = '';
            nombre.value = '';
            descripcion.value = '';
            header.classList.add('bg-info', 'text-white');
        }
    });
});
