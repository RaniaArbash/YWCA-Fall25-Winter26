import React from "react";
import { View, Text, TextInput, StyleSheet } from "react-native";

export default function InputField({ label, value, onChangeText, placeholder }) {
  return (
    <View style={styles.container}>
      <Text style={styles.label}>{label}</Text>
      <TextInput
        style={styles.input}
        value={value}
        placeholder={placeholder}
        keyboardType="numeric"
        onChangeText={onChangeText}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    marginBottom: 15,
    alignItems: "center", // center the input
  },
  label: {
    fontSize: 16,
    marginBottom: 5,
  },
  input: {
    width: 200,           // fixed smaller width
    height: 50,
    backgroundColor: "#fff",
    borderRadius: 8,
    paddingHorizontal: 15,
    borderWidth: 1,
    borderColor: "#ccc",
  },
});
