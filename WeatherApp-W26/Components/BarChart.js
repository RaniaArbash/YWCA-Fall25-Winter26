import { BarChart } from "react-native-chart-kit";
import { ScrollView, View ,Dimensions, Text} from 'react-native';


const screenWidth = Dimensions.get("window").width;

export default function WeatherBarChart({ weatherData }) { 
// weatherdata = [{toronto:-11} , {Rome,3}]
const filteredData = weatherData.filter(item => item.city && item.temp != null && item.temp !== undefined)
const chartData = {
        labels: [filteredData.map(item => item.city)],
        datasets: [
                {
                data: filteredData.map(item => item.temp)
                }]
};
    return (
    <ScrollView horizontal contentContainerStyle={{ paddingHorizontal: 10 }}>
        <BarChart
            data={chartData}
            width={screenWidth}
            height={220}
            yAxisSuffix="°C"
            fromZero
            showValuesOnTopOfBars
            chartConfig={{
            backgroundGradientFrom: "#f0f4f7",
            backgroundGradientTo: "#d0e0f0",
            color: (opacity = 1) => `rgba(0, 123, 255, ${opacity})`,
            labelColor: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
        }}
    ></BarChart>
    </ScrollView>   
    );

}// data = [{city2:temp} , {city3:temp}]