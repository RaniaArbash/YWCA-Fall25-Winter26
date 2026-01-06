// part 1 - import statements 
import { StyleSheet, Text, View ,Image, Switch, Button} from 'react-native';
import { useState } from 'react';
// component = function 
// part 2 - function body - JSX Object = HTML Tags in web pages 
// <img> <H6> <p></p>

export default function App() {

  const [userName, setUserName] = useState("Josina");
  const [semester, setSemester] = useState("Fall Semester");
  const year = 2026
  return ( // JSX 
    <View style={styles.container}>
      <Text style={styles.textStyle}>Welcome { userName } to React Native Course - { year}</Text>
      <Text style={styles.textStyle}>YWCA Program</Text>
      <Text style={styles.textStyle}>{semester }</Text>

      <Image style={styles.imageStyle}
        source={{uri: 'https://images.freeimages.com/images/premium/previews/3761/37619410-winter.jpg?fmt=webp&w=350'}}></Image>
      <Switch onValueChange={() => { 
          setUserName("Everyone")// this will cause the component to re-build 
      }}></Switch>
        <Switch onValueChange={() => { 
          setUserName("Rania")// this will cause the component to re-build 
      }}></Switch>
      <Button title='Update Semester' onPress={() => {
        setSemester("Winter Semester")
      }}></Button>
    </View>
  );
}

// part 3 - stylesheet === CSS in Web page 
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1266abff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  textStyle: {
    color: 'white',
    fontSize: 20
  },
  imageStyle: {
    width: 200,
    height: 200 
  }
});
