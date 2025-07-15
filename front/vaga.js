document.addEventListener('DOMContentLoaded', async () => {
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
    if (!id) return;

    const token = localStorage.getItem("jwt");
    const role = localStorage.getItem("role")?.toLowerCase();

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
            <p class="text-gray-800 whitespace-pre-line mb-6">${vaga.description}</p>

            ${role === 'admin' ? `
              <button id="btnExcluirVaga"
                  class="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition flex items-center gap-2">
                  <i data-feather="trash-2"></i> Excluir Vaga
              </button>` : ''}
        `;

        feather.replace();

        if (role === 'admin') {
            document.getElementById('btnExcluirVaga').addEventListener('click', async () => {
                if (!confirm("Tem certeza que deseja excluir esta vaga?")) return;

                try {
                    const deleteRes = await fetch(`http://localhost:8080/v1/jobs/${id}`, {
                        method: "DELETE",
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    });

                    if (deleteRes.ok) {
                        alert("Vaga excluída com sucesso.");
                        window.location.href = "home.html";
                    } else {
                        alert("Erro ao excluir vaga. Verifique suas permissões.");
                        console.error(await deleteRes.text());
                    }
                } catch (e) {
                    console.error("Erro ao deletar vaga:", e);
                    alert("Erro de rede ao tentar excluir.");
                }
            });
        }

    } catch (e) {
        document.getElementById('vagaDetalhes').innerHTML = `
            <p class="text-red-600">Erro ao carregar detalhes da vaga.</p>
        `;
        console.error(e);
    }
});
