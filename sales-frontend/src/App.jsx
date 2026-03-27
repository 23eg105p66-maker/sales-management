import { Navigate, Route, Routes, useNavigate } from "react-router-dom";
import { useMemo, useState } from "react";
import Navbar from "./components/Navbar";
import Dashboard from "./components/Dashboard";
import AddProduct from "./components/product/AddProduct";
import ViewProducts from "./components/product/ViewProducts";
import AddSale from "./components/sales/AddSale";
import ViewSales from "./components/sales/ViewSales";
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import AuthService from "./services/AuthService";

function PrivateRoute({ isLoggedIn, children }) {
  if (!isLoggedIn) {
    return <Navigate to="/login" replace />;
  }
  return children;
}

export default function App() {
  const navigate = useNavigate();
  const [authVersion, setAuthVersion] = useState(0);

  const user = useMemo(() => AuthService.getCurrentUser(), [authVersion]);
  const isLoggedIn = Boolean(user);

  const handleLogout = () => {
    AuthService.logout();
    setAuthVersion((v) => v + 1);
    navigate("/login");
  };

  const handleAuthSuccess = () => {
    setAuthVersion((v) => v + 1);
    navigate("/");
  };

  return (
    <div className="app-shell">
      {isLoggedIn && <Navbar user={user} onLogout={handleLogout} />}

      <main className="page-wrap">
        <Routes>
          <Route
            path="/login"
            element={
              isLoggedIn ? (
                <Navigate to="/" replace />
              ) : (
                <Login onLoginSuccess={handleAuthSuccess} />
              )
            }
          />

          <Route
            path="/register"
            element={
              isLoggedIn ? (
                <Navigate to="/" replace />
              ) : (
                <Register onRegisterSuccess={handleAuthSuccess} />
              )
            }
          />

          <Route
            path="/"
            element={
              <PrivateRoute isLoggedIn={isLoggedIn}>
                <Dashboard />
              </PrivateRoute>
            }
          />

          <Route
            path="/products/add"
            element={
              <PrivateRoute isLoggedIn={isLoggedIn}>
                <AddProduct />
              </PrivateRoute>
            }
          />

          <Route
            path="/products"
            element={
              <PrivateRoute isLoggedIn={isLoggedIn}>
                <ViewProducts />
              </PrivateRoute>
            }
          />

          <Route
            path="/sales/add"
            element={
              <PrivateRoute isLoggedIn={isLoggedIn}>
                <AddSale />
              </PrivateRoute>
            }
          />

          <Route
            path="/sales"
            element={
              <PrivateRoute isLoggedIn={isLoggedIn}>
                <ViewSales />
              </PrivateRoute>
            }
          />

          <Route
            path="*"
            element={<Navigate to={isLoggedIn ? "/" : "/login"} replace />}
          />
        </Routes>
      </main>
    </div>
  );
}