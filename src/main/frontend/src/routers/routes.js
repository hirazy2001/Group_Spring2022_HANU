import LoginPage from "../pages/auth/login";
import SiupPage from "../pages/auth/register";
import HomePage from "../pages/client/home";
import DashBoardPage from "../pages/admin/dashboard";
import AdminLayout from "../layouts/AdminLayout";
import UserLayout from "../layouts/UserLayout";
import ManagerCustomerPage from "../pages/admin/customer";
import ProfilePage from "../pages/admin/profile";
import AdminTransactionPage from "../pages/admin/transaction"
import AdminTransactionLogPage from "../pages/admin/transaction-log"
import AdminAccountPage from "../pages/admin/account"
import AdminAccountDetail from "../pages/admin/account-detail"
import UserAccountPage from "../pages/user/account"
import UserDashBoardPage from "../pages/user/dashboard"
import UserAccountCreate from "../pages/user/account-create"
import UserAccountDetail from "../pages/user/account-detail"
import UserTransactionPage from "../pages/user/transaction"
import UserProfilePage from "../pages/user/profile"
const routes = [{
        component: LoginPage,
        path: "/signin",
        isLayout: false,
        authen: false,
        breadcrumb: ""
    },
    {
        component: SiupPage,
        path: "/signup",
        isLayout: false,
        authen: false,
        breadcrumb: ""
    },
    {
        component: HomePage,
        path: "/",
        isLayout: false,
        // layout: ClientLayout,
        authen: false,
        breadcrumb: ""
    },
    {
        component: DashBoardPage,
        path: "/admin-dashboard",
        isLayout: true,
        layout: AdminLayout,
        authen: true,
        breadcrumb: "Admin-dashboard"
    },
    {
        component: ManagerCustomerPage,
        path: "/admin-customer",
        isLayout: true,
        layout: AdminLayout,
        authen: true,
        breadcrumb: "Admin-customer"
    },
    {
        component: ProfilePage,
        path: "/admin-profile",
        isLayout: true,
        layout: AdminLayout,
        authen: true,
        breadcrumb: "Admin-profile"
    },
    {
        component: AdminTransactionPage,
        path: "/admin-transaction",
        isLayout: true,
        layout: AdminLayout,
        authen: true,
        breadcrumb: "Admin-transaction"
    },
    {
        component: AdminTransactionLogPage,
        path: "/admin-transaction-log/:id",
        isLayout: true,
        layout: AdminLayout,
        authen: true,
        breadcrumb: "Admin-transaction-log"
    },
    {
        component: AdminAccountPage,
        path: "/admin-account",
        isLayout: true,
        layout: AdminLayout,
        authen: true,
        breadcrumb: "Admin-account"
    },
    {
        component: AdminAccountDetail,
        path: "/admin-account-detail/:id",
        isLayout: true,
        layout: AdminLayout,
        authen: true,
        breadcrumb: "Admin-account-detail"
    },
    {
        component: UserAccountPage,
        path: "/user-account",
        isLayout: true,
        layout: UserLayout,
        authen: true,
        breadcrumb: "User-account"
    },

    {
        component: UserDashBoardPage,
        path: "/user-dashboard",
        isLayout: true,
        layout: UserLayout,
        authen: true,
        breadcrumb: "User-dashboard"
    },
    {
        component: UserAccountCreate,
        path: "/user-account-create",
        isLayout: true,
        layout: UserLayout,
        authen: true,
        breadcrumb: "User-account-create"
    },
    {
        component: UserAccountDetail,
        path: "/user-account-detail/:id",
        isLayout: true,
        layout: UserLayout,
        authen: true,
        breadcrumb: "User-account-detail"
    },
    {
        component: UserTransactionPage,
        path: "/user-transaction",
        isLayout: true,
        layout: UserLayout,
        authen: true,
        breadcrumb: "User-transaction"
    },
    {
        component: UserProfilePage,
        path: "/user-profile",
        isLayout: true,
        layout: UserLayout,
        authen: true,
        breadcrumb: "User-profile"
    }
];

export default routes;