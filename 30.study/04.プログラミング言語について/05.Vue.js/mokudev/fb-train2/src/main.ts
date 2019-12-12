import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import './registerServiceWorker';

import { firebaseConfig } from '@/repository/firebaseConfig';
import firebase from 'firebase/app';
import 'firebase/firestore';
import Vuetify from 'vuetify';
import colors from 'vuetify/es5/util/colors';

Vue.use(Vuetify, {
  theme: {
    original: colors.purple.base,
    theme: '#5982EE',
    twitter: '#00aced',
    facebook: '#305097',
    line: '#5ae628',
    error: '#F26964',
    succcess: '#698FF0',
  },
  options: {
    themeVariations: ['original', 'secondary'],
  },
});

firebase.initializeApp(firebaseConfig);
firebase.firestore.FieldValue.serverTimestamp();

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
