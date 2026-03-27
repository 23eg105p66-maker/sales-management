import { Link } from "react-router-dom";
import { useState } from "react";
import AuthService from "../../services/AuthService";

const initialForm = {
  username: "",
  password: "",
  email: "",
  role: "SALES_REP",
  fullName: ""
};

export default function Register({ onRegisterSuccess }) {
  const [form, setForm] = useState(initialForm);
  const [error, setError] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const change = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const submit = async (e) => {
    e.preventDefault();
    setError("");
    setMessage("");
    setLoading(true);

    try {
      await AuthService.register(form);
      setMessage("Registration successful. Redirecting...");
      setTimeout(() => onRegisterSuccess(), 600);
    } catch (err) {
      setError(err.message || "Failed to register");
    } finally {
      setLoading(false);
    }
  };

  return (
    <section className="auth-shell">
      <form className="card auth-card" onSubmit={submit}>
        <h2>Create Account</h2>
        <p>Register a new sales user account.</p>

        <label>
          Full Name
          <input name="fullName" value={form.fullName} onChange={change} required />
        </label>

        <label>
          Username
          <input name="username" value={form.username} onChange={change} required />
        </label>

        <label>
          Email
          <input type="email" name="email" value={form.email} onChange={change} required />
        </label>

        <label>
          Password
          <input
            type="password"
            name="password"
            value={form.password}
            onChange={change}
            required
          />
        </label>

        <label>
          Role
          <select name="role" value={form.role} onChange={change}>
            <option value="SALES_REP">SALES_REP</option>
            <option value="MANAGER">MANAGER</option>
            <option value="ADMIN">ADMIN</option>
          </select>
        </label>

        <button className="btn btn-primary" disabled={loading} type="submit">
          {loading ? "Creating..." : "Register"}
        </button>

        {message && <p className="status-text ok">{message}</p>}
        {error && <p className="status-text error">{error}</p>}

        <p className="auth-footnote">
          Already have an account? <Link to="/login">Sign in</Link>
        </p>
      </form>
    </section>
  );
}