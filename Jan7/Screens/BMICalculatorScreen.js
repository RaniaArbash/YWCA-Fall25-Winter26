import React, { useState } from "react";
import { View, Text, TouchableOpacity, StyleSheet } from "react-native";
import InputField from "../Components/InputField";
import ResultCard from "../Components/ResultCard";
import AgeField from "../Components/AgeField";
import GenderSelector from "../Components/GenderSelector";

export default function BMICalculatorScreen() {
  const [weight, setWeight] = useState("");
  const [height, setHeight] = useState("");
  const [age, setAge] = useState("");
  const [gender, setGender] = useState("");
  const [bmi, setBmi] = useState(null);
  const [category, setCategory] = useState("");
  const [error, setError] = useState("");

  const calculateBMI = () => {
    setError("");
    setBmi(null);
    setCategory("");

    const weightNum = parseFloat(weight);
    const heightNum = parseFloat(height);
    const ageNum = parseInt(age);

    if (!weight || !height || !age || !gender) {
      setError("Please fill in all fields and select gender.");
      return;
    }
    if (isNaN(weightNum) || isNaN(heightNum) || isNaN(ageNum)) {
      setError("Please enter valid numeric values.");
      return;
    }
    if (weightNum <= 0 || heightNum <= 0 || ageNum <= 0) {
      setError("Values must be greater than zero.");
      return;
    }

    const heightInMeters = heightNum / 100;
    const bmiValue = (weightNum / (heightInMeters * heightInMeters)).toFixed(1);

    setBmi(bmiValue);
    setCategory(getBMICategory(bmiValue));
  };

  const getBMICategory = (bmi) => {
    const bmiNum = parseFloat(bmi);
    if (bmiNum < 18.5) return "Underweight";
    if (bmiNum < 25) return "Normal weight";
    if (bmiNum < 30) return "Overweight";
    return "Obese";
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>BMI Calculator</Text>

      <AgeField age={age} onChangeAge={setAge} />

      <GenderSelector gender={gender} onSelectGender={setGender} />

      <InputField
        label="Weight (kg)"
        placeholder="Enter your weight"
        value={weight}
        onChangeText={setWeight}
      />

      <InputField
        label="Height (cm)"
        placeholder="Enter your height"
        value={height}
        onChangeText={setHeight}
      />

      <TouchableOpacity style={styles.button} onPress={calculateBMI}>
        <Text style={styles.buttonText}>Calculate BMI</Text>
      </TouchableOpacity>

      {error !== "" && <Text style={styles.error}>{error}</Text>}

      <ResultCard bmi={bmi} category={category} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 24,
    justifyContent: "center",
    backgroundColor: "#f4f6f8",
  },
  title: {
    fontSize: 26,
    fontWeight: "bold",
    textAlign: "center",
    marginBottom: 30,
  },
  button: {
    width: 200,
    height: 50,
    backgroundColor: "#255",
    borderRadius: 8,
    justifyContent: "center",
    alignItems: "center",
    marginTop: 20,
    alignSelf: "center",
  },
  buttonText: {
    color: "#fff",
    fontSize: 18,
    fontWeight: "bold",
  },
  error: {
    color: "red",
    fontSize: 16,
    marginTop: 15,
    textAlign: "center",
  },
});