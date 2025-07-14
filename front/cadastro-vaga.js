// cadastro-vaga.js
document.getElementById("vagaForm").addEventListener("submit", async function (e) {
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
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(vaga)
        });

        if (response.ok) {
            alert("Vaga cadastrada com sucesso!");
            document.getElementById("vagaForm").reset();
        } else {
            alert("Erro ao cadastrar vaga.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
        alert("Erro ao cadastrar vaga.");
    }
});
