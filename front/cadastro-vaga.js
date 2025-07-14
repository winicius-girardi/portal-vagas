document.addEventListener("DOMContentLoaded", () => {
    const token = localStorage.getItem("jwt");

    if (!token) {
        window.location.href = "login.html";
        return;
    }

    const form = document.getElementById("vagaForm");

    form.addEventListener("submit", async function (e) {
        e.preventDefault();

        const vaga = {
            title: document.getElementById("title").value,
            description: document.getElementById("description").value,
            company: document.getElementById("company").value,
            expertiseLevel: document.getElementById("expertiseLevel").value,
            typeOfJob: document.getElementById("typeOfJob").value,
            state: document.getElementById("state").value,
            city: document.getElementById("city").value,
            location: document.getElementById("location").value,
            accept_pcd: document.getElementById("accept_pcd").checked,
            temporary: document.getElementById("temporary").checked
        };

        try {
            const response = await fetch("http://localhost:8080/v1/jobs", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(vaga)
            });

            if (response.ok) {
                alert("Vaga cadastrada com sucesso!");
                form.reset();
                window.location.href="home.html";
            } else {
                let errorText = '';
                try {
                    const error = await response.json();
                    if (Array.isArray(error.detalis)) {
                        errorText = error.detalis.map(e => `${e.ErrorField}: ${e.Message}`).join('\n');
                    } else {
                        errorText = `${error.ErrorField}: ${error.Message}`;
                    }
                } catch (err) {
                    errorText = "Erro desconhecido ao processar resposta do backend.";
                }

                alert("Erro ao cadastrar vaga:\n" + errorText);
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            alert("Erro ao cadastrar vaga.");
        }
    });
});
