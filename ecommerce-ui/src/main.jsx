import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import { Route } from "react-router-dom";
import { Bounce, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import {
  createBrowserRouter,
  createRoutesFromElements,
  RouterProvider,
} from "react-router-dom";
import Home from "./components/Home.jsx";
import Cart from "./components/Cart.jsx";
import About from "./components/About.jsx";
import Contact from "./components/Contact.jsx";
import Login, { loginAction } from "./components/Login.jsx";
import Register, { registerAction } from "./components/Register.jsx";
import ErrorPage from "./components/ErrorPage.jsx";
import { productLoader } from "./components/Home.jsx";
import { contactAction } from "./components/Contact.jsx";
import ProductDetails from "./components/ProductDetails.jsx";
import { CartProvider } from "./store/cart-context.jsx";
import { AuthProvider } from "./store/auth-context.jsx";
import CheckoutForm from "./components/CheckoutForm.jsx";
import ProtectedRoute from "./components/ProtectedRoute.jsx";
import Profile, {
  profileLoader,
  profileAction,
} from "./components/Profile.jsx";
import Orders, { ordersLoader } from "./components/Orders.jsx";
import AdminOrders, {
  adminOrdersLoader,
} from "./components/admin/AdminOrders.jsx";
import AdminMessages, {
  messagesLoader,
} from "./components/admin/AdminMessages.jsx";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import OrderSuccess from "./components/OrderSuccess.jsx";

const stripePromise = loadStripe(import.meta.env.VITE_STRIPE_PUBLISHABLE_KEY);

const routeDefinitions = createRoutesFromElements(
  <Route path="/" element={<App />} errorElement={<ErrorPage />}>
    <Route index element={<Home />} loader={productLoader} />
    <Route path="/home" element={<Home />} loader={productLoader} />
    <Route path="/about" element={<About />} />
    <Route path="/contact" element={<Contact />} action={contactAction} />
    <Route path="/login" element={<Login />} action={loginAction} />
    <Route path="/register" element={<Register />} action={registerAction} />
    <Route path="/cart" element={<Cart />} />
    <Route path="/products/:productId" element={<ProductDetails />} />
    <Route element={<ProtectedRoute />}>
      <Route path="/checkout" element={<CheckoutForm />} />
      <Route path="/order-success" element={<OrderSuccess />} />
      <Route
        path="/profile"
        element={<Profile />}
        loader={profileLoader}
        action={profileAction}
        shouldRevalidate={({ actionResult }) => {
          return !actionResult?.success;
        }}
      />
      <Route path="/orders" element={<Orders />} loader={ordersLoader} />
      <Route
        path="/admin/orders"
        element={<AdminOrders />}
        loader={adminOrdersLoader}
      />
      <Route
        path="/admin/messages"
        element={<AdminMessages />}
        loader={messagesLoader}
      />
    </Route>
  </Route>
);
const router = createBrowserRouter(routeDefinitions);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Elements stripe={stripePromise}>
      <AuthProvider>
        <CartProvider>
          <RouterProvider router={router} />
        </CartProvider>
      </AuthProvider>
      <ToastContainer
        position="top-right"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        draggable
        pauseOnHover
        theme={localStorage.getItem("theme") === "dark" ? "dark" : "light"}
        transition={Bounce}
      />
    </Elements>
  </StrictMode>
);
