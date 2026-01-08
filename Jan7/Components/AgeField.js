import React from "react";
import InputField from "./InputField";

export default function AgeField({ age, onChangeAge }) {
  return (
    <InputField
    label="Age"
    placeholder="Enter your age"
    value={age}
    onChangeText={onChangeAge}
    />
  );
}