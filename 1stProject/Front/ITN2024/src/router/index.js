import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/',
    component: () => import('../views/HomeView.vue')
  },
  {
    path: '/about',
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/example',
    component: () => import('../views/basic/example/ExampleView.vue')
  },
  {
    path: '/basic/mypage/inquiry',
    component: () => import('../views/basic/Mypage/InquiryView.vue')
  },
  {
    path: '/basic/mypage/inquiry/:iqid',
    component: () => import('../views/basic/Mypage/InquiryDetailView.vue')
  },
  {
    path: '/basic/mypage/inquiry/write',
    component: () => import('../views/basic/Mypage/InquiryWriteView.vue')
  },
  {
    path: '/basic/mypage',
    component: () => import('../views/basic/Mypage/MypageView.vue')
  },
  {
    path: '/basic/mypage/detail/:paid',
    component: () => import('../views/basic/Mypage/MypageDetailView.vue')
  },
  {
    path: '/basic/mypage/memberinfo',
    component: () => import('../views/basic/Mypage/MemberInfoView.vue')
  },
  
  //회원가입 , 로그인 , 홈
  {
    path: '/auth/register',
    component: () => import('../views/auth/RegisterView.vue')
  },
  {
    path: '/auth/login',
    component: () => import('../views/auth/LoginView.vue')
  },

  // 고객센터 - 공지사항
  {
    path: '/basic/notice',
    component: () => import('../views/basic/Center/notice/NoticeView.vue')
  },
  {
    path: '/basic/notice/add-notice',
    component: () => import('../views/basic/Center/notice/AddNotice.vue')
  },
  {
    path: '/basic/notice/:noid',
    component: () => import('../views/basic/Center/notice/NoticeDetail.vue')
  },

  //쇼핑카트
  {
    // path(/gallery), component(GalleryView.vue)
    path: '/advanced/cart',
    name: 'cart',
    component: () => import('../views/advanced/cart/CartView.vue')
  },
  {
    // path(/add-gallery), component(AddGallery.vue)
    path: '/advanced/add-cart',
    component: () => import('../views/advanced/cart/AddCart.vue')
  },
  {
    // path(/fileDb/:uuid), component(FileDbDetail.vue)
    path: '/advanced/cart/:uuid',
    component: () => import('../views/advanced/cart/CartDetail.vue')
  },

//관리자
{
  path: "/admin/AdminHome",
  component: () => import("../views/admin/AdminHomeVue.vue"),
},
{
  path: "/admin/AdminMember",
  component: () => import("../views/admin/AdminMemberVue.vue"),
},
{
  path: "/admin/AdminMerchandise",
  component: () => import("../views/admin/AdminMerchandiseVue.vue"),
},
{
  path: "/admin/AdminMerchandiseAdd",
  component: () => import("../views/admin/AdminMerchandiseAdd.vue"),
},
{
  path: "/admin/AdminMerchandiseUpdate/:prid",
  component: () => import("../views/admin/AdminMerchandiseUpdate.vue"),
},
{
  path: "/admin/AdminMerchandise/:prid",
  component: () => import("../views/admin/AdminMerchandiseDetailView.vue"),
},
{
  path: "/admin/AdminAnouncement",
  component: () => import("../views/admin/AdminAnnouncementVue.vue"),
},
{
  path: "/admin/AdminAnouncementAdd",
  component: () => import("../views/admin/AdminAnnouncementAdd.vue"),
},
{
  path: "/admin/AdminAnouncement/:noid",
  component: () => import("../views/admin/AdminAnnouncementDetailView.vue"),
},
{
  path: "/admin/AdminAnouncementUpdate/:noid",
  component: () => import("../views/admin/AdminAnnouncementUpdate.vue"),
},
{
  path: "/admin/AdminFaq",
  component: () => import("../views/admin/AdminFaqVue.vue"),
},
{
  path: "/admin/AdminFaqDetailView/:iqid",
  component: () => import("../views/admin/AdminFaqDetailView.vue"),
},
{
  path: "/admin/AdminOrder",
  component: () => import("../views/admin/AdminOrderVue.vue"),
},
{
  path: "/admin/AdminOrderDetail",
  component: () => import("../views/admin/AdminOrderDetailVue.vue"),
},

//상품
{
  path: '/basic/product',
  component: () => import('../views/basic/product/ProductMainView.vue')
},
{
  path: '/basic/product/jeogori',
  component: () => import('../views/basic/product/ProductJeogoriView.vue')
},
{
  path: '/basic/product/bottoms',
  component: () => import('../views/basic/product/ProductBottomsView.vue')
},
{
  path: '/basic/product/ProductDetail/:prid',
  component: () => import('../views/basic/product/ProductDetails.vue')
},
{
  path: '/basic/product/ProductReview',
  component: () => import('../views/basic/product/ProductReviews.vue')
},
{
  path: '/basic/product/durumagi',
  component: () => import('../views/basic/product/ProductDurumagiView.vue')
},
// 결제
{
  path: '/payment',
  component: () => import('../views/advanced/payment/PaymentView.vue')
}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
