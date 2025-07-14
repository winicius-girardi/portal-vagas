async function carregarAnalytics() {
    try {
        const jwt = localStorage.getItem('jwt');

        const res = await fetch('http://localhost:8080/analytics/resume', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwt}`
            }
        });

        if (!res.ok) throw new Error('Erro ao carregar analytics');

        const data = await res.json();

        document.querySelectorAll('div.bg-white p.text-3xl').forEach((el, idx) => {
            switch (idx) {
                case 0:
                    el.textContent = data.vagasTotal;
                    break;
                case 1:
                    el.textContent = data.vagaCLT;
                    break;
                case 2:
                    el.textContent = data.vagaEstagio;
                    break;
                case 3:
                    el.textContent = data.vagaPJ || 0;
                    break;
            }
        });

        const labels = Object.keys(data.vagasPorDia);
        const values = Object.values(data.vagasPorDia);

        const ctx = document.getElementById('vagasChart').getContext('2d');

        new Chart(ctx, {
            type: 'line',
            data: {
                labels,
                datasets: [{
                    label: 'Vagas publicadas por dia',
                    data: values,
                    borderColor: 'rgba(34,197,94,1)',
                    backgroundColor: 'rgba(34,197,94,0.3)',
                    fill: true,
                    tension: 0.3
                }]
            },
            options: {
                scales: {
                    x: {
                        display: true,
                        title: {
                            display: true,
                            text: 'Data'
                        }
                    },
                    y: {
                        display: true,
                        title: {
                            display: true,
                            text: 'Quantidade'
                        },
                        beginAtZero: true
                    }
                }
            }
        });

    } catch (err) {
        console.error(err);
        alert('Erro ao carregar os dados de analytics.');
    }
}

document.addEventListener('DOMContentLoaded', () => {
    carregarAnalytics();
    feather.replace();
});
