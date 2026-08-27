// =========================================================
// CONFIGURAÇÃO DA API
// =========================================================

const API = "/api";

let obras = [];
let generos = [];
let editoras = [];


// =========================================================
// FUNÇÃO AUXILIAR PARA PEGAR ELEMENTOS HTML
// =========================================================

const $ = id => document.getElementById(id);


// =========================================================
// FUNÇÃO PADRÃO PARA REQUISIÇÕES
// =========================================================

async function request(url, options = {}) {

    const response = await fetch(url, {

        headers: {
            "Content-Type": "application/json",
            ...(options.headers || {})
        },

        ...options
    });


    // Verifica se o servidor retornou erro

    if (!response.ok) {

        let mensagem = "Ocorreu um erro.";

        try {

            const data = await response.json();

            mensagem = data.erro || mensagem;

        } catch { }

        throw new Error(mensagem);
    }


    // DELETE normalmente retorna 204

    if (response.status === 204) {
        return null;
    }


    return response.json();
}


// =========================================================
// INICIALIZAÇÃO
// =========================================================

async function iniciar() {

    try {

        // Busca gêneros e editoras ao mesmo tempo

        [generos, editoras] = await Promise.all([

            request(`${API}/generos`),

            request(`${API}/editoras`)

        ]);


        // Preenche os filtros

        preencherSelect(
            "generoFiltro",
            generos,
            "Todos"
        );

        preencherSelect(
            "editoraFiltro",
            editoras,
            "Todas"
        );


        // Preenche os selects do formulário

        preencherSelect(
            "genero",
            generos
        );

        preencherSelect(
            "editora",
            editoras
        );


        // Busca os possíveis status

        const status = await request(
            `${API}/status`
        );


        $("status").innerHTML = status
            .map(s =>
                `<option value="${s}">
                    ${textoStatus(s)}
                </option>`
            )
            .join("");


        // Finalmente carrega as obras

        await carregarObras();

    } catch (e) {

        mostrarToast(e.message);
    }
}


// =========================================================
// PREENCHER SELECT
// =========================================================

function preencherSelect(
    id,
    lista,
    primeiro = null
) {

    const select = $(id);


    select.innerHTML =
        primeiro !== null
            ? `<option value="">${primeiro}</option>`
            : "";


    select.innerHTML += lista
        .map(item =>
            `<option value="${item.id}">
                ${escapeHtml(item.nome)}
            </option>`
        )
        .join("");
}


// =========================================================
// CARREGAR OBRAS
// =========================================================

async function carregarObras() {

    const params = new URLSearchParams();


    // Pesquisa por título ou autor

    if ($("busca").value.trim()) {

        params.set(
            "busca",
            $("busca").value.trim()
        );
    }


    // Filtro de gênero

    if ($("generoFiltro").value) {

        params.set(
            "generoId",
            $("generoFiltro").value
        );
    }


    // Filtro de editora

    if ($("editoraFiltro").value) {

        params.set(
            "editoraId",
            $("editoraFiltro").value
        );
    }


    try {

        obras = await request(
            `${API}/obras?${params.toString()}`
        );


        renderizar();

    } catch (e) {

        mostrarToast(e.message);
    }
}


// =========================================================
// RENDERIZAR TABELA
// =========================================================

function renderizar() {

    const tbody = $("tabelaObras");


    // Atualiza contador

    $("contador").textContent =
        `${obras.length} ${obras.length === 1
            ? "obra"
            : "obras"
        }`;


    // Mostra/esconde mensagem de vazio

    $("vazio").hidden =
        obras.length !== 0;


    // =====================================================
    // CRIA AS LINHAS
    // =====================================================

    tbody.innerHTML = obras
        .map(obra => {


            // -------------------------------------------------
            // VERIFICA SE EXISTE IMAGEM
            // -------------------------------------------------

            const imagemUrl =
                obra.imagemUrl;


            let capaHTML;


            // Se existe imagem

            if (
                imagemUrl &&
                imagemUrl.trim() !== ""
            ) {

                capaHTML = `

                    <img
                        src="${escapeHtml(imagemUrl)}"
                        alt="Capa de ${escapeHtml(obra.titulo)}"
                        class="capa-obra"
                        onerror="this.style.display='none'; this.nextElementSibling.style.display='grid';"
                    >

                    <span
                        class="capa-sem-imagem"
                        style="display:none;"
                    >
                        📖
                    </span>

                `;

            } else {

                // Caso não exista imagem

                capaHTML = `

                    <span class="capa-sem-imagem">
                        📖
                    </span>

                `;
            }


            // -------------------------------------------------
            // RETORNA A LINHA COMPLETA
            // -------------------------------------------------

            return `

                <tr>

                    <!-- CAPA -->

                    <td class="capa-cell">
                        ${capaHTML}
                    </td>


                    <!-- TÍTULO -->

                    <td>
                        <strong>
                            ${escapeHtml(obra.titulo)}
                        </strong>
                    </td>


                    <!-- VOLUME -->

                    <td>
                        ${obra.volume}
                    </td>


                    <!-- AUTOR -->

                    <td>
                        ${escapeHtml(obra.autor)}
                    </td>


                    <!-- GÊNERO -->

                    <td>
                        ${escapeHtml(
                obra.genero?.nome || ""
            )}
                    </td>


                    <!-- EDITORA -->

                    <td>
                        ${escapeHtml(
                obra.editora?.nome || ""
            )}
                    </td>


                    <!-- NOTA -->

                    <td>
                        ⭐ ${Number(
                obra.nota
            ).toFixed(1)}
                    </td>


                    <!-- STATUS -->

                    <td>

                        <span
                            class="badge ${obra.status.toLowerCase()}"
                        >
                            ${textoStatus(
                obra.status
            )}
                        </span>

                    </td>


                    <!-- AÇÕES -->

                    <td>

                        <div class="actions">

                            <button
                                class="btn btn-edit"
                                onclick="editar(${obra.id})"
                            >
                                Editar
                            </button>

                            <button
                                class="btn btn-danger"
                                onclick="excluir(${obra.id})"
                            >
                                Excluir
                            </button>

                        </div>

                    </td>

                </tr>

            `;

        })
        .join("");
}


