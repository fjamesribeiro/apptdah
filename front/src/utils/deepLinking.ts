import { Linking } from 'react-native';

interface DeepLinkParams {
  url: string;
}

export const handleDeepLink = async ({ url }: DeepLinkParams): Promise<void> => {
  if (url.includes('oauth2/callback')) {
    const token = url.split('token=')[1];
    if (token) {
      try {
        // await AsyncStorage.setItem('token', token);
        // navigation.navigate('Home');
      } catch (error) {
        console.error('Erro ao processar deep link:', error);
      }
    }
  }
};

export const setupDeepLinking = (navigation: any): (() => void) => {
  const listener = Linking.addEventListener('url', handleDeepLink);

  Linking.getInitialURL()
    .then(url => {
      if (url) {
        handleDeepLink({ url });
      }
    })
    .catch(err => console.error('Erro ao obter URL inicial:', err));

  return () => {
    listener.remove();
  };
}; 