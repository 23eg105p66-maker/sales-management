import { useEffect, useState } from "react";
import SalesService from "../../services/SalesService";

const statuses = ["PENDING", "COMPLETED", "CANCELLED", "REFUNDED"];

export default function ViewSales() {
  const [sales, setSales] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const fetchSales = async () => {
    try {
      setLoading(true);
      const data = await SalesService.getAll();
      setSales(data || []);
    } catch (err) {
      setError(err.message || "Failed to fetch sales");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchSales();
  }, []);

  const updateStatus = async (id, status) => {
    try {
      const updated = await SalesService.updateStatus(id, status);
      setSales((prev) =>
        prev.map((sale) => (sale.id === id ? { ...sale, ...updated } : sale))
      );
    } catch (err) {
      setError(err.message || "Failed to update status");
    }
  };

  const deleteSale = async (id) => {
    if (!window.confirm("Delete this sale?")) {
      return;
    }
    try {
      await SalesService.remove(id);
      setSales((prev) => prev.filter((sale) => sale.id !== id));
    } catch (err) {
      setError(err.message || "Failed to delete sale");
    }
  };

  if (loading) {
    return <p className="status-text">Loading sales...</p>;
  }

  return (
    <section>
      <div className="section-heading">
        <h2>Sales Ledger</h2>
        <p>Review all transactions and adjust lifecycle status</p>
      </div>

      {error && <p className="status-text error">{error}</p>}

      <div className="card table-wrap">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Product</th>
              <th>User</th>
              <th>Customer</th>
              <th>Quantity</th>
              <th>Total Amount</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {sales.length === 0 ? (
              <tr>
                <td colSpan="8" className="empty-row">
                  No sales found.
                </td>
              </tr>
            ) : (
              sales.map((sale) => (
                <tr key={sale.id}>
                  <td>{sale.id}</td>
                  <td>{sale.productName || "-"}</td>
                  <td>{sale.salesRepName || "-"}</td>
                  <td>{sale.customerName}</td>
                  <td>{sale.quantity}</td>
                  <td>${Number(sale.totalAmount || 0).toFixed(2)}</td>
                  <td>
                    <select
                      value={sale.status || "PENDING"}
                      onChange={(e) => updateStatus(sale.id, e.target.value)}
                    >
                      {statuses.map((status) => (
                        <option key={status} value={status}>
                          {status}
                        </option>
                      ))}
                    </select>
                  </td>
                  <td>
                    <button className="btn btn-danger" onClick={() => deleteSale(sale.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </section>
  );
}