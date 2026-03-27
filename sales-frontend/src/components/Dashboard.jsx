import { useEffect, useState } from "react";
import SalesService from "../services/SalesService";

const defaultStats = {
  totalRevenue: 0,
  totalSales: 0,
  totalProducts: 0,
  totalUsers: 0,
  revenueThisMonth: 0,
  salesThisMonth: 0
};

export default function Dashboard() {
  const [stats, setStats] = useState(defaultStats);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchDashboard = async () => {
      try {
        const data = await SalesService.getDashboard();
        setStats({ ...defaultStats, ...data });
      } catch (err) {
        setError(err.message || "Unable to load dashboard");
      } finally {
        setLoading(false);
      }
    };

    fetchDashboard();
  }, []);

  if (loading) {
    return <p className="status-text">Loading dashboard...</p>;
  }

  if (error) {
    return <p className="status-text error">{error}</p>;
  }

  return (
    <section>
      <div className="section-heading">
        <h2>Revenue Dashboard</h2>
        <p>Live metrics from your sales backend API</p>
      </div>

      <div className="stats-grid">
        <article className="stat-card warm">
          <h3>Total Revenue</h3>
          <p>${Number(stats.totalRevenue).toFixed(2)}</p>
        </article>

        <article className="stat-card cool">
          <h3>Total Sales</h3>
          <p>{stats.totalSales}</p>
        </article>

        <article className="stat-card neutral">
          <h3>Total Products</h3>
          <p>{stats.totalProducts}</p>
        </article>

        <article className="stat-card accent">
          <h3>Total Users</h3>
          <p>{stats.totalUsers}</p>
        </article>

        <article className="stat-card wide warm">
          <h3>Revenue This Month</h3>
          <p>${Number(stats.revenueThisMonth).toFixed(2)}</p>
        </article>

        <article className="stat-card wide cool">
          <h3>Sales This Month</h3>
          <p>{stats.salesThisMonth}</p>
        </article>
      </div>
    </section>
  );
}