// =========================================================
// TEXTO DOS STATUS
// =========================================================

function textoStatus(status) {

    return {

        NAO_LIDO: "Não lido",

        LENDO: "Lendo",

        LIDO: "Lido"

    }[status] || status;
}


// =========================================================
// ABRIR MODAL
// =========================================================

function abrirModal(id = null) {

    $("obraForm").reset();

    $("obraId").value = "";

    $("modalTitulo").textContent =
        id
            ? "Editar obra"
            : "Nova obra";


    // Se estiver editando

    if (id) {

        const obra =
            obras.find(o => o.id === id);


        if (!obra) {
            return;
        }


        $("obraId").value =
            obra.id;

        $("titulo").value =
            obra.titulo;

        $("volume").value =
            obra.volume;

        $("autor").value =
            obra.autor;

        $("genero").value =
            obra.genero.id;

        $("editora").value =
            obra.editora.id;

        $("nota").value =
            obra.nota;

        $("status").value =
            obra.status;
    }


    $("modal")
        .classList
        .add("open");
}


// =========================================================
// FECHAR MODAL
// =========================================================

function fecharModal() {

    $("modal")
        .classList
        .remove("open");
}


// =========================================================
// EDITAR
// =========================================================

async function editar(id) {

    abrirModal(id);
}


// =========================================================
// SALVAR OBRA
// =========================================================

$("obraForm").addEventListener(
    "submit",
    async event => {

        event.preventDefault();


        const id =
            $("obraId").value;


        const dados = {

            titulo:
                $("titulo")
                    .value
                    .trim(),

            volume:
                Number(
                    $("volume").value
                ),

            autor:
                $("autor")
                    .value
                    .trim(),

            generoId:
                Number(
                    $("genero").value
                ),

            editoraId:
                Number(
                    $("editora").value
                ),

            nota:
                Number(
                    $("nota").value
                ),

            status:
                $("status").value
        };


        try {

            // POST para nova obra
            // PUT para edição

            await request(

                id
                    ? `${API}/obras/${id}`
                    : `${API}/obras`,

                {

                    method:
                        id
                            ? "PUT"
                            : "POST",

                    body:
                        JSON.stringify(dados)
                }
            );


            fecharModal();


            // Recarrega a tabela

            await carregarObras();


            mostrarToast(

                id
                    ? "Obra atualizada!"
                    : "Obra cadastrada!"
            );


        } catch (e) {

            mostrarToast(
                e.message
            );
        }
    }
);


// =========================================================
// EXCLUIR OBRA
// =========================================================

async function excluir(id) {

    const obra =
        obras.find(
            o => o.id === id
        );


    if (!obra) {
        return;
    }


    if (
        !confirm(
            `Excluir "${obra.titulo}"?`
        )
    ) {
        return;
    }


    try {

        await request(
            `${API}/obras/${id}`,
            {
                method: "DELETE"
            }
        );


        await carregarObras();


        mostrarToast(
            "Obra excluída!"
        );


    } catch (e) {

        mostrarToast(
            e.message +
            " Verifique se ela não está sendo usada por outro registro."
        );
    }
}


// =========================================================
// LIMPAR FILTROS
// =========================================================

function limparFiltros() {

    $("busca").value = "";

    $("generoFiltro").value = "";

    $("editoraFiltro").value = "";


    carregarObras();
}


// =========================================================
// PESQUISA
// =========================================================

let timerBusca;


$("busca").addEventListener(
    "input",
    () => {

        clearTimeout(
            timerBusca
        );


        timerBusca =
            setTimeout(
                carregarObras,
                250
            );
    }
);


// =========================================================
// FILTROS
// =========================================================

$("generoFiltro")
    .addEventListener(
        "change",
        carregarObras
    );


$("editoraFiltro")
    .addEventListener(
        "change",
        carregarObras
    );


// =========================================================
// FECHAR MODAL CLICANDO FORA
// =========================================================

$("modal").addEventListener(
    "click",
    e => {

        if (
            e.target === $("modal")
        ) {

            fecharModal();
        }
    }
);


// =========================================================
// TOAST
// =========================================================

function mostrarToast(mensagem) {

    const toast =
        $("toast");


    toast.textContent =
        mensagem;


    toast.classList.add(
        "show"
    );


    setTimeout(
        () =>
            toast.classList.remove(
                "show"
            ),
        3000
    );
}


// =========================================================
// SEGURANÇA
// =========================================================

function escapeHtml(valor) {

    return String(
        valor ?? ""
    )

        .replaceAll(
            "&",
            "&amp;"
        )

        .replaceAll(
            "<",
            "&lt;"
        )

        .replaceAll(
            ">",
            "&gt;"
        )

        .replaceAll(
            '"',
            "&quot;"
        )

        .replaceAll(
            "'",
            "&#039;"
        );
}


// =========================================================
// INICIAR SISTEMA
// =========================================================

iniciar();