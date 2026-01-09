import { StyleSheet, View, FlatList ,Text,Button, TouchableOpacity} from 'react-native';
import SearchBar from '../Components/SearchBar';
import { useState } from 'react';


const CitySearch = ({navigation}) => {
    const [searchTerm, setSearchTerm] = useState('');
    const [citiesList, setCitiesList] = useState([]);

    const fetchCity = async (query) => {
        if (query.length > 2) {
            try {
                const response = await fetch('http://gd.geobytes.com/AutoCompleteCity?&q='+query); 
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
                const result = await response.json();
                console.log(result)
                setCitiesList(result)
            }
            catch(err) {
                setError(err.message);
                console.log('Error in fetching cities')
            }
        } else {
            setCitiesList([])
            }
    }

    return (
    <View style={styles.parent}>
            <SearchBar term={searchTerm}
                onTermChanged={(newTerm) => {
                    setSearchTerm(newTerm)
                    fetchCity(newTerm)
                }}></SearchBar>
            
            <FlatList
                data={citiesList}
                keyExtractor={(item, i) => i}
                renderItem={({ item }) =>
                    <TouchableOpacity  onPress={() => {
                        console.log("press on " + item)
                        navigation.navigate('WeatherInCity',{selectedCity: item})
                    }}>
                        <Text style={styles.title}>{item}</Text>
                    </TouchableOpacity>
                }

    />
    </View>
);
}

const styles = StyleSheet.create({
    parent: {
        flex: 1,
        backgroundColor: 'white',
    },
    title: {
        fontSize: 18,
        borderWidth: 1,
        borderColor: 'gray',
        padding: 2
},
});
export default CitySearch;