import { NavLink } from "react-router-dom";

export default function Navbar({ user, onLogout }) {
  return (
    <header className="topbar">
      <div className="brand-block">
        <h1>Sales Command</h1>
        <p>Track products, reps, and revenue in one place</p>
      </div>

      <nav className="nav-links">
        <NavLink to="/">Dashboard</NavLink>
        <NavLink to="/products">View Products</NavLink>
        <NavLink to="/products/add">Add Product</NavLink>
        <NavLink to="/sales">View Sales</NavLink>
        <NavLink to="/sales/add">Add Sale</NavLink>
      </nav>

      <div className="account-chip">
        <div>
          <strong>{user?.fullName || user?.username}</strong>
          <small>{user?.role || "USER"}</small>
        </div>
        <button className="btn btn-danger" onClick={onLogout}>
          Logout
        </button>
      </div>
    </header>
  );
}