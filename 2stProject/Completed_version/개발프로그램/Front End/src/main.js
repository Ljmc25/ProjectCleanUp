import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

// Bootstrap
import 'bootstrap/dist/js/bootstrap.bundle';
import 'bootstrap/dist/css/bootstrap.min.css';
import BootstrapVue3 from 'bootstrap-vue-3';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';


// vue-quill-editor 추가
import { QuillEditor } from '@vueup/vue-quill';
import 'quill/dist/quill.snow.css'; // Quill 스타일 추가



// Create and mount the app
createApp(App)
  .use(BootstrapVue3)
  .use(store)
  .use(router)
  .component('quill-editor', QuillEditor)  // quill-editor 전역 등록
  .mount('#app')
