import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";
const USER_API = `${API_BASE_URL}/api/users`;
const LOGIN_API = `${USER_API}/login`;
const USER_KEY = "sales.currentUser";

function formatApiError(error, fallbackMessage) {
  if (error?.response?.data?.message) {
    return error.response.data.message;
  }
  if (error?.response?.status) {
    return `${fallbackMessage} (HTTP ${error.response.status})`;
  }
  if (error?.message) {
    return error.message;
  }
  return fallbackMessage;
}

const AuthService = {
  async getAllUsers() {
    try {
      const { data } = await axios.get(USER_API);
      return data;
    } catch (error) {
      throw new Error(formatApiError(error, "Unable to load users"));
    }
  },

  async login(username, password) {
    try {
      const { data } = await axios.post(LOGIN_API, {
        username,
        password
      });
      localStorage.setItem(USER_KEY, JSON.stringify(data));
      return data;
    } catch (error) {
      throw new Error(formatApiError(error, "Invalid credentials"));
    }
  },

  async register(payload) {
    try {
      const { data } = await axios.post(USER_API, payload);
      localStorage.setItem(USER_KEY, JSON.stringify(data));
      return data;
    } catch (error) {
      throw new Error(formatApiError(error, "Failed to register"));
    }
  },

  logout() {
    localStorage.removeItem(USER_KEY);
  },

  getCurrentUser() {
    const raw = localStorage.getItem(USER_KEY);
    if (!raw) {
      return null;
    }

    try {
      return JSON.parse(raw);
    } catch (_err) {
      return null;
    }
  }
};

export default AuthService;