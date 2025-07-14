document.addEventListener('DOMContentLoaded', () => {
    feather.replace();

    const role = localStorage.getItem('role');
    if (role === 'admin') {
        document.getElementById('adminBtn').classList.remove('hidden');
    }

    const inputBusca = document.getElementById('inputBusca');
    const chkEstagio = document.getElementById('filtro-estagio');
    const chkClt = document.getElementById('filtro-clt');
    const radioRecente = document.getElementById('filtro-recente');
    const vagasContainer = document.getElementById('vagasContainer');
    const btnAnterior = document.getElementById('btnAnterior');
    const btnProxima = document.getElementById('btnProxima');
    const paginaAtualSpan = document.getElementById('paginaAtual');

    let paginaAtual = 0;
    const tamanhoPagina = 6; // ajuste conforme desejar
    let totalPaginas = 0;

    async function buscarVagas() {
        const searchField = inputBusca.value.trim();
        const mostRecent = radioRecente.checked;

        const body = {
            mostRecent: mostRecent,
            searchField: searchField.length > 0 ? searchField : '',
            page: paginaAtual,
            size: tamanhoPagina
        };

        try {
            const response = await fetch('http://localhost:8080/v1/jobs/search', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(body)
            });

            if (!response.ok) throw new Error('Erro ao buscar vagas');

            const data = await response.json();
            totalPaginas = data.totalPages;
            renderizarVagas(data.content);

            atualizarPaginacao();
        } catch (error) {
            console.error('Erro:', error);
        }
    }

    function renderizarVagas(vagas) {
        vagasContainer.innerHTML = '';
        vagas.forEach(vaga => {
            const link = document.createElement('a');
            link.href = 'vaga?id=' + vaga.id;  // <-- garante a extensão
            link.className = 'block bg-white p-5 rounded-lg shadow hover:shadow-md transition transform hover:scale-[1.01] mb-4';
            link.style.textDecoration = 'none';
            link.style.color = 'inherit';

            link.innerHTML = `
            <h2 class="text-lg font-bold text-gray-800 flex items-center gap-2">
                <i data-feather="briefcase"></i> ${vaga.title}
            </h2>
            <p class="text-sm text-gray-500">Empresa: ${vaga.company}</p>
            <p class="text-sm text-gray-500">Publicada em: ${vaga.publishDate}</p>
            <p class="text-sm text-gray-500">Cidade: ${vaga.city} - ${vaga.state}</p>
            <p class="text-sm text-gray-500">Tipo: ${vaga.temporary ? 'Temporária' : 'Efetiva'}</p>
        `;

            vagasContainer.appendChild(link);
        });

        feather.replace();
    }




    function atualizarPaginacao() {
        paginaAtualSpan.textContent = `Página ${paginaAtual + 1}`;
        btnAnterior.disabled = paginaAtual === 0;
        btnProxima.disabled = paginaAtual >= totalPaginas - 1;
    }

    btnAnterior.addEventListener('click', () => {
        if (paginaAtual > 0) {
            paginaAtual--;
            buscarVagas();
        }
    });

    btnProxima.addEventListener('click', () => {
        if (paginaAtual < totalPaginas - 1) {
            paginaAtual++;
            buscarVagas();
        }
    });

    document.querySelectorAll('input').forEach(input => {
        input.addEventListener('change', () => {
            paginaAtual = 0;
            buscarVagas();
        });
    });

    inputBusca.addEventListener('input', debounce(() => {
        paginaAtual = 0;
        buscarVagas();
    }, 500));

    function debounce(func, delay) {
        let timer;
        return function (...args) {
            clearTimeout(timer);
            timer = setTimeout(() => func.apply(this, args), delay);
        };
    }

    document.addEventListener("DOMContentLoaded", async () => {
        feather.replace();

        const token = localStorage.getItem("jwt");
        const role = localStorage.getItem("role");
        const email = localStorage.getItem("email");

        if (token && email) {
            try {
                const res = await fetch("http://localhost:8080/v1/user/email", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${token}`
                    },
                    body: JSON.stringify({ email })
                });

                if (res.ok) {
                    const data = await res.json();
                    const username = data.name;

                    document.getElementById("usernameDisplay").textContent = `👤 ${username}`;
                    document.getElementById("usernameDisplay").classList.remove("hidden");

                    document.getElementById("loginLink").classList.add("hidden");
                    document.getElementById("cadastroLink").classList.add("hidden");

                    const cta = document.querySelector(".mt-10.text-center");
                    if (cta) cta.classList.add("hidden");

                    if (role === "admin") {
                        document.getElementById("adminBtn").classList.remove("hidden");
                    }
                }
            } catch (err) {
                console.error("Erro ao obter nome do usuário:", err);
            }
        }

    });
    async function inicializarUsuario() {
        const token = localStorage.getItem("jwt");
        const role = localStorage.getItem("role");
        const email = localStorage.getItem("email");

        if (token && email) {
            try {
                const res = await fetch("http://localhost:8080/v1/user/email", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${token}`
                    },
                    body: JSON.stringify({ email })
                });

                if (res.ok) {
                    const data = await res.json();
                    const username = data.name;

                    const usernameDisplay = document.getElementById("usernameDisplay");
                    usernameDisplay.textContent = `👤 ${username}`;
                    usernameDisplay.classList.remove("hidden");

                    document.getElementById("loginLink").classList.add("hidden");
                    document.getElementById("cadastroLink").classList.add("hidden");

                    if (role === "admin") {
                        document.getElementById("adminBtn").classList.remove("hidden");
                    }

                    const logoutBtn = document.getElementById("logoutBtn");
                    logoutBtn.classList.remove("hidden");
                    logoutBtn.addEventListener("click", () => {
                        localStorage.clear();
                        window.location.href = "home.html";
                    });

                }
            } catch (err) {
                console.error("Erro ao obter nome do usuário:", err);
            }
        }
    }



    buscarVagas();

    const btnCadastrarVagaPublico = document.getElementById("btnCadastrarVagaPublico");
    if (btnCadastrarVagaPublico) {
        btnCadastrarVagaPublico.addEventListener("click", () => {
            const token = localStorage.getItem("jwt");
            if (token) {
                window.location.href = "cadastro-vaga.html";
            } else {
                window.location.href = "login.html";
            }
        });
    }

    inicializarUsuario();

});
