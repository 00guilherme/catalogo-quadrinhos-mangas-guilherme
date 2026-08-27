async function carregarDashboard() {
    try {
        const [obras, generos, editoras] = await Promise.all([
            fetch("/api/obras").then(r => r.json()),
            fetch("/api/generos").then(r => r.json()),
            fetch("/api/editoras").then(r => r.json())
        ]);

        document.getElementById("totalObras").textContent = obras.length;
        document.getElementById("totalGeneros").textContent = generos.length;
        document.getElementById("totalEditoras").textContent = editoras.length;

        const media = obras.length
            ? obras.reduce((soma, obra) => soma + Number(obra.nota), 0) / obras.length
            : 0;

        document.getElementById("mediaNotas").textContent =
            media.toLocaleString("pt-BR", { minimumFractionDigits: 1, maximumFractionDigits: 1 });
    } catch (erro) {
        console.error("Não foi possível carregar o dashboard.", erro);
    }
}

carregarDashboard();
