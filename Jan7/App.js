import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from '@react-navigation/stack';

import BoxObjectModelScreen from "./Screens/BoxObjectModelScreen";
import FlexObjectModelScreen from "./Screens/FlexObjectModelScreen"
import BMICalculatorScreen from './Screens/BMICalculatorScreen';
import SimpleCalculatorScreen from './Screens/SimpleCalculatorScreen';

export default function App() {

const Tab = createBottomTabNavigator();
  const Stack = createStackNavigator();
  const MyFirstStack = () => {
    return (
      <Stack.Navigator>
        <Stack.Screen name="BoxModel" component={BoxObjectModelScreen}></Stack.Screen>
        <Stack.Screen name="FlexBox" component={FlexObjectModelScreen}></Stack.Screen>
      </Stack.Navigator>
    );
  }
  return (
    <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen name="BMICalculator" component={BMICalculatorScreen} ></Tab.Screen>
        <Tab.Screen name="SimpleCalculator" component={SimpleCalculatorScreen} ></Tab.Screen>
        <Tab.Screen name="LayoutModels" component={MyFirstStack}></Tab.Screen>
      </Tab.Navigator>
    </NavigationContainer>
  );
}
