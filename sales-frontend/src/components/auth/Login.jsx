import { Link } from "react-router-dom";
import { useState } from "react";
import AuthService from "../../services/AuthService";

export default function Login({ onLoginSuccess }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const submit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      await AuthService.login(username, password);
      onLoginSuccess();
    } catch (err) {
      setError(err.message || "Invalid credentials");
    } finally {
      setLoading(false);
    }
  };

  return (
    <section className="auth-shell">
      <form className="card auth-card" onSubmit={submit}>
        <h2>Welcome Back</h2>
        <p>Sign in with your username to continue managing sales data.</p>

        <label>
          Username
          <input
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="admin"
            required
          />
        </label>

        <label>
          Password
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password (kept for compatibility)"
            required
          />
        </label>

        <button className="btn btn-primary" disabled={loading} type="submit">
          {loading ? "Signing in..." : "Login"}
        </button>

        {error && <p className="status-text error">{error}</p>}

        <p className="auth-footnote">
          New user? <Link to="/register">Create account</Link>
        </p>
      </form>
    </section>
  );
}