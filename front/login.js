/* login.js */
document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const email = document.getElementById('email').value.trim();
    const senha = document.getElementById('senha').value;

    const payload = {
        email: email,
        password: senha
    };

    try {
        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            const errText = await response.text();
            throw new Error(errText || 'Falha no login');
        }

        const token = await response.text();

        localStorage.setItem('jwt', token);

        if (email === 'admin@admin.com') {
            localStorage.setItem('role', 'admin');
            window.location.href = 'analytics.html';
        } else {
            localStorage.setItem('role', 'user');
            window.location.href = 'home.html';
        }

    } catch (err) {
        alert('Erro: ' + err.message);
        console.error(err);
    }
});
