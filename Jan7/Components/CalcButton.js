import React from 'react';
import { TouchableOpacity, Text, StyleSheet } from 'react-native';

// Reusable Button Component
const CalcButton = ({ label, onPress, style }) => {
return (
    <TouchableOpacity 
        style={[styles.button, style]} 
        onPress={() => onPress(label)}
    >
    <Text style={styles.buttonText}>{label}</Text>
    </TouchableOpacity>
);
};

const styles = StyleSheet.create({
  button: {
    width: 70,
    height: 70,
    backgroundColor: '#2196F3',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 35,
    margin: 5,
  },
  buttonText: {
    color: 'white',
    fontSize: 24,
    fontWeight: 'bold',
  },
});

export default CalcButton; 