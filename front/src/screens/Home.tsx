import React from 'react';
import { Pressable, Text, View } from 'react-native';
import { authService } from '../services/api';
import { useAuthStore } from '../stores/authStore';

const HomeScreen = () => {
  const { logout } = useAuthStore();

  const handleLogout = async () => {
    try {
      // Usar o serviço de autenticação para fazer logout
      await authService.logout();
      // Limpar os tokens armazenados
      await logout();
    } catch (error) {
      console.error('Erro ao fazer logout:', error);
    }
  };

  return (
    <View className="flex-1 bg-neutral-50 px-6 py-12">
      <View className="mb-12">
        <Text className="text-3xl font-bold text-neutral-800 mb-2">Página Inicial</Text>
        <Text className="text-neutral-500">Bem-vindo ao aplicativo</Text>
      </View>

      <View className="flex-1 justify-center">
        <Text className="text-neutral-600 text-center mb-8">
          Você está logado com sucesso via Supabase!
        </Text>
      </View>

      <Pressable
        onPress={handleLogout}
        className="bg-error py-3 rounded-lg"
      >
        <Text className="text-white text-center font-semibold">
          Sair
        </Text>
      </Pressable>
    </View>
  );
};

export default HomeScreen; 