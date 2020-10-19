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
                  v-for="atividade in atividades"
                  v-bind:key="atividade.id"
                >
                  <div style="padding: 15px">
                    <a
                      class="btn-floating btn-medium waves-effect waves-light right red"
                      ><i class="material-icons">close</i></a
                    >
                  </div>
                  <div class="card-panel grey lighten-5 z-depth-1 hoverable">
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
  </div>
</template>

<script>
import axios from "axios";
let usuario
if (sessionStorage.usuario) {
  usuario = JSON.parse(sessionStorage.usuario);
}

let atividades = {};
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
export default {
  name: "Notifications",
  data() {
    return {
      atividades,
    };
  },
  props: {
    usuario: Object,
  },
  methods: {
    getAtividades,
  },
  beforeMount: async function () {
    this.atividades = await this.getAtividades();
    console.log(this.atividades);
  },
};
</script>