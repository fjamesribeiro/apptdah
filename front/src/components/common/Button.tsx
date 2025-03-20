import React from 'react';
import { ActivityIndicator, Text, TouchableOpacity } from 'react-native';

interface ButtonProps {
  title: string;
  onPress: () => void;
  variant?: 'primary' | 'secondary' | 'outline';
  loading?: boolean;
  disabled?: boolean;
}

export function Button({ 
  title, 
  onPress, 
  variant = 'primary', 
  loading = false,
  disabled = false 
}: ButtonProps) {
  const baseStyles = 'flex-row justify-center items-center rounded-lg p-4';
  
  const buttonStyles = {
    primary: `${baseStyles} ${disabled ? 'bg-primary-light' : 'bg-primary'}`,
    secondary: `${baseStyles} bg-neutral-100`,
    outline: `${baseStyles} border-2 ${disabled ? 'border-neutral-200' : 'border-primary'}`
  }[variant];

  const textStyles = {
    primary: 'font-semibold text-white',
    secondary: 'font-semibold text-neutral-700',
    outline: `font-semibold ${disabled ? 'text-neutral-400' : 'text-primary'}`
  }[variant];

  return (
    <TouchableOpacity 
      className={buttonStyles}
      onPress={onPress}
      disabled={disabled || loading}
    >
      {loading ? (
        <ActivityIndicator color={variant === 'primary' ? 'white' : '#2563EB'} />
      ) : (
        <Text className={textStyles}>{title}</Text>
      )}
    </TouchableOpacity>
  );
} 