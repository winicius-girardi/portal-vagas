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
            let errorMsg = "Erro ao fazer login.";
            const contentType = response.headers.get("Content-Type");
            if (contentType && contentType.includes("application/json")) {
                const err = await response.json();
                errorMsg = `${err.ErrorField}: ${err.Message}`;
            } else {
                const text = await response.text();
                errorMsg = text;
            }
            throw new Error(errorMsg);

        }

        const result = await response.json();
        const token = result.token;

        localStorage.setItem('jwt', token);
        localStorage.setItem('email', email);

        const roleResponse = await fetch("http://localhost:8080/v1/user/role", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`
            },
            body: JSON.stringify({ email: email })
        });

        if (!roleResponse.ok) {
            throw new Error("Erro ao buscar role do usuário");
        }

        const roleResult = await roleResponse.json();
        const role = roleResult.role;

        localStorage.setItem("role", role);

        window.location.href = 'home.html';

    } catch (err) {
        alert('Erro: ' + err.message);
        console.error(err);
    }
});
