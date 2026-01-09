import { StyleSheet, View, TextInput } from 'react-native';
import { Feather } from '@expo/vector-icons';

const SearchBar = ({term, onTermChanged}) => {

    return (
        <View style={styles.containerStyle}>
            <Feather style={styles.iconStyle}
                name='search' size={24} color="black" ></Feather>
            <TextInput
                style={styles.textStyle}
                placeholder='Enter City Name'
                value={ term}
                onChangeText={onTermChanged}
            ></TextInput>
    </View>
);
}

const styles = StyleSheet.create({

    containerStyle: {
        flexDirection: 'row',
        height: 45,
        alignItems: 'stretch',
         borderWidth: 2,
        borderBlockColor: 'black'
    },
    textStyle: {
      fontSize: 18
  },
    iconStyle: {
        color: 'black',
        alignSelf:'center',
        marginHorizontal: 15
    }
});


export default SearchBar;