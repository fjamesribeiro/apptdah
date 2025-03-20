import AsyncStorage from '@react-native-async-storage/async-storage';
import { create } from 'zustand';
import { supabase } from '../lib/supabase';

interface AuthState {
  accessToken: string | null;
  refreshToken: string | null;
  isLoading: boolean;
  isAuthenticated: boolean;
  setTokens: (access: string, refresh: string) => Promise<void>;
  logout: () => Promise<void>;
  setLoading: (loading: boolean) => void;
}

export const useAuthStore = create<AuthState>((set) => ({
  accessToken: null,
  refreshToken: null,
  isLoading: false,
  isAuthenticated: false,

  setTokens: async (access: string, refresh: string) => {
    try {
      // Salva tokens no AsyncStorage
      await AsyncStorage.setItem('supabase.auth.token.access_token', access);
      await AsyncStorage.setItem('supabase.auth.token.refresh_token', refresh);
      
      set({
        accessToken: access,
        refreshToken: refresh,
        isAuthenticated: true,
      });
    } catch (error) {
      console.error('Erro ao salvar tokens:', error);
    }
  },

  logout: async () => {
    try {
      // Deslogar do Supabase
      await supabase.auth.signOut();
      
      // Limpar o storage
      await AsyncStorage.removeItem('supabase.auth.token.access_token');
      await AsyncStorage.removeItem('supabase.auth.token.refresh_token');
      
      set({
        accessToken: null,
        refreshToken: null,
        isAuthenticated: false,
      });
    } catch (error) {
      console.error('Erro ao fazer logout:', error);
    }
  },

  setLoading: (loading: boolean) => set({ isLoading: loading }),
})); 