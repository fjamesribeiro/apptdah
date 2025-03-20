import { GoogleSignin } from '@react-native-google-signin/google-signin';
import React, { useEffect, useState } from 'react';
import { Alert, Pressable, Text, TextInput, View } from 'react-native';
import { authService } from '../services/api';
import { useAuthStore } from '../stores/authStore';

// Definindo interface auxiliar para tratar o resultado do GoogleSignin
interface GoogleSignInResult {
  idToken?: string;
  user?: {
    id: string;
    name?: string | null;
    email?: string | null;
    photo?: string | null;
  };
}

const LoginScreen = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const { setTokens } = useAuthStore();

  useEffect(() => {
    // TODO: Substitua os placeholders pelos seus IDs de cliente reais do Google
    // Para obter esses IDs, acesse https://console.cloud.google.com/
    GoogleSignin.configure({
      // Web Client ID do seu projeto no Google Cloud Console
      webClientId: '1044829945373-rha7s11m95i23saib86fd4on7p7s610u.apps.googleusercontent.com', 
      // iOS Client ID (necessário apenas para iOS)
      iosClientId: 'SEU_IOS_CLIENT_ID_AQUI',
    });
  }, []);

  const handleCredentialsLogin = async () => {
    try {
      setIsLoading(true);
      
      // Usando o serviço de autenticação para login
      const data = await authService.login(email, password);
      
      // Guardando tokens
      await setTokens(
        data.session.access_token,
        data.session.refresh_token
      );
    } catch (error: any) {
      Alert.alert('Erro no login', error.message || 'Credenciais inválidas');
    } finally {
      setIsLoading(false);
    }
  };

  const handleGoogleLogin = async () => {
    try {
      setIsLoading(true);
      await GoogleSignin.hasPlayServices();
      
      // Usando uma asserção de tipo para resolver o problema
      const userInfo = await GoogleSignin.signIn() as unknown as GoogleSignInResult;
      
      // Verificar se temos o token do Google
      if (!userInfo.idToken) {
        throw new Error('Não foi possível obter o token do Google');
      }
      
      // Usando o serviço de autenticação para login com Google
      const data = await authService.loginWithGoogle(userInfo.idToken);
      
      // Guardando tokens
      await setTokens(
        data.session.access_token,
        data.session.refresh_token
      );
    } catch (error: any) {
      Alert.alert('Erro no login com Google', error.message || 'Tente novamente');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <View className="flex-1 bg-neutral-50 px-6 py-12">
      <View className="mb-12">
        <Text className="text-3xl font-bold text-neutral-800 mb-2">Bem-vindo</Text>
        <Text className="text-neutral-500">Faça login para continuar</Text>
      </View>

      <View className="space-y-4">
        <View>
          <TextInput
            className="bg-white px-4 py-3 rounded-lg border border-neutral-200"
            placeholder="Email"
            value={email}
            onChangeText={setEmail}
            keyboardType="email-address"
            editable={!isLoading}
            placeholderTextColor="#94A3B8"
          />
        </View>

        <View>
          <TextInput
            className="bg-white px-4 py-3 rounded-lg border border-neutral-200"
            placeholder="Senha"
            value={password}
            onChangeText={setPassword}
            secureTextEntry
            editable={!isLoading}
            placeholderTextColor="#94A3B8"
          />
        </View>

        <Pressable
          onPress={handleCredentialsLogin}
          disabled={isLoading}
          className={`bg-primary py-3 rounded-lg ${isLoading ? 'opacity-50' : ''}`}
        >
          <Text className="text-white text-center font-semibold">
            {isLoading ? 'Carregando...' : 'Entrar'}
          </Text>
        </Pressable>

        <View className="flex-row items-center my-6">
          <View className="flex-1 h-[1px] bg-neutral-200" />
          <Text className="mx-4 text-neutral-400">ou</Text>
          <View className="flex-1 h-[1px] bg-neutral-200" />
        </View>

        <Pressable
          onPress={handleGoogleLogin}
          disabled={isLoading}
          className={`bg-white py-3 rounded-lg border border-neutral-200 ${isLoading ? 'opacity-50' : ''}`}
        >
          <Text className="text-neutral-700 text-center font-semibold">
            Continuar com Google
          </Text>
        </Pressable>
      </View>
    </View>
  );
};

export default LoginScreen;