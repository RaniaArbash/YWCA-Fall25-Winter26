import React, { useState } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import CalcButton from '../Components/CalcButton'; // Import the reusable component

const SimpleCalculatorScreen = () => {
const [display, setDisplay] = useState('');
const [result, setResult] = useState('');

  const handlePress = (value) => {
    if (value === '=') {
    try {
        setResult(eval(display).toString());
    } catch {
        setResult('Error');
    }
    } else if (value === 'C') {
    setDisplay('');
    setResult('');
    } else {
    setDisplay(display + value);
    }
  };

  const buttons = [
    ['7', '8', '9', '/'],
    ['4', '5', '6', '*'],
    ['1', '2', '3', '-'],
    ['C', '0', '=', '+'],
  ];

  return (
    <View style={styles.container}>
      <View style={styles.displayContainer}>
        <Text style={styles.displayText}>{display || '0'}</Text>
        <Text style={styles.resultText}>{result}</Text>
    </View>
    <View style={styles.buttonContainer}>
        {buttons.map((row, i) => (
        <View key={i} style={styles.row}>
            {row.map((char) => (
            <CalcButton 
                key={char} 
                label={char} 
                onPress={handlePress} 
                // Custom style for the Clear button
                style={char === 'C' ? { backgroundColor: '#FF5722' } : null}
            />
            ))}
          </View>
        ))}
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#f5f5f5', padding: 20 },
  displayContainer: { flex: 1, justifyContent: 'flex-end', alignItems: 'flex-end', paddingBottom: 20 },
  displayText: { fontSize: 48, color: '#333' },
  resultText: { fontSize: 32, color: '#888', marginTop: 10 },
  buttonContainer: { flex: 2 },
  row: { flexDirection: 'row', justifyContent: 'space-between' },
});

// THIS LINE FIXES YOUR ERROR:
export default SimpleCalculatorScreen;