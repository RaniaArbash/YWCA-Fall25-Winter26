import { View, Text, Image, StyleSheet } from "react-native";

export default function WeatherCard({ weather }) {
  if (!weather?.main) return null;

  const icon = weather.weather[0]?.icon; // e.g., "10d"
  const iconUrl = `https://openweathermap.org/img/wn/${icon}@4x.png`;

  return (
    <View style={[
      styles.card
    ]}>
      
      <Text>
        {weather.name}
      </Text>

      <Image
        source={{ uri: iconUrl }}
        style={styles.icon}
      />

      <Text style={[styles.temp]}>
        {weather.main.temp}°C
      </Text>

      <Text style={[styles.desc]}>
        {weather.weather[0]?.description}
      </Text>

    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    marginTop: 20,
    padding: 20,
    width: 250,
    borderRadius: 16,
    alignItems: "center",
    elevation: 4,
    shadowColor: "#000",
    shadowOpacity: 0.2,
    shadowOffset: { width: 0, height: 3 },
  },
  city: {
    fontSize: 20,
    marginBottom: 10,
    fontWeight: "600",
  },
  icon: {
    width: 120,
    height: 120,
  },
  temp: {
    fontSize: 32,
    fontWeight: "bold",
    marginTop: 10,
  },
  desc: {
    fontSize: 16,
    fontStyle: "italic",
    marginTop: 6,
    textTransform: "capitalize",
  }
});
