import axios from 'axios';
import { supabase } from '../lib/supabase';
import { useAuthStore } from '../stores/authStore';

// Crie uma instância do axios com a URL base apropriada
const api = axios.create({
  baseURL: 'SUA_URL_BASE_API' // Substitua com sua URL API
});

// Adicione o token de acesso a todas as requisições
api.interceptors.request.use((config) => {
  const accessToken = useAuthStore.getState().accessToken;
  if (accessToken) {
    // Garante que headers existe antes de adicionar a Authorization
    config.headers = config.headers || {};
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

// Intercepta respostas com erro 401 (não autorizado) para refresh do token
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    
    // Se o erro for 401 (não autorizado) e ainda não tentamos refresh
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      
      try {
        // Com Supabase, a sessão é gerenciada automaticamente, mas podemos forçar um refresh
        const { data, error: refreshError } = await supabase.auth.refreshSession();
        
        if (refreshError) throw refreshError;
        
        if (data.session) {
          // Atualiza os tokens no store
          await useAuthStore.getState().setTokens(
            data.session.access_token,
            data.session.refresh_token
          );
          
          // Atualiza o token na requisição original e tenta novamente
          originalRequest.headers.Authorization = `Bearer ${data.session.access_token}`;
          return api(originalRequest);
        }
      } catch (refreshError) {
        // Se falhar o refresh, faz logout
        await useAuthStore.getState().logout();
        return Promise.reject(refreshError);
      }
    }
    
    return Promise.reject(error);
  }
);

// Serviços de autenticação usando Supabase diretamente
export const authService = {
  login: async (email: string, password: string) => {
    const { data, error } = await supabase.auth.signInWithPassword({
      email,
      password
    });
    
    if (error) throw error;
    return data;
  },
  
  loginWithGoogle: async (token: string) => {
    const { data, error } = await supabase.auth.signInWithIdToken({
      provider: 'google',
      token
    });
    
    if (error) throw error;
    return data;
  },
  
  logout: async () => {
    const { error } = await supabase.auth.signOut();
    if (error) throw error;
  },
  
  register: async (email: string, password: string) => {
    const { data, error } = await supabase.auth.signUp({
      email,
      password
    });
    
    if (error) throw error;
    return data;
  }
};

export default api; 