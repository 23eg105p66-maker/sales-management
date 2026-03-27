import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";
const SALES_API = `${API_BASE_URL}/api/sales`;

const SalesService = {
  async getAll() {
    const { data } = await axios.get(SALES_API);
    return data;
  },

  async getDashboard() {
    const { data } = await axios.get(`${SALES_API}/dashboard`);
    return data;
  },

  async create(payload) {
    const { data } = await axios.post(SALES_API, payload);
    return data;
  },

  async updateStatus(id, status) {
    const { data } = await axios.patch(`${SALES_API}/${id}/status`, { status });
    return data;
  },

  async remove(id) {
    const { data } = await axios.delete(`${SALES_API}/${id}`);
    return data;
  }
};

export default SalesService;