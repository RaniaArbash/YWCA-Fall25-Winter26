import * as SQLite from 'expo-sqlite';

export let db = null;

export const getDB = async () => {
    if (!db) {
    db = await SQLite.openDatabaseAsync('citiesDB.db');
}
return db;
};

export const initTable = async () => {
const database = await getDB();
await database.execAsync(`
    CREATE TABLE IF NOT EXISTS cities (
      id INTEGER PRIMARY KEY NOT NULL,
      city TEXT NOT NULL
    );
  `);
};
