// Atualiza os indicadores da página inicial com dados consolidados da API.
async function carregarDashboard() {
    try {
        // As três consultas são independentes e podem ocorrer em paralelo.
        const [obras, generos, editoras] = await Promise.all([
            fetch("/api/obras").then(r => r.json()),
            fetch("/api/generos").then(r => r.json()),
            fetch("/api/editoras").then(r => r.json())
        ]);

        document.getElementById("totalObras").textContent = obras.length;
        document.getElementById("totalGeneros").textContent = generos.length;
        document.getElementById("totalEditoras").textContent = editoras.length;

        // Evita divisão por zero quando ainda não há obras cadastradas.
        const media = obras.length
            ? obras.reduce((soma, obra) => soma + Number(obra.nota), 0) / obras.length
            : 0;

        // Formata a média conforme a convenção numérica brasileira.
        document.getElementById("mediaNotas").textContent =
            media.toLocaleString("pt-BR", { minimumFractionDigits: 1, maximumFractionDigits: 1 });
    } catch (erro) {
        // Falhas do dashboard são registradas sem quebrar a página inteira.
        console.error("Não foi possível carregar o dashboard.", erro);
    }
}

carregarDashboard();
