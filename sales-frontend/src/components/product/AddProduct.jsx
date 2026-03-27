import { useState } from "react";
import ProductService from "../../services/ProductService";

const initialForm = {
  name: "",
  description: "",
  price: "",
  stockQuantity: "",
  category: "",
  sku: ""
};

export default function AddProduct() {
  const [form, setForm] = useState(initialForm);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const [saving, setSaving] = useState(false);

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
      const payload = {
        ...form,
        price: Number(form.price),
        stockQuantity: Number(form.stockQuantity)
      };
      await ProductService.create(payload);
      setMessage("Product created successfully.");
      setForm(initialForm);
    } catch (err) {
      setError(err.message || "Failed to create product");
    } finally {
      setSaving(false);
    }
  };

  return (
    <section>
      <div className="section-heading">
        <h2>Add Product</h2>
        <p>Create a new inventory item</p>
      </div>

      <form className="card form-grid" onSubmit={onSubmit}>
        <label>
          Product Name
          <input name="name" value={form.name} onChange={onChange} required />
        </label>

        <label>
          Category
          <input name="category" value={form.category} onChange={onChange} required />
        </label>

        <label>
          Price
          <input
            type="number"
            step="0.01"
            min="0"
            name="price"
            value={form.price}
            onChange={onChange}
            required
          />
        </label>

        <label>
          Stock Quantity
          <input
            type="number"
            min="0"
            name="stockQuantity"
            value={form.stockQuantity}
            onChange={onChange}
            required
          />
        </label>

        <label>
          SKU
          <input name="sku" value={form.sku} onChange={onChange} required />
        </label>

        <label className="full">
          Description
          <textarea
            rows="4"
            name="description"
            value={form.description}
            onChange={onChange}
            required
          />
        </label>

        <div className="full actions-row">
          <button className="btn btn-primary" disabled={saving} type="submit">
            {saving ? "Saving..." : "Save Product"}
          </button>
        </div>

        {message && <p className="status-text ok full">{message}</p>}
        {error && <p className="status-text error full">{error}</p>}
      </form>
    </section>
  );
}