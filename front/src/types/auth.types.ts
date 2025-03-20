export interface LoginCredentials {
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  user: {
    id: number;
    name: string;
    email: string;
  };
}

export interface LoginProps {
  navigation: {
    navigate: (screen: string) => void;
  };
} 