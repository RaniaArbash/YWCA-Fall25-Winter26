import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from '@react-navigation/stack';
import CitySearch from "./Screens/CitySearch";
import WeatherInCity from "./Screens/WeatherInCity"
export default function App() {
    const Stack = createStackNavigator();

  return (
    <NavigationContainer>
      <Stack.Navigator>
      <Stack.Screen name="CitySearch" component={CitySearch}></Stack.Screen>
      <Stack.Screen name="WeatherInCity" component={WeatherInCity}></Stack.Screen>
</Stack.Navigator>
    </NavigationContainer>
  );
}
