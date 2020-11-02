<template>
<div class="row">
    <div class="col s12">
        <div class="card blue-grey darken-1 pop">
            <div class="card-content white-text">
                <span class="card-title" style="padding-block: 5px"><i class="material-icons">notifications</i> Atividades</span>
                <div class="row blue-grey darken-2">
                    <div class="input-field col s12 m6 l6">
                        <i class="material-icons prefix">search</i>
                        <input v-on:keyup.enter="buscarAtividadesPorTitulo" id="search_title" type="text" class="validate white-text" />
                        <label for="icon_prefix">Pesquisar por titulo</label>
                    </div>
                    <div class="input-field col s12 m8 l8">
                        <p class="col s12 m3 l3">
                            <label>
                                <input type="checkbox" id="atv_abertas" v-on:click="buscarAtividadesPorStatus()" />
                                <span class="white-text">Abertas</span>
                            </label>
                        </p>
                        <p class="col s12 m3 l3">
                            <label>
                                <input type="checkbox" id="atv_fechadas" v-on:click="buscarAtividadesPorStatus()" />
                                <span class="white-text">Fechadas</span>
                            </label>
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="scrolable">
                        <div v-if="atividadesFiltradas.length >= 1">
                            <div class="col s12 m6 l6 pop" style="cursor: pointer" v-for="(atividade, key) in atividadesFiltradas" v-bind:key="atividade.id" v-bind:id="key">
                                <div style="padding: 15px">
                                    <a class="btn-floating btn-medium waves-effect waves-light right red" v-on:click="deleteAtividade(atividade.id, key)"><i class="material-icons">close</i></a>
                                </div>
                                <div class="card-panel grey lighten-5 z-depth-1 hoverable modal-trigger" href="#modal_atividade" v-on:click="setModalContent(atividade, key)">
                                    <div class="row valign-wrapper">
                                        <div class="col s4 m3 l3 avatar">
                                            <i v-if="atividade.status == 1" class="large material-icons circle green">notifications</i>
                                            <i v-if="atividade.status == 0" class="large material-icons circle red">notifications</i>
                                        </div>
                                        <div class="col s9">
                                            <span class="black-text truncate">
                                                <p>{{ atividade.titulo }}</p>
                                                <br />
                                                {{ atividade.conteudo }}
                                                <br />
                                                <p class="secondary-content" style="
                              color: #66bb6a;
                              padding: 10px;
                              font-weight: bold;
                            ">
                                                    Prazo:
                                                    {{
                              `${atividade.dataLimite
                                .split("T")[0]
                                .slice(8, 10)}/${atividade.dataLimite
                                .split("T")[0]
                                .slice(5, 7)}/${atividade.dataLimite
                                .split("T")[0]
                                .slice(0, 4)}`
                            }}
                                                </p>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div v-if="atividadesFiltradas.length == 0">Sem registros</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="modal_atividade" class="modal" style="height: 400px">
        <div class="modal-content">
            <div class="row">
                <h4 class="col s12 center-align green-text text-lighten-1">
                    {{ atividadeSelectedTitulo }}
                </h4>
            </div>
            <div class="grey lighten-3 col s12 center-align">
                <blockquote>{{ atividadeSelectedTituloConteudo }}</blockquote>
            </div>
            <div class="grey lighten-3 col s12 left-align" style="margin-top: 5px">
                <p>
                    Disparada por: {{ atividadeSelectedRemetenteNome }}
                    {{ atividadeSelectedDataDisparo }}
                </p>
                <p>Prazo de conclusão: {{ atividadeSelectedDataAgendada }}</p>
            </div>

            <div class="modal-footer center-align">
                <a style="margin-right: 10px" class="modal-close red btn center-align"><i class="material-icons left">close</i>Fechar
                </a>
                <a class="modal-close green btn center-align" v-on:click="
              deleteAtividade(atividadeSelectedID, atividadeSelectedElementID)
            "><i class="material-icons left">check</i>Concluir Atividade
                </a>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from "axios";
import M from "materialize-css";
let usuario;
if (sessionStorage.usuario) {
    usuario = JSON.parse(sessionStorage.usuario);
}

let atividades = [];
let atividadesFiltradas = [];

let atividadeSelectedElementID;
let atividadeSelectedID;
let atividadeSelectedTitulo;
let atividadeSelectedTituloConteudo;
let atividadeSelectedDataDisparo;
let atividadeSelectedDataAgendada;
let atividadeSelectedRemetenteNome;

