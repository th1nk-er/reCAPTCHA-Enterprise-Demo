import { createRouter, createWebHashHistory } from "vue-router";

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      name: "index",
      component: () => import("@/pages/Main.vue"),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("@/pages/Login.vue"),
    },
  ],
});
export default router;
