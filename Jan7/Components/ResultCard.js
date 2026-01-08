import React from "react";
import { View, Text, StyleSheet } from "react-native";

export default function ResultCard({ bmi, category }) {
  if (!bmi) return null;

  return (
    <View style={styles.card}>
      <Text style={styles.text}>BMI: {bmi}</Text>
      <Text style={styles.text}>Category: {category}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    width: 200,          // same as inputs
    marginTop: 30,
    padding: 15,
    borderRadius: 10,
    backgroundColor: "#e0f2f1",
    alignItems: "center",
    alignSelf: "center",
  },
  text: {
    fontSize: 18,
    marginVertical: 3,
    fontWeight: "600",
  },
});