function setModalContent(atividade, key) {
    this.atividadeSelectedElementID = key;
    this.atividadeSelectedID = atividade.id;
    this.atividadeSelectedTitulo = atividade.titulo;
    this.atividadeSelectedTituloConteudo = atividade.conteudo;
    this.atividadeSelectedDataDisparo = `${atividade.dataDisparo
    .split("T")[0]
    .slice(8, 10)}/${atividade.dataDisparo
    .split("T")[0]
    .slice(5, 7)}/${atividade.dataDisparo.split("T")[0].slice(0, 4)}`;
    this.atividadeSelectedDataAgendada = `${atividade.dataLimite
    .split("T")[0]
    .slice(8, 10)}/${atividade.dataLimite
    .split("T")[0]
    .slice(5, 7)}/${atividade.dataLimite.split("T")[0].slice(0, 4)}`;
    this.atividadeSelectedRemetenteNome = atividade.notRemetente.nome;
    return;
}
async function getAtividades() {
    atividades = await axios.get(
        `http://localhost:8081/spring-app/usuario/buscarNotificacoesEnviadas?nome=${usuario.nome}`, {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        }
    );
    atividades = atividades.data;
    return atividades;
}

async function deleteAtividade(id, key) {
    console.log(id);
    await axios
        .post(
            "http://localhost:8081/spring-app/not/deletar", {
                id,
            }, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            }
        )
        .then(() => {
            document.getElementById(key).classList.add("popOut");
            setTimeout(() => {
                this.atividades.splice(key, 1);
            }, 500);
        });

    return;
}
export default {
    name: "Notifications",
    data() {
        return {
            atividades,
            atividadesFiltradas,
            atividadeSelectedElementID,
            atividadeSelectedID,
            atividadeSelectedTitulo,
            atividadeSelectedTituloConteudo,
            atividadeSelectedDataDisparo,
            atividadeSelectedDataAgendada,
            atividadeSelectedRemetenteNome,
        };
    },
    props: {
        usuario: Object,
    },
    methods: {
        getAtividades,
        deleteAtividade,
        setModalContent,
        buscarAtividadesPorTitulo,
        buscarAtividadesPorStatus,
    },
    mounted() {
        M.Modal.init(document.querySelectorAll(".modal"));
    },
    beforeMount: async function () {
        this.atividades = await this.getAtividades();
        this.atividadesFiltradas = this.atividades;
    },
};

function buscarAtividadesPorTitulo() {
    let searchInput = document.getElementById("search_title").value;

    let atividadesFiltradasTitulo = [];
    if (searchInput && searchInput != "") {
        this.atividadesFiltradas.forEach((atividade) => {
            console.log(atividade);
            if (atividade.titulo.toLowerCase().includes(searchInput.toLowerCase())) {
                atividadesFiltradasTitulo.push(atividade);
            }
        });
        this.atividadesFiltradas = atividadesFiltradasTitulo;
        return;
    }
    this.atividadesFiltradas = this.atividades;
    return;
}

function buscarAtividadesPorStatus() {
    let abertas = document.getElementById("atv_abertas").checked;
    let fechadas = document.getElementById("atv_fechadas").checked;
    let searchInput = document.getElementById("search_title").value;

    if (searchInput && searchInput != "") {
        this.buscarAtividadesPorTitulo();
    }

    let atividadesFiltradasStatus = [];
    if (abertas && !fechadas) {
        this.atividadesFiltradas.forEach((atividade) => {
            if (atividade.status == 1) {
                atividadesFiltradasStatus.push(atividade);
            }
        });
        this.atividadesFiltradas = atividadesFiltradasStatus;
        return;
    } else if (!abertas && fechadas) {
        this.atividadesFiltradas.forEach((atividade) => {
            if (atividade.status == 0) {
                atividadesFiltradasStatus.push(atividade);
            }
        });
        this.atividadesFiltradas = atividadesFiltradasStatus;
        return;
    } else {
        if (searchInput && searchInput != "") {
            this.atividadesFiltradas = this.atividades;
            this.buscarAtividadesPorTitulo();
            return;
        }
        this.atividadesFiltradas = this.atividades;
        return;
    }
}
</script>

<style>
blockquote {
    border-left: 5px solid #66bb6a;
}
</style>
