import { StyleSheet, View, Text, Button } from 'react-native';

export default function ColorBox({colorName, moreColor, lessColor}) {
    return (
<View style={styles.rowStyle}>
            <Text style={styles.textStyle}>{ colorName }</Text>
            <Button title='+' onPress={moreColor}></Button>
            <Button title='-' onPress={lessColor}></Button>
            </View>
);
}

const styles = StyleSheet.create({
    rowStyle: {
        borderWidth: 2,
        marginTop: 20,
        width: 200,
        flexDirection: 'row',
        justifyContent: 'space-around'
        
    }
    , textStyle: {
        fontSize: 20
    }
});
