<template>
  <div>
    <div class="row">
      <div class="">
        <div class="col s12">
          <div class="card blue-grey darken-1 pop">
            <div class="card-content white-text">
              <span class="card-title" style="padding-block: 5px"
                ><i class="material-icons">group</i> Funcionários</span
              >
              <div class="row blue-grey">
                <div class="container">
                  <form class="col s12 center-align">
                    <div class="row">
                      <div class="input-field col s12">
                        <i class="material-icons prefix">search</i>
                        <textarea
                          id="icon_prefix2"
                          class="materialize-textarea"
                        ></textarea>
                        <label for="icon_prefix2" class="white-text"
                          >Pesquisar</label
                        >
                      </div>
                      <p class="col s6">
                        <label>
                          <input class="red" type="checkbox" />
                          <span class="white-text">Diretores</span>
                        </label>
                      </p>
                      <p class="col s6">
                        <label>
                          <input type="checkbox" />
                          <span class="white-text">Atendentes</span>
                        </label>
                      </p>
                    </div>
                  </form>
                </div>
                <div>
                  <div
                    v-for="(user, key) in usuarios"
                    v-bind:key="user.id"
                    v-bind:id="key"
                  >
                    <div
                      v-if="usuario.id != user.id"
                      class="col s12 m3 black-text pop hoverable"
                      style="cursor: pointer"
                    >
                      <div
                        class="card horizontal modal-trigger" v-on:click="() => { this.selectedUser = user;}"
                        href="#modal_usuario"
                      >
                        <div class="card-image">
                          <img
                            src="../assets/profile.png"
                            style="height: 120px; width: 100px"
                          />
                        </div>
                        <div class="card-stacked">
                          <div class="card-content center-align">
                            <p>{{ user.nome }}</p>
                            <br />
                            <p>Diretor</p>
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
      </div>
    </div>

    <div id="modal_usuario" class="modal" style="width: 400px">
      <div class="modal-content center-align">
        <div class="col s12 m8 offset-m2 l6 offset-l3">
          <div class="card-panel grey lighten-5 z-depth-1">
            <div class="row valign-wrapper">
              <div class="col s2">
                <img
                  src="../assets/profile.png"
                  alt=""
                  class="circle responsive-img"
                />
                <!-- notice the "circle" class -->
              </div>
              <div class="col s6">
                <span class="black-text"> Fabiola </span>
                <br />
                <span class="black-text"> Diretor </span>
              </div>
              <div class="col s6">
                <a class="btn-small" style="margin-bottom: 5px"
                  >Enviar Mensagem</a
                >
                <br />
                <a href="#modal_atividade" class="btn-small modal-trigger"
                  >Disparar Atividade</a
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div id="modal_atividade" class="modal" style="width: 400px">
      <div class="modal-content">
        <h4>Atividade</h4>
        <hr />
        <div class="row">
          <form class="col s12">
            <div class="row">
              <div class="input-field col s6">
                <i class="material-icons prefix">title</i>
                <input id="atividade_titulo" type="text" data-length="10" />
                <label for="input_text">Título</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <i class="material-icons prefix">mode_edit</i>
                <textarea
                  style="
                    border-bottom: 1px solid #66bb6a;
                    box-shadow: 0 1px 0 0 #66bb6a;
                  "
                  id="atividade_conteudo"
                  class="materialize-textarea"
                  data-length="120"
                ></textarea>
                <label for="textarea2">Conteúdo</label>
              </div>
            </div>
          </form>
          <a class="btn green" v-on:click="postAtividade()">Disparar Atividade</a>
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import axios from "axios";
import M from "materialize-css";

document.addEventListener("DOMContentLoaded", () => {
  var elems = document.querySelectorAll(".modal");
  var instances = M.Modal.init(elems);
});

let usuario;
if (sessionStorage.usuario) {
  usuario = JSON.parse(sessionStorage.usuario);
}

let usuarios;
async function getUsuarios() {
  usuarios = await axios.get(
    `http://localhost:8081/spring-app/usuario/`,
    {
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    }
  );
  usuarios = usuarios.data;
  return usuarios;
}

async function postAtividade(){
    console.log(this.selectedUser)
    await axios
    .post(
      "http://localhost:8081/spring-app/not/",
      { "titulo": document.getElementById('atividade_titulo').value,
        "conteudo": document.getElementById('atividade_conteudo').value,
        "nomeRemetente": usuario.nome,
        "nomeDestinatario": this.selectedUser.nome },
      { headers: { "Access-Control-Allow-Origin": "*" } }
    ).then(() =>{
        document.getElementById('atividade_titulo').value = "";
        document.getElementById('atividade_conteudo').value = "";
        M.toast({html: '<p class="green-text">Atividade disparada com sucesso!</p>'})
    })

}
let selectedUser;

export default {
  name: "Employes",
  props: {
    usuario: Object,
  },
  data() {
    return {
      usuarios,
      selectedUser
    };
  },
  methods: {
    getUsuarios,
    postAtividade
  },
  beforeMount: async function () {
    this.usuarios = await this.getUsuarios();
    console.log(this.usuarios);
  },
};
</script>
