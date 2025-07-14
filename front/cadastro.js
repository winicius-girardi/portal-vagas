/* cadastro.js */
document.getElementById('cadastroForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const nome = document.getElementById('nome').value.trim();
    const email = document.getElementById('email').value.trim();
    const senha = document.getElementById('senha').value;
    const confirmaSenha = document.getElementById('confirmaSenha').value;

    if (senha !== confirmaSenha) {
        alert('As senhas não coincidem.');
        return;
    }

    const payload = {
        name: nome,
        email: email,
        password: senha
    };

    try {
        const response = await fetch('http://localhost:8080/v1/user', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert('Cadastro realizado com sucesso!');
            window.location.href = 'login.html';
        } else {
            const error = await response.text();
            alert('Erro ao cadastrar: ' + error);
        }
    } catch (err) {
        console.error(err);
        alert('Erro ao conectar com o servidor.');
    }
});
