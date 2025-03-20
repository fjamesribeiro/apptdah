import AsyncStorage from '@react-native-async-storage/async-storage';
import { createClient } from '@supabase/supabase-js';
import 'react-native-url-polyfill/auto';

// TODO: Substitua essas variáveis pelos valores reais do seu projeto Supabase
// Acesse essas informações no painel de controle do Supabase: Settings > API
const supabaseUrl = 'https://hnmyxslmrrsbgljlvxmr.supabase.co'; // exemplo: 'https://abcdefghijklmn.supabase.co'
const supabaseAnonKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhubXl4c2xtcnJzYmdsamx2eG1yIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDIzMDMwNDIsImV4cCI6MjA1Nzg3OTA0Mn0.B_PcWxe5SWKSWK4C3tCMFxMiEBJDPf-jXtLdVrc7QDg'; // A chave anônima (não a de serviço)

export const supabase = createClient(supabaseUrl, supabaseAnonKey, {
  auth: {
    storage: AsyncStorage,
    autoRefreshToken: true,
    persistSession: true,
    detectSessionInUrl: false,
  },
}); 