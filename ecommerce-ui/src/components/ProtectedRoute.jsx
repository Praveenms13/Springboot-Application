import React, { useEffect } from "react";
import { Outlet, Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../store/auth-context";

export default function ProtectedRoute() {
  const { isAuthenticated } = useAuth();
  const location = useLocation();
  const skipRedirectPath = sessionStorage.getItem("skipRedirectPath") === "true";
  useEffect(() => {
    if (!isAuthenticated && location.pathname !== "/login" && !skipRedirectPath) {
      sessionStorage.setItem("redirectPath", location.pathname);
    }
  }, [isAuthenticated, location.pathname]);
  return isAuthenticated ? <Outlet /> : <Navigate to="/login" />;
}
