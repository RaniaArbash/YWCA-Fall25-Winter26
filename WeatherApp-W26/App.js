import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import CitySearch from "./Screens/CitySearch";
import WeatherInCity from "./Screens/WeatherInCity"
import WeatherInLocationScreen from "./Screens/WeatherInLocationScreen";
import MapScreen from "./Screens/MapScreen";
import { useEffect } from "react";
import { initTable } from "./Model/Database";
import FavCitiesScreen from "./Screens/FavCitiesScreen";
import { Provider } from "react-redux";
import store from "./ReduxToolkit/ReduxStore";
export default function App() {
    const Stack = createStackNavigator();
    const Tab = createBottomTabNavigator();

  useEffect(() => {
        initTable();
    }, []);
  
  const SearchStack = () => {
    return (
      <Stack.Navigator>
      <Stack.Screen name="CitySearch" component={CitySearch}></Stack.Screen>
      <Stack.Screen name="WeatherInCity" component={WeatherInCity}></Stack.Screen>
</Stack.Navigator>
    );
  }

  return (
    <Provider store={store}>
    <NavigationContainer>
    <Tab.Navigator>
        <Tab.Screen name="Current Location" component={WeatherInLocationScreen} ></Tab.Screen>
        <Tab.Screen name="City Search" component={SearchStack}></Tab.Screen>
        <Tab.Screen name="Map" component={MapScreen}></Tab.Screen>
        <Tab.Screen name="FavCities" component={FavCitiesScreen}></Tab.Screen>
      </Tab.Navigator>
      </NavigationContainer>
      </Provider>
  );
}
