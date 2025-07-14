document.addEventListener('DOMContentLoaded', async () => {
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
    if (!id) return;

    try {
        const res = await fetch(`http://localhost:8080/v1/jobs/${id}`);
        console.log('Status da resposta:', res.status);
        if (!res.ok) throw new Error('Erro ao carregar vaga');
        const vaga = await res.json();

        const vagaDiv = document.getElementById('vagaDetalhes');
        vagaDiv.innerHTML = `
      <h1 class="text-2xl font-bold mb-4 text-gray-800">${vaga.title}</h1>

      <div class="mb-2 text-gray-700"><strong>Empresa:</strong> ${vaga.company}</div>
      <div class="mb-2 text-gray-700"><strong>Publicada em:</strong> ${vaga.publishDate}</div>
      <div class="mb-2 text-gray-700"><strong>Expira em:</strong> ${vaga.expireDate}</div>
      <div class="mb-2 text-gray-700"><strong>Tipo:</strong> ${vaga.temporary ? 'Temporária' : 'Efetiva'}</div>
      <div class="mb-2 text-gray-700"><strong>Nível de experiência:</strong> ${vaga.expertiseLevel}</div>
      <div class="mb-2 text-gray-700"><strong>Tipo de vaga:</strong> ${vaga.typeOfJob}</div>
      <div class="mb-2 text-gray-700"><strong>Local:</strong> ${vaga.city} - ${vaga.state}</div>
      <div class="mb-2 text-gray-700"><strong>Localização específica:</strong> ${vaga.location}</div>
      <div class="mb-4 text-gray-700"><strong>Aceita PCD:</strong> ${vaga.accept_pcd ? 'Sim' : 'Não'}</div>

      <h2 class="text-lg font-semibold mb-2 text-gray-700">Descrição da vaga</h2>
      <p class="text-gray-800 whitespace-pre-line">${vaga.description}</p>
    `;

        feather.replace();
    } catch (e) {
        document.getElementById('vagaDetalhes').innerHTML = `
      <p class="text-red-600">Erro ao carregar detalhes da vaga.</p>
    `;
        console.error(e);
    }
});