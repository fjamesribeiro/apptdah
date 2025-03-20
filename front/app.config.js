module.exports = {
  name: "Appdah",
  slug: "appdah",
  version: "1.0.0",
  orientation: "portrait",
  icon: "./src/assets/icon.png",
  userInterfaceStyle: "light",
  splash: {
    image: "./src/assets/splash.png",
    resizeMode: "contain",
    backgroundColor: "#2563EB"
  },
  assetBundlePatterns: [
    "**/*"
  ],
  ios: {
    supportsTablet: true
  },
  android: {
    adaptiveIcon: {
      foregroundImage: "./src/assets/adaptive-icon.png",
      backgroundColor: "#2563EB"
    }
  },
  web: {
    favicon: "./src/assets/favicon.png"
  },
  plugins: [],
  // Desabilitar o Expo Router
  experiments: {
    router: {
      enabled: false
    }
  }
}; 