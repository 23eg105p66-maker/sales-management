import { useEffect, useState } from "react";
import SalesService from "../../services/SalesService";
import ProductService from "../../services/ProductService";
import AuthService from "../../services/AuthService";

const initialForm = {
  productId: "",
  userId: "",
  quantity: "",
  customerName: "",
  customerEmail: "",
  notes: ""
};

export default function AddSale() {
  const [form, setForm] = useState(initialForm);
  const [products, setProducts] = useState([]);
  const [users, setUsers] = useState([]);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const [saving, setSaving] = useState(false);

  useEffect(() => {
    const bootstrap = async () => {
      try {
        const [productData, userData] = await Promise.all([
          ProductService.getActive(),
          AuthService.getAllUsers()
        ]);
        setProducts(productData || []);
        setUsers((userData || []).filter((u) => u.active !== false));
      } catch (err) {
        setError(err.message || "Failed to load products/users");
      }
    };

    bootstrap();
  }, []);

  const onChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setError("");
    setSaving(true);

    try {
      await SalesService.create({
        ...form,
        productId: Number(form.productId),
        userId: Number(form.userId),
        quantity: Number(form.quantity)
      });

      setMessage("Sale created successfully.");
      setForm(initialForm);
    } catch (err) {
      setError(err.message || "Failed to create sale");
    } finally {
      setSaving(false);
    }
  };

  return (
    <section>
      <div className="section-heading">
        <h2>Add Sale</h2>
        <p>Create a sale and map it to product and sales rep</p>
      </div>

      <form className="card form-grid" onSubmit={onSubmit}>
        <label>
          Product
          <select
            name="productId"
            value={form.productId}
            onChange={onChange}
            required
          >
            <option value="">Select product</option>
            {products.map((p) => (
              <option key={p.id} value={p.id}>
                {p.name} (Stock: {p.stockQuantity})
              </option>
            ))}
          </select>
        </label>

        <label>
          Sales User
          <select name="userId" value={form.userId} onChange={onChange} required>
            <option value="">Select user</option>
            {users.map((u) => (
              <option key={u.id} value={u.id}>
                {u.fullName || u.username}
              </option>
            ))}
          </select>
        </label>

        <label>
          Quantity
          <input
            type="number"
            min="1"
            name="quantity"
            value={form.quantity}
            onChange={onChange}
            required
          />
        </label>

        <label>
          Customer Name
          <input
            name="customerName"
            value={form.customerName}
            onChange={onChange}
            required
          />
        </label>

        <label>
          Customer Email
          <input
            type="email"
            name="customerEmail"
            value={form.customerEmail}
            onChange={onChange}
            required
          />
        </label>

        <label className="full">
          Notes
          <textarea name="notes" rows="4" value={form.notes} onChange={onChange} />
        </label>

        <div className="full actions-row">
          <button className="btn btn-primary" disabled={saving} type="submit">
            {saving ? "Saving..." : "Save Sale"}
          </button>
        </div>

        {message && <p className="status-text ok full">{message}</p>}
        {error && <p className="status-text error full">{error}</p>}
      </form>
    </section>
  );
}