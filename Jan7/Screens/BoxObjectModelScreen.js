import { StyleSheet, View ,Button} from 'react-native';

export default function BoxObjectModelScreen({navigation}) {
    return (
    <View style={styles.backgroud}>
            <View style={styles.container}>
                <View style={styles.innerViewStyle}></View>
            </View>
            <Button title='To FlexObject Model' onPress={() => {
                navigation.navigate('FlexBox');
            }}></Button>
    </View>
);
}

const styles = StyleSheet.create({
    backgroud: {
        flex: 1, 
        backgroundColor: '#0000',
    },
container: {
        height: 200,
        width: 200,
        backgroundColor: '#c00f0fff',
    borderWidth: 6,
    marginLeft: 50,
    marginTop: 60,
    paddingLeft: 10
    },
    innerViewStyle: {
        width: 100,
        height: 100,
        backgroundColor: '#0fc0a3ff',
    }
});
