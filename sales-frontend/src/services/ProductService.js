import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";
const PRODUCT_API = `${API_BASE_URL}/api/products`;

const ProductService = {
  async getAll() {
    const { data } = await axios.get(PRODUCT_API);
    return data;
  },

  async getActive() {
    const { data } = await axios.get(`${PRODUCT_API}/active`);
    return data;
  },

  async create(payload) {
    const { data } = await axios.post(PRODUCT_API, payload);
    return data;
  },

  async remove(id) {
    const { data } = await axios.delete(`${PRODUCT_API}/${id}`);
    return data;
  }
};

export default ProductService;