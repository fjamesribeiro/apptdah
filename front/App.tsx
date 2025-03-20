import AsyncStorage from '@react-native-async-storage/async-storage';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import React, { useEffect } from 'react';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import HomeScreen from './src/screens/Home';
import LoginScreen from './src/screens/index';
import { useAuthStore } from './src/stores/authStore';

const Stack = createNativeStackNavigator();

export default function App() {
  const { setTokens, setLoading, accessToken } = useAuthStore();

  useEffect(() => {
    const loadTokens = async () => {
      try {
        setLoading(true);
        const accessToken = await AsyncStorage.getItem('supabase.auth.token.access_token');
        const refreshToken = await AsyncStorage.getItem('supabase.auth.token.refresh_token');
        
        if (accessToken && refreshToken) {
          await setTokens(accessToken, refreshToken);
        }
      } catch (error) {
        console.error('Erro ao carregar tokens:', error);
      } finally {
        setLoading(false);
      }
    };

    loadTokens();
  }, []);

  return (
    <SafeAreaProvider>
      <NavigationContainer>
        <Stack.Navigator screenOptions={{ headerShown: false }}>
          {!accessToken ? (
            <Stack.Screen name="Login" component={LoginScreen} />
          ) : (
            <>
              <Stack.Screen name="Home" component={HomeScreen} />
              {/* Outras telas protegidas por autenticação */}
            </>
          )}
        </Stack.Navigator>
      </NavigationContainer>
    </SafeAreaProvider>
  );
} 