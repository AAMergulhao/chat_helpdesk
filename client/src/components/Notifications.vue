<template>
  <div class="row">
    <div class="">
      <div class="col s12">
        <div class="card blue-grey darken-1 pop">
          <div class="card-content white-text">
            <span class="card-title" style="padding-block: 5px"
              ><i class="material-icons">notifications</i> Atividades</span
            >
            <div class="row">
              <div class="scrolable">
                <div
                  class="col s12 m6 l6 pop"
                  style="cursor: pointer"
                  v-for="(atividade, key) in atividades"
                  v-bind:key="atividade.id"
                  v-bind:id="key"
                >
                  <div style="padding: 15px">
                    <a
                      class="btn-floating btn-medium waves-effect waves-light right red"
                      v-on:click="deleteAtividade(atividade.id, key)"
                      ><i class="material-icons">close</i></a
                    >
                  </div>
                  <div
                    class="card-panel grey lighten-5 z-depth-1 hoverable modal-trigger"
                    href="#modal_atividade"
                    v-on:click="setModalContent(atividade, key)"
                  >
                    <div class="row valign-wrapper">
                      <div class="col s2">
                        <img
                          src="../assets/bell.png"
                          alt=""
                          class="circle responsive-img"
                        />
                      </div>
                      <div class="col s10">
                        <span class="black-text truncate">
                          <p>{{ atividade.titulo }}</p>
                          <br />
                          {{ atividade.conteudo }}
                        </span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div id="modal_atividade" class="modal" style="width: 400px">
      <div class="modal-content center-align">
        <h4>{{ atividadeSelectedTitulo }}</h4>
        <p>{{ atividadeSelectedTituloConteudo }}</p>
        <div class="modal-footer" style="width: 270px">
          <a
            class="modal-close green btn center-align"
            v-on:click="
              deleteAtividade(atividadeSelectedID, atividadeSelectedElementID)
            "
            >Concluir Atividade
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

document.addEventListener("DOMContentLoaded", () => {
  var elems = document.querySelectorAll(".modal");
  var instances = M.Modal.init(elems);
});

let atividades = {};

let atividadeSelectedElementID;
let atividadeSelectedID;
let atividadeSelectedTitulo;
let atividadeSelectedTituloConteudo;

function setModalContent(atividade, key) {
  this.atividadeSelectedElementID = key;
  this.atividadeSelectedID = atividade.id;
  this.atividadeSelectedTitulo = atividade.titulo;
  this.atividadeSelectedTituloConteudo = atividade.conteudo;
  return;
}
async function getAtividades() {
  atividades = await axios.get(
    `http://localhost:8081/spring-app/usuario/buscarNotificacoesEnviadas?nome=${usuario.nome}`,
    {
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
      "http://localhost:8081/spring-app/not/deletar",
      { id },
      { headers: { "Access-Control-Allow-Origin": "*" } }
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
      atividadeSelectedElementID,
      atividadeSelectedID,
      atividadeSelectedTitulo,
      atividadeSelectedTituloConteudo,
    };
  },
  props: {
    usuario: Object,
  },
  methods: {
    getAtividades,
    deleteAtividade,
    setModalContent,
  },
  beforeMount: async function () {
    this.atividades = await this.getAtividades();
    console.log(this.atividades);
  },
};
</script>