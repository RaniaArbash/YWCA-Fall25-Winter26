import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from '@react-navigation/stack';

import BoxObjectModelScreen from "./Screens/BoxObjectModelScreen";
import FlexObjectModelScreen from "./Screens/FlexObjectModelScreen"
import ColorGeneratorScreen from "./Screens/ColorGeneratorScreen";
import BMICalculatorScreen from './Screens/BMICalculatorScreen';

const tab = createBottomTabNavigator();
  const Stack = createStackNavigator();

export default function App() {

  const myFirstStack = () => {
    return (
      <Stack.Navigator>
        <Stack.Screen name="BoxModel" component={BoxObjectModelScreen}></Stack.Screen>
        <Stack.Screen name="FlexBox" component={FlexObjectModelScreen}></Stack.Screen>
      </Stack.Navigator>
    );
  }
  return (
    <NavigationContainer>
      <tab.Navigator>
        <tab.Screen name='BMICalculator' component={BMICalculatorScreen} ></tab.Screen>
        <tab.Screen name="LayoutModels" component={myFirstStack}></tab.Screen>
      </tab.Navigator>
    </NavigationContainer>
  );
}
