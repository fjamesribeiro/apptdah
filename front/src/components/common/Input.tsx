import React from 'react';
import { Text, TextInput, TextInputProps, View } from 'react-native';

interface InputProps extends TextInputProps {
  label?: string;
  error?: string;
}

export function Input({ label, error, ...props }: InputProps) {
  return (
    <View className="mb-4">
      {label && (
        <Text className="text-neutral-700 mb-2 font-medium">
          {label}
        </Text>
      )}
      <TextInput
        className={`bg-neutral-50 border rounded-lg p-4 text-neutral-700
          ${error ? 'border-error' : 'border-neutral-200'}
          ${props.editable === false ? 'bg-neutral-100' : ''}
        `}
        placeholderTextColor="#94A3B8"
        {...props}
      />
      {error && (
        <Text className="text-error text-sm mt-1">
          {error}
        </Text>
      )}
    </View>
  );
} 