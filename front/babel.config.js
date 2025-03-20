/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./App.{js,jsx,ts,tsx}",
    "./src/**/*.{js,jsx,ts,tsx}"
  ],
  theme: {
    extend: {
      colors: {
        // Cores principais
        primary: {
          light: '#60A5FA', // Azul claro - elementos secundários
          DEFAULT: '#2563EB', // Azul principal - botões principais, links
          dark: '#1D4ED8', // Azul escuro - hover, estados ativos
        },
        // Cores neutras
        neutral: {
          50: '#F8FAFC',  // Fundo claro, cards
          100: '#F1F5F9', // Fundo alternativo
          200: '#E2E8F0', // Bordas, divisores
          300: '#CBD5E1', // Bordas mais escuras
          400: '#94A3B8', // Texto desabilitado
          500: '#64748B', // Texto secundário
          600: '#475569', // Texto principal
          700: '#334155', // Títulos
          800: '#1E293B', // Texto em destaque
          900: '#0F172A', // Texto muito escuro
        },
        // Cores de feedback
        success: {
          light: '#86EFAC', // Sucesso suave
          DEFAULT: '#22C55E', // Sucesso principal
          dark: '#16A34A',   // Sucesso hover
        },
        warning: {
          light: '#FDE68A',  // Alerta suave
          DEFAULT: '#F59E0B', // Alerta principal
          dark: '#D97706',   // Alerta hover
        },
        error: {
          light: '#FCA5A5',  // Erro suave
          DEFAULT: '#EF4444', // Erro principal
          dark: '#DC2626',   // Erro hover
        }
      }
    },
  },
  plugins: [],
}

module.exports = function (api) {
  api.cache(true);
  
  return {
    presets: ['babel-preset-expo'],
    plugins: [
      'nativewind/babel',
      'react-native-reanimated/plugin',
      [
        'module-resolver',
        {
          root: ['.'],
          extensions: ['.ios.js', '.android.js', '.js', '.ts', '.tsx', '.json'],
          alias: {
            '@components': './src/components',
            '@screens': './src/screens',
            '@navigation': './src/navigation',
            '@assets': './src/assets',
            '@hooks': './src/hooks',
            '@utils': './src/utils',
            '@services': './src/services',
            '@stores': './src/stores',
          },
        },
      ],
    ],
  };
};