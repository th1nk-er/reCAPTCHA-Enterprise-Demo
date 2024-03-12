import { createApp } from "vue";
import router from "@/router";
import App from "./App.vue";
const app = createApp(App);

// TODO CHANGE THIS
app.config.globalProperties.$siteKey = "";

app.use(router);
app.mount("#app");
