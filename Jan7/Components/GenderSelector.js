import React from "react";
import { View, TouchableOpacity, Text, StyleSheet } from "react-native";

export default function GenderSelector({ gender, onSelectGender }) {
  return (
    <View style={styles.genderContainer}>
      <TouchableOpacity
        style={[
          styles.genderButton,
          gender === "Male" && styles.genderSelected,
        ]}
        onPress={() => onSelectGender("Male")}
      >
        <Text style={[styles.genderText, gender === "Male" && styles.genderTextSelected]}>
          Male
        </Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={[
          styles.genderButton,
          gender === "Female" && styles.genderSelected,
        ]}
        onPress={() => onSelectGender("Female")}
      >
        <Text style={[styles.genderText, gender === "Female" && styles.genderTextSelected]}>
          Female
        </Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  genderContainer: {
    flexDirection: "row",
    justifyContent: "center",
    marginTop: 10,
    marginBottom: 20,
  },
  genderButton: {
    width: 90,
    height: 40,
    borderRadius: 8,
    borderWidth: 1,
    borderColor: "#255",
    justifyContent: "center",
    alignItems: "center",
    marginHorizontal: 10,
  },
  genderSelected: {
    backgroundColor: "#255",
  },
  genderText: {
    color: "#000",
    fontWeight: "bold",
  },
  genderTextSelected: {
    color: "#fff",
  },
});
