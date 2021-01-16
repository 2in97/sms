<template>
  <div id="app">
    <main-body class='main'></main-body>
  </div>
</template>

<script>
import mainBody from '@/view/mainBody.vue'
export default {
  name: 'App',
  components: {
    mainBody,
  },
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height:100%;
  width:100%;
}

.main {
  height:100%;
  width:100%;
  float:left;
}
</style>
